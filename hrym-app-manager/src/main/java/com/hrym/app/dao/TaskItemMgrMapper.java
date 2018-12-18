package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.ManageLog;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hrym13 on 2017/8/1.
 */
@Repository
public interface TaskItemMgrMapper {

    /**
     * 查询功课
     */
    @DataSource("slave")
//    @Select("select item_id,item_name,item_intro,order_num,alias_name,title_desc,item_pic,catalogue_id from t_resource_item")
    List<TaskItem> findAllTaskItem();

    /**
     * 通过功课ID查询功课
     */
    @DataSource("slave")
    @Select("select item_id,item_name,is_support,item_intro,order_num,alias_name,title_desc,item_pic,catalogue_id,create_time,update_time " +
            "from t_resource_item where item_id=#{itemId}")
    TaskItem findTaskItemByItemId(Integer itemId);

    /**
     * 通过目录ID查询功课
     */
    @DataSource("slave")
    @Select("SELECT m.item_id,m.item_name,t.type_id FROM t_work_item t left JOIN t_resource_item m ON t.item_id=m.item_id WHERE type_id = #{Id}")
    List<TaskItem> findTaskItemById(@Param("Id")Integer Id);

    /**检索
     * 通过功课名称和目录ID查询功课
     */
    @DataSource("slave")
    List<TaskItem> findTaskItemByName(@Param("itemName") String itemName);

    @DataSource("slave")
    List<TaskItem> searchBycatalogueName(@Param("catalogueName") String catalogueName);


    /**
     * 添加功课
     */
    @Insert("insert into t_resource_item(item_name,item_intro,order_num,alias_name,title_desc,item_pic,catalogue_id,create_time,update_time,is_support)" +
            "values (#{itemName},#{itemIntro},#{orderNum},#{aliasName},#{titleDesc},#{itemPic},#{catalogueId},#{createTime},#{updateTime},#{isSupport})")
    void insertTaskItem(TaskItem taskItem );

    /**
     * 功课修改
     */
    @Update("update t_resource_item set item_name = #{t.itemName},item_intro = #{t.itemIntro}," +
            "alias_name = #{t.aliasName},title_desc = #{t.titleDesc},item_pic = #{t.itemPic},update_time = #{t.updateTime},catalogue_id = #{t.catalogueId},is_support = #{t.isSupport}" +
            " where item_id=#{t.itemId}")
    void updateTaskItem(@Param("t") TaskItem taskItem);

    /**
     * 功课删除
     */
    @Delete("delete from t_resource_item where item_id=#{itemId}")
    void deleteTaskItem(Integer itemId);

    /**
     * 查询最大ID
     *
     * @return
     */
    @DataSource("slave")
    @Select("select max(item_id) from t_resource_item ")
    int findMaxItemId();

    /**
     * 查找最新插的ID
     * @return
     */
    @DataSource("master")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastId();

    @DataSource("slave")
    @Select("SELECT item_id,item_name FROM  t_resource_item WHERE item_id=#{itemId}")
    ManageLog findByItemId(Integer itemId);
}
