package com.hrym.rpc.mine.service;

import com.alibaba.fastjson.JSON;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.message.KafkaProducerSingleton;
import com.hrym.common.message.MessageBean;
import com.hrym.common.message.MessageConstant;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.common.constant.MyParam;
import com.hrym.rpc.app.common.constant.UcenterConstant;
import com.hrym.rpc.app.dao.model.association.MsgInfo;
import com.hrym.rpc.app.dao.model.task.FeedBack;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.association.dao.mapper.MsgInfoMapper;
import com.hrym.rpc.auth.dao.mapper.UserMapper;
import com.hrym.rpc.mine.MyHomepageService;
import com.hrym.rpc.mine.dao.mapper.FeedbackMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 我的业务层实现类
 * Created by mj on 2017/8/24.
 */
public class MyHomepageServiceImpl implements MyHomepageService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private MsgInfoMapper msgInfoMapper;


    /**
     * 手机认证
     * @return
     */
    @Override
    public BaseResult mobleIdentity(MyParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //获取手机号
        String phoneStr = param.getPhoneNo();
        //获取用户输入验证码
        String phoneCode = param.getAuthCode();
        //redis中去获取验证码
        String redisKey = phoneStr+ UcenterConstant.VERIFY_CODE_SUFFIX;
        String verifyCode = RedisUtil.get(redisKey);

        //验证码校验
        if (StringUtils.isNotBlank(verifyCode) && verifyCode.equals(phoneCode)){
            userMapper.updateByUserId(param.getPhoneNo(),1,param.getUserId());
        }else{
            code = BaseConstants.GWSCODE4002;
            message = BaseConstants.GWSMSG4002;
        }
        return new BaseResult(code,message,null);
    }

    /**
     * 用户实名认证
     * @return
     */
    @Override
    public BaseResult identification(MyParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != param.getUserId()){
            UserInfo userInfo = new UserInfo();
            userInfo.setUuid(param.getUserId());
            userInfo.setIdentityAuthState(0);
            userInfo.setIdentityCardNo(param.getIdentityCard());
            userInfo.setIdentityCardUrl1(param.getPic1());
            userInfo.setIdentityCardUrl2(param.getPic2());
            userInfo.setIdentityCardUrl3(param.getPic3());
            userInfo.setRealName(param.getRealName());
            userMapper.updateIdentification(userInfo);
        }else{
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }

        //定义社群消息对象
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setMsgTypeValue(param.getUserId());
        //0表示系统
        msgInfo.setFromId(0);
        //2表示申请
        msgInfo.setContentType(2);
        //0表是未读
        msgInfo.setIsread(0);
        msgInfo.setStatus(2);
        msgInfo.setCreateTime(DateUtil.currentSecond());
        //1:系统；2：社群；3：功课;4:用户认证
        msgInfo.setMsgType(4);
        msgInfo.setToId(param.getUserId());
        //插入一条社群申请消息
        msgInfoMapper.insertMsgInfo(msgInfo);

        try {
            int id = msgInfoMapper.getLastInsertId();
            //消息推送
            MessageBean msg = new MessageBean();
            msg.setMsgId(id);
            msg.setMsgType(4);       //消息类型(1:系统；2：社群；3：功课;4:用户认证)
            msg.setTitle("慧修行");
            msg.setSendType(3);     //发送方式(1:广播，2：标签，3：点对点)
            msg.setSendTypeValue(String.valueOf(param.getUserId()));
            msg.setStatus(0);       //社群：0:社群申请，1: 审批通过，2: 审批驳回，3:退出群 用户管理：0:认证中，1:审批通过，2:审批驳回
            msg.setMsgTypeValue(String.valueOf(param.getUserId()));
            //查询用户昵称
            msg.setContent("实名认证提交成功，我们将在两个工作日完成审核，请耐心等待。");
            KafkaProducerSingleton.getInstance().sendKafkaMessage(MessageConstant.TOPIC_HRYM_MESSAGE,msg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new BaseResult(code,message,null);
    }

    /**
     * 意见反馈
     * @param param
     * @return
     */
    @Override
    public BaseResult feedback(MyParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null == param.getPhone()){
            return new BaseResult(BaseConstants.GWSCODE2002,BaseConstants.GWSMSG2002,null);
        }
        FeedBack feedBack = new FeedBack();
        feedBack.setPhone(param.getPhone());
        feedBack.setContent(param.getContent());

        //Redis中获取用户对象
        if(!StringUtils.isEmpty(param.getToken())) {
            UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);
            if (null != user){
                feedBack.setUserId(user.getUuid());
            }
        }
        //状态\0:未处理\1:已阅读\2:已处理
        feedBack.setState(0);
        feedBack.setCreateTime(DateUtil.currentSecond());
        //保存意见反馈
        feedbackMapper.saveFeedback(feedBack);

        return new BaseResult(code,message,null);
    }

    /**
     * 判断手机号是否注册过
     * @param param
     * @return
     */
    @Override
    public BaseResult isPhoneNumRegister(MyParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (StringUtils.isBlank(param.getPhoneNo())){
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
            return new BaseResult(code,message,null);
        }
        UserInfo user = userMapper.findAllUserByMobile(param.getPhoneNo());
        if (null != user){
            code = BaseConstants.GWSCODE1003;
            message = BaseConstants.GWSMSG1003;
        }
        return new BaseResult(code,message,null);
    }
}
