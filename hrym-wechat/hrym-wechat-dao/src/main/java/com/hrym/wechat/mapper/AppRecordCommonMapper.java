package com.hrym.wechat.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.wechat.entity.AppRecordCommon;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/4/14.
 */
@Repository
public interface AppRecordCommonMapper {


    /**
     * 删除app 中的 record 记录
     *
     * @param parentId
     */
    void removeByPrimaryKey(@Param("parentId") Integer parentId, @Param("tableName") String tableName);

    /**
     * 根据recordId  查询 taskId
     *
     * @param parentId
     * @param tableName
     */
    Integer queryTaskIdByRecordId(@Param("parentId") Integer parentId, @Param("tableName") String tableName);

    /**
     * 查询taskRecord  今日报数量
     *
     * @param taskId
     * @param timeType
     * @param format
     * @return
     */
    @DataSource("slave")
    Long queryByUuidAndItemIdAndItemIdAndTypeAndTableName(@Param("taskId") Integer taskId,
                                                          @Param("timeType") Integer timeType,
                                                          @Param("format") Integer format,
                                                          @Param("tableName") String tableName);


    /**
     * 查询 record   from  t_task_record_lesson
     *
     * @param taskId
     * @return
     */
    @DataSource("slave")
    List<AppRecordCommon> queryRecordByTaskId(@Param("taskId") Integer taskId,
                                              @Param("tableName") String tableName);

}
