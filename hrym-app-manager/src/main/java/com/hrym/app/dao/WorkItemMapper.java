package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mj on 2017/8/23.
 */
@Repository
public interface WorkItemMapper {

    /**
     *  根据功课ID添加workItem
     */
    @Insert("insert into t_work_item(item_id,type_id) values(#{itemId},#{typeId})")
    void insertWorkItem(@Param("typeId") Integer typeId, @Param("itemId") Integer itemId);

    /**
     * 根据功课ID删除t_work_item内容
     */
    @Delete("delete   from t_work_item where item_id = #{id} ")
    void deleteWorkItem(Integer id);

    /**
     *  根据功课ID查询功课（workitem）所有的内容
     */
    @DataSource("slave")
    @Select("SELECT work_item,item_id,type_id FROM t_work_item WHERE item_id = #{itemId}")
    List<TaskItem> findAllWorkItem(Integer itemId);

    /**
     * 修改workItem
     */
    @Update("update t_work_item set type_id = #{typeId} where item_id = #{itemId}")
    void updateWorkItem(TaskItem taskItem);

    /**
     * 查找workitem中的type_id的值
     * @param id
     * @return
     */
    @Select("SELECT w.item_id,w.type_id,w.work_item FROM t_resource_item m " +
            "LEFT JOIN t_work_item w ON m.item_id=w.item_id " +
            "WHERE m.item_id=#{id} ")
    List<TaskItem> findWorkTypeId( @Param("id") Integer id);

}
