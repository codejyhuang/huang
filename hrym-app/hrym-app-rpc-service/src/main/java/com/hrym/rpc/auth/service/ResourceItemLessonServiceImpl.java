package com.hrym.rpc.auth.service;

import com.github.pagehelper.PageHelper;
import com.hrym.common.enums.TaskAreaEnum;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.TokenUtil;
import com.hrym.rpc.app.common.constant.ResourceItemLessonParam;
import com.hrym.rpc.app.dao.model.VO.customVO.TaskPlanCustomVO;
import com.hrym.rpc.app.dao.model.VO.lessonVO.ContentLessonVO;
import com.hrym.rpc.app.dao.model.VO.lessonVO.ItemLessonVO;
import com.hrym.rpc.app.dao.model.VO.lessonVO.TaskAreaLessonVO;
import com.hrym.rpc.app.dao.model.VO.lessonVO.TaskPlanLessonVO;
import com.hrym.rpc.app.dao.model.association.ResourceArticle;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.app.dao.model.task.custom.ResourceItemCustom;
import com.hrym.rpc.app.dao.model.task.custom.TaskPlanCustom;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceTag;
import com.hrym.rpc.app.dao.model.task.lesson.TaskPlanLesson;
import com.hrym.rpc.association.dao.mapper.ResourceArticleMapper;
import com.hrym.rpc.auth.api.ResourceItemLessonService;
import com.hrym.rpc.auth.dao.mapper.custom.ResourceItemCustomMapper;
import com.hrym.rpc.auth.dao.mapper.custom.TaskPlanCustomMapper;
import com.hrym.rpc.auth.dao.mapper.lesson.ResourceContentLessonMapper;
import com.hrym.rpc.auth.dao.mapper.lesson.ResourceItemLessonMapper;
import com.hrym.rpc.auth.dao.mapper.lesson.ResourceTagMapper;
import com.hrym.rpc.auth.dao.mapper.lesson.TaskAreaLessonMapper;
import com.hrym.rpc.auth.dao.mapper.lesson.TaskPlanLessonMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/6/20.
 * 功课接口
 */
public class ResourceItemLessonServiceImpl implements ResourceItemLessonService {

    @Autowired
    private ResourceItemLessonMapper itemLessonMapper;
    @Autowired
    private ResourceContentLessonMapper contentLessonMapper;
    @Autowired
    private TaskPlanCustomMapper taskPlanCustomMapper;
    @Autowired
    private TaskPlanLessonMapper taskPlanLessonMapper;
    @Autowired
    private ResourceItemCustomMapper resourceItemCustomMapper;
    @Autowired
    private ResourceTagMapper resourceTagMapper;
    @Autowired
    private TaskAreaLessonMapper taskAreaLessonMapper;
    @Autowired
    private ResourceArticleMapper resourceArticleMapper;


