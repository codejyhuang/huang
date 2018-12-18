package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.wechat.smallProgram.WechatUserParam;
import com.hrym.wechat.smallProgram.WechatUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/12.
 */
@Controller
@RequestMapping(value = "/hrym/program")
public class WechatUsersController extends BaseController {

    @Autowired
    private WechatUsersService wechatUsersService;

    @RequestMapping(value = "/wechatUsersOPt")
    @Allowed
    @ResponseBody
    public BaseResult WechatUsersOPt(@RequestBody WechatUserParam param){

        String cmd = param.getCmd();
        if ("insertWechatUser".equals(cmd)){
            return insertWechatUser(param);
        }
//        if ("finAllWechatUsers".equals(cmd)){
//            return finAllWechatUsers(param);
//        }

        return null;

    }
//    public List finAllWechatUsers(WechatUserParam param){
//        return wechatUsersService.finAllWechatUsers(param);
//    }

    public BaseResult insertWechatUser (WechatUserParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;


       String openId= wechatUsersService.insertWechatUser(param);

        return new BaseResult(code,message,openId);
    }
}
