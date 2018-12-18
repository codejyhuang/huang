package com.hrym.rpc.association.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 文章Mapper
 * Created by mj on 2017/8/31.
 */
public interface ArticleMapper {

    /**
     * 查找所有文章
     * @return
     */
    @DataSource("slave")
    List<Article> findAllArticle();

    /**
     * 通过ID查找文章
     * @param id
     * @return
     */
    @DataSource("slave")
    @Select("select * from t_article where idt_article=#{id}")
    Article findAllArticleById(Integer id);
}
