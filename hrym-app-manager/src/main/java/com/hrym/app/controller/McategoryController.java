package com.hrym.app.controller;

import com.google.common.collect.Maps;
import com.hrym.app.service.FdfsService;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.VO.ManageLogVO;
import com.hrym.rpc.app.dao.model.VO.TaskMusicVO;
import com.hrym.rpc.app.dao.model.system.BackUser;
import com.hrym.rpc.app.dao.model.system.Menu;
import com.hrym.rpc.app.dao.model.task.*;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import com.hrym.app.service.McategoryMgrService;
import com.hrym.rpc.app.util.Result;
import com.hrym.common.base.BaseController;
import com.hrym.app.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by mj on 2017/7/19.
 */
@Controller
@RequestMapping(value = "/admin")
public class McategoryController extends BaseController {

    private final static Logger _log = LoggerFactory.getLogger(McategoryController.class);
    @Autowired
    private FdfsService fdfsService;
    @Autowired
    private McategoryMgrService mcategoryMgrService;
    @Autowired
    private MenuService menuService;

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {

        return "/login";
    }

    @RequestMapping(value = {"/submit"}, method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("userName") String userName,
                              @RequestParam("passWord") String passWord, ModelAndView model) {
        System.out.println("do submit......");
        BackUser backUser = mcategoryMgrService.login(userName, passWord);
        if (backUser != null) {
            List<Menu> list = menuService.findMenuPageByUserId(backUser.getUserId());
            model.addObject("menuList", list);
            model.addObject("userId", backUser.getUserId());
            model.addObject("userName", userName);
            model.setViewName("/index");
            return model;
        } else {
            model.addObject("message", "用户名/密码不正确,并确保登陆未被限制！");
            model.setViewName("/login");
        }


        return model;

    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public ModelAndView execute(HttpSession session, ModelAndView model) {
        session.invalidate();
        model.setViewName("/login");
        return model;
    }


    /**
     * 查询所有的功课类型
     *
     * @return
     */
    @RequestMapping(value = "/category")
    @ResponseBody
    public Result categoryManage(ManagerParam param) {
        System.out.println(param.getPageNumber() + "---------" + param.getPageSize());
        return mcategoryMgrService.categoryManage(param.getPageNumber(), param.getPageSize());
    }

    /**
     * 目录检索
     *
     * @return
     */
    @RequestMapping(value = "/searchCategory")
    @ResponseBody
    public Result searchCategory(ResourceCatalogue rcl, ManagerParam param) throws Exception {

        if (null != rcl.getCatalogueName()) {
            //转码
            String val = URLDecoder.decode(rcl.getCatalogueName(), "UTF-8");
            rcl.setCatalogueName(val);

        }

        return mcategoryMgrService.searchCatalogueById(rcl, param.getPageNumber(), param.getPageSize());
    }


    /**
     * 插入类目功课管理
     *
     * @param rc
     * @return
     */
    @RequestMapping("/insertCatalogue")
    @ResponseBody
    public Result insertCatalogue(ResourceCatalogue rc, @RequestParam("taskImg") CommonsMultipartFile img, Integer userId) throws Exception {

        Map<String, String> ret = Maps.newHashMap();
        String imgUrl = "";
        if (!img.isEmpty()) {
            ret = fdfsService.uploadStream(img.getSize(), img.getOriginalFilename(), img.getInputStream());
            if (!"0".equals(ret.get("code"))) {
                return new Result("1", "图片上传失败", null);
            }
            //服务器返回的URL地址
            imgUrl = ret.get("fileStorePath");
        }

        if (StringUtils.isNotBlank(imgUrl)) {
            rc.setImg(imgUrl);
        }

        return mcategoryMgrService.insertCatalogue(rc, userId);
    }

    /**
     * 更新功课目录
     *
     * @param
     * @return
     */
    @RequestMapping("/updateResourceCatalogue")
    @ResponseBody
    public Result updateResourceCatalogue(ResourceCatalogue resourceCatalogue, Integer userId) throws Exception {

        return mcategoryMgrService.updateResourceCatalogue(resourceCatalogue, userId);
    }

    /**
     * 删除功课类型
     *
     * @param manageLog
     * @return
     */
    @RequestMapping("/deleteTaskType")
    @ResponseBody
    public Result deleteCatalogue(ManageLog manageLog) {

        return mcategoryMgrService.deleteCatalogue(manageLog);
    }

    /**
     * 根据功课ID查找功课内容
     *
     * @param taskContent
     * @return
     */
    @RequestMapping("/selectContentById")
    @ResponseBody
    public Result selectContentById(TaskContent taskContent) {

        return mcategoryMgrService.selectContentById(taskContent);
    }

    /**
     * 根据目录ID查找功课
     *
     * @return
     */
    @RequestMapping("/findTaskItemById")
    @ResponseBody
    public Result findTaskItemById(Integer Id) {

        return mcategoryMgrService.findTaskItemById(Id);
    }

    /**
     * 编辑功课内容
     *
     * @param taskContent
     * @return
     */
    @RequestMapping(value = "/initEditContent")
    public ModelAndView initEditContent(TaskContent taskContent) {

        TaskContent ret = mcategoryMgrService.editTaskContent(taskContent);
        ModelAndView mav = new ModelAndView();
        mav.addObject("bean", ret);
        mav.setViewName("/resource/editContent");
        return mav;
    }

    /**
     * 编辑目录
     */
    @RequestMapping(value = "/initEditCatalogue")
    public ModelAndView initEditCatalogue(ResourceCatalogue resourceCatalogue, Integer userId) {

        ResourceCatalogue gue = mcategoryMgrService.editCatalogue(resourceCatalogue, userId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("bean", gue);
        mav.addObject("userId", userId);
        mav.setViewName("/catalogue/editCatalogue");
        return mav;
    }

    /**
     * 删除功课内容
     *
     * @param manageLog
     * @return
     */
    @RequestMapping("/deleteTaskContent")
    @ResponseBody
    public Result deleteTaskContent(ManageLog manageLog) {

        return mcategoryMgrService.deleteTaskContent(manageLog);
    }


    /**
     * 添加功课内容
     */
    @RequestMapping("/insertTaskContent")
    @ResponseBody
    public Result insertTaskContent(TaskContent taskContent, Integer userId,
                                    @RequestParam("taskFileTxt") CommonsMultipartFile fileTxt,
                                    @RequestParam("taskFilePic") CommonsMultipartFile filePic) throws Exception {

        Map<String, String> ret = Maps.newHashMap();
        String fileTxtUrl = "";
        if (!fileTxt.isEmpty()) {

            ret = fdfsService.uploadStream(fileTxt.getSize(), fileTxt.getOriginalFilename(), fileTxt.getInputStream());
            if (!"0".equals(ret.get("code"))) {
                return new Result("1", "图片上传失败", null);
            }
            //服务器返回的URL地址
            fileTxtUrl = ret.get("fileStorePath");
        }

        String filePicUrl = "";
        if (!filePic.isEmpty()) {
            ret = fdfsService.uploadStream(filePic.getSize(), filePic.getOriginalFilename(), filePic.getInputStream());
            if (!"0".equals(ret.get("code"))) {
                return new Result("1", "图片上传失败", null);
            }
            //服务器返回的URL地址
            filePicUrl = ret.get("fileStorePath");
        }

        if (StringUtils.isNotBlank(filePicUrl)) {
            taskContent.setFilePic(filePicUrl);
        }
        if (StringUtils.isNotBlank(fileTxtUrl)) {
            taskContent.setFileTxt(fileTxtUrl);
        }

        return mcategoryMgrService.insertTaskContent(taskContent, userId);
    }

    /**
     * 修改功课内容
     */
    @RequestMapping("/updateTaskContent")
    @ResponseBody
    public Result updateTaskContent(TaskContent taskContent, Integer userId) throws Exception {

        return mcategoryMgrService.updateTaskContent(taskContent, userId);
    }


    /**
     * 添加功课
     */
    @RequestMapping("/insertTaskItem")
    @ResponseBody
    public Result insertTaskItem(TaskItem taskItem, @RequestParam("taskTypeId") String typeId, @RequestParam("taskItemPic") CommonsMultipartFile itemPic, Integer userId) throws Exception {

        Map<String, String> ret = Maps.newHashMap();
        String itemPicUrl = "";
        if (!itemPic.isEmpty()) {
            ret = fdfsService.uploadStream(itemPic.getSize(), itemPic.getOriginalFilename(), itemPic.getInputStream());
            if (!"0".equals(ret.get("code"))) {
                return new Result("1", "图片上传失败", null);
            }
            //服务器返回的URL地址
            itemPicUrl = ret.get("fileStorePath");
        }

        if (StringUtils.isNotBlank(itemPicUrl)) {
            taskItem.setItemPic(itemPicUrl);
        }

        return mcategoryMgrService.insertTaskItem(taskItem, typeId, userId);
    }

    /**
     * 编辑功课
     *
     * @param taskItem
     * @return
     */
    @RequestMapping(value = "/initEditItem")
    public ModelAndView initEditItem(TaskItem taskItem, Integer userId) {

        Map<String, Object> tsm = mcategoryMgrService.findItemById(taskItem, userId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("resource", tsm);
        mav.setViewName("/resource/editResource");
        return mav;
    }

    /**
     * 修改功课
     *
     * @param taskItem
     * @return
     */
    @RequestMapping("/updateTaskItem")
    @ResponseBody
    public Result updateTaskItem(TaskItem taskItem, @RequestParam("typeId") String typeId, Integer userId) throws Exception {

        return mcategoryMgrService.updateTaskItem(taskItem, typeId, userId);
    }

    /**
     * 功课查询
     */
    @RequestMapping("/findAllTaskItem")
    @ResponseBody
    public Result findAllTaskItem(ManagerParam param) {

        return mcategoryMgrService.findAllTaskItem(param.getPageNumber(), param.getPageSize());
    }

    /**
     * 功课检索
     *
     * @return
     */
    @RequestMapping(value = "/searchItem")
    @ResponseBody
    public Result searchItem(TaskItem taskItem, ManagerParam param) throws Exception {

        if (null != taskItem.getItemName()) {
            //转码
            String val = URLDecoder.decode(taskItem.getItemName(), "UTF-8");
            taskItem.setItemName(val);
        }
        return mcategoryMgrService.searchItem(taskItem, param.getPageNumber(), param.getPageSize());
    }

    @RequestMapping(value = "/searchcatalogueName")
    @ResponseBody
    public Result searchcatalogueName(TaskItem taskItem, ManagerParam param) throws Exception {

        if (null != taskItem.getCatalogueName()) {
            //转码
            String val = URLDecoder.decode(taskItem.getCatalogueName(), "UTF-8");
            taskItem.setCatalogueName(val);
        }
        return mcategoryMgrService.searchItem(taskItem, param.getPageNumber(), param.getPageSize());
    }

    /**
     * 功课删除
     */
    @RequestMapping("/deleteTaskItem")
    @ResponseBody
    public Result deleteTaskItem(ManageLog manageLog) {

        return mcategoryMgrService.deleteTaskItem(manageLog);
    }
    
    /**
     * 上传文件
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public String updateFile(@RequestParam("file") CommonsMultipartFile musicFile) throws Exception {
        
        Map<String, String> ret = Maps.newHashMap();
        if (!musicFile.isEmpty()) {
            ret = fdfsService.uploadStream(musicFile.getSize(), musicFile.getOriginalFilename(), musicFile.getInputStream());
            if (!"0".equals(ret.get("code"))) {
                
                return "";
            }
            //服务器返回的URL地址
            return ret.get("fileStorePath");
        }
        return "";
    }

    /**
     * 音频添加
     */
    @RequestMapping("/insertTaskMusic")
    @ResponseBody
    public Result insertTaskMusic(TaskMusicVO taskMusicVO, Integer userId,
                                  @RequestParam("taskPushTime") String pushTime,
                                  @RequestParam("taskMusicFile") CommonsMultipartFile musicFile,
                                  @RequestParam("taskMusicSubtitle") CommonsMultipartFile musicSubtitle) throws Exception {

        Map<String, String> ret = Maps.newHashMap();
        String musicFileUrl = "";
        if (!musicFile.isEmpty()) {
            ret = fdfsService.uploadStream(musicFile.getSize(), musicFile.getOriginalFilename(), musicFile.getInputStream());
            if (!"0".equals(ret.get("code"))) {
                return new Result("1", "图片上传失败", null);
            }
            //服务器返回的URL地址
            musicFileUrl = ret.get("fileStorePath");
        }

        String musicSubtitleUrl = "";
        if (!musicSubtitle.isEmpty()) {
            ret = fdfsService.uploadStream(musicSubtitle.getSize(), musicSubtitle.getOriginalFilename(), musicSubtitle.getInputStream());
            if (!"0".equals(ret.get("code"))) {
                return new Result("1", "图片上传失败", null);
            }
            //服务器返回的URL地址
            musicSubtitleUrl = ret.get("fileStorePath");
        }

        if (StringUtils.isNotBlank(pushTime)) {
            int newPushTime = DateUtil.getDateToLinuxTime(pushTime, DateUtil.TIME_PATTON_DEFAULT);
            taskMusicVO.setPushTime(newPushTime);
        }
        if (StringUtils.isNotBlank(musicFileUrl)) {
            taskMusicVO.setMusicFile(musicFileUrl);
        }
        if (StringUtils.isNotBlank(musicSubtitleUrl)) {
            taskMusicVO.setMusicSubtitle(musicSubtitleUrl);
        }

        return mcategoryMgrService.insertTaskMusic(taskMusicVO, userId);

    }

    /**
     * 音频修改
     */
    @RequestMapping("/updateTaskMusic")
    @ResponseBody
    public Result updateTaskMusic(TaskMusicVO taskMusicVO, Integer userId,
                                  @RequestParam("taskPushTime") String pushTime) throws Exception {

        long startTime = System.currentTimeMillis();//记录开始时间
        System.out.println("开始记录：" + startTime);

        if (StringUtils.isNotBlank(pushTime)) {
            int newPushTime = DateUtil.getDateToLinuxTime(pushTime, DateUtil.TIME_PATTON_DEFAULT);
            taskMusicVO.setPushTime(newPushTime);
        }

        Result result = mcategoryMgrService.updateTaskMusic(taskMusicVO, userId);

        long endTime = System.currentTimeMillis();//记录结束时间
        System.out.println("endTime:" + endTime);
        float excTime = (float) (endTime - startTime) / 1000;
        _log.info("修改音频执行时间差>>>>>>>>>>>>>：" + excTime + "s");

        return result;
    }

    /**
     * 音频删除
     */
    @RequestMapping("/deleteTaskmusic")
    @ResponseBody
    public Result deleteTaskmusic(TaskMusicVO taskMusicVO, Integer userId) {

        return mcategoryMgrService.deleteTaskmusic(taskMusicVO, userId);

    }

    /**
     * 获取功课类型树形列表
     */
    @RequestMapping(value = "/getTree")
    @ResponseBody  //此注解表明返回值跳过视图处理部分，直接写入 http response body中
    public List<TaskType> getTree() {

        return mcategoryMgrService.getTree();
    }

}
