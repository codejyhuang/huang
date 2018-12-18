package com.hrym.rpc.app.common.constant;

import java.io.Serializable;

/**
 * Created by hrym13 on 2017/7/31.
 */

public class ManagerParam implements Serializable {
    private Integer pageNumber ;
    private Integer pageSize;
    private Integer typeId;
    //文章实体
    private Integer idtArticle;             //文章id
    private Integer specialColumnId;        //专栏id
    private String articleTitle;            //文章名称
    private Integer userId;                 //用户id
    private Integer createTime;             //创建时间
    private Integer updateTime;             //更新时间
    private Integer statusArticle;     //文章状态0：未发表；1：已发表；
    //文章内容
    private Integer idtArticleContent;            //文章内容ID
    private Integer articleId;                     //文章ID
    private Integer contentType;                   //'内容类型\n0:正文\n1:引用\n2:图片'
    private String contentValue;                   //'内容'
    private Integer sort;                           //'序号'
    private String pic;                             //'当类型为图片时必须有值'
    private String columnName;                      // 专栏名称

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getIdtArticle() {
        return idtArticle;
    }

    public void setIdtArticle(Integer idtArticle) {
        this.idtArticle = idtArticle;
    }

    public Integer getSpecialColumnId() {
        return specialColumnId;
    }

    public void setSpecialColumnId(Integer specialColumnId) {
        this.specialColumnId = specialColumnId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatusArticle() {
        return statusArticle;
    }

    public void setStatusArticle(Integer statusArticle) {
        this.statusArticle = statusArticle;
    }

    public Integer getIdtArticleContent() {
        return idtArticleContent;
    }

    public void setIdtArticleContent(Integer idtArticleContent) {
        this.idtArticleContent = idtArticleContent;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
