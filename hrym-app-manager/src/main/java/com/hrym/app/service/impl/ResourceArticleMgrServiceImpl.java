package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.hrym.app.dao.ResourceArticleMgrMapper;
import com.hrym.app.service.ResourceArticleMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.VO.ResourceArticleVO;
import com.hrym.rpc.app.dao.model.association.ResourceArticle;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hrym13 on 2018/6/26.
 */
@Service
public class ResourceArticleMgrServiceImpl implements ResourceArticleMgrService {

    @Autowired
    private ResourceArticleMgrMapper articleMgrMapper;

    /**
     * 文章列表展示
     * @return
     */
    @Override
    public Result findAllArticle(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        PageHelper.startPage(page,rows,"e.create_time desc");
        List<ResourceArticleVO> articleVOList = articleMgrMapper.findAllArticle();

        for ( ResourceArticleVO articleVO :articleVOList) {

            if (articleVO.getCreateTime() != null) {

                String createTime = DateUtil.timestampToDates(articleVO.getCreateTime(),DateUtil.TIME_PATTON_DEFAULT);
                articleVO.setCreateTimes(createTime);
            }
            if (articleVO.getUpdateTime() != null) {

                String updateTime = DateUtil.timestampToDates(articleVO.getUpdateTime(),DateUtil.TIME_PATTON_DEFAULT);
                articleVO.setUpdateTimes(updateTime);
            }
            if (articleVO.getDownTime() != null) {

                String downTime = DateUtil.timestampToDates(articleVO.getDownTime(),DateUtil.TIME_PATTON_DEFAULT);
                articleVO.setDownTimes(downTime);
            }
            if (articleVO.getPublishTime() != null) {

                String pubLishTime = DateUtil.timestampToDates(articleVO.getPublishTime(),DateUtil.TIME_PATTON_DEFAULT);
                articleVO.setPublishTimes(pubLishTime);
            }


        }
        PageInfo pageInfo = new PageInfo(articleVOList);
        long total = pageInfo.getTotal();

        return new Result(code,message,total,articleVOList);
    }

    /**
     * 根据文章ID查找文章详情
     * @param Id
     * @return
     */
    @Override
    public Result findAllArticleById(Integer Id) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSCODE0000;

        ResourceArticle article = articleMgrMapper.findArticleById(Id);

        return new Result(code,message,article);
    }

    /**
     * 根据文章标题查找文章详情
     * @param articleTitle
     * @return
     */
    @Override
    public Result findArticleByArticleTitle(Integer page,Integer rows,String articleTitle,String columnName) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        PageHelper.startPage(page,rows);
        List<ResourceArticleVO> articleVOList = articleMgrMapper.findArticleByArticleTitle(articleTitle,columnName);

        if (articleTitle == null && columnName ==null) {
            return findAllArticle(page,rows);

        }else {

            for (ResourceArticleVO articleVO : articleVOList) {

                if (articleVO.getCreateTime() != null) {

                    String createTime = DateUtil.timestampToDates(articleVO.getCreateTime(), DateUtil.TIME_PATTON_DEFAULT);
                    articleVO.setCreateTimes(createTime);
                }
                if (articleVO.getUpdateTime() != null) {

                    String updateTime = DateUtil.timestampToDates(articleVO.getUpdateTime(), DateUtil.TIME_PATTON_DEFAULT);
                    articleVO.setUpdateTimes(updateTime);
                }
                if (articleVO.getDownTime() != null) {

                    String downTime = DateUtil.timestampToDates(articleVO.getDownTime(), DateUtil.TIME_PATTON_DEFAULT);
                    articleVO.setDownTimes(downTime);
                }
                if (articleVO.getPublishTime() != null) {

                    String pubLishTime = DateUtil.timestampToDates(articleVO.getPublishTime(), DateUtil.TIME_PATTON_DEFAULT);
                    articleVO.setPublishTimes(pubLishTime);
                }


            }
            PageInfo pageInfo = new PageInfo(articleVOList);
            long total = pageInfo.getTotal();

            return new Result(code, message, total, articleVOList);
        }
    }


    /**
     * 文章录入
     * @param resourceArticle
     */
    @Override
    public Result insertArticle(ResourceArticle resourceArticle) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        resourceArticle.setCreateTime(DateUtil.currentSecond());
        resourceArticle.setUpdateTime(DateUtil.currentSecond());
        resourceArticle.setStatusArticle(0);
        articleMgrMapper.insertArticle(resourceArticle);

        return new Result(code,message,null);
    }


    /**
     * 文章更新
     * @param resourceArticle
     */
    @Override
    public Result updateArticle(ResourceArticle resourceArticle) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        resourceArticle.setUpdateTime(DateUtil.currentSecond());
        articleMgrMapper.updateArticle(resourceArticle);

        return new Result(code,message,null);
    }

    /**
     * 文章发表
     * @param articleId
     */
    @Override
    public Result updateStatusArticle(Integer articleId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        articleMgrMapper.updateStatusArticle(DateUtil.currentSecond(),articleId);

        return new Result(code,message,null);
    }

    /**
     * 文章下架
     * @param articleId
     */
    @Override
    public Result updateArticleStatus(Integer articleId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        articleMgrMapper.updateArticleStatus(DateUtil.currentSecond(),articleId);

        return new Result(code,message,null);
    }

    /**
     * 文章删除
     * @param Id
     */
    @Override
    public Result deleteArticle(Integer Id) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        articleMgrMapper.deleteArticle(Id);

        return new Result(code,message,null);
    }


}
