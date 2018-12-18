package com.hrym.rpc.association.dao.mapper;

import com.hrym.common.annotation.DataSource;
import com.hrym.rpc.app.dao.model.association.ArticleContent;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 文章内容Mapper
 * Created by mj on 2017/9/14.
 */
public interface ArticleContentMapper {

    /**
     * 通过文章ID查询文章内容
     * @param articleId
     * @return
     */
    @DataSource("slave")
    @Select("select idt_article_content,article_id,content_type,content_value,sort,pic from t_article_content where article_id=#{articleId}")
    List<ArticleContent> findArticleContentByArticleId(Integer articleId);
}
