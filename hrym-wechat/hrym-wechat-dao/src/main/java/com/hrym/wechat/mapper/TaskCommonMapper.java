package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.CountRecord;
import com.hrym.wechat.entity.TaskCommon;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/4/14.
 */
@Repository
public interface TaskCommonMapper {


    /**
     * 根据功课 去查询 任务信息
     *
     * @param type
     * @param itemId
     * @param itemContentId
     * @param uUid
     * @param tableName
     * @param isExist
     * @return
     */
    @DataSource("slave")
    int queryCountByItemIdAndItemContentIdAndTableNameAndUuid(@Param("type") Integer type,
                                                              @Param("itemId") Integer itemId,
                                                              @Param("itemContentId") Integer itemContentId,
                                                              @Param("uuid") Integer uUid,
                                                              @Param("tableName") String tableName,
                                                              @Param("isExist") Integer isExist);

    /**
     * 添加  task 记录
     *
     * @param taskCommon
     * @param tableName
     */
    void insert(@Param("task") TaskCommon taskCommon, @Param("tableName") String tableName, @Param("type") Integer type);

    /**
     * 更新功课的任务
     *
     * @param type
     * @param itemId
     * @param itemContentId
     * @param uUid
     * @param tableName
     */
    void updateBookIsExist(@Param("type") Integer type,
                           @Param("itemId") Integer itemId,
                           @Param("itemContentId") Integer itemContentId,
                           @Param("uuid") Integer uUid,
                           @Param("tableName") String tableName);

    /**
     * 更新功课的任务
     *
     * @param type
     * @param itemId
     * @param itemContentId
     * @param uUid
     * @param tableName
     */
    void updateIsExist(@Param("type") Integer type,
                       @Param("itemId") Integer itemId,
                       @Param("itemContentId") Integer itemContentId,
                       @Param("uuid") Integer uUid,
                       @Param("tableName") String tableName);


    /**
     * 更新任务 doneNum
     *
     * @param taskId
     * @param num
     * @param tableName
     * @param type
     */
    void updateDoneNumByTaskId(@Param("taskId") Integer taskId,
                               @Param("num") Integer num,
                               @Param("tableName") String tableName,
                               @Param("type") Integer type,
                               @Param("isRemove")Integer isRemove);

    /**
     * 我的功课  查询用户经书 task
     *
     * @param userId
     * @return
     */
    List<TaskCommon> queryTaskByUuidFromBookCase(@Param("userId") Integer userId,
                                                 @Param("currentSecond") Integer currentSecond,
                                                 @Param("taskId") Integer taskId);

    /**
     * 我的功课  查询系统  自建功课 task
     *
     * @param userId
     * @return
     */
    List<TaskCommon> queryTaskByUuidFromView(@Param("userId") Integer userId,
                                             @Param("taskId") Integer taskId);

    /**
     * 主键更新  任务 task 的状态
     * @param taskId
     * @param state
     */
    void updateStateByPrimaryKey(@Param("taskId") Integer taskId,
                                 @Param("state") int state,
                                 @Param("type")Integer type,
                                 @Param("tableName")String tableName);
}
