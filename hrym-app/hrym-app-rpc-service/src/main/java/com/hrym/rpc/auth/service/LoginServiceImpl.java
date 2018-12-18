package com.hrym.rpc.auth.service;

import com.google.common.collect.Maps;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.util.*;
import com.hrym.rpc.app.common.constant.LoginParam;
import com.hrym.rpc.app.common.constant.MsgParam;
import com.hrym.rpc.app.common.constant.UcenterConstant;
import com.hrym.rpc.app.dao.model.VO.PhoneCode;
import com.hrym.rpc.app.dao.model.VO.SplashScreemVO;
import com.hrym.rpc.app.dao.model.task.InviteDetail;
import com.hrym.rpc.app.dao.model.task.LoginInfo;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.app.dao.model.task.VersionInfo;
import com.hrym.rpc.auth.api.LoginService;
import com.hrym.rpc.auth.dao.mapper.LoginMapper;
import com.hrym.rpc.auth.dao.mapper.PhoneMapper;
import com.hrym.rpc.auth.dao.mapper.UserMapper;
import com.hrym.rpc.auth.dao.mapper.VersionMapper;
import com.hrym.rpc.auth.service.sms.ProcSMSSoapBindingStub;
import com.hrym.rpc.auth.service.sms.SMS;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by mj on 2017/6/22.
 */
public class LoginServiceImpl implements LoginService {

    private static final Log log = LogFactory.getLog(LoginService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private VersionMapper versionMapper;
    @Autowired
    private PhoneMapper phoneMapper;

    // 验证码范围值
    private final int VERIFY_CODE_START_NUM = 1000;
    private final int VERIFY_CODE_END_NUM = 9999;

    /**验证码校验
     * @param param
     * @return
     */
    @Override
    public BaseResult login(LoginParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //获取手机号
        String phoneStr =param.getPhone();
        //获取用户输入验证码
        String phoneCode = param.getPhoneCode();
        //redis中去获取验证码
        String redisKey = phoneStr+UcenterConstant.VERIFY_CODE_SUFFIX;
        String verifyCode = RedisUtil.get(redisKey);

        UserInfo userInfo = new UserInfo();
        LoginInfo loginInfo = new LoginInfo();
        //验证码校验
        if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(phoneCode)){
            //查询数据库中用户信息
            UserInfo userInfoBean = userMapper.findUserInfoByMobile(phoneStr);
            userInfo.setMobile(phoneStr);
            userInfo.setDeviceId(param.getDeviceId());
            // 1表示有效登录用户
            userInfo.setStatus(1);
            userInfo.setSource(0);
            // 用手机号的md5做为token
            String token = VerifyCodeUtil.getToken(phoneStr);
            loginInfo.setToken(token);
            //是否第三方登录/0：是；1否
            loginInfo.setIsSocialLogin(0);
            //系统(0:android,1:ios)
            if ("IOS".equals(param.getPlatform().substring(0,3))){
                loginInfo.setOs(1);
            }else {
                loginInfo.setOs(0);
            }
            loginInfo.setPhoneModel(param.getPlatform());
            loginInfo.setOsVersion(param.getV());

            //判断手机号是否登录过
            if (null == userInfoBean){
                userInfo.setCreatedTime(DateUtil.currentSecond());
                userInfo.setUpdatedTime(DateUtil.currentSecond());
                userInfo.setRecentTime(DateUtil.currentSecond());
                //保存用户信息
                userMapper.saveUserInfo(userInfo);
                userInfoBean = userMapper.findUserInfoByMobile(phoneStr);

                loginInfo.setUuid(userInfoBean.getUuid());
                loginInfo.setLoginTime(DateUtil.currentSecond());

                //判断是否已经有过token
                LoginInfo login = loginMapper.findLoginInfoByToken(token);
                if (null == login){
                    loginMapper.saveLoginInfo(loginInfo);
                }else {
                    //更新登录信息
                    loginMapper.updateLoginInfoByToken(loginInfo);
                }

                RedisUtil.remove(token);
                RedisUtil.set(token, JSONObject.fromObject(userInfoBean).toString(),RedisUtil.EXRP_YEAR);

                if(!StringUtils.isEmpty(param.getInviteCode()))
                    this.addInviteDetail(param.getInviteCode(),loginInfo.getUuid(),param.getDeviceId());

            } else {
                loginInfo.setUuid(userInfoBean.getUuid());
                loginInfo.setLoginTime(DateUtil.currentSecond());

                //判断是否已经有过token
                LoginInfo login = loginMapper.findLoginInfoByToken(token);
                if (null == login){
                    loginMapper.saveLoginInfo(loginInfo);
                }else {
                    //更新登录信息
                    loginMapper.updateLoginInfoByToken(loginInfo);
                }

                RedisUtil.remove(token);
                RedisUtil.set(token, JSONObject.fromObject(userInfoBean).toString(),RedisUtil.EXRP_YEAR);
                userInfo.setUpdatedTime(DateUtil.currentSecond());
                userMapper.updateByToken(userInfo,token);
            }
        }else{
            code = BaseConstants.GWSCODE4002;
            message = BaseConstants.GWSMSG4002;
        }
        return new BaseResult(code,message,loginInfo);
    }

