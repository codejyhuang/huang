package com.hrym.mine.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.logUtil.GwsLoggerTypeEnum;
import com.hrym.common.logUtil.LogBean;
import com.hrym.rpc.app.common.constant.MyParam;
import com.hrym.rpc.app.common.constant.TaskParam;
import com.hrym.rpc.mine.MyHomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 我的控制层
 * Created by mj on 2017/8/25.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class MyHomepageController extends BaseController {

    @Autowired
    private MyHomepageService myHomepageService;

    @RequestMapping(value = "/my")
    @ResponseBody
    public BaseResult getMyHome(@RequestBody MyParam param) {

        String cmd = param.getCmd();
        if ("mobleIdentity".equals(cmd)){
            return doMobleIdentity(param);
        }
        if ("identification".equals(cmd)){
            return doIdentification(param);
        }
        if ("isRegister".equals(cmd)){
            return isPhoneNumRegister(param);
        }
        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    @RequestMapping(value = "/doMy")
    @Allowed
    @ResponseBody
    public BaseResult doGetMyHome(@RequestBody MyParam param) {

        String cmd = param.getCmd();
        if ("feedback".equals(cmd)){
            return feedback(param);
        }
        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 手机认证
     * @return
     */
    public BaseResult doMobleIdentity(MyParam param){

        BaseResult ret = myHomepageService.mobleIdentity(param);

        LogBean bean = new LogBean();
        bean.setUuid(param.getUserId());
        bean.setGroup(GwsLoggerTypeEnum.PHONE.getType());
        bean.setContent(param.getPhoneNo());
        GwsLogger.info("手机认证",bean);

        return ret;
    }

    /**
     * 用户实名认证
     * @return
     */
    public BaseResult doIdentification(MyParam param){

        BaseResult ret = myHomepageService.identification(param);

//        LogBean bean = new LogBean();
//        bean.setUuid(param.getUserId());
//        bean.setGroup(GwsLoggerTypeEnum.REALNAME.getType());
//        bean.setContent(param.getRealName());
//        GwsLogger.info("用户实名认证",bean);

        return ret;
    }

    /**
     * 意见反馈
     * @param param
     * @return
     */
    public BaseResult feedback(MyParam param) {

        BaseResult ret = myHomepageService.feedback(param);

        LogBean bean = new LogBean();
        bean.setGroup(GwsLoggerTypeEnum.FEADBACK.getType());
        bean.setContent(param.getContent());
        GwsLogger.info("意见反馈",bean);

        return ret;
    }

    /**
     * 判断手机号是否注册过
     * @param param
     * @return
     */
    public BaseResult isPhoneNumRegister(MyParam param) {

        return myHomepageService.isPhoneNumRegister(param);
    }
}
