package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.app.dao.*;
import com.hrym.app.dao.WorkItemMapper;
import com.hrym.app.service.McategoryMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.MD5Util;
import com.hrym.rpc.app.config.ESCommon;
import com.hrym.rpc.app.dao.model.VO.TaskMusicVO;
import com.hrym.rpc.app.dao.model.system.BackUser;
import com.hrym.rpc.app.dao.model.task.*;
import com.hrym.rpc.app.util.Result;
import com.hrym.app.dao.BackUserMapper;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by mj on 2017/7/21.
 */
@Service
public class McategoryMgrServiceImpl implements McategoryMgrService {

    @Autowired
    private ResourceCatalogueMapper resourceCatalogueMapper;

    @Autowired
    private TaskContentMgrMapper taskContentMapper;

    @Autowired
    private TaskItemMgrMapper taskItemMapper;

    @Autowired
    private TaskMusicMgrMapper taskMusicMapper;

    @Autowired
    private WorkItemMapper workItemMapper;

    @Autowired
    private TaskPlanMgrMapper taskPlanMgrMapper;

    @Autowired
    private BackUserMapper backUserMapper;

    @Autowired
    private ManageLogMgrMapper manageLogMgrMapper;
    @Autowired
    private TransportClient transportClient;

    /**
     * 登录验证
     *
     * @return
     */
    @Override
    public BackUser login(String userName, String pwd) {

      BackUser backUser =  backUserMapper.findByUserName(userName);
      if (backUser!=null) {
          if (StringUtils.isNotBlank(backUser.getUsername()) && userName.equals(backUser.getUsername())) {
              if (StringUtils.isNotBlank(pwd) && MD5Util.MD5(pwd).equals(backUser.getPassword())) {
                  if (backUser.getStatus() == 0) {
                      return backUser;
                  } else {
                      return null;
                  }
              } else {
                  return null;
              }
          } else {
              return null;
          }
      }else {
          return null;
      }
    }
    /**
     * 资源目录查询
     *
     * @return
     */
    @Override
    public Result categoryManage(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page, rows);
        List<ResourceCatalogue> resourceCatalogueList = resourceCatalogueMapper.findAllCatalogue();

        PageInfo pageInfo = new PageInfo(resourceCatalogueList);
        List<Map<String, Object>> list = new ArrayList<>();


        for (ResourceCatalogue rc : resourceCatalogueList) {
            if(10000 == rc.getCatalogueId()) continue;
            Map<String, Object> map = Maps.newHashMap();
            map.put("catalogueName", rc.getCatalogueName());
            map.put("catalogueDesc",rc.getCatalogueDesc());
            map.put("introduceInfo",rc.getIntroduceInfo());
            map.put("img",rc.getImg());
            map.put("creatTime",DateUtil.timestampToDates(rc.getCreateTime(),TIME_PATTON_DEFAULT));
            map.put("creator", rc.getCreator());
            if (null != rc.getLevel()){
                switch (rc.getLevel()){
                    case 1: map.put("level","一级类目");break;
                    case 2: map.put("level","二级类目");break;
                    case 3: map.put("level","三级类目");break;
                }
            }
            map.put("catalogueId", rc.getCatalogueId());
            list.add(map);
        }
        long total = pageInfo.getTotal();

