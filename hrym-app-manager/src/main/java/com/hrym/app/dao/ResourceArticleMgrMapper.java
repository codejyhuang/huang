package com.hrym.app.dao;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.VO.ResourceArticleVO;
import com.hrym.rpc.app.dao.model.association.ResourceArticle;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hrym13 on 2018/6/26.
 * t_resource_article
 */
@Repository
public interface ResourceArticleMgrMapper {

    /**
     * 文章列表展示
     * @return
     */
    @DataSource("slave")
    @Select(" SELECT e.*,n.column_name FROM t_resource_article e LEFT JOIN t_special_column n ON n.idt_special_column = e.special_column_id ")
   List<ResourceArticleVO> findAllArticle();

    /**
     * 根据文章ID查找文章详情
     * @param Id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT e.*,n.column_name FROM t_resource_article e " +
            "LEFT JOIN t_special_column n ON n.idt_special_column = e.special_column_id " +
            "WHERE article_id = #{Id}")
    ResourceArticleVO findArticleById( Integer Id);

    /**
     * 根据文章标题查找文章详情
     * @param articleTitle
     * @return
     */
    @DataSource("slave")
    @Select("<script>" +
            "SELECT e.*,n.column_name FROM t_resource_article e " +
            "LEFT JOIN t_special_column n ON n.idt_special_column = e.special_column_id " +
            "WHERE 1=1" +
            "<if test =\"articleTitle !=null and articleTitle !=''\">" +
            " and e.article_title like '%${articleTitle}%'" +
            "</if>" +
            "<if test =\"columnName !=null and columnName !=''\">" +
            " and n.column_name like '%${columnName}%'" +
            "</if>" +
            "</script>")
    List<ResourceArticleVO> findArticleByArticleTitle( @Param("articleTitle") String articleTitle,@Param("columnName") String columnName);


    /**
     * 文章录入
     * @param resourceArticle
     */
    @Insert("insert into t_resource_article " +
            "(article_title,article_abstract,article_pic,article_url,article_content,status_article,create_time,update_time,special_column_id,author,assemble,arrays)" +
            "values" +
            "(#{articleTitle},#{articleAbstract},#{articlePic},#{articleUrl},#{articleContent},#{statusArticle},#{createTime},#{updateTime},#{specialColumnId},#{author},#{assemble},#{arrays})")
   void insertArticle( ResourceArticle resourceArticle);

    /**
     * 文章更新
     * @param resourceArticle
     */
    @Update("update t_resource_article set  article_title = #{articleTitle},article_abstract = #{articleAbstract},article_pic = #{articlePic}," +
            "article_url = #{articleUrl},article_content = #{articleContent},status_article = #{statusArticle},update_time = #{updateTime}," +
            "special_column_id = #{specialColumnId},author = #{author},arrays=#{arrays},assemble = #{assemble} " +
            "where article_id = #{articleId}  ")
    void updateArticle(ResourceArticle resourceArticle);

    /**
     * 文章发表
     * @param publishTime
     * @param articleId
     */
    @Update("update t_resource_article set  status_article = 1,publish_time = #{publishTime}" +
            " where article_id = #{articleId}")
    void updateStatusArticle ( @Param("publishTime") Integer publishTime,
                              @Param("articleId") Integer articleId);
    /**
     * 文章下架
     * @param downTime
     * @param articleId
     */
    @Update("update t_resource_article set  status_article = 0,down_time = #{downTime} " +
            "where article_id = #{articleId}")
    void updateArticleStatus (@Param("downTime") Integer downTime,
                              @Param("articleId") Integer articleId);

    /**
     * 文章删除
     * @param Id
     */
    @Delete("delete from t_resource_article where article_id = #{Id}")
    void deleteArticle(Integer Id);


}
