package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.association.*;
import com.hrym.rpc.app.util.Result;

import java.util.List;
import java.util.Map;

/**业务管理接口
 * Created by hrym13 on 2017/8/24.
 */
public interface AssociationMgrService {

    /**
     * 查询社群
     * @return
     */

    Result findAllAssociation(Integer page, Integer rows);

    /**
     * 社群检索
     */
    Result searchAllAssociation(Association association, Integer page,Integer rows);

    /**
     * 文章列表
     */
    Result findAllArticle (Integer page, Integer rows);

    /**
     * 删除文章
     */
    Result deleteAllArticle(Article article);

    /**
     * 删除文章内容
     * @param articleContent
     * @return
     */
    Result deleteArticleContent(ArticleContent articleContent);

    /**
     * 发表文章
     */
    Result insertAllArticle(Article article);

    String insertArticle(Article article);
    /**
     * 文章内容发表
     */
    Result insertArticleContent(ArticleContent articleContent);

    /**
     * 文章检索
     */
    Result searchAllArticle(Article article,Integer page,Integer rows);

    /**
     * 专栏名称
     * @param specialColumn
     * @param page
     * @param rows
     * @return
     */
    Result searchAllSpecialName(SpecialColumn specialColumn,Integer page,Integer rows);

    /**
     * 文章名称查找
     */
    Result findColumnName();

    /**
     * 编辑文章
     * @param article
     * @return
     */
    Article editArticleById(Article article);

    /**
     * 文章修改
     * @param article
     * @return
     */
    Result updateAllArticle(Article article);

    /**
     * 发表
     * @param article
     * @return
     */
    Result updateArticle(Article article);

    /**
     * 文章内容修改
     * @param articleContent
     * @return
     */
    Result updateArticleContent(ArticleContent articleContent);

    /**
     * 活动列表查找
     * @param
     * @return
     */
    Result findAllBanner (Integer page,Integer rows);

    /**
     * 根据活动ID查找活动内容
     * @param banner
     * @return
     */
    Banner findAllBannerById(Banner banner);

    /**
     * 检索
     * @param banner
     * @param page
     * @param rows
     * @return
     */
    Result findAllBannerByTitle(Banner banner,Integer page,Integer rows);
    Result findAllBannerByTime(Banner banner,Integer page,Integer rows);

    /**
     * 活动添加
     * @param banner
     * @return
     */
    Result insertAllBanner(Banner banner);

    /**
     * 活动更新
     * @param banner
     * @return
     */
    Result updateAllBanner(Banner banner);

    /**
     * 活动删除
     * @param banner
     * @return
     */
    Result deleteAllBanner(Banner banner);



//    ===============愿望活动接口============

    /**
     * 记录愿望
     * @param desire
     * @return
     */
    Map<String ,Object> insertALLDesire(Desire desire);

    /**
     * 查看愿望总数
     * @return
     */
    Map<String,Object> findAllCountByDesire();
    /**
     * 许愿活动列表
     * @param
     * @return
     */
    Result findAllDesire (Integer page,Integer rows);

    /**
     * 活动记录列表
     * @param
     * @return
     */
    Result findAllWishActivity (Integer page,Integer rows);

    /**
     * 记录完成任务电话号码
     * @param wishActivity
     * @return
     */
    Map<String ,Object> insertAllWishActivity(WishActivity wishActivity);

    /**
     * 任务活动首页接口
     * @param
     * @return
     */
    Map<String ,Object> findWishActivity(Integer userId);


    /**
     * 获取所有抽奖名单
     * @return
     */
    List<WishActivity> findAllWishActivity();


    /**
     * 更新中奖状态
     * @param id
     */
    void updateWinnerByUserId(Integer id);


}
