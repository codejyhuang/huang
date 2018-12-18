package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.rpc.app.dao.model.task.TaskRecord;
import org.apache.ibatis.annotations.Insert;

/**
 *
 * Created by mj on 2017/10/30.
 */
public interface TaskRecordMapper extends Mapper<TaskRecord> {

    /**
     * 保存功课记录
     */
    @Insert("insert t_task_record(task_id,user_id,report_num,report_time,item_id,record_method,type_id) " +
            "values(#{taskId},#{userId},#{reportNum},#{reportTime},#{itemId},#{recordMethod},#{typeId})")
    void saveTaskRecord(TaskRecord record);
}

