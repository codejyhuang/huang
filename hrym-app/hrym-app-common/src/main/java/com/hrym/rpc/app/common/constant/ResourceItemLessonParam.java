package com.hrym.rpc.app.common.constant;

import com.hrym.common.base.BaseRequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hrym13 on 2018/6/20.
 */
public class ResourceItemLessonParam extends BaseRequestParam implements Serializable{

    private LessonData data;

    public LessonData getData() {
        return data;
    }

    public void setData(LessonData data) {
        this.data = data;
    }

    public String getToken() {
        return data.getToken();
    }

    public Integer getPageNo() {
        return data.getPageNo();
    }

    public String getCustomName() {
        return data.getCustomName();
    }

    public Integer getTaskId() {
        return data.getTaskId();
    }

    public Integer getTypeId() {
        return data.getTypeId();
    }

    public Integer getItemId() {
        return data.getItemId();
    }

    public Integer getItemContentId() {
        return data.getItemContentId();
    }

    public Integer getType() {
        return data.getType();
    }
    public String getTagsName() {
        return data.getTagsName();
    }
    public String getFilterStr() {
        return data.getFilterStr();
    }
    public String getDevice_id() {
        return data.getDevice_id();
    }
    public Integer getPageSize() {
        return data.getPageSize();
    }


}
class LessonData implements Serializable{

    private String token;           // 用户token
    private Integer pageNo;
    private Integer pageSize;
    private String customName;      // 自定义功课名称
    private Integer taskId;         //任务ID
    private Integer typeId;         //功课类型ID
    private Integer itemId;         //索引ID
    private Integer itemContentId;      // 功课内容ID
    private Integer type;           //类型
    private String tagsName;        //标签名称
    private String filterStr;       //搜索过滤字段
    private String device_id;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemContentId() {
        return itemContentId;
    }

    public void setItemContentId(Integer itemContentId) {
        this.itemContentId = itemContentId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    public String getFilterStr() {
        return filterStr;
    }

    public void setFilterStr(String filterStr) {
        this.filterStr = filterStr;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
