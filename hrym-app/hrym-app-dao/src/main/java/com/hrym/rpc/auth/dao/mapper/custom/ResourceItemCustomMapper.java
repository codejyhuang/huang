package com.hrym.rpc.auth.dao.mapper.custom;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import com.hrym.rpc.app.dao.model.task.custom.ResourceItemCustom;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceItemLesson;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hrym13 on 2018/6/20.
 * 自定义功课Mapper
 */
public interface ResourceItemCustomMapper {

    /**
     * 自定义功课添加
     * @param resourceItemCustom
     */
    @Insert(" insert into t_resource_item_custom " +
            "(custom_name,user_id,create_time,update_time)" +
            " values " +
            "(#{customName},#{userId},#{createTime},#{updateTime})")
    void insertResourceItemCustom(ResourceItemCustom resourceItemCustom);

    /**
     * 查找最新插的ID
     * @return
     */
    @DataSource("master")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastId();
}
