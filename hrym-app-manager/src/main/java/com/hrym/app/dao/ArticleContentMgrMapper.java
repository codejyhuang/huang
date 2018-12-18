package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Article;
import com.hrym.rpc.app.dao.model.association.ArticleContent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2017/8/25.
 */
@Repository
public interface ArticleContentMgrMapper {


    /**
     * 发表文章内容 SELECT idt_article_content,article_id,content_type,content_value,sort,pic FROM t_article_content
     */
    @Insert("insert into t_article_content (article_id,content_type,content_value,sort,pic)" +
            "values(#{articleId},#{contentType},#{contentValue},#{sort},#{pic})")
    void insertArticleContent(ArticleContent articleContent);

    /**
     * 更新文章内容
     */
    @Update("update t_article_content set content_type = #{contentType},content_value = #{contentValue},sort = #{sort},pic = #{pic} where idt_article_content = #{idtArticleContent}")
    void updateArticleContent(ArticleContent articleContent);

    /**
     * 文章内容删除(按文章ID)
     */
    @Delete("delete from t_article_content where article_id = #{id}")
    void deletetArticleContent(Integer id);


    /**
     * 文章内容删除(按文章内容ID)
     */
    @Delete("delete from t_article_content where idt_article_content = #{id}")
    void deleteArticleContent(Integer id);

    /**
     * 根据文章ID查找文章内容
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT idt_article_content,article_id,content_type,content_value,sort,pic " +
            "FROM t_article_content WHERE article_id = #{id}")
    List<ArticleContent> findArticleContentById(Integer id);

    /**
     * 根据文章ID和类型ID查找文章内容
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("select article_id,content_type,idt_article_content,content_value,pic from t_article_content " +
            "where content_type=#{typeId} and article_id=#{id}")
    List<ArticleContent> findContentByContentType(@Param("id") Integer id, @Param("typeId") Integer typeId);



}