    /**
     * 第三方登录
     * @param param
     * @return
     */
    @Override
    public BaseResult socialLogin(LoginParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        // 第三方登录ID的md5做为token
        String token = VerifyCodeUtil.getToken(param.getSocialUid());

        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar(param.getAvatar());
        userInfo.setStatus(1);
        userInfo.setSocialUid(param.getSocialUid());
        userInfo.setNickName(StringUtil.filterEmoji(param.getNickName()));
        if (null != param.getSex() && param.getSex().equals("男")){
            userInfo.setSex(0);
        }
        if (null != param.getSex() && param.getSex().equals("女")){
            userInfo.setSex(1);
        }
        userInfo.setSource(param.getSource());
        userInfo.setDeviceId(param.getDeviceId());

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken(token);
        //是否第三方登录/0：是；1否
        loginInfo.setIsSocialLogin(1);
        //系统(0:android,1:ios)
        if ("IOS".equals(param.getPlatform().substring(0,3))){
            loginInfo.setOs(1);
        }else {
            loginInfo.setOs(0);
        }
        loginInfo.setPhoneModel(param.getPlatform());
        loginInfo.setOsVersion(param.getV());

        //查询用户是否登录过
        UserInfo user = userMapper.findUserBySocialUid(param.getSocialUid());
        if (null == user){
            userInfo.setCreatedTime(DateUtil.currentSecond());
            userInfo.setUpdatedTime(DateUtil.currentSecond());
            userInfo.setRecentTime(DateUtil.currentSecond());
            //保存用户信息
            userMapper.saveUserInfo(userInfo);

            user = userMapper.findUserBySocialUid(param.getSocialUid());
            loginInfo.setUuid(user.getUuid());
            loginInfo.setLoginTime(DateUtil.currentSecond());
            //保存登录信息
            //判断是否已经有过token
            LoginInfo login = loginMapper.findLoginInfoByToken(token);
            if (null == login){
                loginMapper.saveLoginInfo(loginInfo);
            }else {
                //更新登录信息
                loginMapper.updateLoginInfoByToken(loginInfo);
            }

            if(!StringUtils.isEmpty(param.getInviteCode()))
                this.addInviteDetail(param.getInviteCode(),loginInfo.getUuid(),param.getDeviceId());
        }else {
            loginInfo.setUuid(user.getUuid());
            loginInfo.setLoginTime(DateUtil.currentSecond());

            //判断是否已经有过token
            LoginInfo login = loginMapper.findLoginInfoByToken(token);
            if (null == login){
                loginMapper.saveLoginInfo(loginInfo);
            }else {
                //更新登录信息
                loginMapper.updateLoginInfoByToken(loginInfo);
            }

            userInfo.setUpdatedTime(DateUtil.currentSecond());
            userMapper.updateByToken(userInfo,token);
        }
        //将token作为key用户信息作为值存入redis中
        RedisUtil.remove(token);
        RedisUtil.set(token, JSONObject.fromObject(user).toString(),RedisUtil.EXRP_YEAR);

        return new BaseResult(code,message,loginInfo);
    }

