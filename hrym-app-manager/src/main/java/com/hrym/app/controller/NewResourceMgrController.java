package com.hrym.app.controller;

import com.google.common.collect.Maps;
import com.hrym.app.service.FdfsService;
import com.hrym.app.service.NewResourceMgrService;
import com.hrym.common.base.BaseController;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.VO.NewResourceContentVO;
import com.hrym.rpc.app.dao.model.VO.TaskMusicVO;
import com.hrym.rpc.app.dao.model.task.ManageLog;
import com.hrym.rpc.app.dao.model.task.TaskContent;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import com.hrym.rpc.app.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by mj on 2018/5/22.
 */
@Controller
@RequestMapping(value = "/admin")
public class NewResourceMgrController extends BaseController {

    @Autowired
    private NewResourceMgrService newResourceMgrService;
    @Autowired
    private FdfsService fdfsService;

    /**
     * 功课查询
     */
    @RequestMapping("/findAllByType")
    @ResponseBody
    public Result findAllByType(ManagerParam param){

        return newResourceMgrService.findAllByType(param.getTypeId(),param.getPageNumber(),param.getPageSize());
    }

    /**
     * 功课检索
     * @return
     */
    @RequestMapping(value = "/searchResource")
    @ResponseBody
    public Result searchResource(TaskItem taskItem, ManagerParam param) throws Exception{

        if (null != taskItem.getItemName()){
            //转码
            String val = URLDecoder.decode(taskItem.getItemName(), "UTF-8");
            taskItem.setItemName(val);
        }
        return newResourceMgrService.searchResource(taskItem,param.getPageNumber(),param.getPageSize());
    }

    /**
     * 添加功课
     */
    @RequestMapping("/saveTaskItem")
    @ResponseBody
    public Result saveTaskItem(TaskItem taskItem, @RequestParam("taskTypeId") String typeId, Integer userId){

        return newResourceMgrService.saveTaskItem(taskItem,typeId,userId);
    }

