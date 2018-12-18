package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Article;
import com.hrym.rpc.app.dao.model.association.Association;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 社群mapper
 * Created by hrym13 on 2017/8/24.
 */
@Repository
public interface AssociationMgrMapper {

    /**
     * 查询社群
     */
    @DataSource("slave")
    @Select("SELECT c.idt_association,c.name,c.type,c.create_time,c.intro,c.rq_code_url," +
            "c.avatar_url,c.user_id,c.user_name FROM t_association c")
    List<Association> findAllAssociation ();


    /**
     * 根据社群查询社群人数
     */
    @Select("SELECT COUNT(user_id) FROM t_association_member WHERE idt_association=#{id}")
    int findCountAssociation(Integer id);

    /**
     * 社群名称搜索
     */
    @Select("SELECT idt_association, name,type,create_time,intro,rq_code_url,avatar_url,user_id,user_name " +
            "FROM t_association WHERE name like '%${name}%'")
    List<Association> searchByName(@Param("name")String name);

    /**
     * 根据ID搜索
     * @param idtAssociation
     * @return
     */
    @Select("SELECT idt_association, name,type,create_time,intro,rq_code_url,avatar_url,user_id,user_name " +
            "FROM t_association WHERE idt_association = #{idtAssociation}")
    List<Association> searchByIdtAssociation(@Param("idtAssociation")Integer idtAssociation);

}
