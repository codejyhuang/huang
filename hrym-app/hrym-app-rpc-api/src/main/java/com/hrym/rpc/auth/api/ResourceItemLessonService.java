package com.hrym.rpc.auth.api;

import com.hrym.rpc.app.common.constant.ResourceItemLessonParam;
import com.hrym.rpc.app.dao.model.VO.lessonVO.ItemLessonVO;
import com.hrym.rpc.app.dao.model.VO.lessonVO.TaskAreaLessonVO;
import com.hrym.rpc.app.dao.model.VO.lessonVO.TaskPlanLessonVO;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceTag;

import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/6/20.
 * 功课接口
 */
public interface ResourceItemLessonService {

    /**
     * 根据功课名称和别名筛选功课
     * @param param
     * @return
     */
    List<ItemLessonVO> search(ResourceItemLessonParam param);

    /**
     * 添加自定义功课
     * @param param
     */
    void customAdd(ResourceItemLessonParam param);

    /**
     * 添加功课任务
     * @param
     */
    void lessonAdd(Integer itemId,Integer itemContentId,String token);

    /**
     * 获取我的功课
     * @return
     */
    List<TaskPlanLessonVO> getMyLesson(ResourceItemLessonParam param);

    /**
     * 更新新添加标签
     * @param param
     */
    void updateTag(ResourceItemLessonParam param);

    /**
     * 推荐功课列表
     * @param param
     * @return
     */
    List<TaskAreaLessonVO> pushArea(ResourceItemLessonParam param);

    /**
     * 全部功课搜索
     * @param param
     * @return
     */
    List<ItemLessonVO> searchLesson(ResourceItemLessonParam param);

    /**
     * 获取标签
     * @param param
     * @return
     */
    List<ResourceTag> getTags(ResourceItemLessonParam param);

    /**
     * 文章阅读人数+1
     * @param param
     */
    void updateOrderNum (ResourceItemLessonParam param);

    /**
     * 做功课报数
     * @param param
     * @return
     */
    Map<String, Object> preLesson(ResourceItemLessonParam param);

}
