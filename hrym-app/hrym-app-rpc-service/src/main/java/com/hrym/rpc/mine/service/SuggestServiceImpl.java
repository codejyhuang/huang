package com.hrym.rpc.mine.service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.base.PageInfo;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.rpc.app.common.constant.MyParam;
import com.hrym.rpc.app.dao.model.task.Duden;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import com.hrym.rpc.app.dao.model.task.TaskMusic;
import com.hrym.rpc.app.dao.model.view.TaskItemView;
import com.hrym.rpc.auth.dao.mapper.TaskItemMapper;
import com.hrym.rpc.auth.dao.mapper.TaskMusicMapper;
import com.hrym.rpc.mine.SuggestService;
import com.hrym.rpc.mine.dao.mapper.SuggestMapper;
import org.elasticsearch.search.suggest.Suggest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 推荐业务实现类
 * Created by mj on 2017/9/4.
 */
public class SuggestServiceImpl implements SuggestService {

    @Autowired
    private TaskMusicMapper taskMusicMapper;
    @Autowired
    private TaskItemMapper taskItemMapper;
    @Autowired
    private SuggestMapper suggestMapper;

    /**
     * 推荐首页
     * @return
     */
    @Override
    public BaseResult suggestHomePage(MyParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<Map<String,Object>> list = new ArrayList<>();
        //分页核心代码
        PageHelper.startPage(param.getPageNo(), 3);
        List<TaskMusic> taskMusicList = taskMusicMapper.findAllMusicOrderByTime(param.getFilterStr());
        for (TaskMusic tm : taskMusicList){
            Map<String,Object> map = Maps.newHashMap();
            map.put("resourceName",tm.getMusicName());
            map.put("resourceUrl",tm.getMusicFile());
            map.put("singer",tm.getSinger());
            map.put("musicId",tm.getMusicId());
            map.put("musicSubtitle",tm.getMusicSubtitle());
            map.put("albumName",tm.getAlbumName());
            map.put("musicPic",tm.getMusicPic());
            if (null != tm.getTaskType()){
                map.put("typeId",tm.getTaskType().getTypeId());
            }
            list.add(map);
        }
        List<Map<String,Object>> mapList = new ArrayList<>();
        //分页核心代码
        PageHelper.startPage(param.getPageNo(), 3);
        List<TaskItem> taskItemList = taskItemMapper.findItemOrderByTime(param.getFilterStr());
        for (TaskItem ti : taskItemList){
            Map<String,Object> map = Maps.newHashMap();
            map.put("author",ti.getTitleDesc());
            map.put("resourceName",ti.getItemName());
            map.put("resourceId",ti.getItemId());
            map.put("resourceIntro",ti.getItemIntro());
            map.put("resourcePic",ti.getItemPic());
            if (null != ti.getType()){
                map.put("typeId",ti.getType().getTypeId());
                map.put("typeName",ti.getType().getTypeName());
            }else {
                map.put("typeId",null);
                map.put("typeName",null);
            }
            mapList.add(map);
        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("bookList",mapList);
        map.put("musicList",list);

        return new BaseResult(code,message,map);
    }

    /**
     * 基于类型查看全部
     * @param param
     * @return
     */
    @Override
    public BaseResult findAllByType(MyParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<Map<String,Object>> mapList = new ArrayList<>();
        PageInfo pageInfo = null;
        //查询经文列表
        if (param.getType() == 0){
            //分页核心代码
            PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
            List<TaskItem> taskItemList = taskItemMapper.findItemOrderByTime(param.getFilterStr());
            pageInfo = new PageInfo(taskItemList);
            for (TaskItem ti : taskItemList){
                Map<String,Object> map = Maps.newHashMap();
                map.put("author",ti.getTitleDesc());
                map.put("resourceName",ti.getItemName());
                map.put("resourceId",ti.getItemId());
                map.put("resourceIntro",ti.getItemIntro());
                map.put("resourcePic",ti.getItemPic());
                if (null != ti.getType()){
                    map.put("typeId",ti.getType().getTypeId());
                    map.put("typeName",ti.getType().getTypeName());
                }else {
                    map.put("typeId",null);
                    map.put("typeName",null);
                }
                mapList.add(map);
            }
        }
        //查询音乐列表
        if (param.getType() == 1){
            //分页核心代码
            PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
            List<TaskMusic> taskMusicList = taskMusicMapper.findAllMusicOrderByTime(param.getFilterStr());
            pageInfo = new PageInfo(taskMusicList);
            for (TaskMusic tm : taskMusicList){
                Map<String,Object> map = Maps.newHashMap();
                map.put("resourceName",tm.getMusicName());
                map.put("resourceUrl",tm.getMusicFile());
                map.put("author",tm.getSinger());
                map.put("resourceId",tm.getMusicId());
                map.put("musicSubtitle",tm.getMusicSubtitle());
                map.put("albumName",tm.getAlbumName());
                map.put("resourcePic",tm.getMusicPic());
                if (null != tm.getTaskType()){
                    map.put("typeId",tm.getTaskType().getTypeId());
                }
                mapList.add(map);
            }
        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("list",mapList);
        map.put("hasNextPage",pageInfo.isHasNextPage());

        return new BaseResult(code,message,map);
    }

    /**
     * 搜索全部
     * @param param
     * @return
     */
    @Override
    public BaseResult searchAllByType(MyParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<Map<String,Object>> mapList = new ArrayList<>();
        //分页核心代码
        PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
        List<TaskItemView> taskItemViewList = taskItemMapper.findAllItemOrderByTime(param.getFilterStr());
        for (TaskItemView t : taskItemViewList){
            Map<String,Object> map = Maps.newHashMap();
            if (t.getTypeId() == 10008){
                map.put("author",t.getTitleDesc());
                map.put("resourceName",t.getMusicName());
                map.put("resourceUrl",t.getMusicFile());
                map.put("resourceId",t.getMusicId());
                map.put("musicSubtitle",t.getMusicSubtitle());
                map.put("albumName",t.getAlbumName());
                map.put("resourcePic",t.getMusicPic());
                map.put("typeId",t.getTypeId());
                mapList.add(map);
            }else {
                map.put("author",t.getTitleDesc());
                map.put("resourceName",t.getItemName());
                map.put("resourceId",t.getItemId());
                map.put("resourceIntro",t.getItemIntro());
                map.put("resourcePic",t.getItemPic());
                map.put("typeId",t.getTypeId());
                map.put("typeName",t.getTypeName());
                mapList.add(map);
            }
        }
        PageInfo pageInfo = new PageInfo(taskItemViewList);
        Map<String,Object> map = Maps.newHashMap();
        map.put("list",mapList);
        map.put("hasNextPage",pageInfo.isHasNextPage());

        return new BaseResult(code,message,map);
    }

    /**
     * 根据id查询大词典详情
     * @param param
     * @return
     */
    @Override
    public BaseResult dudenIntro(MyParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;


        Duden duden = suggestMapper.findDudenById(param.getTextId());

        Map<String,Object> map = Maps.newHashMap();
        map.put("textName",duden.getTextName());
        map.put("textType",duden.getTextType());
        map.put("textContent",duden.getTextContent());
        map.put("textId",duden.getTextId());
        map.put("typeId",duden.getTypeId());
        map.put("from",duden.getTextFrom());

        return new BaseResult(code,message,map);

    }


}
