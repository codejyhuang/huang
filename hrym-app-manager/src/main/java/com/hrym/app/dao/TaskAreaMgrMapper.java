package com.hrym.app.dao;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskArea;
import com.hrym.rpc.app.dao.model.task.TaskType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by hrym13 on 2018/1/2.
 */
@Repository
public interface TaskAreaMgrMapper extends Mapper<TaskArea> {

    /**
     * 查找所有的功课专区内容
     * @return
     */
    @DataSource("slave")
    @Select("SELECT t.area_id,t.area_name,t.item_id,t.version,t.task_target,t.task_period,t.area_type," +
            "t.create_time,t.update_time,t.type_id,c.version_name FROM" +
            " t_area t LEFT JOIN t_resource_content c ON t.version=c.item_content_id ")
    List<TaskArea> findAllTaskArea();

    /**
     * 根据功课计划ID查找计划内容
     * @param Id
     * @return
     */
    @DataSource("/slave")
    @Select("SELECT t.version_name,a.type_id,a.update_time,a.create_time,a.area_type,a.task_period,a.task_target,a.version,a.item_id,a.area_name,a.area_id,i.item_name" +
            " FROM t_area a LEFT JOIN t_resource_item i ON a.item_id=i.item_id " +
            "LEFT JOIN t_resource_content t ON t.item_content_id=a.version" +
            " WHERE a.area_id=#{Id}")
    TaskArea findTaskAreaById( Integer Id);

}
