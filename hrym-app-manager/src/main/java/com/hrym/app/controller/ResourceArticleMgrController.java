package com.hrym.app.controller;

import com.hrym.app.service.ResourceArticleMgrService;
import com.hrym.common.base.BaseController;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.association.ResourceArticle;
import com.hrym.rpc.app.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;

/**
 * Created by hrym13 on 2018/6/26.
 */
@Controller
@RequestMapping("/admin")
public class ResourceArticleMgrController extends BaseController {

    @Autowired
    private ResourceArticleMgrService articleMgrService;

    /**
     * 文章列表显示
     * @param param
     * @return
     */
    @RequestMapping("/newFindAllArticle")
    @ResponseBody
    public Result findAllArticle(ManagerParam param) {

        return articleMgrService.findAllArticle(param.getPageNumber(),param.getPageSize());
    }

    /**
     * 根据文章ID查找文章详情
     * @param Id
     * @return
     */
    @RequestMapping(value = "/newFindAllArticleById",method = RequestMethod.POST
            , produces = {"application/json", "application/xml"}
            ,  consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Result findAllArticleById( Integer Id) {

        return articleMgrService.findAllArticleById(Id);
    }

    /**
     * 文章标题和专栏名称模糊查询
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping("/newFindArticleByArticleTitle")
    @ResponseBody
    public Result findArticleByArticleTitle(ManagerParam param) throws Exception {

        if (param.getArticleTitle() !=null) {
            String val = URLDecoder.decode(param.getArticleTitle(),"UTF-8");
            param.setArticleTitle(val);
        }
        if (param.getColumnName() != null) {
           String val = URLDecoder.decode(param.getColumnName(),"UTF-8");
           param.setColumnName(val);
        }

        return articleMgrService.findArticleByArticleTitle(param.getPageNumber(),
                param.getPageSize(),
                param.getArticleTitle(),
                param.getColumnName());
    }

    /**
     * 文章录入
     * @param resourceArticle
     * @return
     */
    @RequestMapping(value = "/newInsertArticle",method = RequestMethod.POST
            , produces = {"application/json", "application/xml"}
            ,  consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Result insertArticle( ResourceArticle resourceArticle) {

        return articleMgrService.insertArticle(resourceArticle);
    }

    /**
     * 文章更新
     * @param resourceArticle
     * @return
     */
    @RequestMapping(value = "/newUpdateArticle",method = RequestMethod.POST
            , produces = {"application/json", "application/xml"}
            ,  consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public Result updateArticle ( ResourceArticle resourceArticle) {

        return articleMgrService.updateArticle(resourceArticle);
    }

    /**
     * 文章发表
     * @param articleId
     * @return
     */
    @RequestMapping("/newUpdateStatusArticle")
    @ResponseBody
    public Result updateStatusArticle (Integer articleId) {

        return articleMgrService.updateStatusArticle(articleId);
    }

    /**
     * 文章下架
     * @param articleId
     * @return
     */
    @RequestMapping("/newUpdateArticleStatus")
    @ResponseBody
    public Result updateArticleStatus (Integer articleId) {

        return articleMgrService.updateArticleStatus(articleId);
    }

    /**
     * 文章删除
     * @param Id
     * @return
     */
    @RequestMapping("/newDeleteArticle")
    @ResponseBody
    public Result deleteArticle(Integer Id) {

        return articleMgrService.deleteArticle(Id);
    }

}
