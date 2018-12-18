package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.system.Menu;
import com.hrym.rpc.app.dao.model.system.Role;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.app.util.TreeNode;

import java.util.List;

/**
 * Created by xiaohan on 2017/10/11.
 */
public interface RoleService {

    //查询所有角色
    Result findAllRole(Integer page, Integer rows);


    //保存用户及对应的菜单
    Result saveRole(Role role,String roleMenuIds);


    //查询所有角色(用于父选框显示)
    List<Role> findRoles();

    List<TreeNode> findMenuById(Role role);

    /**
     * 根据角色ID删除角色表内容
     * @param id
     * @return
     */
    Result deleteRoleByRoleId (Integer id);

}
