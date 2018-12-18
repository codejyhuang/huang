package com.hrym.auth.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseRequestParam;
import com.hrym.common.base.BaseResult;
import com.hrym.common.base.PageInfo;
import com.hrym.rpc.app.common.constant.MeditationParam;
import com.hrym.rpc.app.common.constant.ResourceItemLessonParam;
import com.hrym.rpc.app.dao.model.VO.lessonVO.ItemLessonVO;
import com.hrym.rpc.app.dao.model.VO.lessonVO.TaskAreaLessonVO;
import com.hrym.rpc.app.dao.model.VO.lessonVO.TaskPlanLessonVO;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceTag;
import com.hrym.rpc.auth.api.ResourceItemLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/6/21.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class ResourceItemLessonController {

    @Autowired
    private ResourceItemLessonService itemLessonService;

    @RequestMapping(value = "/custom")
    @ResponseBody
    @Allowed
    public BaseResult customList(@RequestBody ResourceItemLessonParam param) {

        String cmd = param.getCmd();

        if ("search".equals(cmd)) {
            return search(param);

        }
        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);

    }

    @RequestMapping(value = "/searchLesson")
    @ResponseBody
    @Allowed
    public BaseResult getHomePage(@RequestBody ResourceItemLessonParam param) {

        String cmd = param.getCmd();

        if ("getTags".equals(cmd)) {
            return getTags(param);
        }
        if ("searchLesson".equals(cmd)) {
            return searchLesson(param);
        }
        if ("pushArea".equals(cmd)) {
            return pushArea(param);
        }
        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);

    }

    @RequestMapping(value = "/lesson")
    @ResponseBody
    public BaseResult addCustom(@RequestBody ResourceItemLessonParam param) {

        String cmd = param.getCmd();

        if ("customAdd".equals(cmd)) {
            return customAdd(param);
        }
        if ("myLesson".equals(cmd)){
            return getMyLesson(param);
        }
        if ("lessonAdd".equals(cmd)) {
            return lessonAdd(param);
        }
        if ("updateTag".equals(cmd)) {
            return updateTag(param);
        }
        if ("updateOrderNum".equals(cmd)) {
            return updateOrderNum(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);

    }

    /**
     * 根据功课名称和别名筛选功课
     * @param param
     * @return
     */
    public BaseResult search(ResourceItemLessonParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<ItemLessonVO> itemList = itemLessonService.search(param);

        Map<String,Object> map = new HashMap<>();
        map.put("list",itemList);

        return new BaseResult(code,message,map);
    }

    /**
     * 自定义功课添加
     * @param param
     * @return
     */
    public BaseResult customAdd (ResourceItemLessonParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        itemLessonService.customAdd(param);

        return new BaseResult(code,message,null);
    }

    /**
     * 正常功课任务添加
     * @param param
     * @return
     */
    public BaseResult lessonAdd(ResourceItemLessonParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        itemLessonService.lessonAdd(param.getItemId(),param.getItemContentId(),param.getToken());

        return new BaseResult(code,message,null);
    }

    /**
     * 更新功课新添加的标签
     * @param param
     * @return
     */
    public BaseResult updateTag (ResourceItemLessonParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        itemLessonService.updateTag(param);

        return new BaseResult(code,message,null);
    }

    /**
     * 获取我的功课
     * @return
     */
    public BaseResult getMyLesson(ResourceItemLessonParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        List<TaskPlanLessonVO> vos = itemLessonService.getMyLesson(param);
        Map<String,Object> map = new HashMap<>();
        map.put("list",vos);
        return new BaseResult(code,message,map);
    }

    /**
     * 获取标签
     * @param param
     * @return
     */
    public BaseResult getTags(ResourceItemLessonParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        List<ResourceTag> tags = itemLessonService.getTags(param);
        Map<String,Object> map = new HashMap<>();
        map.put("list",tags);
        map.put("tagType",param.getType());
        return new BaseResult(code,message,map);
    }

    /**
     * 全部功课搜索
     * @param param
     * @return
     */
    public BaseResult searchLesson(ResourceItemLessonParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        List<ItemLessonVO> items = itemLessonService.searchLesson(param);
        PageInfo pageInfo = new PageInfo(items);
        Map<String,Object> map = new HashMap<>();
        map.put("list",items);
        map.put("hasNextPage",pageInfo.isHasNextPage());
        return new BaseResult(code,message,map);
    }

    /**
     * 推荐功课
     * @param param
     * @return
     */
    public BaseResult pushArea (ResourceItemLessonParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<TaskAreaLessonVO> voList = itemLessonService.pushArea(param);
        Map<String,Object> map = new HashMap<>();
        map.put("list",voList);
        map.put("areaType",param.getType());

        return new BaseResult(code,message,map);
    }

    /**
     * 增加阅读人数
     * @param param
     * @return
     */
    public BaseResult updateOrderNum (ResourceItemLessonParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message =BaseConstants.GWSMSG0000;

        itemLessonService.updateOrderNum(param);
        return new BaseResult(code,message,null);
    }

}
