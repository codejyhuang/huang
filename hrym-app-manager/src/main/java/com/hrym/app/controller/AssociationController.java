package com.hrym.app.controller;

import com.google.common.collect.Maps;
import com.hrym.app.service.FdfsService;
import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.association.*;
import com.hrym.rpc.app.util.Result;
import com.hrym.app.service.AssociationMgrService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * 业务管理
 * Created by hrym13 on 2017/8/24.
 */

@Controller
@RequestMapping(value = "/admin")
public class AssociationController extends BaseController {

    @Autowired
    private AssociationMgrService associationMgrService;
    @Autowired
    private FdfsService fdfsService;


    /**
     * 查询社群
     *
     * @param
     * @param
     * @return
     */

    @RequestMapping("/findAllAssociation")
    @ResponseBody
    public Result findAllAssociation(ManagerParam param) {

        return associationMgrService.findAllAssociation(param.getPageNumber(), param.getPageSize());
    }


    /**
     * 社群检索
     */

    @RequestMapping("/searchAllAssociation")
    @ResponseBody
    public Result searchAllAssociation(Association association, ManagerParam param) throws Exception {

        //自负转码
        if (null != association.getName()) {
            //转码
            String val = URLDecoder.decode(association.getName(), "UTF-8");
            association.setName(val);
        }
        return associationMgrService.searchAllAssociation(association, param.getPageNumber(), param.getPageSize());
    }


    /**
     * 文章列表
     */

    @RequestMapping("/findAllArticle")
    @ResponseBody
    public Result findAllArticle(ManagerParam param) {

        return associationMgrService.findAllArticle(param.getPageNumber(), param.getPageSize());
    }

    /**
     * 文章检索
     *
     * @param article
     * @param param
     * @return
     */
    @RequestMapping("/searchAllArticle")
    @ResponseBody
    public Result searchAllArticle(Article article, ManagerParam param) throws Exception {

        //自负转码
        if (null != article.getArticleTitle()) {
            //转码
            String val = URLDecoder.decode(article.getArticleTitle(), "UTF-8");
            article.setArticleTitle(val);
        }
        return associationMgrService.searchAllArticle(article, param.getPageNumber(), param.getPageSize());

    }

    /**
     * 专栏名称
     *
     * @param specialColumn
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping("/searchAllSpecialName")
    @ResponseBody
    public Result searchAllSpecialName(SpecialColumn specialColumn, ManagerParam param) throws Exception {

        //自负转码
        if (null != specialColumn.getColumnName()) {
            //转码
            String val = URLDecoder.decode(specialColumn.getColumnName(), "UTF-8");
            specialColumn.setColumnName(val);
        }
        return associationMgrService.searchAllSpecialName(specialColumn, param.getPageNumber(), param.getPageSize());
    }


    /**
     * 文章保存
     */

    @RequestMapping("/insertAllArticle")
    @ResponseBody
    public Result insertAllArticle(Article article) {


        return associationMgrService.insertAllArticle(article);
    }

    /**
     * 文章内容保存
     */

    @RequestMapping("/insertArticleContent")
    @ResponseBody
    public Result insertArticleContent(ArticleContent articleContent) {

        return associationMgrService.insertArticleContent(articleContent);

    }

    /**
     * 文章删除
     */

    @RequestMapping("/deleteAllArticle")
    @ResponseBody
    public Result deleteAllArticle(Article article) {

        return associationMgrService.deleteAllArticle(article);
    }

    /**
     * 文章内容删除
     *
     * @param articleContent
     * @return
     */
    @RequestMapping("/deleteArticleContent")
    @ResponseBody
    public Result deleteArticleContent(ArticleContent articleContent) {

        return associationMgrService.deleteArticleContent(articleContent);

    }

    /**
     * 文章名称查找
     */
    @RequestMapping(value = "/findColumnName")
    @ResponseBody
    public Result findColumnName() {

        return associationMgrService.findColumnName();

    }

    /**
     * 编辑文章及文章内容
     *
     * @param article
     * @return
     */
    @RequestMapping("/editArticleById")
    public ModelAndView editArticleById(Article article) {

        Article article1 = associationMgrService.editArticleById(article);
        ModelAndView mav = new ModelAndView();
        mav.addObject("artilelist", article1);
        mav.setViewName("/association/editArticle");
        return mav;
    }


    /**
     * 文章发表
     *
     * @param article
     * @return
     */
    @RequestMapping("/updateArticle")
    @ResponseBody
    public Result updateArticle(Article article) {

        return associationMgrService.updateArticle(article);

    }

