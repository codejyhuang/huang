package com.hrym.rpc.app.dao.model.VO.lessonVO;

import com.hrym.rpc.app.dao.model.association.ResourceArticle;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceContentLesson;
import com.hrym.rpc.app.dao.model.task.lesson.TaskAreaLesson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hrym13 on 2018/6/25.
 */
public class TaskAreaLessonVO extends TaskAreaLesson implements Serializable {

    private Integer orderNum;       // 定制数量
    private Integer onlineNum;      // 正在做
    private String itemPic;        // 图片pic
    private Integer isItemAdd;      // 	功课是否已添加：1:已添加；0：未添加
    private List<ContentLessonVO> contentList;
    private ResourceArticle resourceArticle;

    public ResourceArticle getResourceArticle() {
        return resourceArticle;
    }

    public void setResourceArticle(ResourceArticle resourceArticle) {
        this.resourceArticle = resourceArticle;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(Integer onlineNum) {
        this.onlineNum = onlineNum;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

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
        return "TaskAreaLessonVO{" +
                "orderNum=" + orderNum +
                ", onlineNum=" + onlineNum +
                ", itemPic='" + itemPic + '\'' +
                ", isItemAdd=" + isItemAdd +
                ", contentList=" + contentList +
                ", resourceArticle=" + resourceArticle +
                '}';
    }
}
