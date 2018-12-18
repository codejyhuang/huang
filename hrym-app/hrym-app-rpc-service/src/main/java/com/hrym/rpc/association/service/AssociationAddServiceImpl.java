package com.hrym.rpc.association.service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.base.PageInfo;
import com.hrym.common.enums.AssociationMemberRole;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.util.DateFormat;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.common.constant.AssociationParam;
import com.hrym.rpc.app.dao.model.VO.AssociationVO;
import com.hrym.rpc.app.dao.model.association.*;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.association.AssociationAddService;
import com.hrym.rpc.association.dao.mapper.*;
import com.hrym.rpc.auth.dao.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by mj on 2017/8/17.
 */
public class AssociationAddServiceImpl implements AssociationAddService {

    @Autowired
    private AssociationMapper associationMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private AssociationMemberMapper associationMemberMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private MsgInfoMapper msgInfoMapper;



    /**
     * 社群主页-文章
     * @param param
     * @return
     */
    @Override
    public BaseResult getAssociationHomepage(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //核心分页
        PageHelper.startPage(param.getPageNo(),5);
        //查询所有文章专栏
        List<Article> articleList = articleMapper.findAllArticle();
        List<Map<String,Object>> list = new ArrayList<>();
        for (Article atc : articleList){
            Map<String,Object> map = Maps.newHashMap();
            map.put("articleId",atc.getIdtArticle());
            map.put("articleTitle",atc.getArticleTitle());
            map.put("specialColumnId",atc.getSpecialColumnId());
            map.put("updateTime", DateFormat.format(DateUtil.timestampToDates(atc.getUpdateTime(),DateUtil.TIME_PATTON_DEFAULT)));
            map.put("articlePic",atc.getArticlePic());
            map.put("articleUrl",atc.getArticleUrl());
            map.put("articleAbstract",atc.getArticleAbstract());
            if (null != atc.getSpecialColumn()){
                map.put("specialColumnName",atc.getSpecialColumn().getColumnName());
            }
            list.add(map);
        }
        PageInfo pageInfo = new PageInfo(articleList);
        Map<String,Object> maps = Maps.newHashMap();
        maps.put("articleList",list);
        maps.put("hasNextPage",pageInfo.isHasNextPage());

        return new BaseResult(code,message,maps);
    }

    /**
     * 社群主页接口-我加入的社群
     * @param param
     * @return
     */
    @Override
    public BaseResult getMyAssociation(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //更新最近在线时间
        userMapper.updateTimeByUuid(DateUtil.currentSecond(),param.getUserId());
        //查询用户加入的社群
        List<Association> associationList = associationMapper.findAllAssociationById(param.getUserId());
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Association a : associationList){
            Map<String,Object> map = Maps.newHashMap();
            map.put("associationId",a.getIdtAssociation());
            map.put("associationName",a.getName());
            map.put("avatarUrl",a.getAvatarUrl());
            map.put("userName",a.getUserName());
            mapList.add(map);
        }
        //0：无未读消息；1：有新消息
        int status = 0;
        //查询用户未读消息
        List<MsgInfo> msg = msgInfoMapper.findMsgInfoByToIdAndIsRead(param.getUserId(),0);
        if (msg.size() > 0 ){
            status = 1;
        }
        Map<String,Object> maps = Maps.newHashMap();
        maps.put("associationList",mapList);
        maps.put("status",status);