    /**
     * 完善用信息
     * @param param
     * @return
     */
    @Override
    public BaseResult updateUserInfos(LoginParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        UserInfo userInfo = new UserInfo();
        if (null != param || null != param.getNickName() || null != param.getAvatar()) {
            userInfo.setNickName(StringUtil.filterEmoji(param.getNickName()));
            userInfo.setAvatar(param.getAvatar());
            if (!StringUtils.isEmpty(param.getEmail())){
                userInfo.setEmail(param.getEmail());
            }
            userInfo.setMobile(param.getPhone());
            if (null != param.getSex() && param.getSex().equals("男")){
                userInfo.setSex(0);
            }
            if (null != param.getSex() && param.getSex().equals("女")){
                userInfo.setSex(1);
            }
            userInfo.setDeviceId(param.getDeviceId());

            if (!StringUtils.isEmpty(param.getProfile())){
                userInfo.setProfile(StringUtil.filterEmoji(param.getProfile()));
            }
            if (!StringUtils.isEmpty(param.getYmd())){
                userInfo.setYmd(DateUtil.getDateToLinuxTime(param.getYmd(),DateUtil.DATE_PATTON_DEFAULT2));
            }
            userMapper.updateByToken(userInfo,param.getToken());
        }else {
            code =BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }

        GwsLogger.info("完善用信息",new BaseResult(code,message,null));
        return new BaseResult(code,message,null);
    }

    /**
     * 获取用户信息
     * @param param
     * @return
     */
    @Override
    public BaseResult findAllInfos(LoginParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        String token = param.getToken();

        UserInfo userInfo = userMapper.findUserInfoByToken(token);
        Map<String,Object> map = Maps.newHashMap();
        map.put("uuid",userInfo.getUuid());
        map.put("avatar",userInfo.getAvatar());
        map.put("email",userInfo.getEmail());
        map.put("identityAuthState",userInfo.getIdentityAuthState());
        map.put("nickName",userInfo.getNickName());
        map.put("phone",userInfo.getMobile());
        map.put("phoneAuthState",userInfo.getPhoneAuthState());
        map.put("profile",userInfo.getProfile());
        map.put("realName",userInfo.getRealName());
        if (null == userInfo.getSex()){
            map.put("sex",null);
        }
        if (null != userInfo.getSex() && userInfo.getSex() == 0){
            map.put("sex","男");
        }
        if (null != userInfo.getSex() && userInfo.getSex() == 1){
            map.put("sex","女");
        }
        if (null != userInfo.getYmd()){
            map.put("ymd",DateUtil.timestampToDates(userInfo.getYmd(),DateUtil.DATE_PATTON_DEFAULT2));
        }else {
            map.put("ymd",null);
        }

        return new BaseResult(code,message,map);
    }

    /**
     * 获取版本信息
     * @param param
     * @return
     */
    @Override
    public BaseResult finAllVersion(LoginParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        VersionInfo versionInfo = versionMapper.findAllVersion();
        if (null == versionInfo){
            code = BaseConstants.GWSCODE4003;
            message = BaseConstants.GWSMSG4003;
        }

        return new BaseResult(code,message,versionInfo);
    }