    /**
     * 获取修改功课视图
     * @param taskItem
     * @return
     */
    @RequestMapping(value = "/getEditItem")
    public ModelAndView getEditItem(TaskItem taskItem, Integer userId) {

        Map<String,Object> tsm = newResourceMgrService.findItemById(taskItem,userId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("resource",tsm);
        mav.setViewName("/newResource/editResource");
        return mav;
    }

    /**
     * 修改功课
     * @param taskItem
     * @return
     */
    @RequestMapping("/editTaskItem")
    @ResponseBody
    public Result editTaskItem(TaskItem taskItem,String typeId,Integer userId) throws Exception{

        return newResourceMgrService.updateTaskItem(taskItem,typeId, userId);
    }

    /**
     * 功课删除
     */
    @RequestMapping("/removeTaskItem")
    @ResponseBody
    public Result removeTaskItem(ManageLog manageLog,Integer typeId ){

        final Result result = newResourceMgrService.deleteTaskItem(manageLog, typeId);
        return result;
    }

    /**
     * 添加功课内容
     */
    @RequestMapping("/saveTaskContent")
    @ResponseBody
    public Result saveTaskContent (NewResourceContentVO taskContent, Integer userId, Integer typeId) throws Exception{

        return newResourceMgrService.insertTaskContent(taskContent,userId,typeId);
    }

    /**
     * 音频添加
     */
    @RequestMapping("/saveTaskMusic")
    @ResponseBody
    public Result saveTaskMusic(TaskMusicVO taskMusicVO, Integer userId,Integer typeId,
                                  @RequestParam("taskPushTime") String pushTime,
                                  @RequestParam("taskMusicFile") CommonsMultipartFile musicFile,
                                  @RequestParam("taskMusicSubtitle") CommonsMultipartFile musicSubtitle) throws Exception{

        Map<String,String> ret = Maps.newHashMap();
        String musicFileUrl = "";
        if (!musicFile.isEmpty()){
            ret = fdfsService.uploadStream(musicFile.getSize(),musicFile.getOriginalFilename(),musicFile.getInputStream());
            if(!"0".equals(ret.get("code"))) {
                return new Result("1","图片上传失败",null);
            }
            //服务器返回的URL地址
            musicFileUrl = ret.get("fileStorePath");
        }

        String musicSubtitleUrl = "";
        if (!musicSubtitle.isEmpty()){
            ret = fdfsService.uploadStream(musicSubtitle.getSize(),musicSubtitle.getOriginalFilename(),musicSubtitle.getInputStream());
            if(!"0".equals(ret.get("code"))) {
                return new Result("1","图片上传失败",null);
            }
            //服务器返回的URL地址
            musicSubtitleUrl = ret.get("fileStorePath");
        }

        if (StringUtils.isNotBlank(pushTime)){
            int newPushTime = DateUtil.getDateToLinuxTime(pushTime,DateUtil.TIME_PATTON_DEFAULT);
            taskMusicVO.setPushTime(newPushTime);
        }
        if (StringUtils.isNotBlank(musicFileUrl)) {
            taskMusicVO.setMusicFile(musicFileUrl);
        }
        if (StringUtils.isNotBlank(musicSubtitleUrl)){
            taskMusicVO.setMusicSubtitle(musicSubtitleUrl);
        }

        return newResourceMgrService.insertTaskMusic(taskMusicVO,userId,typeId);

    }

    /**
     * 编辑功课内容
     * @param taskContent
     * @return
     */
    @RequestMapping(value = "/getEditContent")
    public ModelAndView getEditContent(NewResourceContentVO taskContent) {

        NewResourceContentVO ret = newResourceMgrService.editTaskContent(taskContent);
        ModelAndView mav = new ModelAndView();
        mav.addObject("resource",ret);
        mav.setViewName("/newResource/editContent");
        return mav;
    }

    /**
     * 修改功课内容
     */
    @RequestMapping("/editTaskContent")
    @ResponseBody
    public Result editTaskContent( NewResourceContentVO taskContent,Integer userId) {

        return newResourceMgrService.updateTaskContent(taskContent,userId);
    }

    /**
     * 音频修改
     */
    @RequestMapping("/editTaskMusic")
    @ResponseBody
    public Result editTaskMusic( TaskMusicVO taskMusicVO,Integer userId,Integer typeId,
                                   @RequestParam("taskPushTime") String pushTime) throws Exception{

        if (StringUtils.isNotBlank(pushTime)){
            int newPushTime = DateUtil.getDateToLinuxTime(pushTime,DateUtil.TIME_PATTON_DEFAULT);
            taskMusicVO.setPushTime(newPushTime);
        }

        Result result = newResourceMgrService.updateTaskMusic(taskMusicVO,userId,typeId);

        return result;
    }

    /**
     * 音频删除
     */
    @RequestMapping("/removeTaskMusic")
    @ResponseBody
    public Result removeTaskMusic(TaskMusicVO taskMusicVO,Integer userId,Integer typeId){

        return newResourceMgrService.deleteTaskMusic(taskMusicVO,userId,typeId);

    }

    /**
     * 删除功课内容
     * @param manageLog
     * @return
     */
    @RequestMapping("/removeTaskContent")
    @ResponseBody
    public Result removeTaskContent(ManageLog manageLog,Integer typeId){

        return newResourceMgrService.deleteTaskContent(manageLog,typeId);
    }

    /**
     * 根据功课ID查找功课内容
     * @param taskContent
     * @return
     */
    @RequestMapping("/findContentById")
    @ResponseBody
    public Result findContentById(TaskContent taskContent){

        return newResourceMgrService.selectContentById(taskContent);
    }


    /**
     * 查询所有功课
     * @return
     */
    @RequestMapping("/findAllItemLesson")
    @ResponseBody
    public Result findAllItemLesson() {
        return newResourceMgrService.findAllItemLesson();
    }

    /**
     * 查询所有功课内容（条件功课id）
     * @param itemId
     * @return
     */
    @RequestMapping("/findContentLessonById")
    @ResponseBody
    public Result findContentLessonById(Integer itemId) {
        return newResourceMgrService.findContentLessonById(itemId);
    }
}
