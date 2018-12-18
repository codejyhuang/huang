package com.hrym.rpc.association.service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.base.PageInfo;
import com.hrym.common.enums.AssociationMemberRole;
import com.hrym.common.message.KafkaProducerSingleton;
import com.hrym.common.message.MessageBean;
import com.hrym.common.message.MessageConstant;
import com.hrym.common.util.DateFormat;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.StringUtil;
import com.hrym.rpc.app.common.constant.AssociationParam;
import com.hrym.rpc.app.dao.model.association.Association;
import com.hrym.rpc.app.dao.model.association.MsgInfo;
import com.hrym.rpc.app.dao.model.association.AssociationMember;
import com.hrym.rpc.app.dao.model.association.Topic;
import com.hrym.rpc.app.dao.model.task.ThumbsUp;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.association.AssociationHandleService;
import com.hrym.rpc.association.dao.mapper.MsgInfoMapper;
import com.hrym.rpc.association.dao.mapper.AssociationMapper;
import com.hrym.rpc.association.dao.mapper.AssociationMemberMapper;
import com.hrym.rpc.association.dao.mapper.TopicMapper;
import com.hrym.rpc.auth.dao.mapper.ThumbsUpMapper;
import com.hrym.rpc.auth.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mj on 2017/9/10.
 */
public class AssociationHandleServiceImpl implements AssociationHandleService {

    @Autowired
    private AssociationMapper associationMapper;
    @Autowired
    private MsgInfoMapper msgInfoMapper;
    @Autowired
    private AssociationMemberMapper associationMemberMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private ThumbsUpMapper thumbsUpMapper;