    /**
     * 退出登录
     * @param param
     * @return
     */
    @Override
    public BaseResult loginOut(LoginParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (null != param.getUuid()){
            UserInfo user = new UserInfo();
            user.setUuid(param.getUuid());
            user.setStatus(0);
            //更新用户为注销状态（0表示注销）
            userMapper.updateByPrimaryKeySelective(user);
            //清除redis中的token
            RedisUtil.remove(param.getToken());
        }else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }
        return new BaseResult(code,message,null);
    }

    /**
     * 发送验证码
     * @param param
     * @return
     */
    @Override
    public BaseResult sendSms(LoginParam param) {

        String retCode = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        String phoneStr = param.getPhone();
        // 获取万能手机号
        PhoneCode phoneCode = new PhoneCode();
        phoneCode.setPhone(phoneStr);
        PhoneCode phone = phoneMapper.selectOne(phoneCode);
        //如果有将对应验证码塞进redis中
        if (null != phone){
            String redisKey = phoneStr+UcenterConstant.VERIFY_CODE_SUFFIX;
            RedisUtil.remove(redisKey);
            RedisUtil.set(redisKey,phone.getCode(),RedisUtil.EXRP_ONE_THIRDE_HOUR);
            return new BaseResult(retCode,message,null);
        }

        //redis中去获取验证码
        String redisKey = phoneStr+UcenterConstant.VERIFY_CODE_SUFFIX;
        String verifyCode = RedisUtil.get(redisKey);

        if (StringUtils.isEmpty(verifyCode)){
            // 清除redis历史信息
            RedisUtil.remove(redisKey);
            verifyCode = VerifyCodeUtil.getRandNum(this.VERIFY_CODE_START_NUM,this.VERIFY_CODE_END_NUM);
            RedisUtil.set(redisKey,verifyCode,RedisUtil.EXRP_ONE_THIRDE_HOUR);
        }
        retCode = this.sendSmsByFT(phoneStr,this.buildVerifyCodeMsg(verifyCode));

        // 调用顺丰接口，发送短信验证码
        if (retCode != "0"){
            message = BaseConstants.GWSMSG4001;
        }
        return new BaseResult(retCode,message,null);
    }


    /**
     * 构建验证码短信
     *
     * @param verifyCode
     * @return
     */
    private String buildVerifyCodeMsg(String verifyCode) {
        if(StringUtils.isEmpty(verifyCode))
            return "";
        else
            return "验证码"+verifyCode+"，10分钟内有效，请勿把此验证码告诉他人。";
    }


    /**
     * 调用丰泰云接口发送短信
     *
     * @param phoneNo
     * @param msg
     * @return 0：成功，1：失败
     */
    private String sendSmsByFT(String phoneNo, String msg) {

        String retCode = BaseConstants.GWSCODE0000;
        try {
            String wsdlUrl = "http://sfsms-hap.sf-express.com/sfsms/services/procSMS?wsdl";
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();
            ProcSMSSoapBindingStub stub = new ProcSMSSoapBindingStub(new URL(wsdlUrl), service);

            SMS sms = new SMS();
            sms.setMobileno(phoneNo);
            sms.setMsg(msg);
            sms.setMsgId(phoneNo+"_"+System.currentTimeMillis());
            sms.setMsgtype("HRYM");

            log.info("sendSms start phoneNO:"+phoneNo+",currentTime:"+System.currentTimeMillis());
            stub.sendSMS(sms);
            log.info("sendSms end phoneNO:"+phoneNo+",currentTime:"+System.currentTimeMillis());


            return retCode;
        } catch (RemoteException e) {

            retCode = BaseConstants.GWSCODE4001;
            e.printStackTrace();
        } catch (MalformedURLException e) {
            retCode = BaseConstants.GWSCODE4001;
            e.printStackTrace();
        }
        return retCode;
    }


    public BaseResult getSplashScreem() {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        long st = System.currentTimeMillis()/1000;
        SplashScreemVO vo = loginMapper.getSplashInfo(st);

        return new BaseResult(code,message,vo);

    }

    @Override
    public BaseResult broadcastMsg(MsgParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<String> phoneList = null;
        //获取3天前
        if(StringUtils.isEmpty(param.getPhonesNo())){
            phoneList = userMapper.getMobilesNeedCallback(param.getSt());
        } else{
            phoneList = Arrays.asList(param.getPhonesNo().split(","));
        }


        for(String phone:phoneList) {
            this.sendSmsByFT(phone,param.getMsg());
        }

        return new BaseResult(code,message,null);

    }

    /**
     * 增加邀请明细
     *
     * @param code
     * @param uuid
     */
    private void addInviteDetail(String code, int uuid,String device_id) {

        InviteDetail info = new InviteDetail();
        code=code.replaceAll(" ","");
        code=code.toUpperCase();
        info.setCode(code);
        info.setUuid(String.valueOf(uuid));
        info.setDevice_id(device_id);
        userMapper.saveInviteDetail(info);

    }


}