        return new BaseResult(code,message,maps);
    }

    /**
     * 社群主页接口-banner
     * @return
     */
    @Override
    public BaseResult getBanner() {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //当前时间
        int currentTime = DateUtil.currentSecond();
        List<Banner> bannerList = bannerMapper.findAllBannerByType(0,currentTime);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Banner b : bannerList){

            Map<String,Object> map = Maps.newHashMap();
            map.put("bannerDesc",b.getBannerDesc());
            map.put("bannerId",b.getBannerId());
            map.put("bannerTime",b.getStartTime()+"-"+b.getEndTime());
            map.put("bannerTitle",b.getBannerTitle());
            map.put("bannerUrl",b.getBannerUrl());
            map.put("bannerPic",b.getBannerPic());
            map.put("shareUrl",b.getShareUrl());
            map.put("needLogin",b.getNeedLogin());
            map.put("needShare",b.getNeedShare());
            map.put("additionalParam",b.getAdditionalParam());
            map.put("needResult",b.getNeedResult());
            if (StringUtils.isNotBlank(b.getShareChannel())){
                char [] chars = b.getShareChannel().toCharArray();
                List list = new ArrayList();
                for (int i = 0;i<chars.length;i++){
                    list.add(chars[i]);
                }
                map.put("shareChannel",list);
            }else {
                map.put("shareChannel",null);
            }
            mapList.add(map);
        }
        Map<String,Object> maps = Maps.newHashMap();
        maps.put("list",mapList);

        return new BaseResult(code,message,maps);
    }

    /**
     * 社群列表(可根据社群名称或者创建者名称过滤)
     * @param param
     * @return
     */
    @Override
    public BaseResult getAssociationList(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null == param.getPageNo()){
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
            return new BaseResult(code,message,null);
        }
        // 核心分页代码
        PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
        List<Association> associationList = associationMapper.findAllAssociation(param.getFilterStr());

        List<AssociationVO> mapList = new ArrayList<>();
        for (Association a : associationList){
            //过滤掉用户自己的社群
            if (null != param.getUserId() && a.getUserId() == param.getUserId()){
                continue;
            }
//            //查询社群人数
//            int num = associationMemberMapper.findTotalMember(a.getIdtAssociation());
            AssociationVO vo = new AssociationVO();

            vo.setAssociationId(a.getIdtAssociation());
            vo.setAssociationIntro(a.getIntro());
            vo.setAssociationName(a.getName());
            vo.setMemberNum(a.getNum());
            vo.setAvatarUrl(a.getAvatarUrl());

            if (null == param.getUserId()){
                vo.setStatus(0);
            }else {
                //查询最大时间  2：社群
                Integer time = msgInfoMapper.findMaxCreateTime(a.getIdtAssociation(),param.getUserId(),2);
                //查询用户加入社群状态  2：社群
                MsgInfo msgInfo = msgInfoMapper.findStatusById(a.getIdtAssociation(),param.getUserId(),time,2);
                AssociationMember member = associationMemberMapper.findMemberByIdAndUserId(a.getIdtAssociation(),param.getUserId());
                if (null == msgInfo){
                    vo.setStatus(0);
                }else if (null == member && msgInfo.getIsread() == 1) {
                    vo.setStatus(0);
                }else if (null == member){
                    vo.setStatus(msgInfo.getStatus());
                }else {
                    vo.setStatus(1);
                }
            }
            mapList.add(vo);
        }

//        //按成员数量降序排列
//        Collections.sort(mapList, new Comparator<AssociationVO>() {
//            @Override
//            public int compare(AssociationVO o1, AssociationVO o2) {
//                return o2.getMemberNum().compareTo(o1.getMemberNum());
//            }
//        });

        PageInfo pageInfo = new PageInfo(associationList);
        Map<String,Object> map = Maps.newHashMap();
        map.put("list",mapList);
        map.put("hasNextPage",pageInfo.isHasNextPage());

        return new BaseResult(code,message,map);
    }

    /**
     * 社群详情
     * @param param
     * @return
     */
    @Override
    public BaseResult associationDetails(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        GwsLogger.info(param);
        if (null == param.getAssociationId()){
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
            return new BaseResult(code,message,null);
        }
        Association association = associationMapper.findAssociationById(param.getAssociationId());
        UserInfo userInfo = userMapper.findUserByUserId(association.getUserId());
        Map<String,Object> map = Maps.newHashMap();
        map.put("associationIntro",association.getIntro());
        map.put("associationName",association.getName());
        map.put("userAvatarUrl",association.getAvatarUrl());
        map.put("userId",association.getUserId());
        map.put("userIntro",userInfo.getProfile());
        map.put("userName",userInfo.getNickName());

        return new BaseResult(code,message,map);
    }

    /**
     * 创建社群
     * @param param
     * @return
     */
    @Override
    public BaseResult createAssociation(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //社群头像，名称，简介必传
        if (null == param.getAssociationName() || null == param.getAssociationIntro() || null == param.getAvatarUrl()){
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
            return new BaseResult(code,message,null);
        }
        //获取创建者信息
        UserInfo userInfo = userMapper.findUserInfoByToken(param.getToken());
        Association association = new Association();

        association.setAvatarUrl(param.getAvatarUrl());
        association.setIntro(param.getAssociationIntro());
        association.setName(param.getAssociationName());
        association.setCreateTime(DateUtil.currentSecond());
        association.setUserId(userInfo.getUuid());
        association.setUserName(userInfo.getNickName());
        //0:系统学习佛学\n1:交流群
        association.setType(1);
        association.setUpdateTime(DateUtil.currentSecond());
        //创建社群
        associationMapper.saveAssociation(association);
        //获取插入后的ID
        int id = associationMapper.getLastInsertId();

        AssociationMember associationMember = new AssociationMember();
        associationMember.setIdtAssociation(id);
        associationMember.setUserId(userInfo.getUuid());
        associationMember.setUserType(AssociationMemberRole.MAINGROU.getVal());
        //插入到社群成员表
        associationMemberMapper.insertAssociationMember(associationMember);

        return new BaseResult(code,message,id);
    }
}
