package com.hrym.rpc.auth.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.NumUtil;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.TokenUtil;
import com.hrym.rpc.app.common.constant.AssociationParam;
import com.hrym.rpc.app.common.constant.BookParam;
import com.hrym.rpc.app.common.constant.LogParam;
import com.hrym.rpc.app.dao.model.VO.DateVO;
import com.hrym.rpc.app.dao.model.VO.ResourceArticleVO;
import com.hrym.rpc.app.dao.model.VO.bookVO.TaskItemVO;
import com.hrym.rpc.app.dao.model.association.Association;
import com.hrym.rpc.app.dao.model.association.ResourceArticle;
import com.hrym.rpc.app.dao.model.association.WishActivity;
import com.hrym.rpc.app.dao.model.task.CustomTask;
import com.hrym.rpc.app.dao.model.task.TaskItem;
import com.hrym.rpc.app.dao.model.task.TaskPlan;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceContentLesson;
import com.hrym.rpc.app.dao.model.task.lesson.ResourceItemLesson;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.association.dao.mapper.AssociationMapper;
import com.hrym.rpc.association.dao.mapper.ResourceArticleMapper;
import com.hrym.rpc.auth.api.HtmlViewService;
import com.hrym.rpc.auth.dao.mapper.*;
import com.hrym.rpc.auth.dao.mapper.bookMapper.BookMapper;
import com.hrym.rpc.auth.dao.mapper.lesson.ResourceContentLessonMapper;
import com.hrym.rpc.auth.dao.mapper.lesson.ResourceItemLessonMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import scala.util.parsing.combinator.testing.Str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mj on 2017/11/2.
 */
public class HtmlViewServiceImpl implements HtmlViewService {

    @Autowired
    private TaskPlanMapper taskPlanMapper;
    @Autowired
    private AssociationMapper associationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TaskItemMapper taskItemMapper;
    @Autowired
    private DateVOMapper dateVOMapper;
    @Autowired
    private CustomTaskMapper customTaskMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ResourceArticleMapper resourceArticleMapper;
    @Autowired
    private ResourceContentLessonMapper resourceContentLessonMapper;

    /**
     * 分享
     * @return
     */
    @Override
    public Map<String, Object> doShare(Integer type, Integer id, double doneNum, double todayCommitNum, String targetNum) {

        Map<String,Object> map = Maps.newHashMap();
        if (type == 0){
            Association association = associationMapper.findAssociationById(id);
            UserInfo user = userMapper.findUserByUserId(association.getUserId());
            map.put("uesrname",user.getNickName());
            map.put("group_name",association.getName());
            map.put("img_url",association.getAvatarUrl());
            map.put("type",type);

            return map;
        }
        if (type == 1){
            TaskPlan taskPlan = taskPlanMapper.findTaskPlanByTaskId(id);
            UserInfo user = userMapper.findUserByUserId(taskPlan.getUuid());
            //判断自定义功课
            if (null == taskPlan.getItemId() && null != taskPlan.getCustomId()){
                CustomTask custom = customTaskMapper.selectByPrimaryKey(taskPlan.getCustomId());
                map.put("itemId",taskPlan.getCustomId());
                map.put("lessons_name",custom.getCustomName());
            }else {
                TaskItem item = taskItemMapper.findTaskByItemId(taskPlan.getItemId());
                map.put("itemId",taskPlan.getItemId());
                map.put("lessons_name",item.getItemName());
            }
            map.put("type",type);
            map.put("uesrname",user.getNickName());
            if (StringUtils.isNotBlank(user.getAvatar())){
                map.put("img_url",user.getAvatar());
            }else {
                map.put("img_url","http://202.104.112.185:8089/group1/M00/00/01/wKgAN1n_xjKACiemAAC0d7SdfuU969.jpg");
            }

            map.put("lessons_target", NumUtil.formatFloatNumber(taskPlan.getPlanTarget()));
            map.put("lessons_completed_all",(int)doneNum);
            if (StringUtils.isBlank(targetNum) || targetNum.contains("-")){
                map.put("lessons_proposal",0);
            }else {
                map.put("lessons_proposal",Integer.valueOf(targetNum));
            }
            map.put("lessons_completed_today",(int)todayCommitNum);
            return map;
        }
        if (type == 2){
            TaskPlan taskPlan = taskPlanMapper.findTaskPlanByTaskId(id);
            UserInfo user = userMapper.findUserByUserId(taskPlan.getUuid());
            //判断自定义功课
            if (null == taskPlan.getItemId() && null != taskPlan.getCustomId()){
                CustomTask custom = customTaskMapper.selectByPrimaryKey(taskPlan.getCustomId());
                map.put("itemId",taskPlan.getCustomId());
                map.put("lessons_name",custom.getCustomName());
            }else {
                TaskItem item = taskItemMapper.findTaskByItemId(taskPlan.getItemId());
                map.put("itemId",taskPlan.getItemId());
                map.put("lessons_name",item.getItemName());
            }
            map.put("type",type);
            map.put("uesrname",user.getNickName());
            if (StringUtils.isNotBlank(user.getAvatar())){
                map.put("img_url",user.getAvatar());
            }else {
                map.put("img_url","http://202.104.112.185:8089/group1/M00/00/01/wKgAN1n_xjKACiemAAC0d7SdfuU969.jpg");
            }
            map.put("lessons_target",NumUtil.formatFloatNumber(taskPlan.getPlanTarget()));
            map.put("lessons_term",taskPlan.getPlanPeriod());
            map.put("lessons_statrtAt", DateUtil.timestampToDates(taskPlan.getStartTime(),DateUtil.DATE_PATTON_DEFAULT2));
            return map;
        }

        return null;
    }

