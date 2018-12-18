package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**
 * Created by hrym13 on 2018/6/26.
 */
public class ResourceArticle implements Serializable {

    private Integer articleId;              // 文章id
    private Integer specialColumnId;        // 专栏id
    private String articleTitle;            // 文章名称
    private Integer userId;                 // 用户id
    private Integer createTime;             // 创建时间
    private Integer updateTime;             // 更新时间
    private Integer statusArticle;          // 文章状态0：未发表；1：已发表；
    private String articlePic;              // 文章图片
    private String articleUrl;              // 文章地址
    private String articleAbstract;         // 文章摘要
    private String articleContent;          // 文章内容
    private Integer orderNum;               // 文章阅读量
    private Integer publishTime;            // 文章发表时间
    private Integer downTime;               // 文章下架时间
    private String author;                  // 文章作者
    private String assemble;                // 组件
    private String arrays;                  // 数组


    public String getArrays() {
        return arrays;
    }

    public void setArrays(String arrays) {
        this.arrays = arrays;
    }

    public String getAssemble() {
        return assemble;
    }

    public void setAssemble(String assemble) {
        this.assemble = assemble;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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

    public String getArticlePic() {
        return articlePic;
    }

    public void setArticlePic(String articlePic) {
        this.articlePic = articlePic;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Integer publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getDownTime() {
        return downTime;
    }

    public void setDownTime(Integer downTime) {
        this.downTime = downTime;
    }

    @Override
    public String toString() {
        return "ResourceArticle{" +
                "articleId=" + articleId +
                ", specialColumnId=" + specialColumnId +
                ", articleTitle='" + articleTitle + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", statusArticle=" + statusArticle +
                ", articlePic='" + articlePic + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", articleAbstract='" + articleAbstract + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", orderNum=" + orderNum +
                ", publishTime=" + publishTime +
                ", downTime=" + downTime +
                ", author='" + author + '\'' +
                ", assemble='" + assemble + '\'' +
                ", arrays='" + arrays + '\'' +
                '}';
    }
}
