package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.task.ManageLog;
import com.hrym.rpc.app.dao.model.task.ResourceCatalogue;
import com.hrym.rpc.app.dao.model.task.TaskTree;
import com.hrym.rpc.app.dao.model.task.TaskType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功课类型Mapper
 * Created by mj on 2017/7/21.
 */
@Repository
public interface ResourceCatalogueMapper {

    /**
     * 查询所有功课目录
     * @return
     */
    @DataSource("slave")
    @Select("select catalogue_id,catalogue_name,catalogue_desc,introduceInfo,img,create_time,creator,level from t_resource_catalogue")
    List<ResourceCatalogue> findAllCatalogue();

    /**
     * 查询所有功课目录
     * @return
     */
    @DataSource("slave")
    @Select("select catalogue_id,catalogue_name from t_resource_catalogue where ")
    List<ResourceCatalogue> findAllCatalogueName();

    /**
     * 根据ID查询功课目录
     */
    @Select("SELECT catalogue_id,catalogue_name,level,parent_level,catalogue_desc,introduceInfo,img,creator,create_time,parent_type_id " +
            "FROM t_resource_catalogue " +
            "WHERE catalogue_id = #{id} ")
    ResourceCatalogue findCatalogueById( Integer id);

    /**
     * 查询功课目录(过滤条件：目录ID目录层级)
     */
    @DataSource("slave")
    @Select("SELECT catalogue_id,catalogue_name,level,parent_level,catalogue_desc,introduceInfo,img,creator,create_time,parent_type_id " +
            "FROM t_resource_catalogue " +
            "WHERE catalogue_id = #{id} or level = #{level} ")
    List<ResourceCatalogue> searchCatalogueById(@Param("id") Integer id,@Param("level")Integer level);

    /**
     * 目录名称检索
     * @param catalogueName
     */
    @DataSource("slave")
    @Select("SELECT catalogue_id,catalogue_name,level,parent_level,catalogue_desc,introduceInfo,img,creator,create_time,parent_type_id " +
            "FROM t_resource_catalogue " +
            "WHERE  catalogue_name like '%${catalogueName}%'")
    List<ResourceCatalogue> searchCatalogueByName(@Param("catalogueName") String catalogueName );

    /**
     * 根据目录ID查找目录名称《日志表专用》
     * @param catalogueId
     * @return
     */
    @DataSource("slave")
    @Select("SELECT catalogue_id,catalogue_name FROM t_resource_catalogue WHERE catalogue_id=#{catalogueId}")
    ManageLog findByCatalogueId(Integer catalogueId);

    /**
     * 删除功课目录
     */
    @Delete("delete  from t_resource_catalogue where catalogue_id=#{id}")
    void deleteCatalogue(Integer id);

    /**
     * 添加功课目录
     */
    @Insert("insert into t_resource_catalogue (catalogue_id,catalogue_name,create_time,creator,level,catalogue_desc,img,parent_type_id,parent_level,introduceInfo)values(#{catalogueId},#{catalogueName},#{createTime},#{creator},#{level},#{catalogueDesc},#{img},#{parentTypeId},#{parentLevel},#{introduceInfo})")
    void insertCatalogue(ResourceCatalogue rc);

    /**
     * 更新目录
     */
    @Update("update t_resource_catalogue set catalogue_name = #{catalogueName},catalogue_desc = #{catalogueDesc},parent_type_id = #{parentTypeId},introduceInfo = #{introduceInfo}," +
            "img = #{img},creator = #{creator} where catalogue_id = #{catalogueId}")
    void updateResourceCatalogue (ResourceCatalogue resourceCatalogue);

    /**
     * 通过父目录ID查询目录ID
     * @param id
     * @return
     */
    @Select("select max(catalogue_id) from t_resource_catalogue where parent_type_id=#{id}")
    int findMaxId(Integer id);

    @Select("select catalogue_id as id,catalogue_name as text,parent_type_id as pid from t_resource_catalogue where parent_type_id=#{pid}")
    List<TaskTree> findByPid(Integer pid);

    @Select("select catalogue_id as id,catalogue_name as text,parent_type_id from t_resource_catalogue where catalogue_id=#{id}")
    List<TaskTree> findById(Integer id);

    @Select("select level from t_resource_catalogue where catalogue_id=#{id}")
    int findlevelById(Integer id);
}