    /**
     * 功课是否已添加：1:已添加；0：未添加
     *
     * @param itemId
     * @param userId
     * @return
     */
    public boolean isItemAdd(Integer itemId, Integer userId) {

        TaskPlanLesson taskPlanLesson = taskPlanLessonMapper.findLessonByItemIdAndUuid(itemId, userId);

        if (taskPlanLesson == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 功课内容是否已添加
     *
     * @param itemId
     * @param userId
     * @return
     */
    public boolean isContentAdd(Integer itemId, Integer userId, Integer itemContentId) {

        TaskPlanLesson taskPlanLesson = taskPlanLessonMapper.findLessonByItemIdAndUuidAndContendId(itemId, userId, itemContentId);

        if (taskPlanLesson == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据功课名称和别名筛选功课
     *
     * @param param
     * @return
     */
    @Override
    public List<ItemLessonVO> search(ResourceItemLessonParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());

        if (user == null) {
            user = new UserInfo();
        }
        PageHelper.startPage(1, 3);

        List<ItemLessonVO> itemList = itemLessonMapper.findTaskItemByItemName(param.getCustomName());

        for (ItemLessonVO itemLesson : itemList) {

            List<ContentLessonVO> contentList = contentLessonMapper.findContentLessonByItemId(itemLesson.getItemId());
            if (contentList.size() < 2) {
                // 功课是否已添加：1:已添加；0：未添加
                boolean isItemAdd = isItemAdd(itemLesson.getItemId(), user.getUuid());

                if (isItemAdd) {

                    itemLesson.setIsItemAdd(1);
                }
            }
            // 功课内容集合
            itemLesson.setContentList(contentList);

            for (ContentLessonVO content : contentList) {
                // 内容是否已添加：1:已添加；0：未添加
                boolean isContentAdd = isContentAdd(content.getItemId(), user.getUuid(), content.getItemContentId());

                if (isContentAdd) {

                    content.setIsContentAdd(1);
                }
            }
        }

        return itemList;
    }

    /**
     * 添加自定义功课
     *
     * @param param
     */
    @Override
    public void customAdd(ResourceItemLessonParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());

        if (user == null) {
            user = new UserInfo();
        }
        // 自定义功课创建
        ResourceItemCustom itemCustom = new ResourceItemCustom();

        itemCustom.setCreateTime(DateUtil.currentSecond());
        itemCustom.setUpdateTime(DateUtil.currentSecond());
        itemCustom.setUserId(user.getUuid());
        itemCustom.setCustomName(param.getCustomName());

        resourceItemCustomMapper.insertResourceItemCustom(itemCustom);
        //获取插入的自定义功课ID
        int lastId = resourceItemCustomMapper.getLastId();

        // 创建自定义任务
        TaskPlanCustom taskPlanCustom = new TaskPlanCustom();

        taskPlanCustom.setCreateTime(DateUtil.currentSecond());
        taskPlanCustom.setUpdateTime(DateUtil.currentSecond());
        taskPlanCustom.setCustomId(lastId);
        taskPlanCustom.setUuid(user.getUuid());
        // 1表示最近添加  0表示已点击过
        taskPlanCustom.setRecentAdd(1);
        // 是否删除0：已删除；1：未删除
        taskPlanCustom.setIsExit(1);

        taskPlanCustomMapper.insertTaskPlanCustom(taskPlanCustom);
    }

    /**
     * 功课任务创建
     *
     * @param
     */
    @Override
    public void lessonAdd(Integer itemId, Integer itemContentId, String token) {

        UserInfo user = TokenUtil.getUserByToken(token);
        boolean folat = isContentAdd(itemId, user.getUuid(), itemContentId);
        if (folat == false) {

            // 正常功课任务添加
            TaskPlanLesson taskPlanLesson = new TaskPlanLesson();

            taskPlanLesson.setUuid(user.getUuid());
            taskPlanLesson.setItemId(itemId);
            taskPlanLesson.setItemContentId(itemContentId);
            taskPlanLesson.setCreateTime(DateUtil.currentSecond());
            taskPlanLesson.setUpdateTime(DateUtil.currentSecond());
            // 1表示最近添加  0表示已点击过
            taskPlanLesson.setRecentAdd(1);
            // 是否删除0：已删除；1：未删除
            taskPlanLesson.setIsExit(1);

            taskPlanLessonMapper.insertTaskPlanLesson(taskPlanLesson);
            // 更新功课表里的定制数量和正在做的人数
            itemLessonMapper.updateLessonOrderNumAndOnlineNum(1, 1, itemId);
            contentLessonMapper.updateLessonOrderNumAndOnlineNum(1, 1, itemContentId);
        }
    }

    /**
     * 获取我的功课
     *
     * @return
     */
    @Override
    public List<TaskPlanLessonVO> getMyLesson(ResourceItemLessonParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());
        List<TaskPlanLessonVO> lessonVOS = taskPlanLessonMapper.findAllByUuid(user.getUuid());
        for (TaskPlanLessonVO vo : lessonVOS) {
            String updateTime = DateUtil.timestampToDates(vo.getUpdateTime(), DateUtil.TIME_PATTON_DEFAULT);
            vo.setLastReportTime(updateTime);
        }
        return lessonVOS;
    }

    /**
     * 更新新添加标签
     *
     * @param param
     */
    @Override
    public void updateTag(ResourceItemLessonParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());

        // 更新自定义功课表签 1表示最近添加  0表示已点击过
        if (param.getTypeId() == 10010) {
            taskPlanCustomMapper.updateTaskPlanCustomByTaskId(0, param.getTaskId(), user.getUuid());

        } else {
            //  1表示最近添加  0表示已点击过
            taskPlanLessonMapper.updateTaskPlanLessonByTaskId(0, param.getTaskId(), user.getUuid());

        }
    }

    /**
     * 推荐功课列表
     *
     * @param param
     * @return
     */
    @Override
    public List<TaskAreaLessonVO> pushArea(ResourceItemLessonParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());
        if (user == null) {
            user = new UserInfo();
        }
        List<TaskAreaLessonVO> taskAreaList = null;

