package com.hrym.rpc.auth.api;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.LoginParam;
import com.hrym.rpc.app.common.constant.MsgParam;

/**
 * Created by mj on 2017/6/25.
 */
public interface LoginService {
    /**
     * 发送短信
     * @param json
     * @return
     */
    BaseResult sendSms(LoginParam json);

    /**
     * 用户登录
     * @param json
     * @return
     */
    BaseResult login(LoginParam json);

    /**
     * 第三方登录
     * @param param
     * @return
     */
    BaseResult socialLogin(LoginParam param);

    /**
     * 完善用户信息
     * @param josn
     * @return
     */
    BaseResult updateUserInfos(LoginParam josn);

    /**
     * 获取用户信息
     * @param param
     * @return
     */
    BaseResult findAllInfos(LoginParam param);

    /**
     * 获取版本信息
     * @param param
     * @return
     */
    BaseResult finAllVersion(LoginParam param);

    /**
     * 退出登录
     * @param param
     * @return
     */
    BaseResult loginOut(LoginParam param);

    /**
     * 获取闪屏页数据
     * @return
     */
    BaseResult getSplashScreem();


    /**
     * 批量发短信
     * @return
     */
    BaseResult broadcastMsg(MsgParam param);

}
