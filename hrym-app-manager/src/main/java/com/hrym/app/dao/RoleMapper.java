package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Article;
import com.hrym.rpc.app.dao.model.system.Menu;
import com.hrym.rpc.app.dao.model.system.Role;
import com.hrym.rpc.app.util.TreeNode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaohan on 2017/10/11.
 */
@Repository
public interface RoleMapper {


     // 插入后查找上一个ID

    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();

    //查询所有菜单
    @DataSource("slave")
    @Select("SELECT role_id,role_name,role_desc from t_role")
    List<Role> findAllRole();

    //根据ID查询角色名
    @DataSource("slave")
    @Select("select role_name from t_role where role_id=#{roleId}")
    String findNameById(@Param("roleId") Integer roleId);

    //保存角色及对应的菜单
    @Insert("insert into t_role(role_id,role_name,role_desc) value(#{roleId},#{roleName},#{roleDesc}) ")
    void saveRole(Role role);

    @DataSource("slave")
    @Select("select role_id,role_name from t_role")
    List<Role> findRoles();

    //根据用户ID查询对应的菜单
    @DataSource("slave")
    @Select("select m.menu_id as id,m.page as page,m.menu_name as name,m.parent_id as parentId from t_menu m LEFT JOIN t_role_menu rm ON m.menu_id=rm.menu_id WHERE rm.role_id=#{roleId}")
    List<TreeNode> findMenuById(@Param("roleId") Integer roleId);

    //将用户id，菜单id存入t_role_menu
    @Insert("insert into t_role_menu(role_id,menu_id) value(#{roleId},#{menuId}) ")
    void saveRoleMenu(@Param("roleId")Integer roleId, @Param("menuId")Integer menuId);

    @DataSource("slave")
    @Select("select role_id,role_name,role_desc from t_role where role_name=#{roleName}")
    List<Role> findByName( String roleName);


    /**
     * 根据角色ID删除角色内容
     * @param id
     */
    @DataSource("slave")
    @Delete("delete from t_role where role_id = #{id}")
    void deleteRoleByRoleId(Integer id);

    /**
     * 根据角色ID删除角色菜单表的内容t_role_menu
     * @param id
     */
    @Delete("delete from t_role_menu where role_id = #{id}")
    void deleteRoleMenuByRoleId(Integer id);
}
