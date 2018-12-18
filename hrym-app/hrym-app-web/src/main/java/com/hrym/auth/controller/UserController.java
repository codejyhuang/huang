package com.hrym.auth.controller;

import com.alibaba.fastjson.JSON;
import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.logUtil.GwsLoggerTypeEnum;
import com.hrym.common.logUtil.LogBean;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.common.constant.LoginParam;
import com.hrym.rpc.app.common.constant.MsgParam;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.auth.api.LoginService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录页面
 * Created by hong on 2017/6/23.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class UserController extends BaseController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/userLogin")
    @Allowed
    @ResponseBody
    public BaseResult login(@RequestBody LoginParam param) {

        String cmd = param.getCmd();
        if("login".equals(cmd)) {
            return doLogin(param);
        }
        if ("socialLogin".equals(cmd)){
            return doSocialLogin(param);
        }
        if("sendSms".equals(cmd)) {
            return doSendSms(param);
        }
        if ("findAllVersion".equals(cmd)){
            return doFindAllVersion(param);
        }

        if ("splashScreen".equals(cmd)){
            return this.getSplashScreen();
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    @RequestMapping(value = "/userInfo")
    @ResponseBody
    public BaseResult userInfo(@RequestBody LoginParam param) {

        String cmd = param.getCmd();
        if("updateUserInfo".equals(cmd)) {
            return doUpdateUserInfos(param);
        }
        if("findAllUserInfo".equals(cmd)) {
            return doFindAllUserInfos(param);
        }
        if ("loginOut".equals(cmd)){
            return loginOut(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 登录页面
     *
     * @param param
     * @return
     */
    public BaseResult doLogin(LoginParam param) {

        return loginService.login(param);
    }

    /**
     * 第三方登录
     * @param param
     * @return
     */
    public BaseResult doSocialLogin(LoginParam param){

        return loginService.socialLogin(param);
    }

    /**
     * 发送验证码
     * @param param
     * @return
     */
    public BaseResult doSendSms(LoginParam param) {

        return loginService.sendSms(param);
    }


    /**
     * 完善用信息
     * @param param
     * @return
     */
    public BaseResult doUpdateUserInfos(LoginParam param){

        //Redis中获取用户对象
        UserInfo user = JSON.parseObject(RedisUtil.get(param.getToken()),UserInfo.class);
        BaseResult ret = loginService.updateUserInfos(param);

        LogBean bean = new LogBean();
        bean.setUuid(user.getUuid());
        bean.setGroup(GwsLoggerTypeEnum.SEX_RATIO.getType());
        bean.setContent(param.getSex());
        GwsLogger.info("完善用信息",bean);

        return ret;
    }

    /**
     * 获取用户信息
     * @param param
     * @return
     */
    public BaseResult doFindAllUserInfos(LoginParam param){

        return loginService.findAllInfos(param);

    }

    /**
     * 获取版本信息
     * @param param
     * @return
     */
    public BaseResult doFindAllVersion(LoginParam param){

        return loginService.finAllVersion(param);
    }

    /**
     * 退出登录
     * @param param
     * @return
     */
    public BaseResult loginOut(LoginParam param) {

        return loginService.loginOut(param);
    }

    /**
     * 闪屏页数据获取
     *
     * @return
     */
    private BaseResult getSplashScreen() {

        return loginService.getSplashScreem();

    }

    @RequestMapping(value = "/broadcast")
    @Allowed
    @ResponseBody
    public BaseResult broadcast(@RequestBody MsgParam param) {

        return loginService.broadcastMsg(param);
    }

    private void broadcastMsg() {


    }
}
