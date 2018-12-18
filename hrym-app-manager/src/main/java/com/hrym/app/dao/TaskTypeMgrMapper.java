package com.hrym.app.dao;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.common.base.baseDao.BaseDao;
import com.hrym.rpc.app.dao.model.task.TaskType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2017/12/6.
 */
@Repository
public interface TaskTypeMgrMapper extends Mapper<TaskType>{

    /**
     * 查找功课类型
     * @return
     */
    @DataSource("slave")
    @Select("SELECT * FROM t_task_type")
    List<TaskType> findAllTaskType();

    /**
     * 根据ID查找功课类型
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT * FROM t_task_type where type_id = #{id}")
    TaskType findByIdTaskType(Integer id);

    @DataSource("slave")
    @Select("SELECT * FROM t_task_type where type_name like '%${typeName}%'")
    List<TaskType> findByTypeNameTaskType(@Param("typeName") String typeName);

    /**
     * 修改功课类型
     * @param taskType
     */
    @Update("update t_task_type set img = #{img},introduceInfo = #{introduceInfo},type_desc = #{typeDesc},type_name = #{typeName} where type_id = #{typeId} ")
    void updateByIdTaskType(TaskType taskType);

    /**
     *添加功课类型
     * @param taskType
     */
    @Insert(" insert into t_task_type(img,introduceInfo,type_desc,type_name)" +
            "values( #{img},#{introduceInfo},#{typeDesc},#{typeName})")
    void insertByIdTaskType(TaskType taskType);

    /**
     * 删除功课类型
     * @param id
     */
    @Delete("delete from t_task_type where type_id = #{id}")
    void deleteByIdTaskType(Integer id);
}
