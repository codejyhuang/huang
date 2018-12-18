package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功课类型Mapper
 * Created by mj on 2017/7/7.
 */
public interface TaskTypeMapper extends Mapper<TaskType> {

    /**
     * 查询所有功课类型
     * @return
     */
    @DataSource("slave")
    List<TaskType> findTaskTypes();

    /**
     * 通过id查询功课类型
     * @param typeId
     * @return
     */
    @DataSource("slave")
    TaskType findTaskTypeById(Integer typeId);

    /**
     * 通过ID和名称模糊查询
     * @param typeId
     * @param itemName
     * @return
     */
    @DataSource("slave")
    List<TaskType> findTaskItemById(@Param("typeId") Integer typeId,@Param("itemName") String itemName);
}
