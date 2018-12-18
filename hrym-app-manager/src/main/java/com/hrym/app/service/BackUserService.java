package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.system.BackUser;
import com.hrym.rpc.app.util.Result;

import java.util.Map;

/**
 * Created by xiaohan on 2017/10/11.
 */
public interface BackUserService {

    //查询所有用户
    Result findAllBackUser(Integer page, Integer rows);

    //根据用户名查询
    Result findByName(BackUser backUser,Integer page,Integer rows);

    //更改后台用户状态（逻辑删除）
    Result changeStatus(BackUser backUser);

    /**
     * 后台用户注册
     * @param backUser
     * @return
     */
    Result insertUser(BackUser backUser);

    /**
     * 删除用户表内容
     * @param id
     * @return
     */
    Result deleteBackUserByUserId(Integer id);

    /**
     * 用户角色修改
     * @param id
     * @return
     */
    Result updateRoleId(Integer id,Integer roleId);

    /**
     * 查找所有角色
     * @return
     */
    Result findALLRole();

}
