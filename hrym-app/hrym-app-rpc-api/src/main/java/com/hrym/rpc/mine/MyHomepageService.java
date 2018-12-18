package com.hrym.rpc.mine;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.MyParam;

/**
 * 我的Service
 * Created by mj on 2017/8/24.
 */
public interface MyHomepageService {

    /**
     * 手机认证
     * @return
     */
    BaseResult mobleIdentity(MyParam param);

    /**
     * 用户实名认证
     * @return
     */
    BaseResult identification(MyParam param);

    /**
     * 意见反馈
     * @param param
     * @return
     */
    BaseResult feedback(MyParam param);

    /**
     * 判断手机号是否注册过
     * @param param
     * @return
     */
    BaseResult isPhoneNumRegister(MyParam param);

}
