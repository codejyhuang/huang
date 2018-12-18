package com.hrym.rpc.mine.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.base.PageInfo;
import com.hrym.rpc.app.common.constant.MyParam;
import com.hrym.rpc.app.config.ESCommon;
import com.hrym.rpc.app.dao.model.VO.SearchResultVO;
import com.hrym.rpc.mine.ESSuggestService;
import net.sf.json.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaohan on 2017/11/27.
 */
public class ESSuggestServiceImpl implements ESSuggestService{


    @Autowired
    private TransportClient transportClient;

    private static final String IndexName = "hrym-elasticsearch";
    private static ObjectMapper mapper = new ObjectMapper();


    @Override
    public BaseResult searchAll(MyParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<Map<String, Object>> mapList = new ArrayList<>();
        PageInfo pageInfo = null;
        QueryBuilder queryBuilders = null;
        String typeName="resource";



        //如果传入的字段不为空，则根据字段进行查询
        if (!"".equals(param.getFilterStr()) && param.getFilterStr() != null) {

            //查询字段
            queryBuilders = QueryBuilders.matchPhraseQuery("resource_name",param.getFilterStr());
//            queryBuilders = QueryBuilders.queryStringQuery(param.getFilterStr())
//                    .field("text_name",5.0f)
//                    .field("title_name",5.0f)
//                    .field("title_desc",0.5f)
//                    .field("title_intro",0.5f)
//                    .field("text_cont ent",0.05f);

        }
        //如果传入的字段为空，则查询所有
        else {
            queryBuilders = QueryBuilders.matchAllQuery();
        }

        SearchResponse response = transportClient.prepareSearch(IndexName)
                .setTypes(typeName)
                .setQuery(queryBuilders)
                .addSort("resource_id", SortOrder.ASC)
                .setFrom((param.getPageNo() - 1) * BaseConstants.PAGE_SIZE)
                .setSize(BaseConstants.PAGE_SIZE)
                .setExplain(true)// 设置是否按查询匹配度排序
                .execute().actionGet();

        SearchHits hits = response.getHits();
        System.out.println("====="+queryBuilders);
        int total = (int) hits.getTotalHits();

        for (SearchHit searchHit : hits) {
            Map source = searchHit.getSource();
            SearchResultVO searchResultVo = (SearchResultVO) JSONObject
                    .toBean(JSONObject.fromObject(source), SearchResultVO.class);
            if (null != searchResultVo && null != searchResultVo.getType_id() && searchResultVo.getType_id()==10006){
                continue;
            }
            Map<String, Object> map = Maps.newHashMap();
            if (searchResultVo.getType_id() == null || "".equals(searchResultVo.getType_id())) {
                map.put("textType", searchResultVo.getText_type());
                map.put("resourceName", searchResultVo.getText_name());
                map.put("resourceId", searchResultVo.getText_id());
                map.put("resourceIntro", searchResultVo.getText_content());
                map.put("textFrom", "丁福保佛学大辞典");
                map.put("typeId", 10009);
                map.put("typeName", "大词典");
                mapList.add(map);
            } else if(searchResultVo.getType_id()==10008) {
                map.put("author", searchResultVo.getTitle_desc());
                map.put("resourceName", searchResultVo.getMusic_name());
                map.put("resourceUrl", searchResultVo.getMusic_file());
                map.put("resourceId", searchResultVo.getMusic_id());
                map.put("musicSubtitle", searchResultVo.getMusic_subtitle());
                map.put("albumName", searchResultVo.getAlbum_name());
                map.put("resourcePic", searchResultVo.getMusic_pic());
                map.put("typeId", searchResultVo.getType_id());
                map.put("typeName", searchResultVo.getType_name());
                mapList.add(map);
            }else{
                map.put("author",searchResultVo.getTitle_desc());
                map.put("resourceName",searchResultVo.getItem_name());
                map.put("resourceId",searchResultVo.getItem_id());
                map.put("resourceIntro",searchResultVo.getItem_intro());
                map.put("resourcePic",searchResultVo.getItem_pic());
                map.put("typeId",searchResultVo.getType_id());
                map.put("typeName",searchResultVo.getType_name());
                mapList.add(map);
            }

        }

        Map<String, Object> map = Maps.newHashMap();
        if (null != mapList){

            boolean hasNext = false;
            int a = total - (param.getPageNo() * BaseConstants.PAGE_SIZE);
            if (a > 0) {
                hasNext = true;
            }

            map.put("list", mapList);
            map.put("hasNextPage", hasNext);
        }else {
            map.put("list", new ArrayList<>());
            map.put("hasNextPage", false);
        }
        return new BaseResult(code, message, map);
    }


}
