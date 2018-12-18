package com.hrym.rpc.app.dao.model.VO;

import com.hrym.rpc.app.dao.model.association.ResourceArticle;
import com.hrym.rpc.app.dao.model.association.SpecialColumn;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/6/26.
 * 文章
 */
public class ResourceArticleVO extends ResourceArticle implements Serializable {

    private String createTimes;
    private String updateTimes;
    private String publishTimes;
    private String downTimes;

    private String columnName;        //专栏名称

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

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

    public String getPublishTimes() {
        return publishTimes;
    }

    public void setPublishTimes(String publishTimes) {
        this.publishTimes = publishTimes;
    }

    public String getDownTimes() {
        return downTimes;
    }

    public void setDownTimes(String downTimes) {
        this.downTimes = downTimes;
    }

    @Override
    public String toString() {
        return "ResourceArticleVO{" +
                "createTimes='" + createTimes + '\'' +
                ", updateTimes='" + updateTimes + '\'' +
                ", publishTimes='" + publishTimes + '\'' +
                ", downTimes='" + downTimes + '\'' +
                ", columnName='" + columnName + '\'' +
                '}';
    }
}
