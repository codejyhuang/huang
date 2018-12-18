package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.MD5Util;
import com.hrym.rpc.app.dao.model.system.BackUser;
import com.hrym.rpc.app.dao.model.system.Role;
import com.hrym.rpc.app.util.Result;
import com.hrym.app.service.BackUserService;
import com.hrym.app.dao.BackUserMapper;
import com.hrym.app.dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2017/10/11.
 */
@Service
public class BackUsererviceImpl implements BackUserService {

    @Autowired
    private BackUserMapper backUserMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Result findAllBackUser(Integer page, Integer rows) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page, rows);
        List<BackUser> backUsers = backUserMapper.findAllBackUser();

        PageInfo pageInfo = new PageInfo(backUsers);
        List<Map<String, Object>> list = new ArrayList<>();

        if (0<backUsers.size()){
            for (BackUser bu : backUsers){
                Map<String,Object> map = Maps.newHashMap();
                map.put("userId",bu.getUserId());
                map.put("username",bu.getUsername());
                map.put("realname",bu.getRealname());
                map.put("userRole",roleMapper.findNameById(bu.getRoleId()));
                map.put("gender",bu.getGender());
                map.put("telephone",bu.getTelephone());
                map.put("roleId",bu.getRoleId());

                if(null != bu.getStatus()){
                    switch (bu.getStatus()){
                        case 0 : map.put("status","可用");break;
                        case 1 : map.put("status","不可用");break;
                    }
                }

                map.put("createTime", DateUtil.timestampToDates(bu.getCreateTime(),DateUtil.DATE_PATTON_DEFAULT));

                list.add(map);
            }
        }
        long toal=pageInfo.getTotal();
        return new Result(code,message,toal,list);
    }


    //通过用户名查询用户信息
    @Override
    public Result findByName(BackUser backUser,Integer page, Integer rows) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page, rows);
        List<BackUser> backUsers = backUserMapper.findByName(backUser.getUsername());

        PageInfo pageInfo = new PageInfo(backUsers);
        List<Map<String, Object>> list = new ArrayList<>();

        if (0<backUsers.size()){
            for (BackUser bu : backUsers){
                Map<String,Object> map = Maps.newHashMap();
                map.put("userId",bu.getUserId());
                map.put("username",bu.getUsername());
                map.put("realname",bu.getRealname());
                map.put("userRole",roleMapper.findNameById(bu.getRoleId()));
                map.put("gender",bu.getGender());
                map.put("telephone",bu.getTelephone());
                map.put("roleId",bu.getRoleId());

                if(null != bu.getStatus()){
                    switch (bu.getStatus()){
                        case 0 : map.put("status","可用");break;
                        case 1 : map.put("status","不可用");break;
                    }
                }

                map.put("createTime", DateUtil.timestampToDates(bu.getCreateTime(),DateUtil.DATE_PATTON_DEFAULT));

                list.add(map);
            }
        }
        long toal=pageInfo.getTotal();
        return new Result(code,message,toal,list);

    }

    /**
     * 状态禁用
     * @param backUser
     * @return
     */
    @Override
    public Result changeStatus(BackUser backUser) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if(backUserMapper.getStatus(backUser)==0){
            backUser.setStatus(1);
        }else if (backUserMapper.getStatus(backUser)==1){
            backUser.setStatus(0);
        }

        backUserMapper.changeStatus(backUser);
        return new Result(code,message,null);
    }

    /**
     * 后台用户注册
     * @param backUser
     * @return
     */
    @Override
    public Result insertUser(BackUser backUser) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        backUser.setCreateTime(DateUtil.currentSecond());
        backUser.setPassword(MD5Util.MD5(backUser.getPassword()));
        List<BackUser> list=backUserMapper.findByName(backUser.getUsername());
        if (list.size()!=0){
            return new Result("1","用户名已存在",null);
        }
        backUserMapper.insertUser(backUser);

        return new Result(code,message,null);
    }

    /**
     * 删除用户表
     * @param id
     * @return
     */
    @Override
    public Result deleteBackUserByUserId(Integer id) {

        String  code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        if (id==null){
            code = BaseConstants.GWSCODE3001;
            message = BaseConstants.GWSMSG3001;
        }else {
            backUserMapper.deleteBackUserByUserId(id);
        }
        return new Result(code,message,null);
    }

    /**
     * 用户角色修改
     * @param id
     * @return
     */
    @Override
    public Result updateRoleId(Integer id,Integer roleId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        backUserMapper.updateRoleId(id,roleId);

        return new Result(code,message,null);
    }

    /**
     * 查找所有的角色
     * @return
     */
    @Override
    public Result findALLRole() {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<Role> list = roleMapper.findAllRole();

        return new Result(code,message,list);
    }


}
