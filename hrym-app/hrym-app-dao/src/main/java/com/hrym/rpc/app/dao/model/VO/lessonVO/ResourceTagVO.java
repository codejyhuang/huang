package com.hrym.rpc.app.dao.model.VO.lessonVO;

import com.hrym.rpc.app.dao.model.task.lesson.ResourceTag;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/7/3.
 */
public class ResourceTagVO extends ResourceTag implements Serializable {

    private String createTimes;
    private String updateTimes;

    public String getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(String createTimes) {
        this.createTimes = createTimes;
    }

    public String getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(String updateTimes) {
        this.updateTimes = updateTimes;
    }

    @Override
    public String toString() {
        return "ResourceTagVO{" +
                "createTimes='" + createTimes + '\'' +
                ", updateTimes='" + updateTimes + '\'' +
                '}';
    }
}
