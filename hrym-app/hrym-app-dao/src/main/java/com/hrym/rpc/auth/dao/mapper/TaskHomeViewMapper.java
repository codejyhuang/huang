package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.view.TaskHomeView;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功课主页视图mapper
 * Created by mj on 2017/12/12.
 */
public interface TaskHomeViewMapper extends Mapper<TaskHomeView> {

    /**
     * 根据用户id查询可用任务信息
     * @return
     */
    @DataSource("slave")
    @Select("SELECT * FROM " +
            "`task_home_view` " +
            "WHERE uuid=#{uuid} AND is_exit=1 AND type_id!=10006 ORDER BY update_time DESC ")
    List<TaskHomeView> findAllByUuidAndIsExit(Integer uuid);

    /**
     * 根据用户id查询可用任务信息
     * @return
     */
    @DataSource("slave")
    @Select("SELECT * " +
            "FROM `task_home_view` " +
            "WHERE uuid=#{uuid} AND is_exit=1 AND type_id!=10003 AND type_id!=10006 AND type_id!=10007 " +
            "ORDER BY update_time DESC")
    List<TaskHomeView> findByUuidAndIsExit(Integer uuid);
 }