    /**
     * 社群申请
     * @return
     */
    @Override
    public BaseResult associationApply(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //定义社群消息对象
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setMsgTypeValue(param.getAssociationId());
        msgInfo.setContent(StringUtil.filterEmoji(param.getMsg()));
        msgInfo.setFromId(param.getUserId());
        //2表示申请入群
        msgInfo.setContentType(2);
        //0表是未读
        msgInfo.setIsread(0);
        msgInfo.setStatus(2);
        msgInfo.setCreateTime(DateUtil.currentSecond());
        //1:系统；2：社群；3：功课;4:用户认证
        msgInfo.setMsgType(2);

        Association association = associationMapper.findAssociationById(param.getAssociationId());
        msgInfo.setToId(association.getUserId());
        //插入一条社群申请消息
        msgInfoMapper.insertMsgInfo(msgInfo);

        try {
            int id = msgInfoMapper.getLastInsertId();
            //消息推送
            MessageBean msg = new MessageBean();
            msg.setMsgId(id);
            msg.setMsgType(2);       //消息类型(1:系统；2：社群；3：功课;4:用户认证)
            msg.setTitle("慧修行");
            msg.setSendType(3);     //发送方式(1:广播，2：标签，3：点对点)
            msg.setSendTypeValue(String.valueOf(association.getUserId()));
            msg.setStatus(0);       //社群：0:社群申请，1: 审批通过，2: 审批驳回，3:退出群 用户管理：0:认证中，1:审批通过，2:审批驳回
            msg.setMsgTypeValue(String.valueOf(param.getAssociationId()));
            //查询用户昵称
            UserInfo userInfo = userMapper.findUserByUserId(param.getUserId());
            msg.setContent("“"+userInfo.getNickName()+"”申请加入社群“"+association.getName()+"”。点击此处查看详情");
            KafkaProducerSingleton.getInstance().sendKafkaMessage(MessageConstant.TOPIC_HRYM_MESSAGE,msg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new BaseResult(code,message,association.getName());
    }

    /**
     * 社群审批
     * @param param
     * @return
     */
    @Override
    public BaseResult associationApprove(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //定义社群消息对象
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setMsgTypeValue(param.getAssociationId());
        msgInfo.setFromId(param.getFromUserId());
        msgInfo.setToId(param.getToUserId());
        //3表示审核
        msgInfo.setContentType(3);
        msgInfo.setParentIdt(param.getMsgId());
        //0：未读；1:已读
        msgInfo.setIsread(0);
        //0：拒绝；1：同意；2：审核中
        msgInfo.setStatus(param.getApplyValue());
        //1:系统；2：社群；3：功课;4:用户认证
        msgInfo.setMsgType(2);
        msgInfo.setCreateTime(DateUtil.currentSecond());
        //插入一条社群审批消息
        msgInfoMapper.insertMsgInfo(msgInfo);

        //定义社群消息对象
        MsgInfo msgInfoBean = new MsgInfo();
        msgInfoBean.setIsread(1);
        msgInfoBean.setContentType(3);
        msgInfoBean.setMsgId(param.getMsgId());
        //更新申请消息状态
        msgInfoMapper.updateStatus(msgInfoBean);
        //1:同意，0:拒绝
        if (1 == param.getApplyValue()){
            AssociationMember associationMember = new AssociationMember();
            associationMember.setIdtAssociation(param.getAssociationId());
            associationMember.setUserId(param.getToUserId());
            associationMember.setUserType(AssociationMemberRole.CONSUMER.getVal());
            //插入到社群成员表
            associationMemberMapper.insertAssociationMember(associationMember);
        }

        Association association = associationMapper.findAssociationById(param.getAssociationId());
        try {
            int id = msgInfoMapper.getLastInsertId();
            //消息推送
            MessageBean msg = new MessageBean();
            msg.setMsgId(id);
            msg.setMsgType(2);       //消息类型(1:系统；2：社群；3：功课;4:用户认证)
            msg.setTitle("慧修行");
            msg.setSendType(3);     //发送方式(1:广播，2：标签，3：点对点)
            msg.setSendTypeValue(String.valueOf(param.getToUserId()));

            if (param.getApplyValue() == 0){
                msg.setStatus(2);       //社群：0:社群申请，1: 审批通过，2: 审批驳回，3:退出群 用户管理：0:认证中，1:审批通过，2:审批驳回
                msg.setContent("抱歉，您加入“"+association.getName()+"”的申请已经被管理员拒绝。点击重新申请");
            }
            if (param.getApplyValue() == 1){
                msg.setStatus(1);       //社群：0:社群申请，1: 审批通过，2: 审批驳回，3:退出群 用户管理：0:认证中，1:审批通过，2:审批驳回
                msg.setContent("您已成功加入“"+association.getName()+"”。点击此处进入社群主页");
            }
            msg.setMsgTypeValue(String.valueOf(param.getAssociationId()));
            KafkaProducerSingleton.getInstance().sendKafkaMessage(MessageConstant.TOPIC_HRYM_MESSAGE,msg);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new BaseResult(code,message,association.getName());
    }

    /**
     * 社群退出
     * @param param
     * @return
     */
    @Override
    public BaseResult associationExit(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != param.getAssociationId() && null != param.getUserId()){

            //定义社群消息对象
            MsgInfo msgInfo = new MsgInfo();
            msgInfo.setMsgTypeValue(param.getAssociationId());
            msgInfo.setFromId(param.getUserId());
            //退群理由
            msgInfo.setContent(param.getMsg());
            //4表示退群
            msgInfo.setContentType(4);
            //0：未读；1:已读
            msgInfo.setIsread(0);
            if (null != param.getType() && param.getType() == 0){
                //0：拒绝；1：同意；2：审核中；3:自动退群；4：被踢退群
                msgInfo.setStatus(3);
            }else {
                //0：拒绝；1：同意；2：审核中；3:自动退群；4：被踢退群
                msgInfo.setStatus(4);
                msgInfo.setToId(param.getUserId());
            }
            //1:系统；2：社群；3：功课;4:用户认证
            msgInfo.setMsgType(2);
            msgInfo.setCreateTime(DateUtil.currentSecond());

            msgInfoMapper.insertMsgInfo(msgInfo);

            //从成员表删除成员
            associationMemberMapper.deleteAssociationMemberById(param.getAssociationId(),param.getUserId());

            if (param.getType() == 1){
                try {
                    int id = msgInfoMapper.getLastInsertId();
                    //消息推送
                    MessageBean msg = new MessageBean();
                    msg.setMsgId(id);
                    msg.setMsgType(2);       //消息类型(1:系统；2：社群；3：功课;4:用户认证)
                    msg.setTitle("慧修行");
                    msg.setSendType(3);     //发送方式(1:广播，2：标签，3：点对点)
                    msg.setSendTypeValue(String.valueOf(param.getUserId()));
                    msg.setStatus(3);       //社群：0:社群申请，1: 审批通过，2: 审批驳回，3:退出群 用户管理：0:认证中，1:审批通过，2:审批驳回
                    msg.setMsgTypeValue(String.valueOf(param.getAssociationId()));
                    //查询用户昵称
                    Association association = associationMapper.findAssociationById(param.getAssociationId());
                    msg.setContent("很遗憾，您已被请出“"+association.getName()+"”。");

                    KafkaProducerSingleton.getInstance().sendKafkaMessage(MessageConstant.TOPIC_HRYM_MESSAGE,msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }

        return new BaseResult(code,message,null);
    }

    /**
     * 社群成员列表
     * @param param
     * @return
     */
    @Override
    public BaseResult findMemberList(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //分页核心代码
        PageHelper.startPage(param.getPageNo(), BaseConstants.PAGE_SIZE);
        List<AssociationMember> list = associationMemberMapper.findMemberListById(param.getAssociationId(),param.getFilterStr());
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (AssociationMember a : list){
            Map<String,Object> map = Maps.newHashMap();
            map.put("userId",a.getUserId());
            map.put("userType", AssociationMemberRole.getEnumByNumber(a.getUserType()).getVal());
            if (null != a.getUserInfo()){
                map.put("avatar",a.getUserInfo().getAvatar());
                map.put("userName",a.getUserInfo().getNickName());
            }
            mapList.add(map);
        }

        PageInfo pageInfo =new PageInfo(list);
        Map<String,Object> map = Maps.newHashMap();
        map.put("list",mapList);
        map.put("hasNextPage",pageInfo.isHasNextPage());

        return new BaseResult(code,message,map);
    }

    /**
     * 我加入的社群首页
     * @param param
     * @return
     */
    @Override
    public BaseResult myAssociationHomePage(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //通过主题类型和社群ID查询主题 (0:功课相关\n1:社群相关)
        PageHelper.startPage(param.getPageNo(),BaseConstants.PAGE_SIZE);
        List<Topic> topicList =  topicMapper.findAllTopicByTypeId(1,param.getAssociationId());
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Topic t : topicList){
            Map<String,Object> map = Maps.newHashMap();
            //获取主题创建者昵称
            UserInfo userInfo = userMapper.findUserByUserId(t.getUserId());
            map.put("topicDesc",t.getTopicDesc());
            map.put("userName",userInfo.getNickName());
            map.put("avatar",userInfo.getAvatar());
            map.put("userId",t.getUserId());
            map.put("topicId",t.getIdtTopic());
            //将所有不为空的图片加入到集合中
            List urlList = new ArrayList();
            if (null != t.getUrl1()){
                urlList.add(t.getUrl1());
            }
            if (null != t.getUrl2()){
                urlList.add(t.getUrl2());
            }
            if (null != t.getUrl3()){
                urlList.add(t.getUrl3());
            }
            if (null != t.getUrl4()){
                urlList.add(t.getUrl4());
            }
            if (null != t.getUrl5()){
                urlList.add(t.getUrl5());
            }
            if (null != t.getUrl6()){
                urlList.add(t.getUrl6());
            }
            if (null != t.getUrl7()){
                urlList.add(t.getUrl7());
            }
            if (null != t.getUrl8()){
                urlList.add(t.getUrl8());
            }
            if (null != t.getUrl9()){
                urlList.add(t.getUrl9());
            }
            map.put("urlList",urlList);
            map.put("createTime", DateFormat.format(DateUtil.timestampToDates(t.getUpdateTime(),DateUtil.TIME_PATTON_DEFAULT)));
            //0:功课类型；1:社群文章
            ThumbsUp thumbsUp = thumbsUpMapper.findThumsUp(t.getIdtTopic(),param.getUuid(),1);
            if (null != thumbsUp){
                map.put("type",thumbsUp.getType());
            }else {
                //查询点赞类型（0-已取消赞 1-有效赞）
                map.put("type",0);
            }
            //获取点赞人昵称
            PageHelper.startPage(1,3);
            //0:功课类型；1:社群文章
            List<ThumbsUp> thumbsUpBean = thumbsUpMapper.findUserIdByTopicId(t.getIdtTopic(),1);
            if (0 < thumbsUpBean.size()){
                String nameStr = "";
                for (ThumbsUp th : thumbsUpBean){
                    String name = th.getUserInfo().getNickName()+"、";
                    nameStr = nameStr+name;
                }
                if (t.getAgreeNum() > 3){
                    map.put("agreeDesc",nameStr.substring(0,nameStr.length()-1)+"等"+t.getAgreeNum()+"人觉得很赞");
                }else {
                    map.put("agreeDesc",nameStr.substring(0,nameStr.length()-1)+"觉得很赞");
                }
            }else {
                map.put("agreeDesc",null);
            }
            mapList.add(map);
        }
        //获取用户在社群中的角色类型
        AssociationMember amb = associationMemberMapper.findMemberByIdAndUserId(param.getAssociationId(),param.getUuid());
        PageInfo pageInfo = new PageInfo(topicList);

        Map<String,Object> map = Maps.newHashMap();
        map.put("list",mapList);
        map.put("userType",amb.getUserType());
        map.put("hasNextPage",pageInfo.isHasNextPage());
        Association association = associationMapper.findAssociationById(param.getAssociationId());
        map.put("associationName",association.getName());

        return new BaseResult(code  ,message,map);
    }

    /**
     * 社群后台信息
     * @param param
     * @return
     */
    @Override
    public BaseResult getBackstageInfo(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Association association = associationMapper.findAssociationById(param.getAssociationId());
        //查询社群成员总数
        int num = associationMemberMapper.findTotalMember(param.getAssociationId());
        Map<String,Object> map = Maps.newHashMap();
        if (null != association){
            map.put("rqCodeUrl",association.getRqCodeUrl());
            map.put("avatarUrl",association.getAvatarUrl());
            map.put("associationIntro",association.getIntro());
        }
        map.put("memberNum",num);

        return new BaseResult(code,message,map);
    }

    /**
     * 社群信息修改
     * @param param
     * @return
     */
    @Override
    public BaseResult associationEdit(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null == param.getAssociationId()){
            return new BaseResult(BaseConstants.GWSCODE2002,BaseConstants.GWSMSG2002,null);
        }
        Association association = new Association();
        association.setIdtAssociation(param.getAssociationId());
        association.setIntro(param.getAssociationIntro());
        association.setAvatarUrl(param.getAvatarUrl());
        association.setUpdateTime(DateUtil.currentSecond());
        //修改社群信息
        associationMapper.updateAssociationInfoById(association);

        return new BaseResult(code,message,null);
    }

    /**
     * 社群主题发表
     * @param param
     * @return
     */
    @Override
    public BaseResult publishTopic(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null == param.getUuid() || null == param.getAssociationId() || null == param.getTopicType()){
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }else {
            //创建主题对象
            Topic topic = new Topic();
            topic.setTopicType(param.getTopicType());
            topic.setTopicTypeValue(param.getAssociationId());
            topic.setTopicDesc(StringUtil.filterEmoji(param.getTopicDesc()));
            topic.setUserId(param.getUuid());
            topic.setUrl1(param.getUrl1());
            topic.setUrl2(param.getUrl2());
            topic.setUrl3(param.getUrl3());
            topic.setUrl4(param.getUrl4());
            topic.setUrl5(param.getUrl5());
            topic.setUrl6(param.getUrl6());
            topic.setUrl7(param.getUrl7());
            topic.setUrl8(param.getUrl8());
            topic.setUrl9(param.getUrl9());
            topic.setCreateTime(DateUtil.currentSecond());
            topic.setUpdateTime(DateUtil.currentSecond());

            //保存主题
            topicMapper.saveTopic(topic);
        }

        return new BaseResult(code,message,null);
    }

    /**
     * 消息列表
     * @param param
     * @return
     */
    @Override
    public BaseResult messageList(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<Map<String,Object>> mapList = new ArrayList<>();
        List<MsgInfo> list = null;
        if (null != param.getUuid()){
            //核心分页代码
            PageHelper.startPage(param.getPageNo(),BaseConstants.PAGE_SIZE);
            list = msgInfoMapper.findMsgInfoByType(param.getUuid());
            for (MsgInfo a : list){
                Map<String,Object> map = Maps.newHashMap();
                map.put("msgId",a.getMsgId());
                map.put("createTime",DateFormat.format(DateUtil.timestampToDates(a.getCreateTime(),DateUtil.TIME_PATTON_DEFAULT)));
                map.put("isRead",a.getIsread());
                map.put("msgType",a.getMsgType());
                map.put("msgTypeValue",a.getMsgTypeValue());
                map.put("status",a.getStatus());
                map.put("fromId",a.getFromId());
                if (a.getMsgType() == 1){
                    map.put("msgTitle","系统管理");
                }
                if (a.getMsgType() == 2){
                    Association association = associationMapper.findAssociationById(a.getMsgTypeValue());
                    map.put("msgTitle","社群管理");
                    if (a.getStatus() == 0){
                        map.put("content","抱歉，您加入“"+association.getName()+"”的申请已经被管理员拒绝。点击重新申请");
                    }
                    if (a.getStatus() == 1){
                        map.put("content","您已成功加入“"+association.getName()+"”。点击此处进入社群主页");
                    }
                    if (a.getStatus() == 2){
                        //查询用户昵称
                        UserInfo userInfo = userMapper.findUserByUserId(a.getFromId());
                        map.put("content","“"+userInfo.getNickName()+"”申请加入社群“"+association.getName()+"”。点击此处查看详情");
                        map.put("applyReason",userInfo.getNickName()+"："+a.getContent());
                    }
//                    if (a.getStatus() == 3){
//                        //查询用户昵称
//                        UserInfo userInfo = userMapper.findUserByUserId(a.getFromId());
//                        map.put("content","佛弟子“"+userInfo.getNickName()+"”已从“"+association.getName()+"”社群退出。");
//                    }
                    if (a.getStatus() == 4){
                        //查询用户昵称
                        UserInfo userInfo = userMapper.findUserByUserId(a.getFromId());
                        map.put("content","很遗憾，您已被请出“"+association.getName()+"”。");
                    }
                }
                if (a.getMsgType() == 3){
                    map.put("msgTitle","功课管理");
                }
                if (a.getMsgType() == 4){
                    map.put("msgTitle","用户管理");
                    if (a.getStatus() == 0){
                        map.put("content","抱歉，您提交的实名认证资料有误，烦请核对后重新认证。点击重新认证");
                    }
                    if (a.getStatus() == 1){
                        map.put("content","实名认证已通过，您已获得“创建社群”权限。点击创建社群");
                    }
                    if (a.getStatus() == 2){
                        map.put("content","实名认证提交成功，我们将在两个工作日完成审核，请耐心等待");
                    }
                }
                mapList.add(map);
            }
        }else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = Maps.newHashMap();
        map.put("list",mapList);
        map.put("hasNextPage",pageInfo.isHasNextPage());

        return new BaseResult(code,message,map);
    }


    /**
     * 判断是否已读
     * @param param
     * @return
     */
    @Override
    public BaseResult isReadMessage(AssociationParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null == param.getMsgId()){
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }
        //定义社群消息对象
        MsgInfo msgInfoBean = new MsgInfo();
        msgInfoBean.setIsread(1);
        msgInfoBean.setMsgId(param.getMsgId());
        //更新申请消息状态
        msgInfoMapper.updateIsRead(msgInfoBean);

        return new BaseResult(code,message,null);
    }
}
