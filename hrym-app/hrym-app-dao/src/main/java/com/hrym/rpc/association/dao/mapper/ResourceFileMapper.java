package com.hrym.rpc.association.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.ResourceFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 资源文件Mapper
 * Created by mj on 2017/9/14.
 */
public interface ResourceFileMapper {

    /**
     * 保存资源文件
     * @param resourceFile
     */
    @Insert("insert into t_resource_file(resource_name,resource_url,resource_role,resource_desc,resource_display_name,association_id,visit_times,down_times," +
            "create_time,create_user_id,create_user_name,resource_type) values(#{r.resourceName},#{r.resourceUrl},#{r.resourceRole}," +
            "#{r.resourceDesc},#{r.resourceDisplayName},#{r.associationId},#{r.visitTimes},#{r.downTimes},#{r.createTime},#{r.createUserId},#{r.createUserName},#{r.resourceType})")
    void saveResourceFile(@Param("r") ResourceFile resourceFile);

    /**
     * 通过社群ID查询资源文件（过滤字段资源名称）
     * @param id
     * @param filterStr
     * @return
     */
    @DataSource("slave")
    @Select("select idt_resource,resource_name,resource_url,resource_role,resource_desc,create_time,create_user_name,resource_type " +
            "from t_resource_file where association_id=#{id} and resource_name like '%${filterStr}%}' order by create_time DESC")
    List<ResourceFile> findResourceFileByAssociationId(@Param("id") Integer id,@Param("filterStr") String filterStr);

}