    @Override
    public boolean updateUserInfo(Integer uuid,String userName, String wx, String phone, String address, String email) {

        try {

            userMapper.updateUserInfo(uuid,userName,wx,phone,address,email);
        }catch (Exception e){

            return false;
        }
        return true;
    }

    /**
     * 获取日期图片
     * @param param
     * @return
     */
    @Override
    public Map<String,Object> getDatePic(LogParam param) {

        Map<String,Object> map = new HashMap<>();
        List<DateVO> dateVOList = getDatePicList();

        int index = 0;
        for (DateVO dv : dateVOList){
            //将前台传来的当前日期转换为int类型
            Integer currentTime = DateUtil.getDateToLinuxTime(param.getTime(),DateUtil.TIME_PATTON_DEFAULT);
            DateVO dateVO = dateVOMapper.findOneByTime(currentTime);
            if (dateVO == null){
                index = -1;
                break;
            }
            if (dv.getId() == dateVO.getId()){
                break;
            }
            index++;
        }

        map.put("index",index);
        map.put("list",dateVOList);
        return map;
    }

    /**
     * 获取图片list
     * @return
     */
    public List<DateVO> getDatePicList() {

        List<DateVO> dateVOList = dateVOMapper.findAllDatePic();

        return dateVOList;
    }

    @Override
    public Map<String, Object> getActivityInfo(int uuid) {


        // 获取礼品
        List<Map> list = dateVOMapper.getPresentInfo();

        //获取活动邀请码,邀请码为uuid+4106，然后转换为16进制
        int num = Integer.valueOf(uuid)+4106;
        String inviteCode = Integer.toHexString(num).toUpperCase();

        // 获取邀请数
        int inviteNum = dateVOMapper.getInviteNum(inviteCode);


        int id=0;
        String msg="";
        for(Map map:list) {

            int needNum = MapUtils.getIntValue(map,"num",0);
            int stock = MapUtils.getIntValue(map,"stock",0);
            int uplevelNum = MapUtils.getIntValue(map,"uplevel_num",0);

            if(stock<=0)
                continue;
            else {
               if(inviteNum>=needNum){
                   id = MapUtils.getIntValue(map,"id",0);

                   if(inviteNum>=uplevelNum){
                       msg = "你邀请了"+inviteNum+"位师兄加入了慧修行，但这个等级的奖品已兑完，你可以兑换'"
                               + MapUtils.getString(map,"desc","")+"'";
                   } else {
                       msg="你邀请了"+inviteNum+"位师兄加入了慧修行，可以兑换'"
                               + MapUtils.getString(map,"desc","")+"'";
                   }
                   break;

               }
            }
        }

        Map<String,Object> ret = Maps.newHashMap();
        ret.put("inviteCode",inviteCode);
        ret.put("inviteNum",inviteNum);
        ret.put("presentList",list);
        ret.put("code",0);
        ret.put("msg",msg);
        ret.put("id",id);

        //先看用户地址表中是否有数据，没有生成（主要邀请码）
        return ret;
    }