        if (TaskAreaEnum.RECOMMEND_TASK_AREA.getType() == param.getType()) {

            // 推荐功课专区
            PageHelper.orderBy("a.create_time desc");
            taskAreaList = taskAreaLessonMapper.findTaskAreaLessonByAreaType(param.getType());
            if (taskAreaList != null) {
                for (TaskAreaLessonVO task : taskAreaList) {

                    ResourceArticle articles = resourceArticleMapper.findResourceArticle(task.getItemId());

                    if (articles != null) {

                        task.setItemPic(articles.getArticlePic());
                        task.setOrderNum(articles.getOrderNum());
                        task.setArticleUrl(articles.getArticleUrl());
                        task.setResourceArticle(articles);
                        task.setItemId(articles.getArticleId());
                        task.setTitleName(task.getTitleName());
                        task.setTitleDesc(task.getTitleDesc());
                    }
                }
            }
            return taskAreaList;

        } else {
            if (TaskAreaEnum.COMMON_TASK_AREA.getType() == param.getType()) {

                // 常用功课
                PageHelper.startPage(1, 4);
                PageHelper.orderBy("a.create_time desc");
            } else if (TaskAreaEnum.HOT_TASK_AREA.getType() == param.getType()) {

                // 热门功课
                PageHelper.orderBy("n.order_num desc");
            } else if (TaskAreaEnum.BSA_TASK_AREA.getType() == param.getType()) {

            }
            taskAreaList = taskAreaLessonMapper.findTaskAreaLessonByAreaType(param.getType());

            if (taskAreaList != null) {
                for (TaskAreaLessonVO taskAreaLessonVO : taskAreaList) {

                    List<ContentLessonVO> contentList = contentLessonMapper.findContentLessonByItemId(taskAreaLessonVO.getItemId());

                    if (contentList.size() < 2) {

                        // 功课是否已添加：1:已添加；0：未添加
                        boolean isItemAdd = isItemAdd(taskAreaLessonVO.getItemId(), user.getUuid());

                        if (isItemAdd) {

                            taskAreaLessonVO.setIsItemAdd(1);
                        }
                    }
                    // 功课内容集合
                    taskAreaLessonVO.setContentList(contentList);

                    for (ContentLessonVO content : contentList) {
                        // 内容是否已添加：1:已添加；0：未添加
                        boolean isContentAdd = isContentAdd(content.getItemId(), user.getUuid(), content.getItemContentId());

                        if (isContentAdd) {

                            content.setIsContentAdd(1);
                        }
                    }
                }
            }
            return taskAreaList;
        }
    }


    /**
     * 全部功课搜索
     *
     * @param param
     * @return
     */
    @Override
    public List<ItemLessonVO> searchLesson(ResourceItemLessonParam param) {
        UserInfo user = TokenUtil.getUserByToken(param.getToken());
        if (user == null) {
            user = new UserInfo();
        }
        List<ItemLessonVO> itemList = null;
        if (param.getType() == 0) {
            //分解标签
            String tags = param.getTagsName();
            String tag1 = "";
            String tag2 = "";
            String tag3 = "";
            if (StringUtils.isNotBlank(tags)) {
                String[] strings = tags.split(",");
                if (strings.length > 0) {
                    tag1 = strings[0];
                }
                if (strings.length > 1) {
                    tag2 = strings[1];
                }
                if (strings.length > 2) {
                    tag2 = strings[2];
                }
            }
            PageHelper.startPage(param.getPageNo(), param.getPageSize());
            itemList = itemLessonMapper.findTaskItemByTags(tag1, tag2, tag3);
        } else {
            PageHelper.startPage(param.getPageNo(), param.getPageSize());
            itemList = itemLessonMapper.findTaskItemByItemName(param.getFilterStr());
        }
        for (ItemLessonVO lessonVO : itemList) {
            List<ContentLessonVO> contentList = contentLessonMapper.findContentLessonByItemId(lessonVO.getItemId());
            if (contentList.size() < 2) {
                // 功课是否已添加：1:已添加；0：未添加
                boolean isItemAdd = isItemAdd(lessonVO.getItemId(), user.getUuid());
                if (isItemAdd) {
                    lessonVO.setIsItemAdd(1);
                }
            }
            //功课内容集合
            lessonVO.setContentList(contentList);
            for (ContentLessonVO content : contentList) {
                // 内容是否已添加：1:已添加；0：未添加
                boolean isContentAdd = isContentAdd(content.getItemId(), user.getUuid(), content.getItemContentId());
                if (isContentAdd) {
                    content.setIsContentAdd(1);
                }
            }
        }

        return itemList;
    }

    /**
     * 获取标签
     *
     * @param param
     * @return
     */
    @Override
    public List<ResourceTag> getTags(ResourceItemLessonParam param) {
        List<ResourceTag> tags = resourceTagMapper.findTagsByTagType(param.getType());
        return tags;
    }

    /**
     * 文章阅读人数+1
     *
     * @param param
     */
    @Override
    public void updateOrderNum(ResourceItemLessonParam param) {

        resourceArticleMapper.updateResourceArticleOrderNum(param.getItemId());

    }

    /**
     * 功课准备页
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> preLesson(ResourceItemLessonParam param) {

        UserInfo user = TokenUtil.getUserByToken(param.getToken());
        if (user == null) {
            user = new UserInfo();
        }
        Map<String,Object> map = new HashMap<>();
        // 自定义功课准备页
        if (param.getTypeId() == 10010) {

            TaskPlanCustomVO custom = taskPlanCustomMapper.CustomNumberStart(param.getTaskId());

            map.put("itemPic",custom.getItemPic());
            map.put("todayCommitNum",custom.getTodayCommitNum());
            return map ;

        } else {

            return null;
        }
    }


}
