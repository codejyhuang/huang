package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;
import java.util.List;

/**
 * 文章专栏实体类
 * Created by mj on 2017/8/17.
 */
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idtArticle;             //文章id
    private Integer specialColumnId;        //专栏id
    private String articleTitle;            //文章名称
    private Integer userId;                 //用户id
    private Integer createTime;             //创建时间
    private Integer updateTime;             //更新时间
    private Integer statusArticle;     //文章状态0：未发表；1：已发表；
    private String articlePic;
    private String articleUrl;          //文章地址
    private String articleAbstract;     //文章摘要

    private SpecialColumn specialColumn;    //专栏
    private List<ArticleContent> articleContentList;


    public List<ArticleContent> getArticleContentList() {
        return articleContentList;
    }

    public void setArticleContentList(List<ArticleContent> articleContentList) {
        this.articleContentList = articleContentList;
    }

    public String getArticlePic() {
        return articlePic;
    }

    public void setArticlePic(String articlePic) {
        this.articlePic = articlePic;
    }

    public Integer getStatusArticle() {return statusArticle;}

    public void setStatusArticle(Integer statusArticle) {this.statusArticle = statusArticle;}

    public SpecialColumn getSpecialColumn() {return specialColumn;}

    public void setSpecialColumn(SpecialColumn specialColumn) {this.specialColumn = specialColumn;}

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
