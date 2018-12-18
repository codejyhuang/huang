package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.meditation.MeditationPlan;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mj on 2018/6/13.
 */
@Repository
public interface TaskPlanMeditationMgrMapper {

    @DataSource("slave")
    @Select("select * from t_task_plan_meditation where item_id=#{itemId}")
    List<MeditationPlan> findMeditationPlanById(Integer itemId);
}
