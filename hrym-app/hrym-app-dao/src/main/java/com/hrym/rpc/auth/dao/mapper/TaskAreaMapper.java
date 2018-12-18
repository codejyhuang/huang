package com.hrym.rpc.auth.dao.mapper;

import com.github.abel533.mapper.Mapper;
import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.TaskArea;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mj on 2018/1/2.
 */
public interface TaskAreaMapper extends Mapper<TaskArea> {

    /**
     * 根据专区类型查询专区信息 关联功课类型表
     * @param type
     * @return
     */
    @DataSource("slave")
    @Select("select a.area_id,a.area_name,a.type_id,a.area_type,b.type_desc,b.type_name,c.unit_desc,d.item_name " +
            "from t_area a,t_task_type b,t_task_unit c,t_resource_item d " +
            "where area_type=#{type} and a.type_id=b.type_id and a.type_id=c.unit_type_id and a.item_id=d.item_id and a.type_id!=10006")
    List<TaskArea> findTaskAreaByAreaType(Integer type);

    /**
     * 根据专区类型查询专区信息 并计算该功课历史和在线人数
     * @param type
     * @return
     */
    @DataSource("slave")
    @Select("SELECT *,b.unit_desc,c.type_desc,c.type_name,d.item_name," +
            "(SELECT COUNT(distinct uuid) FROM `t_task_plan` WHERE is_exit=1 AND item_id=a.item_id AND type_id=a.type_id)AS currentPeopleNum," +
            "(SELECT COUNT(distinct uuid) FROM `t_task_plan` WHERE item_id=a.item_id AND type_id=a.type_id)AS donePeopleNum\n" +
            "FROM`t_area`AS a left join t_task_unit b on a.type_id=b.unit_type_id " +
            "left join t_task_type c on a.type_id=c.type_id " +
            "left join t_resource_item d on a.item_id=d.item_id " +
            "where area_type=#{type} and a.type_id!=10006")
    List<TaskArea> findTaskAreaByAreaTypeAndUuid(@Param("type") Integer type);

    /**
     * 查找热门功课补足功课专区20个
     * @return
     */
    @DataSource("slave")
    @Select("SELECT a.item_content_id as version,a.item_id,a.type_id,a.uuid,a.is_exit,b.unit_desc,c.type_desc,c.type_name,d.item_name,d.item_name AS area_name,COUNT(distinct uuid) AS donePeopleNum\n" +
            "FROM`t_task_plan` a\n" +
            "left join t_task_unit b on a.type_id=b.unit_type_id\n" +
            "left join t_task_type c on a.type_id=c.type_id \n" +
            "left join t_resource_item d on a.item_id=d.item_id\n" +
            "WHERE a.item_id is not null and (a.type_id!=10007 and a.type_id!=10002 and a.type_id!=10003 and a.type_id!=10006 OR d.item_name='禅修') and a.item_id not in (SELECT item_id FROM `t_area` WHERE type_id=a.type_id)\n" +
            "GROUP BY a.item_id,a.type_id ORDER BY donePeopleNum DESC")
    List<TaskArea> findHotTask();

    /**
     * 查找当前使用人数
     * @param itemId
     * @param typeId
     * @return
     */
    @DataSource("slave")
    @Select("SELECT COUNT(distinct uuid) FROM `t_task_plan` WHERE is_exit=1 AND item_id=#{itemId} AND type_id=#{typeId}")
    int getCurrentPeopleNum(@Param("itemId") Integer itemId,@Param("typeId") Integer typeId);
}
