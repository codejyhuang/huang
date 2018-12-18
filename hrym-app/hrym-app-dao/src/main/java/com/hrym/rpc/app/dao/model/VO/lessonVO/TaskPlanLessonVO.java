package com.hrym.rpc.app.dao.model.VO.lessonVO;

import com.hrym.rpc.app.dao.model.task.lesson.TaskPlanLesson;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/6/21.
 */
public class TaskPlanLessonVO extends TaskPlanLesson implements Serializable {

    private String lastReportTime;      //上次上报时间
    private String nameStr;             //标题名称
    private Integer typeId;             //类型id

    public String getLastReportTime() {
        return lastReportTime;
    }

    public void setLastReportTime(String lastReportTime) {
        this.lastReportTime = lastReportTime;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "TaskPlanLessonVO{" +
                "lastReportTime='" + lastReportTime + '\'' +
                ", nameStr='" + nameStr + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
