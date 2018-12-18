package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import com.hrym.common.util.DateUtil;
import com.hrym.common.util.WXAuthUtil;
import com.hrym.wechat.smallProgram.MeditationParam;
import com.hrym.wechat.smallProgram.WechatUserParam;
import com.hrym.wechat.smallProgram.WechatUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hrym13 on 2018/11/14.
 */
@Controller
@RequestMapping("/hrym/wechat")
public class FlockAesController {
    
    @Autowired
    private WechatUsersService wechatUsersService;
    
    /**
     * 获取openID和sessionkey
     * @param param
     * @return
     */
    @RequestMapping("/getSessionkey")
    @Allowed
    @ResponseBody
    public BaseResult getSessionkey(@RequestBody MeditationParam param){
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        String jscode=param.getJscode();
        String encryptedData =param.getEncryptedData();
        String iv = param.getIv();
        Map map = null;
        try {
            map = WXAuthUtil.getSessionkey( encryptedData,iv,jscode);
            
        } catch (ServletException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        map.put("createdTime", DateUtil.currentSecond());
        //库里存取unionId
        Integer uuid = wechatUsersService.insertWechatUserUId(map);
        map.put("uuid",uuid);
        return new BaseResult(code,message,map);
    }
    
    @RequestMapping(value = "/insertFlockWechatUsers")
    @Allowed
    @ResponseBody
    public BaseResult insertWechatUser (@RequestBody WechatUserParam param){
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        
        String openId= wechatUsersService.insertWechatUser(param);
        
        return new BaseResult(code,message,openId);
    }
    
}
