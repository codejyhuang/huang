package com.hrym.auth.controller;

import com.google.common.collect.Maps;
import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.logUtil.LogBean;
import com.hrym.common.util.HttpUtil;
import com.hrym.common.util.RedisUtil;
import com.hrym.rpc.app.common.constant.AssociationParam;
import com.hrym.rpc.app.common.constant.LogParam;
import com.hrym.rpc.app.common.constant.ResourceItemLessonParam;
import com.hrym.rpc.app.dao.model.VO.DateVO;
import com.hrym.rpc.app.dao.model.VO.bookVO.TaskItemVO;
import com.hrym.rpc.app.dao.model.association.WishActivity;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.auth.api.HtmlViewService;
import com.hrym.rpc.auth.api.ResourceItemLessonService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import scala.annotation.meta.param;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mj on 2017/11/2.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class HtmlViewController extends BaseController {

    @Autowired
    private HtmlViewService htmlViewService;
    @Autowired
    private ResourceItemLessonService itemLessonService;

    /**
     * 分享
     * @param
     * @return
     */
    @Allowed
    @RequestMapping(value = "/share/{type}/{id}/{doneNum}/{todayCommitNum}/{targetNum}")
    public ModelAndView doShare(@PathVariable Integer type, @PathVariable Integer id,@PathVariable double doneNum,
                                @PathVariable double todayCommitNum,@PathVariable String targetNum){

        Map<String,Object> map = htmlViewService.doShare(type,id,doneNum,todayCommitNum,targetNum);
        GwsLogger.info("map的值==========",map);
        ModelAndView mav = new ModelAndView();
        mav.addObject("bean",map);

        if (type == 0){
            mav.setViewName("/Invitation");
        }
        if (type == 1){
            mav.setViewName("/lessons_complete");
        }
        if (type == 2){
            mav.setViewName("/lessons_complete");
        }
        return mav;
    }

    /**
     * banner主页注册
     * @param uuid
     * @param userName
     * @param wx
     * @param phone
     * @param address
     * @param email
     * @return
     */
    @RequestMapping(value = "/activity/updateUserInfo")
    @Allowed
    @ResponseBody
    public int updateUserInfo(String uuid,String userName, String wx, String phone, String address, String email) {

        Integer id = Integer.valueOf(uuid);
        boolean ret =  htmlViewService.updateUserInfo(id,userName,wx,phone,address,email);
        return ret?1:0;
    }

    /**
     * 日志统计
     * @param param
     */
    @RequestMapping(value = "/doLog")
    @Allowed
    @ResponseBody
    public BaseResult doLog(@RequestBody LogParam param){

        String cmd = param.getCmd();
        LogBean bean = new LogBean();
        bean.setUuid(param.getUuid());
        bean.setGroup(param.getGroup());
        bean.setGroupValue(param.getGroupValue());
        bean.setGroupValueDesc(param.getGroupValueDesc());
        bean.setType(param.getType());
        bean.setTypeDesc(param.getTypeDesc());
        bean.setContent(param.getContent());
        bean.setContent2(param.getContent2());
        bean.setNum(param.getNum());
        bean.setNum2(param.getNum2());

        GwsLogger.info("日志统计",bean);

        return new BaseResult(BaseConstants.GWSCODE0000,BaseConstants.GWSMSG0000,null);
    }

    /**
     * 获取日期图片
     * @param param
     * @return
     */
    @RequestMapping(value = "/getDatePic")
    @Allowed
    @ResponseBody
    public BaseResult getDatePic(@RequestBody LogParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        Map<String,Object> map = null;
        DateVO dateVO = null;
        if (StringUtils.isNotBlank(param.getTime())){
            map = htmlViewService.getDatePic(param);
        }else {
            code = BaseConstants.GWSCODE2002;
            message = BaseConstants.GWSMSG2002;
        }
        return new BaseResult(code,message,map);
    }

    /**
     * 任务状态修改
     * @param
     * @return
     */
    @RequestMapping("/share")
    @Allowed
    @ResponseBody
    public BaseResult updateAllWishActivity(@RequestBody AssociationParam param){

        if (param.getCmd().equals("updateShareStatus")){
            return htmlViewService.updateAllWishActivity(param);
        }
        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);

    }


    /**
     * 获取活动信息
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/getActivityInfo/{uuid}")
    @Allowed
    @ResponseBody
    public String getActivityInfo(@PathVariable String uuid, String callback) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        //获取所有的奖品信息
        //获取用户信息，包括邀请码，是否填地址，是否兑换
        Map<String,Object>  ret = htmlViewService.getActivityInfo(Integer.valueOf(uuid));
        String result = callback+"("+ JSONObject.fromObject(ret).toString()+");";//拼接可执行的js
        return result;
    }

    /**
     * 活动奖品领取
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/exchange/{uuid}/{id}")
    @Allowed
    @ResponseBody
    public String exchangeActivityPresent(@PathVariable String uuid,@PathVariable String id, String callback) {

        //获取所有的奖品信息
        //获取用户信息，包括邀请码，是否填地址，是否兑换
        Map<String,Object>  ret = htmlViewService.exchangeActivityPresent(uuid,id);

        String result = callback+"("+ JSONObject.fromObject(ret).toString()+");";//拼接可执行的js

        return result;
    }

    /**
     * banner主页注册
     * @param uuid
     * @param userName
     * @param wx
     * @param phone
     * @param address
     * @param email
     * @return
     */
    @RequestMapping(value = "/activity/addUserAddress/{uuid}/{userName}/{wx}/{phone}/{address}/{email}")
    @Allowed
    @ResponseBody
    public String addUserAddress(@PathVariable String uuid,@PathVariable String userName,@PathVariable  String wx,
                                 @PathVariable String phone,@PathVariable  String address,@PathVariable  String email,String callback) {

        Integer id = Integer.valueOf(uuid);
        try {
            userName = java.net.URLDecoder.decode(userName,"UTF-8");
            address = java.net.URLDecoder.decode(address,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        boolean b =  htmlViewService.updateUserInfo(id,userName,wx,phone,address,email);
        Map<String,Object>  ret = Maps.newHashMap();
        int temp = b?1:0;
        ret.put("code",temp);
        String result = callback+"("+ JSONObject.fromObject(ret).toString()+");";
        return result;
    }

    @RequestMapping(value = "/jssdk/getJsSignMap")
    @Allowed
    @ResponseBody
    public String getJsSignMap(String urld,String callback) {

        String ret;
        JSONObject obj;

        String token = RedisUtil.get("wx_token");

        if(token==null||token.isEmpty()) {

            // 先获取token
            String urlToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx01a274f765225716"
                    + "" + "&secret=" + "7601a471da5a9b5e889c1a9fcf592698";

            //发送get请求
            ret = HttpUtil.sendGet(urlToken);
            obj = JSONObject.fromObject(ret);
            token = obj.getString("access_token");

            RedisUtil.set("wx_token",token, 60*60*2);
        }

        String ticket = RedisUtil.get("wx_ticket");
        if(ticket==null||ticket.isEmpty()) {
            String urlTicket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token + "&type=jsapi";
            ret = HttpUtil.sendGet(urlTicket);
            obj = JSONObject.fromObject(ret);
            ticket = obj.getString("ticket");

            RedisUtil.set("wx_ticket",ticket, 60*60*2);
        }


        //3、时间戳和随机字符串
        String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳

        String str = "jsapi_ticket="+ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+urld;
        String sha1 = SHA1(str);

        Map<String,String> result = Maps.newHashMap();
        result.put("appId","wx01a274f765225716");
        result.put("timestamp",timestamp);
        result.put("nonceStr",noncestr);
        result.put("signature",sha1);

        String back = callback+"("+ JSONObject.fromObject(result).toString()+");";
        return back;

    }

    private String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取活动信息
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/bookShare")
    @Allowed
    @ResponseBody
    public String getBookDetails(@RequestParam("itemId") Integer itemId,@RequestParam("callback") String callback) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        TaskItemVO vo = htmlViewService.findBookDetails(itemId);
        String result = callback+"("+ JSONObject.fromObject(vo).toString()+");";//拼接可执行的js
        return result;
    }

    @RequestMapping("/findArticleById")
    @Allowed
    @ResponseBody
    public BaseResult findResourceArticle(@RequestParam("articleId")Integer articleId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String,Object> map = htmlViewService.findResourceArticle(articleId);
        return new BaseResult(code,message,map);
    }

    /**
     * h5页面功课任务添加
     * @param
     * @return
     */
    @RequestMapping(value = "/addLesson")
    @ResponseBody
    @Allowed
    public BaseResult lessonAdd(String cmd,Integer itemId,Integer itemContentId,String token) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        itemLessonService.lessonAdd(itemId,itemContentId,token);

        return new BaseResult(code,message,null);
    }

}
