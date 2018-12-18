package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.app.dao.BackUserMapper;
import com.hrym.app.service.RoleService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.rpc.app.dao.model.system.BackUser;
import com.hrym.rpc.app.dao.model.system.Role;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.app.util.TreeNode;
import com.hrym.app.dao.MenuMapper;
import com.hrym.app.dao.RoleMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaohan on 2017/10/11.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private BackUserMapper backUserMapper;

    // 查询所有角色
    @Override
    public Result findAllRole(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page, rows);
        List<Role> roles = roleMapper.findAllRole();

        PageInfo pageInfo = new PageInfo(roles);
        List<Map<String, Object>> list = new ArrayList<>();

        if (0<roles.size()){
            for (Role role : roles){
                Map<String,Object> map = Maps.newHashMap();
                map.put("roleId",role.getRoleId());
                map.put("roleName",role.getRoleName());
                map.put("roleDesc",role.getRoleDesc());

                list.add(map);
            }
        }
        long toal=pageInfo.getTotal();
        return new Result(code,message,toal,list);

    }

    //保存用户及对应菜单
    @Override
    public Result saveRole(Role role,String roleMenuIds) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<Role> list=roleMapper.findByName(role.getRoleName());
        if (list.size()!=0){
            return new Result("1","角色名已存在",null);
        }

        roleMapper.saveRole(role);

        Integer roleId = roleMapper.getLastInsertId();
        // 关联菜单
        if (StringUtils.isNotBlank(roleMenuIds)) {
            String[] menuIdArray = roleMenuIds.split(",");
            for (String menuId : menuIdArray) {
                roleMapper.saveRoleMenu(roleId,Integer.parseInt(menuId));
            }
        }
        return new Result(code,message,null);
    }

    @Override
    public List<Role> findRoles() {

        return roleMapper.findRoles();

    }

    //根据用户ID查询对应的菜单
    @Override
    public List<TreeNode> findMenuById(Role role) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

       List<TreeNode> treeNodes = roleMapper.findMenuById(role.getRoleId());



        return treeNodes;

    }

    /**
     * 根据角色ID删除角色内容及其角色菜单表相关内容
     * @param id
     * @return
     */
    @Override
    public Result deleteRoleByRoleId(Integer id) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (id ==null){
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }else {
            List<BackUser> userList = backUserMapper.findBackUserByRoleId(id);
            if (userList.size()==0){
                roleMapper.deleteRoleMenuByRoleId(id);
                roleMapper.deleteRoleByRoleId(id);
            }else {
                return new Result("1","该角色存在用户，不能删",null);
            }

        }

        return new Result(code,message,null);
    }



}