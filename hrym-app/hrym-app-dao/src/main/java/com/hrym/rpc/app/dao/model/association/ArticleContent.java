package com.hrym.rpc.app.dao.model.association;

import java.io.Serializable;

/**
 * Created by hrym13 on 2017/9/5.
 */
public class ArticleContent implements Serializable {

    private Integer idtArticleContent;            //文章内容ID
    private Integer articleId;                     //文章ID
    private Integer contentType;                   //'内容类型\n0:正文\n1:引用\n2:图片'
    private String contentValue;                   //'内容'
    private Integer sort;                           //'序号'
    private String pic;                             //'当类型为图片时必须有值'
    private String title;
    private String url;                             //文章内容URL保存

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ArticleContent{" +
                "idtArticleContent=" + idtArticleContent +
                ", articleId=" + articleId +
                ", contentType=" + contentType +
                ", contentValue='" + contentValue + '\'' +
                ", sort=" + sort +
                ", pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
