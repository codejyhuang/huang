package com.hrym.rpc.auth.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskSubPlan;
import org.apache.ibatis.annotations.Select;

/**
 * 禅修子任务表Mapper
 * Created by mj on 2017/9/6.
 */
public interface TaskSubPlanMapper {

    /**
     * 查询总做完功课数
     * @return
     */
    @DataSource("slave")
    @Select("select sum(down_num) from t_task_sub_plan where task_id=#{taskId}")
    double findSumDownNum(Integer taskId);

}