    /**
     * 文章修改
     *
     * @return
     */
    @RequestMapping("/updateAllArticle")
    @ResponseBody
    public Result updateAllArticle(Article article) {

        return associationMgrService.updateAllArticle(article);
    }

    /**
     * 文章内容修改
     *
     * @param articleContent
     * @return
     */
    @RequestMapping("/updateArticleContent")
    @ResponseBody
    public Result updateArticleContent(ArticleContent articleContent) {

        return associationMgrService.updateArticleContent(articleContent);

    }

    /**
     * 活动列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/findAllBanner")
    @ResponseBody
    public Result findAllBanner(ManagerParam param) {

        return associationMgrService.findAllBanner(param.getPageNumber(), param.getPageSize());
    }

    /**
     * 活动检索
     *
     * @param banner
     * @param param
     * @return
     */
    @RequestMapping("/findAllBannerByTitle")
    @ResponseBody
    public Result findAllBannerByTitle(Banner banner, ManagerParam param) throws Exception {

        //自负转码
        if (null != banner.getBannerTitle()) {
            //转码
            String val = URLDecoder.decode(banner.getBannerTitle(), "UTF-8");
            banner.setBannerTitle(val);
        }
        return associationMgrService.findAllBannerByTitle(banner, param.getPageNumber(), param.getPageSize());
    }

    /**
     * 时间检索
     *
     * @param banner
     * @param param
     * @return
     */
    @RequestMapping("/findAllBannerByTime")
    @ResponseBody
    public Result findAllBannerByTime(Banner banner, ManagerParam param) {

        return associationMgrService.findAllBannerByTime(banner, param.getPageNumber(), param.getPageSize());
    }

    /**
     * 编辑活动
     */
    @RequestMapping("/findAllBannerById")
    public ModelAndView findAllBannerById(Banner banner) {
        Banner banner1 = associationMgrService.findAllBannerById(banner);
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", banner1);
        mav.setViewName("/activity/editBanner");
        return mav;
    }

    /**
     * 活动添加
     *
     * @param banner
     * @return
     */
    @RequestMapping("/insertAllBanner")
    @ResponseBody
    public Result insertAllBanner(Banner banner) {

        return associationMgrService.insertAllBanner(banner);
    }


    /**
     * 活动更新
     *
     * @param banner
     * @return
     */
    @RequestMapping("/updateAllBanner")
    @ResponseBody
    public Result updateAllBanner(Banner banner) {

        return associationMgrService.updateAllBanner(banner);

    }

    /**
     * 活动删除
     *
     * @param banner
     * @return
     */
    @RequestMapping("/deleteAllBanner")
    @ResponseBody
    public Result deleteAllBanner(Banner banner) {

        return associationMgrService.deleteAllBanner(banner);
    }

    /**
     * 文章预览
     *
     * @param text
     * @return
     */
    @RequestMapping("/preview")
    public String articleEdit(String text, Model model, HttpServletRequest request) throws Exception {

        String newBackPath = writeFile("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>文章预览</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "  <div>\n" +
                "    <span>\n" + text + "</span>\n" +
                "  </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n", request, 1);

        model.addAttribute("content", text);
        model.addAttribute("url", newBackPath);
        return "/association/summernote";
    }

    /**
     * 文章内容提交
     */
    @RequestMapping("/preUrl")
    @ResponseBody
    public String articleAdd(String articleTitle, Integer specialColumnId, String articlePic,
                             String text, Model model, HttpServletRequest request, Article article) throws Exception {

        if (text.length() > 0) {
            String strPath = writeFile("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <title>文章预览</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "  <div>\n" +
                    "    <span>\n" + text + "</span>\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>\n", request, 2);

            FileInputStream file = new FileInputStream(new File(strPath));
            Map<String, String> ret = Maps.newHashMap();
            if (!strPath.isEmpty()) {
                ret = fdfsService.uploadStream(file.available(), new File(strPath).getName(), file);
                if (!"0".equals(ret.get("code"))) {

                    return "";
                }
                //服务器返回的URL地址

                String articleUrl = ret.get("fileStorePath");
                System.out.println(">>>>>>>>>>>>" + articleUrl);
                article.setArticleUrl(articleUrl);


            }
            article.setArticleTitle(articleTitle);
            article.setSpecialColumnId(specialColumnId);
            article.setArticleUrl(article.getArticleUrl());
            return associationMgrService.insertArticle(article);
        } else {
            return null;
        }
    }

