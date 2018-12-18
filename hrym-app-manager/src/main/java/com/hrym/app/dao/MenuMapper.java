package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Association;
import com.hrym.rpc.app.dao.model.system.Menu;
import com.hrym.rpc.app.dao.model.system.MenuList;
import com.hrym.rpc.app.dao.model.system.Role;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.app.util.TreeNode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaohan on 2017/10/10.
 */
@Repository
public interface MenuMapper{

    //查询生成树形菜单
    @DataSource("slave")
    @Select("select menu_id as id ,parent_id as parentId,menu_name as name from t_menu where parent_id=#{parentId}")
    List<TreeNode> findByPid(@Param("parentId") Integer parentId);

    //查询生成树形菜单
    @DataSource("slave")
    @Select("select m.menu_id as id,m.menu_name as name,m.parent_id as parentId from t_menu m " +
            "LEFT JOIN t_role_menu rm ON m.menu_id=rm.menu_id where m.parent_id=#{parentId} and rm.role_id=#{roleId}")
    List<TreeNode> findMenuByPid(@Param("parentId") Integer parentId, @Param("roleId")Integer roleId);

    @DataSource("slave")
    @Select("select menu_id as id ,parent_id as parentId,menu_name as name from t_menu where menu_id=#{id}")
    List<TreeNode> findById(@Param("id") Integer id);


    //查询所有菜单
    @DataSource("slave")
    @Select("SELECT menu_id,menu_name,page,parent_id from t_menu")
    List<Menu> findAllMenu();

    //根据parentId查询名称
    @DataSource("slave")
    @Select("select  menu_name from t_menu where menu_id=#{parentId}")
    String  findNameById(@Param("parentId") String parentId);

    @DataSource("slave")
    @Select("select  menu_name from t_menu where menu_id=#{id}")
    String findName(@Param("id") Integer id);

    @DataSource("slave")
    @Select("SELECT m.menu_id,m.menu_name,m.page FROM t_back_user u " +
            "LEFT JOIN t_role_menu e ON u.role_id=e.role_id " +
            "LEFT JOIN t_menu m ON e.menu_id=m.menu_id WHERE m.parent_id = 0 and u.user_id=#{Id}")
    List<Menu> findMenuPageByUserId(@Param("Id") Integer id);

    @Select("select * from t_menu where parent_id=#{id}")
    List<Menu> findSubMenuPageByParentId(Integer id);

/**
 * 查找父ID为0的菜单内容
 */
//    @DataSource("slave")
//    @Select("select * from t_menu where menu_id=0")
//    List<Menu> findAllByMenuId();
//
//  @Insert("insert into t_menu(menu_id,menu_name,page,menu_desc,parent_id)values() ")
//    void insertAllMenu(Menu menu);


}
