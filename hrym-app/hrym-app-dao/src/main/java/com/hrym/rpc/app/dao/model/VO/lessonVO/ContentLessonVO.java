package com.hrym.rpc.app.dao.model.VO.lessonVO;

import com.hrym.rpc.app.dao.model.task.lesson.ResourceContentLesson;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/6/20.
 */
public class ContentLessonVO extends ResourceContentLesson implements Serializable {

    private Integer isContentAdd = 0;       // 内容是否已添加：1:已添加；0：未添加

    public Integer getIsContentAdd() {
        return isContentAdd;
    }

    public void setIsContentAdd(Integer isContentAdd) {
        this.isContentAdd = isContentAdd;
    }

    @Override
    public String toString() {
        return "ResourceContentLessonVO{" +
                ", isContentAdd=" + isContentAdd +
                '}';
    }
}
