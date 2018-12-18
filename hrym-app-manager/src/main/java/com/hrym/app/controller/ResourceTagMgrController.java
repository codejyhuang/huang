package com.hrym.app.controller;

import com.hrym.app.service.ResourceTagMgrService;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.VO.lessonVO.ResourceTagVO;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceTag;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hrym13 on 2018/7/3.
 */
@Controller
@RequestMapping("/admin")
public class ResourceTagMgrController {

    @Autowired
    private ResourceTagMgrService tagMgrService;

    /**
     * 功课标签列表
     * @param param
     * @return
     */
    @RequestMapping("/findAllTags")
    @ResponseBody
    public Result findAllTags (ManagerParam param) {

        return tagMgrService.findAllTags(param.getPageNumber(),param.getPageSize());
    }


    /**
     * 获取功课标签
     * @return
     */
    @RequestMapping("findTagsList")
    @ResponseBody
    public Result findTagsList(){

        return tagMgrService.findTagsList();
    }

    /**
     * 根据ID查找功课标签
     * @param id
     * @return
     */
    @RequestMapping("/findResourceTagById")
    @ResponseBody
    public Result findResourceTagById(Integer id) {

        return tagMgrService.findResourceTagById(id);
    }

    /**
     * 功课标签录入
     * @param resourceTag
     */
    @RequestMapping("insertResourceTag")
    @ResponseBody
   public Result insertResourceTag(@RequestBody ResourceTag resourceTag) {

       return tagMgrService.insertResourceTag(resourceTag);
   }

    /**
     * 功课标签更新
     * @param resourceTag
     */
    @RequestMapping("/updateResourceTag")
    @ResponseBody
    public Result updateResourceTag(@RequestBody ResourceTag resourceTag) {

        return tagMgrService.updateResourceTag(resourceTag);
    }

    /**
     * 功课标签删除
     * @param tagId
     */
    @RequestMapping("/deleteResourceTagById")
    @ResponseBody
    public Result deleteResourceTagById(Integer tagId) {

        return tagMgrService.deleteResourceTagById(tagId);
    }

}
