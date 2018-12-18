package com.hrym.rpc.association.service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.common.constant.AssociationParam;
import com.hrym.rpc.app.dao.model.association.Article;
import com.hrym.rpc.app.dao.model.association.ArticleContent;
import com.hrym.rpc.app.dao.model.association.ResourceFile;
import com.hrym.rpc.association.ResourceService;
import com.hrym.rpc.association.dao.mapper.ArticleContentMapper;
import com.hrym.rpc.association.dao.mapper.ArticleMapper;
import com.hrym.rpc.association.dao.mapper.ResourceFileMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 资料业务层
 * Created by mj on 2017/9/14.
 */
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;
    @Autowired
    private ResourceFileMapper resourceFileMapper;

    /**
     * 文章详情
     * @param param
     * @return
     */
    @Override
    public BaseResult getArticleDetails(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String,Object> maps = Maps.newHashMap();
        if (null != param.getArticleId()){
            //查询文章获取封面图
            Article article = articleMapper.findAllArticleById(param.getArticleId());
            //查找文章ID对应的文章内容
            List<ArticleContent> contentList = articleContentMapper.findArticleContentByArticleId(param.getArticleId());
            List<Map<String,Object>> list = new ArrayList<>();
            for (ArticleContent a : contentList){
                Map<String,Object> map = Maps.newHashMap();
                map.put("content",a.getContentValue());
                map.put("pic",a.getPic());
                map.put("typeValue",a.getContentType());
                list.add(map);
            }
            maps.put("articlePic",article.getArticlePic());
            maps.put("list",contentList);
        }

        return new BaseResult(code,message,maps);
    }

    /**
     * 资料上传
     * @param param
     * @return
     */
    @Override
    public BaseResult resourceUpload(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //资源文件对象
        ResourceFile resourceFile = new ResourceFile();

        resourceFile.setAssociationId(param.getAssociationId());
        resourceFile.setResourceDesc(param.getResourceDesc());
        resourceFile.setResourceName(param.getResourceName());
        resourceFile.setResourceType(param.getResourceType());
        resourceFile.setResourceUrl(param.getResourceUrl());
        resourceFile.setCreateUserId(param.getUserId());
        resourceFile.setCreateUserName(param.getUserName());
        resourceFile.setResourceDisplayName(param.getResourceDisplayName());
        resourceFile.setCreateTime(DateUtil.currentSecond());

        resourceFileMapper.saveResourceFile(resourceFile);

        return new BaseResult(code,message,null);
    }

    /**
     * 资料列表
     * @param param
     * @return
     */
    @Override
    public BaseResult resourceList(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
        List<ResourceFile> resourceFileList = resourceFileMapper.findResourceFileByAssociationId(param.getAssociationId(),param.getFilterStr());

        List<Map<String,Object>> mapList = new ArrayList<>();
        for (ResourceFile r : resourceFileList){
            Map<String,Object> map = Maps.newHashMap();
            map.put("resourceId",r.getIdtResource());
            map.put("resourceName",r.getResourceName());
            map.put("resourceType",r.getResourceType());
            map.put("resourceUrl",r.getResourceUrl());
            map.put("userName",r.getCreateUserName());
            map.put("createTime",DateUtil.timestampToDates(r.getCreateTime(),DateUtil.TIME_PATTON_MMdd2));
            mapList.add(map);
        }
        PageInfo pageInfo = new PageInfo(resourceFileList);
        Map<String,Object> map = Maps.newHashMap();
        map.put("list",mapList);
        map.put("hasNextPage",pageInfo.isHasNextPage());

        return new BaseResult(code, message, map);
    }
}
