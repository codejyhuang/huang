package com.hrym.app.service.impl;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.association.*;
import com.hrym.rpc.app.util.Result;
import com.hrym.app.service.AssociationMgrService;
import com.hrym.app.dao.ActivityMgrMapper;
import com.hrym.app.dao.ArticleContentMgrMapper;
import com.hrym.app.dao.ArticleMgrMapper;
import com.hrym.app.dao.AssociationMgrMapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Chars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;


/**
 * Created by hrym13 on 2017/8/24.
 */
@Service
public class AssociationMgrServiceImpl implements AssociationMgrService {

    @Autowired
    private ArticleMgrMapper articleMgrMapper;
    @Autowired
    private AssociationMgrMapper associationMgrMapper;
    @Autowired
    private  ArticleContentMgrMapper articleContentMgrMapper;
    @Autowired
    private ActivityMgrMapper activityMgrMapper;

    /**
     * 查询社群
     * @return
     */
    @Override
    public Result findAllAssociation(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page, rows);
        List<Association> associations = associationMgrMapper.findAllAssociation();

        PageInfo pageInfo = new PageInfo(associations);
        List<Map<String,Object>> list = new ArrayList<>();
        if (0<associations.size()){
            for (Association  a : associations){
                int num = associationMgrMapper.findCountAssociation(a.getIdtAssociation());
                Map<String,Object> map = Maps.newHashMap();
                map.put("idtAssociation",a.getIdtAssociation());
                map.put("avatarUrl",a.getAvatarUrl());
                map.put("createTime",DateUtil.timestampToDates(a.getCreateTime(),DateUtil.DATE_PATTON_DEFAULT));
                map.put("intro",a.getIntro());
                map.put("rqCodeUrl",a.getRqCodeUrl());
                map.put("name",a.getName());
                if(null != a.getType()){
                    switch (a.getType()){
                        case 1 :map.put("type","交流群");break;
                        case 0 : map.put("type","系统学习佛学");break;
                    }
                }
                map.put("userId",a.getUserId());
                map.put("userName",a.getUserName());
                map.put("count",num);
                list.add(map);
            }
        }
            long toal=pageInfo.getTotal();
            return new Result(code,message,toal,list);

    }

    /**
     * 社群检索
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result searchAllAssociation( Association association,Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if ( null == association.getIdtAssociation() && null == association.getName()){
            return findAllAssociation(page,rows);
        }

        String name = association.getName();
        if (null != association.getName()) {
            //分页核心代码
            PageHelper.startPage(page, rows);
            List<Association> associations = associationMgrMapper.searchByName(association.getName());

            PageInfo pageInfo = new PageInfo(associations);
            List<Map<String, Object>> list = new ArrayList<>();
            if (0 < associations.size()) {
                for (Association a : associations) {
                    //社群人数
                    int num = associationMgrMapper.findCountAssociation(a.getIdtAssociation());
                    Map<String, Object> map = Maps.newHashMap();
                    map.put("idtAssociation", a.getIdtAssociation());
                    map.put("avatarUrl", a.getAvatarUrl());
                    map.put("createTime", DateUtil.timestampToDates(a.getCreateTime(), DateUtil.TIME_PATTON_DEFAULT));
                    map.put("intro", a.getIntro());
                    map.put("rqCodeUrl", a.getRqCodeUrl());
                    map.put("name", a.getName());
                    if (null != a.getType()) {
                        switch (a.getType()) {
                            case 1:
                                map.put("type", "交流群");
                                break;
                            case 0:
                                map.put("type", "系统学习佛学");
                                break;
                        }
                    }
                    map.put("userId", a.getUserId());
                    map.put("userName", a.getUserName());
                    map.put("count", num);
                    list.add(map);
                }
            }
            long toal = pageInfo.getTotal();
            return new Result(code, message, toal, list);
        }else {
            //分页核心代码
            PageHelper.startPage(page, rows);
            List<Association> associations = associationMgrMapper.searchByIdtAssociation(association.getIdtAssociation());

            PageInfo pageInfo = new PageInfo(associations);
            List<Map<String, Object>> list = new ArrayList<>();
            if (0 < associations.size()) {
                for (Association a : associations) {
                    //社群人数
                    int num = associationMgrMapper.findCountAssociation(a.getIdtAssociation());
                    Map<String, Object> map = Maps.newHashMap();
                    map.put("idtAssociation", a.getIdtAssociation());
                    map.put("avatarUrl", a.getAvatarUrl());
                    map.put("createTime", DateUtil.timestampToDates(a.getCreateTime(), DateUtil.TIME_PATTON_DEFAULT));
                    map.put("intro", a.getIntro());
                    map.put("rqCodeUrl", a.getRqCodeUrl());
                    map.put("name", a.getName());
                    if (null != a.getType()) {
                        switch (a.getType()) {
                            case 1:
                                map.put("type", "交流群");
                                break;
                            case 0:
                                map.put("type", "系统学习佛学");
                                break;
                        }
                    }
                    map.put("userId", a.getUserId());
                    map.put("userName", a.getUserName());
                    map.put("count", num);
                    list.add(map);
                }
            }
            long toal = pageInfo.getTotal();
            return new Result(code, message, toal, list);
        }
    }

    //文章管理
    @Override
    public Result findAllArticle(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page,rows);
        List<Article> allArticle = articleMgrMapper.findAllArticle();

        PageInfo pageInfo = new PageInfo(allArticle);
        List<Map<String,Object>> list = new ArrayList<>();
        if (0<allArticle.size()){
            for (Article e : allArticle){
                Map<String,Object> map = Maps.newHashMap();
                map.put("articleTitle",e.getArticleTitle());
                map.put("createTime", DateUtil.timestampToDates(e.getCreateTime(),DateUtil.TIME_PATTON_DEFAULT));
                map.put("idtArticle",e.getIdtArticle());
                map.put("specialColumnId",e.getSpecialColumnId());
                map.put("updateTime",e.getUpdateTime());
                map.put("userId",e.getUserId());
                map.put("articleUrl",e.getArticleUrl());
                map.put("articleAbstract",e.getArticleAbstract());
                if (null !=e.getSpecialColumn()) {
                    map.put("columnName", e.getSpecialColumn().getColumnName());
                }else {
                    map.put("columnName", null);
                }

                if (null != e.getStatusArticle()){
                    switch (e.getStatusArticle()){
                        case 0: map.put("statusArticle","未发表");break;
                        case 1: map.put("statusArticle","已发表");break;
                    }
                }
                list.add(map);

            }
        }
        long toal=pageInfo.getTotal();
        return new Result(code,message,toal,list);
    }

    /**
     * 文章内容保存
     */
    @Override
    public Result insertAllArticle(Article article) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        article.setCreateTime(DateUtil.currentSecond());
        if (null == article.getStatusArticle()){
            article.setStatusArticle(0);
        }
        articleMgrMapper.insertArticle(article);

        int articleId = articleMgrMapper.getLastInsertId();
        Map map = Maps.newHashMap();
        map.put("articleId",articleId);

        return new Result(code,message,map);
    }

    /**
     * 文章保存带URL
     * @param article
     * @return
     */
    @Override
    public String insertArticle(Article article) {

        if (article.getArticleUrl().length()>0){
            article.setCreateTime(DateUtil.currentSecond());
            article.setUpdateTime(DateUtil.currentSecond());
            if (null == article.getStatusArticle()){
                article.setStatusArticle(0);
            }
            articleMgrMapper.insertArticle(article);

            return new String(article.getArticleUrl());
        }else {
            return new String(article.getArticleUrl());
        }

    }

    /**
     * 文章内容发表
     * @param articleContent
     * @return
     */
    @Override
    public Result insertArticleContent(ArticleContent articleContent) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null == articleContent.getArticleId()){
            return new Result("1","请先添加文章",null);
        }

        articleContentMgrMapper.insertArticleContent(articleContent);
        List<ArticleContent> list = articleContentMgrMapper.findContentByContentType(articleContent.getArticleId(),articleContent.getContentType());

        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getContentType() == 0){
                list.get(i).setTitle("正文"+(i+1)+"   "+">>>>>>"+"  "+"点击此处查看详细信息");
            }
            if (list.get(i).getContentType() == 1){
                list.get(i).setTitle("引用"+(i+1)+"   "+">>>>>>"+"  "+"点击此处查看详细信息");
            }
            if (list.get(i).getContentType() == 2){
                list.get(i).setTitle("图片"+(i+1)+"   "+">>>>>>"+"  "+"点击此处查看详细信息");
            }
        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("contentList",list);

        return new Result(code,message,map);
    }


    /**
     * 文章检索
     * @param article
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result searchAllArticle(Article article, Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null == article.getArticleTitle() || ""==article.getArticleTitle()){

            return findAllArticle( page,rows);
        }

        //分页核心代码
        PageHelper.startPage(page,rows);
        List<Article> articles = articleMgrMapper.searchAllArticle(article.getArticleTitle());

        PageInfo pageInfo = new PageInfo(articles);
        List<Map<String,Object>> list = new ArrayList<>();

        if (0<articles.size()){
            for (Article e : articles){
                Map<String,Object> map = Maps.newHashMap();
                map.put("articleTitle",e.getArticleTitle());
                map.put("createTime", DateUtil.timestampToDates(e.getCreateTime(),DateUtil.TIME_PATTON_DEFAULT));
                map.put("idtArticle",e.getIdtArticle());
                map.put("specialColumnId",e.getSpecialColumnId());
                map.put("updateTime",e.getUpdateTime());
                map.put("userId",e.getUserId());
                map.put("articleUrl",e.getArticleUrl());
                map.put("articleAbstract",e.getArticleAbstract());
                if (null !=e.getSpecialColumn()) {
                    map.put("columnName", e.getSpecialColumn().getColumnName());
                }else {
                    map.put("columnName", null);
                }
                if (null != e.getStatusArticle()){
                    switch (e.getStatusArticle()){
                        case 0: map.put("statusArticle","未发表");break;
                        case 1: map.put("statusArticle","已发表");break;
                    }
                }
                list.add(map);

            }
        }
        long total=pageInfo.getTotal();
        return new Result(code,message,total,list);
    }

    /**
     * 专栏名称搜索
     * @param specialColumn
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result searchAllSpecialName(SpecialColumn specialColumn, Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null == specialColumn.getColumnName()){

            return findAllArticle( page,rows);
        }
        //分页核心代码
        PageHelper.startPage(page,rows);
        List<SpecialColumn> spe = articleMgrMapper.searchAllSpecialName(specialColumn.getColumnName());
        PageInfo pageInfo = new PageInfo(spe);
        List<Map<String,Object>> list = new ArrayList<>();

        if (0<spe.size()){
            for (SpecialColumn e : spe){
                Map<String,Object> map = Maps.newHashMap();
                if (null !=e.getArticle()||null !=e.getArticle().getSpecialColumnId()) {
                    map.put("articleTitle", e.getArticle().getArticleTitle());
                    map.put("createTime", DateUtil.timestampToDates(e.getArticle().getCreateTime(), DateUtil.TIME_PATTON_DEFAULT));
                    map.put("idtArticle", e.getArticle().getIdtArticle());
                    map.put("specialColumnId", e.getArticle().getSpecialColumnId());
                    map.put("updateTime", e.getArticle().getUpdateTime());
                    map.put("userId", e.getArticle().getUserId());
                    map.put("articleUrl",e.getArticle().getArticleUrl());
                    map.put("articleAbstract",e.getArticle().getArticleAbstract());
                    if (null != e.getColumnName()) {
                        map.put("columnName", e.getColumnName());
                    } else {
                        map.put("columnName", null);
                    }

                    if (null != e.getArticle().getStatusArticle()) {
                        switch (e.getArticle().getStatusArticle()) {
                            case 0:
                                map.put("statusArticle", "未发表");
                                break;
                            case 1:
                                map.put("statusArticle", "已发表");
                                break;
                        }
                    }
                }
                list.add(map);

            }
        }
        long total=pageInfo.getTotal();
         return new Result(code,message,total,list);
    }


    /**
     *  文章删除
     */
    @Override
    public Result deleteAllArticle(Article article) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null !=article.getIdtArticle() ){

            articleMgrMapper.deleteAllArticle(article.getIdtArticle());
            articleContentMgrMapper.deletetArticleContent(article.getIdtArticle());

        }else {

            code = BaseConstants.GWSCODE2003;
            message = BaseConstants.GWSMSG2001;

        }

        return  new Result(code,message,null);
    }

    /**
     * 文章内容删除
     * @param articleContent
     * @return
     */
    public Result deleteArticleContent(ArticleContent articleContent){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != articleContent.getIdtArticleContent()){

            articleContentMgrMapper.deleteArticleContent(articleContent.getIdtArticleContent());
        }else {

            code = BaseConstants.GWSCODE2001;
            message = BaseConstants.GWSMSG2001;
        }
        return new Result(code,message,null);
    }

    /**
     * 文章内容名称查找
     * @param
     * @return
     */
    @Override
    public Result findColumnName() {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<SpecialColumn> spe = articleMgrMapper.findColumnName();//获取名称

        return new Result (code,message,spe) ;
    }

    /**
     * 编辑文章
     * @param article
     * @return
     */
    @Override
    public Article editArticleById(Article article) {

        Article art = articleMgrMapper.findArticleById(article.getIdtArticle());
        List<ArticleContent> list = articleContentMgrMapper.findArticleContentById(art.getIdtArticle());
        int n = 0;
        int m = 0;
        int l = 0;
        for (ArticleContent a : list){
            if (a.getContentType() == 0){
                a.setTitle("正文"+(n+1)+"   "+">>>>>>"+"  "+"点击此处查看详细信息");
                n = n+1;
            }
            if (a.getContentType() == 1){
                a.setTitle("引用"+(m+1)+"   "+">>>>>>"+"  "+"点击此处查看详细信息");
                m = m+1;
            }
            if (a.getContentType() == 2){
                a.setTitle("图片加文字"+(l+1)+"   "+">>>>>>"+"  "+"点击此处查看详细信息");
                l = l+1;
            }
        }
        art.setArticleContentList(list);

        return art;
    }

    /**
     * 文章修改
     * @param article
     * @return
     */
    @Override
    public Result updateAllArticle(Article article) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        article.setUpdateTime(DateUtil.currentSecond());
        articleMgrMapper.updateAllArticle(article);

        return new Result(code,message,null);
    }

    /**
     * 文章内容修改
     * @param articleContent
     * @return
     */
    @Override
    public Result updateArticleContent(ArticleContent articleContent) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        articleContentMgrMapper.updateArticleContent(articleContent);


        return new Result(code,message,null);
    }

    /**
     * 文章发表
     * @param article
     * @return
     */
    @Override
    public Result updateArticle(Article article) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        article.setUpdateTime(DateUtil.currentSecond());
        articleMgrMapper.updateArticle(article);

        return new Result(code,message,null);
    }

    /**
     * 活动列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllBanner(Integer page,Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(page, rows);
        List<Banner> banners =activityMgrMapper.findAllBanner();
        for (Banner t : banners){
            if (null != t.getStartTime()){
                String start = DateUtil.timestampToDates(t.getStartTime(),TIME_PATTON_DEFAULT);
                t.setStartTimeis(start);
            }
            if (null != t.getEndTime()){
                String end = DateUtil.timestampToDates(t.getEndTime(),TIME_PATTON_DEFAULT);
                t.setEndTimeis(end);
            }
        }

        PageInfo pageInfo = new PageInfo(banners);
        long total = pageInfo.getTotal();
        return new Result(code,message,total,banners);
    }

    /**
     * 活动编辑
     * @param banner
     * @return
     */
    @Override
    public Banner findAllBannerById (Banner banner) {

        Banner ban = activityMgrMapper.findAllBannerById(banner.getBannerId());

            if (null != ban.getStartTime()){
                String start = DateUtil.timestampToDates(ban.getStartTime(),TIME_PATTON_DEFAULT);
                ban.setStartTimeis(start);
            }
            if (null != ban.getEndTime()){
                String end = DateUtil.timestampToDates(ban.getEndTime(),TIME_PATTON_DEFAULT);
                ban.setEndTimeis(end);
            }
            if (StringUtils.isNotEmpty(ban.getShareChannel())){
                //字符串分割成数组
                char[] cc=ban.getShareChannel().toCharArray();
                ban.setShareChannelA(String.valueOf(cc[0]));
                ban.setShareChannelB(String.valueOf(cc[1]));
                ban.setShareChannelC(String.valueOf(cc[2]));
                ban.setShareChannelD(String.valueOf(cc[3]));
                ban.setShareChannelE(String.valueOf(cc[4]));
            }

        return ban;
    }

    /**
     * 活动标题检索
     * @param banner
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllBannerByTitle(Banner banner, Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null==banner.getEndTime() && null ==banner.getBannerTitle()){

            return findAllBanner(page,rows);
        }
        //分页核心代码
        PageHelper.startPage(page,rows);
        List<Banner> banners = activityMgrMapper.findAllBannerByTitle(banner.getBannerTitle());
        for (Banner ban : banners) {
            if (null != ban.getStartTime()) {
                String start = DateUtil.timestampToDates(ban.getStartTime(), TIME_PATTON_DEFAULT);
                ban.setStartTimeis(start);
            }
            if (null != ban.getEndTime()) {
                String end = DateUtil.timestampToDates(ban.getEndTime(), TIME_PATTON_DEFAULT);
                ban.setEndTimeis(end);
            }
        }

        PageInfo pageInfo = new PageInfo();
        long total = pageInfo.getTotal();


        return new Result(code,message,total,banners);
    }

    /**
     * 时间检索
     * @param banner
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllBannerByTime(Banner banner, Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (StringUtils.isNotBlank(banner.getStartTimeis())){
            int start = DateUtil.getDateToLinuxTime(banner.getStartTimeis(),TIME_PATTON_DEFAULT);
            banner.setStartTime(start);
        }
        if(StringUtils.isNotBlank(banner.getEndTimeis())){
            int end = DateUtil.getDateToLinuxTime(banner.getEndTimeis(),TIME_PATTON_DEFAULT);
            banner.setEndTime(end);
        }
        if (null==banner.getEndTime() || null==banner.getStartTime()){

            if ( null==banner.getEndTime()){

                PageHelper.startPage(page,rows);
                List<Banner> banners=activityMgrMapper.findAllBannerBystartTime(banner.getStartTime());
                for (Banner ban : banners) {

                    if (null != ban.getStartTime()) {

                        String start = DateUtil.timestampToDates(ban.getStartTime(), TIME_PATTON_DEFAULT);
                        ban.setStartTimeis(start);
                    }
                    if (null != ban.getEndTime()) {

                        String end = DateUtil.timestampToDates(ban.getEndTime(), TIME_PATTON_DEFAULT);
                        ban.setEndTimeis(end);
                    }
                }

                PageInfo pageInfo = new PageInfo(banners);
                long total = pageInfo.getTotal();


                return new Result(code,message,total,banners);
            }
            if ( null==banner.getStartTime()){

                PageHelper.startPage(page,rows);
                List<Banner> banners=activityMgrMapper.findAllBannerByendTime(banner.getEndTime());
                for (Banner ban : banners) {

                    if (null != ban.getStartTime()) {

                        String start = DateUtil.timestampToDates(ban.getStartTime(), TIME_PATTON_DEFAULT);
                        ban.setStartTimeis(start);
                    }
                    if (null != ban.getEndTime()) {

                        String end = DateUtil.timestampToDates(ban.getEndTime(), TIME_PATTON_DEFAULT);
                        ban.setEndTimeis(end);
                    }
                }

                PageInfo pageInfo = new PageInfo(banners);
                long total = pageInfo.getTotal();


                return new Result(code,message,total,banners);
            }
        }
        //分页核心代码
        PageHelper.startPage(page,rows);
        List<Banner> banners=activityMgrMapper.findAllBannerByTime(banner.getStartTime(),banner.getEndTime());

        for (Banner ban : banners) {

            if (null != ban.getStartTime()) {

                String start = DateUtil.timestampToDates(ban.getStartTime(), TIME_PATTON_DEFAULT);
                ban.setStartTimeis(start);
            }
            if (null != ban.getEndTime()) {

                String end = DateUtil.timestampToDates(ban.getEndTime(), TIME_PATTON_DEFAULT);
                ban.setEndTimeis(end);
            }
        }

        PageInfo pageInfo = new PageInfo(banners);
        long total = pageInfo.getTotal();


        return new Result(code,message,total,banners);
    }
    /**
     * 活动添加
     * @param banner
     * @return
     */
    @Override
    public Result insertAllBanner(Banner banner) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (StringUtils.isNotBlank(banner.getStartTimeis())){
            int start = DateUtil.getDateToLinuxTime(banner.getStartTimeis(),TIME_PATTON_DEFAULT);
            banner.setStartTime(start);
        }
        if (StringUtils.isNotBlank(banner.getEndTimeis())){
            int end = DateUtil.getDateToLinuxTime(banner.getEndTimeis(),TIME_PATTON_DEFAULT);
            banner.setEndTime(end);
        }
        if (banner.getShareUrl().length()==0){
            banner.setShareUrl(banner.getBannerUrl());
        }
        if (null ==banner.getShareChannelA()){
            banner.setShareChannelA("0");
        }
        if (null ==banner.getShareChannelB()){
            banner.setShareChannelB("0");
        }
        if (null ==banner.getShareChannelC()){
            banner.setShareChannelC("0");
        }
        if (null ==banner.getShareChannelD()){
            banner.setShareChannelD("0");
        } if (null ==banner.getShareChannelE()){
            banner.setShareChannelE("0");
        }
        banner.setShareChannel(banner.getShareChannelA()+
                banner.getShareChannelB()+banner.getShareChannelC()+
                banner.getShareChannelD()+banner.getShareChannelE());


        activityMgrMapper.insertAllBanner(banner);

        return new Result(code,message,null);
    }

    /**
     * 活动更新
     * @param banner
     * @return
     */
    @Override
    public Result updateAllBanner(Banner banner) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (StringUtils.isNotBlank(banner.getStartTimeis())){
            int start = DateUtil.getDateToLinuxTime(banner.getStartTimeis(),TIME_PATTON_DEFAULT);
            banner.setStartTime(start);
        }
        if (StringUtils.isNotBlank(banner.getEndTimeis())){
            int end = DateUtil.getDateToLinuxTime(banner.getEndTimeis(),TIME_PATTON_DEFAULT);
            banner.setEndTime(end);
        }
        if (null ==banner.getShareChannelA()){
            banner.setShareChannelA("0");
        }
        if (null ==banner.getShareChannelB()){
            banner.setShareChannelB("0");
        }
        if (null ==banner.getShareChannelC()){
            banner.setShareChannelC("0");
        }
        if (null ==banner.getShareChannelD()){
            banner.setShareChannelD("0");
        } if (null ==banner.getShareChannelE()){
            banner.setShareChannelE("0");
        }
        banner.setShareChannel(banner.getShareChannelA()+
                banner.getShareChannelB()+banner.getShareChannelC()+
                banner.getShareChannelD()+banner.getShareChannelE());


        activityMgrMapper.updateAllBanner(banner);

        return new Result(code,message,null);
    }

    /**
     * 活动删除
     * @param banner
     * @return
     */
    @Override
    public Result deleteAllBanner(Banner banner) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        if (null !=banner.getBannerId()){

            activityMgrMapper.deleteAllBanner(banner.getBannerId());

        }
        return new Result(code,message,null);
    }


    /**
     * 记录愿望
     * @param desire
     * @return
     */
    @Override
    public Map<String ,Object> insertALLDesire(Desire desire) {

        Map<String,Object> ret = Maps.newHashMap();
        if (StringUtils.isNotBlank(desire.getDesire())){

            desire.setCreatedTime(DateUtil.currentSecond());
            activityMgrMapper.insertALLDesire(desire);
        }
        ret.put("code",BaseConstants.GWSCODE0000);
        ret.put("message",BaseConstants.GWSMSG0000);

        return ret;
    }


    /**
     * 愿望总数查询
     * @return
     */
    @Override
    public Map<String, Object> findAllCountByDesire() {


        Map<String,Object> ret = Maps.newHashMap();
        Integer count=activityMgrMapper.findAllCountByDesire();

        ret.put("code",BaseConstants.GWSCODE0000);
        ret.put("message",BaseConstants.GWSMSG0000);
        ret.put("total",count);
        return  ret;
    }

    /**
     * 愿望清单
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllDesire(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        PageHelper.startPage(page,rows,"created_time desc");
        List<Desire> desireList = activityMgrMapper.findAllByDesire();

        for(Desire desire :desireList){
            if (null != desire.getCreatedTime()){
                String time = DateUtil.timestampToDates(desire.getCreatedTime(),TIME_PATTON_DEFAULT);
                desire.setCreatedTimes(time);
            }
            desire.setId(desire.getId());
            desire.setDesire(desire.getDesire());
        }
        PageInfo pageInfo = new PageInfo(desireList);
        long total = pageInfo.getTotal();
        return new Result(code,message,total,desireList);
    }

    /**
     * 查看完成任务列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllWishActivity(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        PageHelper.startPage(page,rows,"create_time desc");
        List<WishActivity> wish = activityMgrMapper.findAllWishActivity();

        for (WishActivity w :wish){
            if (null != w.getCreateTime()){
                String time = DateUtil.timestampToDates(w.getCreateTime(),TIME_PATTON_DEFAULT);
                w.setCreateTimes(time);
            }
        }
        PageInfo pageInfo = new PageInfo(wish);
        long total = pageInfo.getTotal();
        return new Result(code,message,total,wish);
    }

    /**
     *添加任务记录
     * @param wishActivity
     * @return
     */
    @Override
    public Map<String, Object> insertAllWishActivity(WishActivity wishActivity) {

        Map<String,Object> map = Maps.newHashMap();

            if (null != wishActivity.getUserId()){
                List <WishActivity> list = activityMgrMapper.findWishActivityByUserId(wishActivity.getUserId());
                if ( list.size()>0){
                    activityMgrMapper.updateALLWishActivity(wishActivity);
                }else {
                    wishActivity.setCreateTime(DateUtil.currentSecond());
                    activityMgrMapper.insertALLWishActivity(wishActivity);
                }
            }

        map.put("code",BaseConstants.GWSCODE0000);
        map.put("message",BaseConstants.GWSMSG0000);
        return map;
    }

    /**
     * 活动任务首页接口
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> findWishActivity(Integer userId) {

        Map<String,Object> map = Maps.newHashMap();
        if (null != userId){
            List<WishActivity> status = activityMgrMapper.findStatusByUserId(userId);
            if (status.size()>0){
                map.put("status",1);
            }else {
                map.put("status",0);
            }
            List<WishActivity> list = activityMgrMapper.findPhoneByUserId(userId);
            if (list.size()>0){
                map.put("phone",1);
            }else {
                map.put("phone",0);
            }

        }
        Integer number = activityMgrMapper.wishActivityNumber();
        map.put("number",number);


        return map;
    }

//    /**
//     * 根据用户ID修改完成任务状态
//     * @param wishActivity
//     * @return
//     */
//    @Override
//    public Map<String, Object> updateAllWishActivity(WishActivity wishActivity) {
//
//        Map<String,Object> ret = Maps.newHashMap();
//        if (null !=wishActivity.getUserId() && 1== wishActivity.getStatus()){
//            activityMgrMapper.updateALLWishActivity(wishActivity);
//
//            ret.put("code",BaseConstants.GWSMSG0000);
//            ret.put("message",BaseConstants.GWSMSG0000);
//        }else {
//            ret.put("code",BaseConstants.GWSCODE3001);
//            ret.put("message",BaseConstants.GWSMSG3001);
//        }
//
//
//        return ret;
//    }

    /**
     * 获取所有抽奖名单
     * @return
     */
    @Override
    public List<WishActivity> findAllWishActivity() {

        List<WishActivity> activityList = activityMgrMapper.findAll();
        return activityList;
    }

    /**
     * 更新中奖状态
     * @param id
     */
    @Override
    public void updateWinnerByUserId(Integer id) {

        activityMgrMapper.updateWinnerByUserId(id);

    }

}
