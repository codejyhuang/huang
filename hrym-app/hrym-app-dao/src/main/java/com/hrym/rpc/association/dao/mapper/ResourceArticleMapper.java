package com.hrym.rpc.association.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.ResourceArticle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by hrym13 on 2018/6/28.
 * 新文章Mapper t_resource_article
 */
public interface ResourceArticleMapper {


    /**
     * 推荐功课里文章展示
     * @param Id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT article_id,article_title,article_abstract,article_pic,article_url\n" +
            "FROM t_resource_article WHERE article_id=#{Id}")
    ResourceArticle findResourceArticle(Integer Id);

    /**
     * 前端h5页面详情展示
     * @param Id
     * @return
     */
    @DataSource("slave")
    @Select("SELECT article_id,article_title,article_abstract,article_pic,article_url,article_content,\n" +
            "status_article,order_num,create_time,update_time,publish_time,down_time,special_column_id,author,assemble,arrays\n" +
            "FROM t_resource_article WHERE article_id=#{Id}")
    ResourceArticle findAllResourceArticle(Integer Id);

    /**
     * 更新文章阅读人数
     * @param Id
     */
   @Update("update t_resource_article set order_num = order_num+1 WHERE article_id=#{Id}  ")
   void updateResourceArticleOrderNum(@Param("Id") Integer Id);
}
