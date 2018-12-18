package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.hrym.app.dao.*;
import com.hrym.app.service.NewResourceMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.VO.NewResourceContentVO;
import com.hrym.rpc.app.dao.model.VO.TaskMusicVO;
import com.hrym.rpc.app.dao.model.task.book.Bookcase;
import com.hrym.rpc.app.dao.model.system.BackUser;
import com.hrym.rpc.app.dao.model.task.*;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceContentLesson;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceItemLesson;
import com.hrym.rpc.app.dao.model.task.meditation.MeditationContent;
import com.hrym.rpc.app.dao.model.task.meditation.MeditationPlan;
import com.hrym.rpc.app.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by mj on 2018/5/22.
 */
@Service
public class NewResourceMgrServiceImpl implements NewResourceMgrService {

    @Autowired
    private NewResourceMgrMapper newResourceMgrMapper;
    @Autowired
    private WorkItemMapper workItemMapper;
    @Autowired
    private TaskItemMgrMapper taskItemMgrMapper;
    @Autowired
    private BackUserMapper backUserMapper;
    @Autowired
    private ManageLogMgrMapper manageLogMgrMapper;
    @Autowired
    private TaskPlanMgrMapper taskPlanMgrMapper;
    @Autowired
    private BookcaseMgrMapper bookcaseMgrMapper;
    @Autowired
    private TaskContentMgrMapper taskContentMgrMapper;
    @Autowired
    private TaskMusicMgrMapper taskMusicMgrMapper;
    @Autowired
    private TaskPlanMeditationMgrMapper taskPlanMeditationMgrMapper;
    @Autowired
    private ResourceItemLessonMgrMapper resourceItemLessonMgrMapper;
    @Autowired
    private ResourceContentLessonMgrMapper resourceContentLessonMgrMapper;

    /**
     * 按类型查询所有功课资源
     */
    @Override
    public Result findAllByType(Integer typeId, Integer page, Integer rows) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<TaskItem> taskItems = new ArrayList<>();
        //分页核心代码
        PageHelper.startPage(page, rows);
        if (typeId == 10000){
            taskItems = newResourceMgrMapper.findAllItem();
        }
        if (typeId == 10006){
            taskItems = newResourceMgrMapper.findAllBook();
        }
        if (typeId == 10007){
            taskItems = newResourceMgrMapper.findAllMeditation();
        }
        if (0 == taskItems.size()){
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
            return new Result(code,message,0,taskItems);
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
            map.put("catalogueName",t.getCatalogueName());

            list.add(map);
        }

        long total = pageInfo.getTotal();

