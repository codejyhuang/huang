package com.hrym.rpc.app.dao.model.system;

import java.io.Serializable;

/**
 * Created by hrym13 on 2017/10/20.
 */
public class MenuList implements Serializable {

    private Integer menuId;
    private String menuName; // 菜单名称
    private String page; // 访问路径

    //关联参数
    private Integer userId;
    private Integer roleId;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
