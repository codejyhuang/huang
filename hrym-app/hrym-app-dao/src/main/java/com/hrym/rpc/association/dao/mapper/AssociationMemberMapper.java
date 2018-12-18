package com.hrym.rpc.association.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Association;
import com.hrym.rpc.app.dao.model.association.AssociationMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by mj on 2017/8/31.
 */
public interface AssociationMemberMapper {

    /**
     * 通过id查询社群总成员数
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("select count(user_id) from t_association_member where idt_association=#{id}")
    int findTotalMember(Integer id);

    /**
     * 插入社群成员表
     * @param associationMember
     */
    @Insert("insert into t_association_member(idt_association,user_type,user_id,intro) values(#{idtAssociation},#{userType},#{userId},#{intro})")
    void insertAssociationMember(AssociationMember associationMember);

    /**
     * 通过社群ID和成员ID删除成员信息
     * @param id
     * @param userId
     */
    @Delete("delete from t_association_member where idt_association=#{id} and user_id=#{userId}")
    void deleteAssociationMemberById(@Param("id") Integer id,@Param("userId") Integer userId);

    /**
     * 通过社群ID查找对应的所有社群成员
     * @param id
     * @return
     */
    @DataSource("slave")
    List<AssociationMember> findMemberListById(@Param("id") Integer id,@Param("filterStr") String filterStr);

    /**
     * 通过社群ID和用户ID查询用户成员所有信心
     * @param id
     * @param userId
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_association_member where idt_association=#{id} and user_id=#{userId}")
    AssociationMember findMemberByIdAndUserId(@Param("id") Integer id,@Param("userId") Integer userId);
}
