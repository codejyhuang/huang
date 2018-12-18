package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskPlan;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2017/10/11.
 */
@Repository
public interface TaskPlanMgrMapper {

    @DataSource("slave")
    @Select("SELECT item_id,is_exit,uuid,type_id FROM t_task_plan WHERE is_exit = 1 AND item_id=#{itemId}")
    List<TaskPlan> findAllByItemId(Integer itemId);
}
