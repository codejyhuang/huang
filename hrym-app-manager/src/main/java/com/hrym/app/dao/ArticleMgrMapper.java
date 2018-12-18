package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Article;
import com.hrym.rpc.app.dao.model.association.SpecialColumn;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2017/8/25.
 */
@Repository
public interface ArticleMgrMapper {
    /**
     * 文章管理
     */
    @DataSource("slave")
    List<Article> findAllArticle ();

    /**
     * 删除文章
     */
    @Delete("delete from t_article where idt_article = #{idtArticle}")
    void deleteAllArticle(Integer id);


    /**
     * 添加文章
     */
    @Insert("insert into t_article(status_article,update_time,create_time,article_title,special_column_id,article_pic,article_url,article_abstract)values" +
            "(#{statusArticle},#{updateTime},#{createTime},#{articleTitle},#{specialColumnId},#{articlePic},#{articleUrl},#{articleAbstract}) ")
    void insertArticle(Article article);

    /**
     * 文章修改
     * @param article
     */
    @Update("update t_article set update_time = #{updateTime},article_title = #{articleTitle},special_column_id = #{specialColumnId},article_pic = #{articlePic},article_abstract = #{articleAbstract} " +
            "where idt_article = #{idtArticle}")
    void updateAllArticle(Article article);

    /**
     * 发表
     * @param
     * @return
     */
    @Update("update t_article set update_time = #{updateTime},status_article = #{statusArticle} where idt_article = #{idtArticle}")
    void updateArticle(Article article);


    /**
     * 根据文章类型ID查找文章名称
     */
    @DataSource("slave")
    @Select("SELECT idt_special_column,column_name,column_type FROM t_special_column " +
            "WHERE idt_special_column =#{idtSpecialColumn}")
    List<Article> findAllSpecialColumn(Integer id);

    /**
     * 文章ID查找文章
     */
    @DataSource("slave")
    @Select("SELECT idt_article,special_column_id,user_id,article_title,create_time,update_time,status_article," +
            "article_pic,article_url,article_abstract FROM t_article WHERE idt_article = #{idtArticle}")
    Article findArticleById (Integer id);


    /**
     * 插入后查找上一个ID
     * @return
     */
    @DataSource("slave")
    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();


    /**
     * 搜索文章（文章名称）
     */
    @DataSource("slave")
    List<Article> searchAllArticle( @Param("articleTitle") String articleTitle );
    //专栏名称
    @DataSource("slave")
    List<SpecialColumn> searchAllSpecialName(@Param("columnName") String columnName);

    /**
     * 专栏名称
     */
    @DataSource("slave")
    @Select(" SELECT idt_special_column,column_name FROM t_special_column")
    List<SpecialColumn> findColumnName();


}
