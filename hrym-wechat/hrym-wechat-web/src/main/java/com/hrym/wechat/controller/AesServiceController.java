package com.hrym.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.common.util.WXAuthUtil;
import com.hrym.wechat.smallProgram.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/27.
 */
@Controller
@RequestMapping("/hrym/program")
public class AesServiceController extends BaseController {

    @Autowired
    private AesService aesService;
    @Autowired
    private WechatUsersService wechatUsersService;
    
    @RequestMapping("/AesServiceController")
    @Allowed
    @ResponseBody
    public BaseResult CountRecord(@RequestBody MeditationParam param){
        String cmd = param.getCmd();
        if ("decryptAes".equals(cmd)){
            return decryptAes(param);
        }

        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }


    /**
     * 微信群ID解密
     * @param param
     * @return
     */

    public BaseResult decryptAes(MeditationParam param){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        Map list = aesService.decryptAes(param);

        return new BaseResult(code,message,list);
    }

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
         //库里存取unionId
         String unionId = (String) map.get("unionId");
         String openId = (String) map.get("openid");
        Integer uuid = wechatUsersService.insertWechatUserUId(unionId,openId);
        map.put("uuid",uuid);
        return new BaseResult(code,message,map);
    }
    
    public static void main(String[] args) {
    
    }
}
