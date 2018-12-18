package com.hrym.rpc.app.dao.model.task;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mj on 2017/8/17.
 */
public class TaskTree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "type_id")
    private Integer id;          //id
    @Column(name = "parent_type_id")
    private Integer pid;         //父id
    @Column(name = "type_name")
    private String text;        //名称
    private List<TaskTree> children;  //用于存储子节点

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TaskTree> getChildren() {
        return children;
    }

    public void setChildren(List<TaskTree> children) {
        this.children = children;
    }
}