    @Override
    public Map<String, Object> exchangeActivityPresent(String uuid, String id) {

        int presentId = 0;
        Map<String,Object> ret = Maps.newHashMap();
        // 0:领取成功，1：已经领取过，不能重复领取，2：兑换奖品已经领完，可以换一个奖品，3:所有奖品已经兑完

        int num = dateVOMapper.checkExchange(uuid);
        if(num>0){
            ret.put("code",1);
            ret.put("mgs","已经领取过，不能重复领取!");
            ret.put("id",0);

            return ret;
        }

        //查询奖品数，看看是否还有库存

        int stock = dateVOMapper.getStock(id);
        if(stock==0) {


            // 获取礼品
            List<Map> list = dateVOMapper.getPresentInfo();

            //获取活动邀请码,邀请码为uuid+4106，然后转换为16进制
            int numHex = Integer.valueOf(uuid)+4106;
            String inviteCode = Integer.toHexString(numHex).toUpperCase();

            // 获取邀请数
            int inviteNum = dateVOMapper.getInviteNum(inviteCode);

            String msg="";
            for(Map map:list) {

                int needNum = MapUtils.getIntValue(map,"num",0);
                int stockT = MapUtils.getIntValue(map,"stock",0);

                if(stockT<=0)
                    continue;
                else {
                    if(inviteNum>=needNum){
                        presentId = MapUtils.getIntValue(map,"id",0);
                        msg="你邀请了"+inviteNum+"位师兄加入了慧修行，但这个等级的奖品已兑完，你可以兑换'"
                                + MapUtils.getString(map,"desc","")+"'";
                        break;

                    }
                }
            }

            if(presentId==0) {
                ret.put("code",3);
                ret.put("mgs","您来晚了，所有的礼品都兑完啦～");
                ret.put("id",presentId);

            }else{
                ret.put("code",2);
                ret.put("mgs",msg);
                ret.put("id",presentId);
            }

            return ret;
        }

        //减库存
        dateVOMapper.updateStock(id);
        dateVOMapper.addExchange(uuid,id);


        ret.put("code",0);
        ret.put("id",0);
        ret.put("msg","礼品兑换成功");

        return ret;
    }

    /**
     * 根据用户ID修改完成任务状态
     * @param
     * @return
     */
    @Override
    public BaseResult updateAllWishActivity(AssociationParam param) {

        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        List <WishActivity> list = dateVOMapper.findWishActivityByUuid(user.getUuid());
        if (list.size()==0){
            int createTime = DateUtil.currentSecond();
            dateVOMapper.insertAllWishActivity(user.getUuid(),param.getStatus(),createTime);
        }else {
            dateVOMapper.updateALLWishActivity(user.getUuid(),param.getStatus());

        }

        return new BaseResult(code,message,null);
    }

    /**
     * 经书详情
     * @param itemId
     * @return
     */
    @Override
    public TaskItemVO findBookDetails(Integer itemId) {

        TaskItemVO vo = bookMapper.findBookByItemId(itemId,10006);
        
        return vo;
    }

    /**
     * 前端h5页面详情展示
     * @param articleId
     * @return
     */
    @Override
    public Map<String,Object> findResourceArticle(Integer articleId) {

        ResourceArticle article = resourceArticleMapper.findAllResourceArticle(articleId);
        // 阅读文章人数加一
        resourceArticleMapper.updateResourceArticleOrderNum(articleId);

        List<Map<String,Object>> mapList = new ArrayList<>();
        if (article != null) {
            if (StringUtils.isNotBlank(article.getAssemble())) {
                String[] strs = article.getAssemble().split(",");
                for (int i=0,len=strs.length;i<len;i++) {
                   Integer itemContentId = Integer.valueOf(strs[i].toString());

                    ResourceContentLesson itemContent = resourceContentLessonMapper.findContentLessonById(itemContentId);
                    Map<String,Object> map = new HashMap<>();
                    map.put("itemId",itemContent.getItemId());
                    map.put("itemContentId",itemContent.getItemContentId());
                    map.put("orderNum",itemContent.getOrderNum());
                    map.put("onlineNum",itemContent.getOnlineNum());
                    mapList.add(map);
                }
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("article",article);
        map.put("items",mapList);
        return map;
    }



}
