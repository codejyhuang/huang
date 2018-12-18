package com.hrym.rpc.app.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaohan on 2017/10/10.
 */
public class TreeNode implements Serializable {

    private Integer id;
    private Integer parentId;
    private String name;
    private String page;

    private List<TreeNode> children;    //  用户存储子节点

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