        return new Result(code,message,total,list);
    }

    /**
     * 按类型和名称模糊查询
     * @param taskItem
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result searchResource(TaskItem taskItem, Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if ( null == taskItem.getItemName()){
            return findAllByType(taskItem.getTypeId(),page,rows);
        }
        List<TaskItem> taskItems = new ArrayList<>();
        //分页核心代码
        PageHelper.startPage(page, rows);
        if (taskItem.getTypeId() == 10000){
            taskItems = newResourceMgrMapper.findItemByName(taskItem.getItemName());
        }
        if (taskItem.getTypeId() == 10006){
            taskItems = newResourceMgrMapper.findBookByName(taskItem.getItemName());
        }
        if (taskItem.getTypeId() == 10007){
            taskItems = newResourceMgrMapper.findMeditationByName(taskItem.getItemName());
        }
        if (0 == taskItems.size()){
            return new Result(code,message,0,taskItems);
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
            map.put("catalogueName",t.getCatalogueName());
            list.add(map);
        }

        long total = pageInfo.getTotal();

        return new Result(code,message,total,list);
    }

    /**
     * 保存功课资源
     * @param taskItem
     * @param typeId
     * @param userId
     * @return
     */
    @Override
    public Result saveTaskItem(TaskItem taskItem, String typeId, Integer userId) {
        String code=BaseConstants.GWSCODE0000;
        String message=BaseConstants.GWSMSG0000;

        int itemId = 0;
        taskItem.setCreateTime(DateUtil.currentSecond());
        taskItem.setUpdateTime(DateUtil.currentSecond());
        if (taskItem.getTypeId() == 10006){
            newResourceMgrMapper.saveBook(taskItem);
            itemId = taskItemMgrMapper.getLastId();
        }else if (taskItem.getTypeId() == 10007){
            newResourceMgrMapper.saveMeditation(taskItem);
            itemId = taskItemMgrMapper.getLastId();
        }else {
            taskItemMgrMapper.insertTaskItem(taskItem);
            itemId = taskItemMgrMapper.getLastId();
            //拆分字符串转int存入数据库
            split(typeId,itemId);
        }

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
     * 根据功课id查询功课
     * @param taskItem
     * @return
     */
    @Override
    public Map<String,Object> findItemById(TaskItem taskItem, Integer userId) {

        TaskItem tkm = null;
        if (taskItem.getTypeId() == 10000){
            tkm = taskItemMgrMapper.findTaskItemByItemId(taskItem.getItemId());
        }
        if (taskItem.getTypeId() == 10006){
            tkm = newResourceMgrMapper.findBookById(taskItem.getItemId());
        }
        if (taskItem.getTypeId() == 10007){
            tkm = newResourceMgrMapper.findMeditationById(taskItem.getItemId());
        }
        List< TaskItem> taskItemList = workItemMapper.findAllWorkItem(tkm.getItemId());

        Map<String,Object> map = Maps.newHashMap();

        map.put("typeId",taskItem.getTypeId());
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
     * 修改功课
     * @param taskItem
     * @return
     */
    @Override
    public Result updateTaskItem(TaskItem taskItem,String typeId,Integer userId) {

        String code=BaseConstants.GWSCODE0000;
        String message=BaseConstants.GWSMSG0000;

        taskItem.setUpdateTime(DateUtil.currentSecond());
        if (null != taskItem.getItemId()){
            if (taskItem.getTypeId() == 10006){
                newResourceMgrMapper.updateBookItem(taskItem);
            }else if (taskItem.getTypeId() == 10007){
                newResourceMgrMapper.updateMeditationItem(taskItem);
            }else {
                taskItemMgrMapper.updateTaskItem(taskItem);
                workItemMapper.deleteWorkItem(taskItem.getItemId());

                //拆分字符串转int存入数据库
                split(typeId,taskItem.getItemId());
            }

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

    /**
     * 功课删除
     * @return
     */
    @Override
    public Result deleteTaskItem(ManageLog manageLog,Integer typeId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != manageLog.getItemId()) {

            ManageLog manageLog1 = null;
            if (typeId == 10006){
                List<Bookcase> bookcases = bookcaseMgrMapper.findBookcaseById(manageLog.getItemId());
                if (0<bookcases.size()){
                    return new Result("1","此经书正在被使用，不能删除",null);
                }
                manageLog1 = newResourceMgrMapper.findByBookId(manageLog.getItemId());
                newResourceMgrMapper.deleteBookItem(manageLog.getItemId());
                newResourceMgrMapper.deleteBookTaskContentByItemId(manageLog.getItemId());
            }

            List<TaskPlan> taskPlan = taskPlanMgrMapper.findAllByItemId(manageLog.getItemId());
            if (0<taskPlan.size()){
                return new Result("1","此功课正在被使用，不能删除",null);
            }
            if (typeId == 10000){
                //日志生成
                manageLog1 = taskItemMgrMapper.findByItemId(manageLog.getItemId());
                //删除功课
                taskItemMgrMapper.deleteTaskItem(manageLog.getItemId());
                taskContentMgrMapper.deleteTaskContentByItemId(manageLog.getItemId());
                taskMusicMgrMapper.deleteTaskmusicByItemId(manageLog.getItemId());
                workItemMapper.deleteWorkItem(manageLog.getItemId());
            }
            if (typeId == 10007){
                List<MeditationPlan> meditationPlans = taskPlanMeditationMgrMapper.findMeditationPlanById(manageLog.getItemId());
                if (0<meditationPlans.size()){
                    return new Result("1","此禅修正在被使用，不能删除",null);
                }
                manageLog1 = newResourceMgrMapper.findByMeditationId(manageLog.getItemId());
                newResourceMgrMapper.deleteMeditationItem(manageLog.getItemId());
                newResourceMgrMapper.deleteMeditationTaskContentByItemId(manageLog.getItemId());

            }

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

        }

        return new Result(code,message,null);
    }

    /**
     * 功课内容添加
     * @param taskContent
     * @return
     */
    @Override
    public Result insertTaskContent(NewResourceContentVO taskContent, Integer userId, Integer typeId) {

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
        taskContent.setUpdateTime(DateUtil.currentSecond());

        if (typeId == 10006){
            newResourceMgrMapper.insertBookContent(taskContent);
        }else if (typeId == 10007){
            newResourceMgrMapper.insertMeditationContent(taskContent);
        }else {
//            taskContentMgrMapper.insertTaskContent(taskContent);
        }
        int itemContentId = taskContentMgrMapper.getLastInsertId();

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
     * 音频添加
     * @param
     * @return
     */
    @Override
    public Result insertTaskMusic(TaskMusicVO taskMusicVO, Integer userId,Integer typeId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (typeId == 10006){
            return new Result("1","经书无音频",null);
        }
        if (null == taskMusicVO.getItemContentId() || null == taskMusicVO.getItemId()){
            return new Result("1","请先添加功课和功课内容",null);
        }

        if (null == taskMusicVO){
            return new Result("1","音频内容为空",null);
        }

        if (typeId == 10000){
            taskMusicMgrMapper.insertTaskMusic(taskMusicVO);
        }
        if (typeId == 10007){
            newResourceMgrMapper.insertMeditationMusic(taskMusicVO);
        }

        if (StringUtils.isNotBlank(taskMusicVO.getValue())){
            int musicId = taskMusicMgrMapper.getLastInsertId();
            taskMusicVO.setResourceId(musicId);
            taskMusicMgrMapper.insertSubResource(taskMusicVO);
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
     * 编辑功课内容
     * @param taskContent
     * @return
     */
    @Override
    public NewResourceContentVO editTaskContent(NewResourceContentVO taskContent) {

        NewResourceContentVO tct = null;
        List<TaskMusic> taskMusicList = new ArrayList<>();
        if (taskContent.getTypeId() == 10006){
            tct = newResourceMgrMapper.findBookContentById(taskContent.getItemContentId());
        }else if (taskContent.getTypeId() == 10007){
            tct = newResourceMgrMapper.findMeditationContentById(taskContent.getItemContentId());
        }else {
//            tct = taskContentMgrMapper.findContentById(taskContent.getItemContentId());
//            taskMusicList = taskMusicMgrMapper.findAllTaskMusicById(tct.getItemContentId());
        }
        if (null != tct){
            tct.setTypeId(taskContent.getTypeId());
        }

        List<TaskMusicVO> taskMusicVOList = new ArrayList<>();
        for (TaskMusic t : taskMusicList){
            TaskMusicVO taskMusicVO = new TaskMusicVO();
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

            taskMusicVOList.add(taskMusicVO);
        }

//        tct.setMusicVos(taskMusicVOList);

        return tct;
    }

    /**
     * 修改功课内容
     * @param taskContent
     * @return
     */
    @Override
    public Result updateTaskContent(NewResourceContentVO taskContent,Integer userId) {

        String code=BaseConstants.GWSCODE0000;
        String message=BaseConstants.GWSMSG0000;

        taskContent.setUpdateTime(DateUtil.currentSecond());
        ManageLog manageLog1 = null;
        if (taskContent.getTypeId() == 10006){
            newResourceMgrMapper.updateBookContent(taskContent);
            manageLog1 = newResourceMgrMapper.findBookByContentId(taskContent.getItemContentId());
        }else if (taskContent.getTypeId() == 10007){
            newResourceMgrMapper.updateMeditationContent(taskContent);
            manageLog1 = newResourceMgrMapper.findMeditationByContentId(taskContent.getContentId());
        }else {
//            taskContentMgrMapper.updateTaskContent(taskContent);
//            manageLog1 =taskContentMgrMapper.findById(taskContent.getItemContentId());
        }

        //日志生成
        ManageLog manageLog = new ManageLog();
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
     * 音频修改
     */
    @Override
    public Result updateTaskMusic(TaskMusicVO taskMusicVO,Integer userId,Integer typeId) {


        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (typeId == 10006){
            return new Result(code,message,null);
        }else if (typeId == 10007){
            return new Result(code,message,null);
        }else {
            taskMusicMgrMapper.updateTaskMusic(taskMusicVO);
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
        manageLog.setMusicName("修改音频："+taskMusicVO.getMusicName());
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
    public Result deleteTaskMusic(TaskMusicVO taskMusicVO,Integer userId,Integer typeId) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != taskMusicVO.getMusicId()){
            //日志打印
            ManageLog manageLog = new ManageLog();
            TaskMusicVO taskMusicVO1 = null;
            if (typeId == 10006){
                return new Result(code,message,null);
            }else if (typeId == 10007){
                return new Result(code,message,null);
            }else {
                taskMusicVO1 = taskMusicMgrMapper.findByMusicId(taskMusicVO.getMusicId());
                manageLog.setItemId(taskMusicVO1.getItemId());
                manageLog.setMusicName("删除音频："+taskMusicVO1.getMusicName());
                manageLog.setItemContentId(taskMusicVO1.getItemContentId());
                taskMusicMgrMapper.deleteTaskmusic(taskMusicVO.getMusicId());
            }
            //查找用户名
            BackUser backUser = backUserMapper.findUserNameById(userId);
            if (backUser !=null){
                manageLog.setUserName(backUser.getUsername());
            }
            manageLog.setCreateTime(DateUtil.currentSecond());
            manageLog.setMusicId(taskMusicVO.getMusicId());
            manageLog.setUserId(userId);
            manageLogMgrMapper.insertAllManageLog(manageLog);
        }else {
            code= BaseConstants.GWSCODE4003;
            message = BaseConstants.GWSMSG4003;
        }
        return new Result(code,message,null);
    }

    /**
     * 删除功课内容
     * @param manageLog
     * @return
     */
    @Override
    public Result deleteTaskContent(ManageLog manageLog,Integer typeId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != manageLog.getItemContentId()) {

            ManageLog manageLog1 = null;
            if (typeId == 10006){
                manageLog1 = newResourceMgrMapper.findBookByContentId(manageLog.getItemContentId());
                manageLog.setItemId(manageLog1.getItemId());
                manageLog.setItemName("删除："+manageLog1.getItemName());
                manageLog.setVersionName("删除内容："+manageLog1.getVersionName());
                newResourceMgrMapper.deleteBookContent(manageLog.getItemContentId());
            }else if (typeId == 10007){
                manageLog1 = newResourceMgrMapper.findMeditationByContentId(manageLog.getItemContentId());
                manageLog.setItemId(manageLog1.getItemId());
                manageLog.setItemName("删除："+manageLog1.getItemName());
                manageLog.setVersionName("删除内容："+manageLog1.getVersionName());

                newResourceMgrMapper.deleteMeditationContent(manageLog.getItemContentId());
            }else {
                manageLog1 = taskContentMgrMapper.findById(manageLog.getItemContentId());
                manageLog.setItemId(manageLog1.getItemId());
                manageLog.setItemName("删除："+manageLog1.getItemName());
                manageLog.setVersionName("删除内容："+manageLog1.getVersionName());
                taskContentMgrMapper.deleteTaskContent(manageLog.getItemContentId());
                taskMusicMgrMapper.deleteTaskMusicByContentId(manageLog.getItemContentId());
            }
            //日志生成
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
        } else {
            code = BaseConstants.GWSCODE2001;
            message = BaseConstants.GWSMSG2001;
        }
        return new Result(code, message, null);
    }

    /**
     * 根据功课ID查找功课内容
     * @param
     * @return
     */
    @Override
    public Result selectContentById(TaskContent taskContent) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String,Object> map = new HashMap<>();
        if (taskContent.getTypeId() == 10006){
            List<TaskContent> taskContents = newResourceMgrMapper.findAllBookContentById(taskContent.getItemId());
            for (TaskContent t : taskContents){
                if (null != t.getYears()){
                    String yearsStr = DateUtil.timestampToDates(t.getYears(),TIME_PATTON_DEFAULT);
                    t.setYearsStr(yearsStr);
                }
            }
            map.put("content",taskContents);
        }else if (taskContent.getTypeId() == 10007){
            List<MeditationContent> contents = newResourceMgrMapper.findAllMeditationContentById(taskContent.getItemId());
            map.put("content",contents);
        }else {
            List<TaskContent> taskContents = taskContentMgrMapper.findAllContentById(taskContent.getItemId());
            for (TaskContent t : taskContents){
                if (null != t.getYears()){
                    String yearsStr = DateUtil.timestampToDates(t.getYears(),TIME_PATTON_DEFAULT);
                    t.setYearsStr(yearsStr);
                }
            }
            map.put("content",taskContents);
        }

        return new Result(code,message,map);
    }

    /**
     * 查询所有功课
     * @return
     */
    @Override
    public Result findAllItemLesson() {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        List<ResourceItemLesson> lessons = resourceItemLessonMgrMapper.findAll();
        Map<String,Object> map = new HashMap<>();
        map.put("lessons",lessons);
        return new Result(code,message,map);
    }

    /**
     * 查询所有功课内容（条件功课id）
     * @param itemId
     * @return
     */
    @Override
    public Result findContentLessonById(Integer itemId) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        List<ResourceContentLesson> contentLessons = resourceContentLessonMgrMapper.findAllContentById(itemId);
        Map<String,Object> map = new HashMap<>();
        map.put("contents",contentLessons);
        return new Result(code,message,map);
    }

}
