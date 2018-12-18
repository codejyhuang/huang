package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.lessonVO.ResourceTagVO;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/7/3.
 */
@Repository
public interface ResourceTagMgrMapper {

    /**
     * 查找标签
     * @param
     * @return
     */
    @DataSource("slave")
    @Select("select tag_id,tag_name,tag_type,tag_weight,create_time,update_time from t_resource_tag ")
    List<ResourceTagVO> findAllTags();

    /**
     * 根据ID查找功课标签
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select(" select tag_id,tag_name,tag_type,tag_weight,create_time,update_time from t_resource_tag where tag_id = #{id} ")
    ResourceTag findResourceTagById(Integer id);

    /**
     * 功课标签录入
     * @param resourceTag
     */
    @Insert(" insert into t_resource_tag " +
            "(tag_name,tag_type,tag_weight,create_time,update_time) " +
            "values" +
            "(#{tagName},#{tagType},#{tagWeight},#{createTime},#{updateTime})")
    void insertResourceTag(ResourceTag resourceTag);

    /**
     * 功课标签更新
     * @param resourceTag
     */
    @Update("update t_resource_tag set " +
            "tag_name = #{tagName},tag_type = #{tagType},tag_weight = #{tagWeight},update_time = #{updateTime} " +
            "where tag_id = #{tagId} ")
    void updateResourceTag(ResourceTag resourceTag);

    /**
     * 功课标签删除
     * @param tagId
     */
    @Delete("delete from t_resource_tag where tag_id = #{tagId}")
    void deleteResourceTagById(Integer tagId);
}
