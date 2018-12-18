package com.hrym.rpc.app.dao.model.VO.lessonVO;

import com.hrym.rpc.app.dao.model.task.lesson.ResourceItemLesson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hrym13 on 2018/6/20.
 * 功课实体类
 */
public class ItemLessonVO extends ResourceItemLesson implements Serializable {

    private List<ContentLessonVO> contentList;    //功课内容集合
    private Integer isItemAdd = 0;      // 	功课是否已添加：1:已添加；0：未添加

    public Integer getIsItemAdd() {
        return isItemAdd;
    }

    public void setIsItemAdd(Integer isItemAdd) {
        this.isItemAdd = isItemAdd;
    }

    public List<ContentLessonVO> getContentList() {
        return contentList;
    }

    public void setContentList(List<ContentLessonVO> contentList) {
        this.contentList = contentList;
    }

    @Override
    public String toString() {
        return "ItemLessonVO{" +
                ", contentList=" + contentList +
                ", isItemAdd=" + isItemAdd +
                '}';
    }
}