        return new Result(code, message,total,list);
    }

    /**
     * 目录检索 (过滤条件：目录ID，目录名称，目录层级)
     * @return
     */
    @Override
    public Result searchCatalogueById(ResourceCatalogue rcl,Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null == rcl.getCatalogueId() && null == rcl.getCatalogueName() && null == rcl.getLevel()) {
            return categoryManage(page, rows);
        }
        if (null !=rcl.getLevel()){
            //分页核心代码
            PageHelper.startPage(page, rows);
            List<ResourceCatalogue> resourceCatalogue = resourceCatalogueMapper.searchCatalogueById(rcl.getCatalogueId(),rcl.getLevel());

            PageInfo pageInfo = new PageInfo(resourceCatalogue);
            List<Map<String, Object>> list = new ArrayList<>();
            if (0 < resourceCatalogue.size()) {
                for (ResourceCatalogue t : resourceCatalogue) {
                    Map<String, Object> map = Maps.newHashMap();

                    map.put("catalogueName", t.getCatalogueName());
                    map.put("catalogueDesc", t.getCatalogueDesc());
                    map.put("introduceInfo", t.getIntroduceInfo());
                    map.put("img", t.getImg());
                    map.put("creatTime", DateUtil.timestampToDates(t.getCreateTime(), TIME_PATTON_DEFAULT));
                    map.put("creator", t.getCreator());
                    if (null != t.getLevel()) {
                        switch (t.getLevel()) {
                            case 1:
                                map.put("level", "一级类目");
                                break;
                            case 2:
                                map.put("level", "二级类目");
                                break;
                            case 3:
                                map.put("level", "三级类目");
                                break;
                        }
                    }
                    map.put("catalogueId", t.getCatalogueId());
                    list.add(map);
                }
            }

            long total = pageInfo.getTotal();
            return new Result(code, message, total, list);
        }
        //catalogueName检索
        //分页核心代码
        PageHelper.startPage(page, rows);
        List<ResourceCatalogue> resourceCatalogue = resourceCatalogueMapper.searchCatalogueByName(rcl.getCatalogueName());

        PageInfo pageInfo = new PageInfo(resourceCatalogue);
        List<Map<String, Object>> list = new ArrayList<>();
        if (0 < resourceCatalogue.size()) {
            for (ResourceCatalogue t : resourceCatalogue) {
                Map<String, Object> map = Maps.newHashMap();

                map.put("catalogueName", t.getCatalogueName());
                map.put("catalogueDesc", t.getCatalogueDesc());
                map.put("introduceInfo", t.getIntroduceInfo());
                map.put("img", t.getImg());
                map.put("creatTime", DateUtil.timestampToDates(t.getCreateTime(), TIME_PATTON_DEFAULT));
                map.put("creator", t.getCreator());
                if (null != t.getLevel()) {
                    switch (t.getLevel()) {
                        case 1:
                            map.put("level", "一级类目");
                            break;
                        case 2:
                            map.put("level", "二级类目");
                            break;
                        case 3:
                            map.put("level", "三级类目");
                            break;
                    }
                }
                map.put("catalogueId", t.getCatalogueId());
                list.add(map);
            }
        }

        long total = pageInfo.getTotal();
        return new Result(code, message, total, list);
        }

    /**
     * 功课模糊检索
     * @param taskItem
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result searchItem(TaskItem taskItem, Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if ( null == taskItem.getCatalogueName() && null == taskItem.getItemName()){
            return findAllTaskItem(page,rows);
        }
        if (null !=taskItem.getItemName()){
            //分页核心代码
            PageHelper.startPage(page,rows);
            List<TaskItem> taskItems = taskItemMapper.findTaskItemByName(taskItem.getItemName());
            if (null == taskItems){
                return new Result(code,message,null);
            }
            List<Map<String, Object>> list = new ArrayList<>();
            PageInfo pageInfo = new PageInfo(taskItems);

            for (TaskItem t :taskItems){
                Map<String,Object> map=new HashMap<>();
                map.put("itemId",t.getItemId());
                map.put("itemName",t.getItemName());
                map.put("aliasName",t.getAliasName());
                map.put("itemPic",t.getItemPic());
                map.put("titleDesc",t.getTitleDesc());
                map.put("itemIntro",t.getItemIntro());
                map.put("catalogueId",t.getCatalogueId());
                map.put("catalogueName",t.getResourceCatalogue().getCatalogueName());
                map.put("CatalogueId",t.getResourceCatalogue().getCatalogueId());
                list.add(map);
            }

            long total = pageInfo.getTotal();

            return new Result(code,message,total,list);
        }
        /**
         * 根据目录名称检索
         */
        //分页核心代码
        PageHelper.startPage(page, rows);
        List<TaskItem> taskItems = taskItemMapper.searchBycatalogueName(taskItem.getCatalogueName());
        if (null ==taskItems){
            return null;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        PageInfo pageInfo = new PageInfo(taskItems);

        for (TaskItem t :taskItems){
            Map<String,Object> map=new HashMap<>();
            map.put("itemId",t.getItemId());
            map.put("itemName",t.getItemName());
            map.put("aliasName",t.getAliasName());
            map.put("itemPic",t.getItemPic());
            map.put("titleDesc",t.getTitleDesc());
            map.put("itemIntro",t.getItemIntro());
            map.put("catalogueId",t.getCatalogueId());
            map.put("catalogueName",t.getResourceCatalogue().getCatalogueName());
            map.put("CatalogueId",t.getResourceCatalogue().getCatalogueId());
            list.add(map);
        }

        long total = pageInfo.getTotal();

        return new Result(code,message,total,list);
    }


    /**
     * 类目管理删除
     */
    @Override
    public Result deleteCatalogue(ManageLog manageLog) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != manageLog.getCatalogueId()) {
            BackUser backUser = backUserMapper.findUserNameById(manageLog.getUserId());
            //日志生成
            ManageLog manageLog1 = resourceCatalogueMapper.findByCatalogueId(manageLog.getCatalogueId());
            manageLog.setCreateTime(DateUtil.currentSecond());
            manageLog.setUserId(manageLog.getUserId());

            if (backUser.getUsername()!=null){

            manageLog.setUserName(backUser.getUsername());
            }
            manageLog.setCatalogueId(manageLog.getCatalogueId());
            manageLog.setCatalogueName("删除类目："+manageLog1.getCatalogueName());
            manageLogMgrMapper.insertAllManageLog(manageLog);

            resourceCatalogueMapper.deleteCatalogue(manageLog.getCatalogueId());
        } else {
            code = BaseConstants.GWSCODE2001;
            message = BaseConstants.GWSMSG2001;
        }
        return new Result(code, message, null);
    }

    /**
     * 类目管里插入
     */
    @Override
    public Result insertCatalogue(ResourceCatalogue rc,Integer userId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null ==rc.getParentTypeId()){

            return new Result("2","请选择父目录",null);
        }
        rc.setCreateTime(DateUtil.currentSecond());
        int level = resourceCatalogueMapper.findlevelById(rc.getParentTypeId());
        rc.setLevel(level+1);
        rc.setParentLevel(level);
        resourceCatalogueMapper.insertCatalogue(rc);
        //日志打印
        int catalogueId = taskMusicMapper.getLastInsertId();
        ManageLog manageLog = new ManageLog();
        manageLog.setCreateTime(DateUtil.currentSecond());
        if (catalogueId!=0){
            manageLog.setCatalogueId(catalogueId);
        }
        manageLog.setUserId(userId);
        manageLog.setCatalogueName("添加类目："+rc.getCatalogueName());
        manageLogMgrMapper.insertAllManageLog(manageLog);
        return new Result(code, message, null);
    }

    /**
     * 修改目录
     * @param resourceCatalogue
     * @return
     */
    @Override
    public Result updateResourceCatalogue(ResourceCatalogue resourceCatalogue,Integer userId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        int level = resourceCatalogueMapper.findlevelById(resourceCatalogue.getParentTypeId());
        resourceCatalogue.setLevel(level+1);
        resourceCatalogue.setParentLevel(level);
        resourceCatalogueMapper.updateResourceCatalogue(resourceCatalogue);

        ManageLog manageLog = new ManageLog();
        manageLog.setCreateTime(DateUtil.currentSecond());
        manageLog.setUserId(userId);
        BackUser backUser = backUserMapper.findUserNameById(manageLog.getUserId());
        if (backUser !=null){
            manageLog.setUserName(backUser.getUsername());
        }
        manageLog.setCatalogueId(resourceCatalogue.getCatalogueId());
        manageLog.setCatalogueName("修改类目："+resourceCatalogue.getCatalogueName());
        manageLogMgrMapper.insertAllManageLog(manageLog);
        return new Result(code,message,null);
    }


    @Override
    public Result findAllTaskContent(Integer page, Integer rows) {
        return null;
    }

    /**
     * 根据目录类型ID查找资源内容
     * @param Id
     * @return
     */
    @Override
    public Result findTaskItemById(Integer Id) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<TaskItem> taskItems = taskItemMapper.findTaskItemById(Id);

        List<Map<String,Object>> list = new ArrayList<>();
        for (TaskItem t :taskItems){

            if (null != t.getItemId()){
                Map<String,Object> map = new HashMap<>();
                map.put("itemId",t.getItemId());
                map.put("typeId",t.getTypeId());
                map.put("itemName",t.getItemName());
                list.add(map);
            }
        }

        return new Result(code,message,list);
    }

    /**
     * 根据功课ID查找功课内容
     * @param
     * @return
     */
    @Override
    public Result selectContentById(TaskContent taskCotent) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<TaskContent> taskContents = taskContentMapper.findAllContentById(taskCotent.getItemId());
        for (TaskContent t : taskContents){
            if (null != t.getYears()){
                String yearsStr = DateUtil.timestampToDates(t.getYears(),TIME_PATTON_DEFAULT);
                t.setYearsStr(yearsStr);
            }
        }
        return new Result(code,message,taskContents);
    }

    /**
     * 编辑功课内容
     * @param taskContent
     * @return
     */
    @Override
    public TaskContent editTaskContent(TaskContent taskContent) {

        TaskContent tct = taskContentMapper.findContentById(taskContent.getItemContentId());
        List<TaskMusic> taskMusicList = taskMusicMapper.findAllTaskMusicById(tct.getItemContentId());

        List<TaskMusicVO> taskMusicVOList = new ArrayList<>();
        for (TaskMusic t : taskMusicList){
            TaskMusicVO taskMusicVO = new TaskMusicVO();
            //查询子音频
            SubResource subResource = taskMusicMapper.findAllSubResource(t.getMusicId());
            taskMusicVO.setAlbumName(t.getAlbumName());
            taskMusicVO.setComposer(t.getComposer());
            taskMusicVO.setDiskNumber(t.getDiskNumber());
            taskMusicVO.setDownTime(t.getDownTime());
            taskMusicVO.setEndTime(t.getEndTime());
            taskMusicVO.setItemContentId(t.getItemContentId());
            taskMusicVO.setItemId(t.getItemId());
            taskMusicVO.setMusicFile(t.getMusicFile());
            taskMusicVO.setMusicId(t.getMusicId());
            taskMusicVO.setMusicName(t.getMusicName());
            taskMusicVO.setMusicPic(t.getMusicPic());
            taskMusicVO.setMusicSubtitle(t.getMusicSubtitle());
            taskMusicVO.setNeedSetTime(t.getNeedSetTime());
            taskMusicVO.setOrbitalNumber(t.getOrbitalNumber());
            taskMusicVO.setPushTime(t.getPushTime());
            taskMusicVO.setRoundTime(t.getRoundTime());
            taskMusicVO.setSettingTime(t.getSettingTime());
            taskMusicVO.setShouEndTime(t.getShouEndTime());
            taskMusicVO.setSinger(t.getSinger());
            taskMusicVO.setStartNum(t.getStartNum());
            taskMusicVO.setStartTime(t.getStartTime());
            taskMusicVO.setStep(t.getStep());
            taskMusicVO.setStyle(t.getStyle());
            taskMusicVO.setVersionId(t.getVersionId());
            taskMusicVO.setVoiceDown(t.getVoiceDown());
            taskMusicVO.setVoiceStart(t.getVoiceStart());
            taskMusicVO.setMusicFileAndroid(t.getMusicFileAndroid());
            taskMusicVO.setMusicVersion(t.getMusicVersion());

            if (null != subResource){
                taskMusicVO.setDes(subResource.getDes());
                taskMusicVO.setValue(subResource.getValue());
                taskMusicVO.setType(subResource.getType());
                taskMusicVO.setResourceId(subResource.getResourceId());
                taskMusicVO.setIdtSubResource(subResource.getIdtSubResource());
            }

            taskMusicVOList.add(taskMusicVO);
        }

        tct.setMusicVos(taskMusicVOList);

        return tct;
    }

    /**
     * 编辑目录
     * @param resourceCatalogue
     * @return
     */
    @Override
    public ResourceCatalogue editCatalogue(ResourceCatalogue resourceCatalogue,Integer userId) {

        ResourceCatalogue res = resourceCatalogueMapper.findCatalogueById(resourceCatalogue.getCatalogueId());
        return res;
    }


    /**
     * 删除功课内容
     * @param manageLog
     * @return
     */
    @Override
    public Result deleteTaskContent(ManageLog manageLog) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != manageLog.getItemContentId()) {

            //日志生成
            ManageLog manageLog1 =taskContentMapper.findById(manageLog.getItemContentId());
            BackUser backUser = backUserMapper.findUserNameById(manageLog.getUserId());
            if (backUser !=null){
                manageLog.setUserName(backUser.getUsername());
            }
            manageLog.setCreateTime(DateUtil.currentSecond());
            manageLog.setItemContentId(manageLog.getItemContentId());
            manageLog.setItemId(manageLog1.getItemId());
            manageLog.setItemName("删除："+manageLog1.getItemName());
            manageLog.setVersionName("删除内容："+manageLog1.getVersionName());
            manageLog.setUserId(manageLog.getUserId());
            manageLogMgrMapper.insertAllManageLog(manageLog);

            taskContentMapper.deleteTaskContent(manageLog.getItemContentId());

           List<TaskMusic> taskMusicList =  taskMusicMapper.findAllTaskMusicById(manageLog.getItemContentId());
           for (TaskMusic t : taskMusicList){
               taskMusicMapper.deleteSubResource(t.getMusicId());
           }

            taskMusicMapper.deleteTaskMusicByContentId(manageLog.getItemContentId());

        } else {
            code = BaseConstants.GWSCODE2001;
            message = BaseConstants.GWSMSG2001;
        }
        return new Result(code, message, null);
    }


    /**
     * 修改功课内容
     * @param taskContent
     * @return
     */
    @Override
    public Result updateTaskContent(TaskContent taskContent,Integer userId) {

        String code=BaseConstants.GWSCODE0000;
        String message=BaseConstants.GWSMSG0000;

        taskContentMapper.updateTaskContent(taskContent);
        //日志生成
        ManageLog manageLog = new ManageLog();
        ManageLog manageLog1 =taskContentMapper.findById(taskContent.getItemContentId());
        BackUser backUser = backUserMapper.findUserNameById(userId);
        if (backUser !=null){
            manageLog.setUserName(backUser.getUsername());
        }
        manageLog.setCreateTime(DateUtil.currentSecond());
        manageLog.setItemId(taskContent.getItemId());
        if (manageLog1.getItemName()!=null){
            manageLog.setItemName("修改："+manageLog1.getItemName());
        }
        manageLog.setItemContentId(taskContent.getItemContentId());
        manageLog.setVersionName("修改功课内容："+taskContent.getVersionName());
        manageLog.setUserId(userId);
        manageLogMgrMapper.insertAllManageLog(manageLog);
        return new Result(code,message,null);
    }


    /**
     * 功课内容添加
     * @param taskContent
     * @return
     */
    @Override
    public Result insertTaskContent(TaskContent taskContent,Integer userId) {

       String code=BaseConstants.GWSCODE0000;
       String message=BaseConstants.GWSMSG0000;

       if (null == taskContent.getItemId()){
           return new Result("1","请先添加功课",null);
       }
       if (StringUtils.isNotBlank(taskContent.getYearsStr())){
           int years = DateUtil.getDateToLinuxTime(taskContent.getYearsStr(),TIME_PATTON_DEFAULT);
           taskContent.setYears(years);
       }
       taskContent.setCreateTime(DateUtil.currentSecond());
       taskContentMapper.insertTaskContent(taskContent);
       int itemContentId = taskContentMapper.getLastInsertId();

       Map map = Maps.newHashMap();
       map.put("itemContentId",itemContentId);
       map.put("itemId",taskContent.getItemId());
        //日志生成
        ManageLog manageLog = new ManageLog();
        BackUser backUser = backUserMapper.findUserNameById(userId);
        if (backUser !=null){
            manageLog.setUserName(backUser.getUsername());
        }
        manageLog.setCreateTime(DateUtil.currentSecond());
        manageLog.setItemContentId(itemContentId);
        manageLog.setVersionName("添加功课内容："+taskContent.getVersionName());
        manageLog.setUserId(userId);
        manageLogMgrMapper.insertAllManageLog(manageLog);

       return new Result("2",message,map);
    }

    /**
     * 修改功课
     * @param taskItem
     * @return
     */
    @Override
    public Result updateTaskItem(TaskItem taskItem,String typeId,Integer userId) {

        String code=BaseConstants.GWSCODE0000;
        String message=BaseConstants.GWSMSG0000;

        taskItem.setUpdateTime(DateUtil.currentSecond());
        if (null !=taskItem.getItemId()){
            taskItemMapper.updateTaskItem(taskItem);
            workItemMapper.deleteWorkItem(taskItem.getItemId());

            //拆分字符串转int存入数据库
            split(typeId,taskItem.getItemId());

            Map map = Maps.newHashMap();
            map.put("itemId",taskItem.getItemId());

            //日志生成
            ManageLog manageLog = new ManageLog();
            BackUser backUser = backUserMapper.findUserNameById(userId);
            if (backUser !=null){
                manageLog.setUserName(backUser.getUsername());
            }
            manageLog.setCreateTime(DateUtil.currentSecond());
            manageLog.setItemId(taskItem.getItemId());
            manageLog.setItemName("修改功课："+taskItem.getItemName());
            manageLog.setUserId(userId);
            manageLogMgrMapper.insertAllManageLog(manageLog);
            return new Result(code,message,null);
        }else {
            code=BaseConstants.GWSCODE3001;
            message=BaseConstants.GWSMSG3001;
            return new Result(code,message,null);
        }

    }


    /**
     * 功课item添加
     * @return
     */
    @Override
    public Result insertTaskItem(TaskItem taskItem,String typeId,Integer userId) {

        String code=BaseConstants.GWSCODE0000;
        String message=BaseConstants.GWSMSG0000;

        taskItem.setCreateTime(DateUtil.currentSecond());
        taskItem.setUpdateTime(DateUtil.currentSecond());
        taskItemMapper.insertTaskItem(taskItem);
        int itemId = taskItemMapper.getLastId();

        //拆分字符串转int存入数据库
        split(typeId,itemId);

        Map map = Maps.newHashMap();
        map.put("itemId",itemId);
        //日志生成
        ManageLog manageLog = new ManageLog();
        BackUser backUser = backUserMapper.findUserNameById(userId);
        if (backUser !=null){
            manageLog.setUserName(backUser.getUsername());
        }
        manageLog.setCreateTime(DateUtil.currentSecond());
        manageLog.setItemId(itemId);
        manageLog.setItemName("添加功课："+taskItem.getItemName());
        manageLog.setUserId(userId);
        manageLogMgrMapper.insertAllManageLog(manageLog);
        return new Result(code,message,map);
    }

    /**
     *功课item查询
     * @return
     */
    @Override
    public Result findAllTaskItem(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page, rows);
        List<TaskItem> taskItems =taskItemMapper.findAllTaskItem();
        if (null==taskItems){
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
            return new Result(code,message,null);
        }
        List<Map<String, Object>> list = new ArrayList<>();
        PageInfo pageInfo = new PageInfo(taskItems);

        for (TaskItem t :taskItems){
            Map<String,Object> map=new HashMap<>();

            map.put("itemName",t.getItemName());
            map.put("aliasName",t.getAliasName());
            map.put("itemPic",t.getItemPic());
            map.put("titleDesc",t.getTitleDesc());
            map.put("itemIntro",t.getItemIntro());
            map.put("itemId",t.getItemId());
            map.put("aliasName",t.getAliasName());
            map.put("catalogueId",t.getCatalogueId());
            if (null == t.getIsSupport()){
                map.put("isSupport","null");
            }
            if (null !=t.getIsSupport()){
                switch (t.getIsSupport()){
                    case 1:
                        map.put("isSupport","可供"); break;
                    case 0:
                        map.put("isSupport","不可供"); break;
                }
            }
            if (null != t.getCatalogueId()){
                map.put("catalogueName",t.getResourceCatalogue().getCatalogueName());
                map.put("CatalogueId",t.getResourceCatalogue().getCatalogueId());
            }

            list.add(map);
        }

        long total = pageInfo.getTotal();

        return new Result(code,message,total,list);
    }




    /**
     * 功课编辑
     * @param taskItem
     * @return
     */
    @Override
    public Map<String,Object> findItemById(TaskItem taskItem, Integer userId) {

        TaskItem tkm = taskItemMapper.findTaskItemByItemId(taskItem.getItemId());
        List< TaskItem> taskItemList = workItemMapper.findAllWorkItem(tkm.getItemId());

        Map<String,Object> map = Maps.newHashMap();

        map.put("userId",userId);
        map.put("itemId",tkm.getItemId());
        map.put("catalogueId",tkm.getCatalogueId());
        map.put("itemName",tkm.getItemName());
        map.put("aliasName",tkm.getAliasName());
        map.put("itemPic",tkm.getItemPic());
        map.put("itemIntro",tkm.getItemIntro());
        map.put("titleDesc",tkm.getTitleDesc());
        map.put("isSupport",tkm.getIsSupport());
        for (TaskItem t : taskItemList){
                 if (10001==t.getTypeId()){map.put("typeIdA",t.getTypeId());}
            else if (10002==t.getTypeId()){map.put("typeIdB",t.getTypeId());}
            else if (10003==t.getTypeId()){map.put("typeIdC",t.getTypeId());}
            else if (10004==t.getTypeId()){map.put("typeIdD",t.getTypeId());}
            else if (10005==t.getTypeId()){map.put("typeIdE",t.getTypeId());}
            else if (10006==t.getTypeId()){map.put("typeIdF",t.getTypeId());}
            else if (10007==t.getTypeId()){map.put("typeIdH",t.getTypeId());}
            else if (10011==t.getTypeId()){map.put("typeIdK",t.getTypeId());}
            else if (10008==t.getTypeId()){map.put("typeIdG",t.getTypeId());}
        }
        return map;
    }

    /**
     * 功课删除
     * @return
     */
    @Override
    public Result deleteTaskItem(ManageLog manageLog) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != manageLog.getItemId()) {

            List<TaskPlan> taskPlan = taskPlanMgrMapper.findAllByItemId(manageLog.getItemId());
            if (0<taskPlan.size()){
                return new Result("1","此功课正在被使用，不能删除",null);
            }
            //日志生成
            ManageLog manageLog1 = taskItemMapper.findByItemId(manageLog.getItemId());
            //查找用户名
            BackUser backUser = backUserMapper.findUserNameById(manageLog.getUserId());
            if (backUser !=null){
                manageLog.setUserName(backUser.getUsername());
            }
            manageLog.setCreateTime(DateUtil.currentSecond());
            manageLog.setItemId(manageLog.getItemId());
            manageLog.setItemName("删除功课："+manageLog1.getItemName());
            manageLog.setVersionName("删除："+manageLog1.getVersionName());
            manageLog.setUserId(manageLog.getUserId());
            manageLogMgrMapper.insertAllManageLog(manageLog);
            //删除功课
            taskItemMapper.deleteTaskItem(manageLog.getItemId());
            List<TaskContent> taskItems = taskContentMapper.findAllContentById(manageLog.getItemId());
            for (TaskContent t : taskItems){
                List<TaskMusic> taskMusics = taskMusicMapper.findAllTaskMusicById(t.getItemContentId());
                for (TaskMusic m : taskMusics){
                    taskMusicMapper.deleteSubResource(m.getMusicId());
                }
            }
            taskContentMapper.deleteTaskContentByItemId(manageLog.getItemId());
            taskMusicMapper.deleteTaskmusicByItemId(manageLog.getItemId());
            workItemMapper.deleteWorkItem(manageLog.getItemId());
            //es删除
        ESCommon esCommon = new ESCommon();
        esCommon.setEsIndex("hrym-elasticsearch","resource",transportClient);
        esCommon.delete(String.valueOf(manageLog.getItemId()));
        }

        return new Result(code,message,null);
    }


    /**
     * 音频修改
     */
    @Override
    public Result updateTaskMusic(TaskMusicVO taskMusicVO,Integer userId) {

        double start  = System.currentTimeMillis() ;

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        System.out.println("time is : " + System.currentTimeMillis());
        taskMusicMapper.updateTaskMusic(taskMusicVO);
        System.out.println("time is : " + System.currentTimeMillis());
        taskMusicMapper.updateSubResource(taskMusicVO);
        System.out.println("time is : " + System.currentTimeMillis());
        double end = System.currentTimeMillis() ;
        System.out.println("time is : " + (end - start));

        //日志打印
        ManageLog manageLog = new ManageLog();
        //查找用户名
        BackUser backUser = backUserMapper.findUserNameById(userId);
        if (backUser !=null){
            manageLog.setUserName(backUser.getUsername());
        }
        manageLog.setCreateTime(DateUtil.currentSecond());
        manageLog.setItemId(taskMusicVO.getItemId());
        manageLog.setMusicName("修改音频："+taskMusicVO.getMusicName());
        manageLog.setItemContentId(taskMusicVO.getItemContentId());
        manageLog.setMusicId(taskMusicVO.getMusicId());
        manageLog.setUserId(userId);
        manageLogMgrMapper.insertAllManageLog(manageLog);
        return new Result(code,message,null);
    }


    /**
     * 音频添加
     * @param
     * @return
     */
    @Override
    public Result insertTaskMusic(TaskMusicVO taskMusicVO,Integer userId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null == taskMusicVO.getItemContentId() || null == taskMusicVO.getItemId()){
            return new Result("1","请先添加功课和功课内容",null);
        }

        if (null == taskMusicVO){
            return new Result("1","音频内容为空",null);
        }
        taskMusicMapper.insertTaskMusic(taskMusicVO);

        if (StringUtils.isNotBlank(taskMusicVO.getValue())){

            int musicId = taskMusicMapper.getLastInsertId();
            taskMusicVO.setResourceId(musicId);
            taskMusicMapper.insertSubResource(taskMusicVO);
        }
        //日志打印
        ManageLog manageLog = new ManageLog();
        //查找用户名
        BackUser backUser = backUserMapper.findUserNameById(userId);
        if (backUser !=null){
            manageLog.setUserName(backUser.getUsername());
        }
        manageLog.setCreateTime(DateUtil.currentSecond());
        manageLog.setItemId(taskMusicVO.getItemId());
        manageLog.setMusicName("添加音频："+taskMusicVO.getMusicName());
        manageLog.setItemContentId(taskMusicVO.getItemContentId());
        manageLog.setMusicId(taskMusicVO.getMusicId());
        manageLog.setUserId(userId);
        manageLogMgrMapper.insertAllManageLog(manageLog);
        return new Result(code,message,null);
    }

    /**
     * 删除music
     * @param taskMusicVO
     * @return
     */
    @Override
    public Result deleteTaskmusic(TaskMusicVO taskMusicVO,Integer userId) {
       String code = BaseConstants.GWSCODE0000;
       String message = BaseConstants.GWSMSG0000;

       if (null != taskMusicVO.getMusicId()){
           //日志打印
           ManageLog manageLog = new ManageLog();
           TaskMusicVO taskMusicVO1 = taskMusicMapper.findByMusicId(taskMusicVO.getMusicId());
           //查找用户名
           BackUser backUser = backUserMapper.findUserNameById(userId);
           if (backUser !=null){
               manageLog.setUserName(backUser.getUsername());
           }
           manageLog.setCreateTime(DateUtil.currentSecond());
           manageLog.setItemId(taskMusicVO1.getItemId());
           manageLog.setMusicName("删除音频："+taskMusicVO1.getMusicName());
           manageLog.setItemContentId(taskMusicVO1.getItemContentId());
           manageLog.setMusicId(taskMusicVO.getMusicId());
           manageLog.setUserId(userId);
           manageLogMgrMapper.insertAllManageLog(manageLog);
           taskMusicMapper.deleteTaskmusic(taskMusicVO.getMusicId());
           taskMusicMapper.deleteSubResource(taskMusicVO.getMusicId());
       }else {
           code= BaseConstants.GWSCODE4003;
           message = BaseConstants.GWSMSG4003;
       }
        return new Result(code,message,null);
    }

    /**
     * 获取下拉框
     * @return
     */
    @Override
    public List<TaskType> getTree() {

        List<TaskTree> root = resourceCatalogueMapper.findById(10000);  //获取根节点（获取的值存到list中）
//        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(buildTree(root));
//        System.out.println(jsonArray.toString());
        return buildTree(root);
    }

    public List buildTree(List<TaskTree> root){

        for(int i=0;i<root.size();i++){
            List<TaskTree> children = resourceCatalogueMapper.findByPid(root.get(i).getId()); //查询某节点的子节点（获取的是list）
            buildTree(children);
            root.get(i).setChildren(children);
        }

        return root;
    }


    /**
     * 功课类型ID字符串拆分存t_work_item表
     * @param string
     * @param n
     */
    public void split(String string,Integer n){

        String[] strings = string.split(",");
        for (int i =0;i < strings.length;i++){
            int a = Integer.parseInt(strings[i]);
            workItemMapper.insertWorkItem(a,n);
        }
    }
}