    /**
     * 文章返回
     *
     * @param back
     * @return
     */
    @RequestMapping("/back")
    public String articleBack(String back, Model model) {

        model.addAttribute("back", back);
        return "/association/upload";
    }

    /**
     * 创建文件
     *
     * @param content
     * @param request
     */
    public String writeFile(String content, HttpServletRequest request, Integer type) throws Exception {

        //得到IE地址栏地址 结果：E:/Tomcat/webapps/TEST
        String path = request.getRequestURL().toString();
        //得到相对地址 结果：/TEST/test
        String newPath = request.getRequestURI();
        String backPath = path.substring(0, path.length() - newPath.length());

        //获取项目的绝对地址
        String finalPath = request.getSession().getServletContext().getRealPath("/");
        int time = DateUtil.currentSecond();
        //返回访问地址
        String newBackPath = backPath + "/hrym-app-manager/view/html5/" + time + ".html";
        System.out.println(">>>>>>>>>>>>" + newBackPath);
        //写入项目地址
        String strPath = finalPath + "view/html5/" + time + ".html";
        System.out.println(">>>>>>>>>>>>" + strPath);

        File file = new File(strPath);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        file.createNewFile();

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(strPath),
                "UTF-8"));
        out.write(content);
        out.newLine();
        out.flush();
        out.close();

        if (2 == type) {
            return strPath;
        } else {
            return newBackPath;
        }

    }

    /**
     * 记录愿望
     *
     * @param desire
     * @return
     */
    @RequestMapping(value = "/insertAllDesire")
    @Allowed
    @ResponseBody
    public String insertAllDesire(Desire desire, String callback) {

        Map<String, Object> ret = associationMgrService.insertALLDesire(desire);

        String result = callback + "(" + JSONObject.fromObject(ret).toString() + ");";//拼接可执行的js

        return result;


    }

    /**
     * 查找许愿总数
     *
     * @return
     */
    @RequestMapping("/findAllCountByDesire")
    @Allowed
    @ResponseBody
    public String findAllCountByDesire(String callback) {
        Map<String, Object> ret = associationMgrService.findAllCountByDesire();

        String result = callback + "(" + JSONObject.fromObject(ret).toString() + ");";//拼接可执行的js

        return result;
    }

    /**
     * 许愿清单
     *
     * @param param
     * @return
     */
    @RequestMapping("/findAllDesire")
    @ResponseBody
    public Result findAllDesire(ManagerParam param) {

        return associationMgrService.findAllDesire(param.getPageNumber(), param.getPageSize());
    }

    /**
     * 任务记录列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/findAllWishActivity")
    @ResponseBody
    public Result findAllWishActivity(ManagerParam param) {
        return associationMgrService.findAllWishActivity(param.getPageNumber(), param.getPageSize());
    }

    /**
     * 任务记录
     *
     * @param wishActivity
     * @param callback
     * @return
     */
    @RequestMapping("/insertAllWishActivity")
    @ResponseBody
    @Allowed
    public String insertAllWishActivity(WishActivity wishActivity, String callback) {

        Map<String, Object> ret = associationMgrService.insertAllWishActivity(wishActivity);
        String result = callback + "(" + JSONObject.fromObject(ret).toString() + ");";//拼接可执行的js

        return result;
    }

    /**
     * 首页接口
     *
     * @param userId
     * @param callback
     * @return
     */
    @RequestMapping("/findWishActivity")
    @Allowed
    @ResponseBody
    public String findWishActivity(Integer userId, String callback) {

        Map<String, Object> ret = associationMgrService.findWishActivity(userId);
        String re = callback + "(" + JSONObject.fromObject(ret).toString() + ")";
        return re;
    }

    /**
     * 获取所有抽奖名单
     *
     * @return
     */
    @RequestMapping("/findAll")
    @Allowed
    @ResponseBody
    public Result findAllWishActivity() {

        List<WishActivity> activityList = associationMgrService.findAllWishActivity();
        return new Result(BaseConstants.GWSCODE0000, BaseConstants.GWSMSG0000, activityList);
    }

    /**
     * 更新中奖状态
     *
     * @param id
     */
    @RequestMapping("/updateWinner")
    @Allowed
    @ResponseBody
    public Result updateWinnerByUserId(Integer id) {

        associationMgrService.updateWinnerByUserId(id);
        return new Result(BaseConstants.GWSCODE0000, BaseConstants.GWSMSG0000, null);
    }
}
