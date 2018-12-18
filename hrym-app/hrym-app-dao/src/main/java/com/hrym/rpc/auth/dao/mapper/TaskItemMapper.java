package com.hrym.rpc.auth.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import com.hrym.rpc.app.dao.model.view.TaskItemView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功课Mapper
 * Created by mj on 2017/7/8.
 */
public interface TaskItemMapper {

    /**
     * 通过功课id查询功课信息
     * @param itemId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_resource_item where item_id=#{itemId}")
    TaskItem findTaskByItemId(Integer itemId);

    /**
     * 查询所有功课信息
     * @return
     */
    @DataSource("slave")
    TaskItem findTaskItemById(@Param("itemId") Integer itemId,@Param("typeId") Integer typeId);

    /**
     * 根据ID更新订制数量
     * @param itemId
     */
    void updateOrderNumById(Integer itemId);

    /**
     * 查询所有功课（通过最新时间排序）
     * @return
     */
    @DataSource("slave")
    List<TaskItem> findItemOrderByTime(@Param("filterStr") String filterStr);

    /**
     * 查询类型为经书、咒语、佛号、 音乐的所有功课（通过最新时间排序）
     * @return
     */
    @DataSource("slave")
    @Select("select item_id,item_name,item_intro,title_desc,item_pic,type_id,type_name,music_name,music_file," +
            "music_subtitle,album_name,music_id,music_pic from resource_search_view " +
            "where item_name like '%${filterStr}%' or title_desc like '%${filterStr}%' or album_name like '%${filterStr}%' order by is_support DESC,item_id ASC")
    List<TaskItemView> findAllItemOrderByTime(@Param("filterStr") String filterStr);

    /**
     * 根据功课名称模糊查询所有功课（通过最新时间排序）
     * @return
     */
    @DataSource("slave")
    @Select("select a.item_id,a.item_name,a.item_intro,a.item_pic,a.title_desc,b.type_id,c.type_name,c.introduceInfo " +
            "from t_resource_item a,t_work_item b,t_task_type c " +
            "where a.item_id=b.item_id and b.type_id=c.type_id and b.type_id!=10003 and b.type_id!=10006 and b.type_id!=10008 and (b.type_id!=10007 OR a.item_name='禅修') and a.item_name like '%${itemName}%'")
    List<TaskItem> findItemLikeName(@Param("itemName") String itemName);

}
