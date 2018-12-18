package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.system.Menu;
import com.hrym.rpc.app.dao.model.system.MenuList;
import com.hrym.rpc.app.dao.model.system.Role;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.app.util.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaohan on 2017/10/10.
 */
public interface MenuService {

    //查询所有菜单
    Result findAllMenu(Integer page, Integer rows);


    //得到全部的树形菜单
    List<TreeNode> findByPid(Integer parentId);
    List<TreeNode> findMenuByPid(Integer parentId,Role role);
    List<TreeNode> findById(Integer id);

    List<Menu> findMenuPageByUserId(Integer userId);





}
