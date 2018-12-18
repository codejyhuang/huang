package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.VO.ResourceArticleVO;
import com.hrym.rpc.app.dao.model.association.ResourceArticle;
import com.hrym.rpc.app.util.Result;

import java.util.List;

/**
 * Created by hrym13 on 2018/6/26.
 */
public interface ResourceArticleMgrService {

    /**
     * 文章列表展示
     * @return
     */
   Result findAllArticle(Integer page, Integer rows);

    /**
     * 根据文章ID查找文章详情
     * @param Id
     * @return
     */
    Result findAllArticleById(Integer Id);

    /**
     * 根据文章标题查找文章详情
     * @param articleTitle
     * @return
     */
    Result findArticleByArticleTitle(Integer page,Integer rows,String articleTitle,String columnName);

    /**
     * 文章录入
     * @param resourceArticle
     */
    Result insertArticle(ResourceArticle resourceArticle);

    /**
     * 文章更新
     * @param resourceArticle
     */
    Result updateArticle(ResourceArticle resourceArticle);

    /**
     * 文章发表
     * @param articleId
     */
    Result updateStatusArticle(Integer articleId);

    /**
     * 文章下架
     * @param articleId
     */
    Result updateArticleStatus(Integer articleId);

    /**
     * 文章删除
     * @param Id
     */
    Result deleteArticle(Integer Id);


}
