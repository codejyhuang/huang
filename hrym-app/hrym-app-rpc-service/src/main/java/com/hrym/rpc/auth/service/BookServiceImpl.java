package com.hrym.rpc.auth.service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.TokenUtil;
import com.hrym.rpc.app.common.constant.BookParam;
import com.hrym.rpc.app.dao.model.VO.SearchResultVO;
import com.hrym.rpc.app.dao.model.VO.bookVO.BookcaseVO;
import com.hrym.rpc.app.dao.model.VO.bookVO.TaskItemVO;
import com.hrym.rpc.app.dao.model.task.book.Bookcase;
import com.hrym.rpc.app.dao.model.task.TaskRecord;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.auth.api.BookService;
import com.hrym.rpc.auth.dao.mapper.bookMapper.BookMapper;
import com.hrym.rpc.auth.dao.mapper.TaskRecordMapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mj on 2018/4/16.
 */
public class BookServiceImpl implements BookService {

    private static final String IndexName = "hrym-elasticsearch";

    @Autowired
    private TransportClient transportClient;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private TaskRecordMapper taskRecordMapper;

    /**
     * 书城列表
     * @param param
     * @return
     */
    @Override
    public Map<String,Object> findBookstoreList(BookParam param) {

        List<TaskItemVO> itemVO = null;
        //判断模糊查询匹配字段是否为空
        if (!StringUtils.isNotBlank(param.getFilterStr())){
            PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
            itemVO = bookMapper.findBookListByTypeId(param.getTypeId());
        } else {
            PageHelper.startPage(param.getPageNo(),BaseConstants.PAGE_SIZE);
            itemVO = bookMapper.findBookListByTypeIdAndFilterStr(param.getTypeId(),param.getFilterStr());
        }

        PageInfo pageInfo = new PageInfo(itemVO);
        Map<String,Object> map = new HashMap<>();
        map.put("list",itemVO);
        map.put("hasNextPage",pageInfo.isHasNextPage());

        return map;
    }

    /**
     * 经书详情
     * @param param
     * @return
     */
    @Override
    public TaskItemVO findBookDetails(BookParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());

        TaskItemVO vo = bookMapper.findBookByItemId(param.getItemId(),param.getTypeId());
        //判断该功课是否添加过
        TaskItemVO itemVO = bookMapper.findBookByItemIdAndUserIdAndTypeId(param.getItemId(),param.getTypeId(),user.getUuid());
        //0：未添加过；1：添加过
        if (null == itemVO){
            vo.setIsAdd(0);
            vo.setIndexId(null);
        } else {
            vo.setIsAdd(1);
            vo.setIndexId(itemVO.getIndexId());
        }
        return vo;
    }

    /**
     * 书架列表
     * @param param
     * @return
     */
    @Override
    public Map<String,Object> findBookCaseList(BookParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());
        List<BookcaseVO> voList = bookMapper.findBookByUserId(user.getUuid());

        PageInfo pageInfo = new PageInfo(voList);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("list",voList);

        return map;
    }

    /**
     * 加入书架
     * @param param
     */
    @Override
    public void bookCaseAdd(BookParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());
        //判断该功课是否添加过
        TaskItemVO itemVO = bookMapper.findByItemIdAndUserIdAndTypeId(param.getItemId(),param.getTypeId(),user.getUuid());
        if (null == itemVO){
            Bookcase bookcase = new Bookcase();
            bookcase.setItemId(param.getItemId());
            bookcase.setUserId(user.getUuid());
            bookcase.setTypeId(param.getTypeId());
            bookcase.setCreateTime(DateUtil.currentSecond());
            bookcase.setUpdateTime(DateUtil.currentSecond());
            bookcase.setIsExist(1);
            bookcase.setDoneNum(0);
            bookMapper.saveBookCase(bookcase);
        }else {
            bookMapper.updateIsExistById(itemVO.getIndexId(),1);
        }
    }

    /**
     * 移除书架
     * @param param
     */
    @Override
    public void bookCaseRemove(BookParam param) {

        bookMapper.updateIsExistById(param.getIndexId(),0);
    }

    /**
     * 经书上报
     * @param param
     */
    @Override
    public void bookReport(BookParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());
        int reportNum = 0;
        String percent = "0%";
        if (param.getReportNum() != null){
            reportNum = param.getReportNum();
        }
        if (StringUtils.isNotBlank(param.getPercent())){
            percent = param.getPercent();
        }
        bookMapper.updateReport(reportNum,percent,param.getIndexId(),DateUtil.currentSecond());

        TaskRecord record = new TaskRecord();
        record.setTaskId(param.getIndexId());
        record.setUserId(user.getUuid());
        record.setReportNum(reportNum);
        record.setPercent(percent);
        record.setReportTime(DateUtil.currentSecond());
        record.setItemId(param.getItemId());
        record.setTypeId(param.getTypeId());
        //自动上报
        record.setRecordMethod(1);
        //插入上报数据
        taskRecordMapper.insert(record);
    }

    /**
     * 经书大辞典查询
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> dudenQuery(BookParam param) {

        Map<String, Object> map = Maps.newHashMap();
        String typeName="resource";
        //构建查询条件
        QueryBuilder queryBuilders = QueryBuilders.termQuery("text_name.keyword",param.getFilterStr());
        SearchResponse response = transportClient.prepareSearch(IndexName)
                .setTypes(typeName)
                .setQuery(queryBuilders)
                .setExplain(true)// 设置是否按查询匹配度排序
                .execute().actionGet();

        SearchHits hits = response.getHits();
        for (SearchHit searchHit : hits) {
            Map source = searchHit.getSource();
            SearchResultVO searchResultVo = (SearchResultVO) JSONObject
                    .toBean(JSONObject.fromObject(source), SearchResultVO.class);

            map.put("textType", searchResultVo.getText_type());
            map.put("resourceName", searchResultVo.getText_name());
            map.put("resourceId", searchResultVo.getText_id());
            map.put("resourceIntro", searchResultVo.getText_content());
            map.put("textFrom", "丁福保佛学大辞典");
            map.put("typeId", 10009);
            map.put("typeName", "大词典");
        }
        return map;
    }
}
