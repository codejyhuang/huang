package com.hrym.app.controller;

import com.hrym.common.base.BaseConstants;
import com.hrym.common.logUtil.GwsLogger;
import com.hrym.common.logUtil.GwsLoggerTypeEnum;
import com.hrym.common.logUtil.LogBean;
import com.hrym.rpc.app.common.constant.ManagerParam;
import com.hrym.rpc.app.dao.model.VO.UserInfoVO;
import com.hrym.rpc.app.dao.model.task.FeedBack;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.app.util.Result;
import com.hrym.app.service.UserInfoMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2017/9/7.
 */
@Controller
@RequestMapping(value = "/admin")
public class UserInfoController extends BaseConstants {

    @Autowired
    private UserInfoMgrService userInfoMgrService;

    /**
     * 用户列表
     */
    @RequestMapping("/findAllUser")
    @ResponseBody
    public Result findAllUser (ManagerParam param,Integer created_time){

        return userInfoMgrService.findAllUser(param.getPageNumber(),param.getPageSize(),created_time);
    }

    /**
     * 用户详情页
     * @param userInfo
     * @return
     */
    @RequestMapping("/findAllUserlist")
    public ModelAndView findAllUserlist (UserInfo userInfo){

        Map user = userInfoMgrService.findAllUserlist(userInfo);
        ModelAndView mav = new ModelAndView();
        mav.addObject("userlist",user);
        mav.setViewName("/userlist/userdetails");
        return  mav;
    }

    /**
     * 实名认证
     * @param param
     * @return
     */
    @RequestMapping("/certification")
    @ResponseBody
    public Result certification (ManagerParam param){

        return userInfoMgrService.certification(param.getPageNumber(),param.getPageSize());
    }

    /**
     * 实名认证审核
     * @param userInfo
     * @return
     */
    @RequestMapping("/updateCertification")
    @ResponseBody
    public Result updateCertification (UserInfo userInfo){

        if (1 == userInfo.getIdentityAuthState()){
            LogBean bean = new LogBean();
            bean.setUuid(userInfo.getUuid());
            bean.setGroup(GwsLoggerTypeEnum.REALNAME.getType());
            bean.setGroupValue(userInfo.getUuid());
            bean.setContent("实名认证通过");
            GwsLogger.info("实名认证审核",bean);
        }

        return userInfoMgrService.updateCertification(userInfo);
    }

    /**
     * 用户称呼搜索
     * @return
     */
    @RequestMapping("/searchNickName")
    @ResponseBody
    public Result searchNickName ( UserInfo userInfo,ManagerParam param) throws Exception{

        if (null != userInfo.getNickName()){
            //        rcl.getCatalogueName()
            //转码
            String val = URLDecoder.decode(userInfo.getNickName(), "UTF-8");

            userInfo.setNickName(val);

        }
        return userInfoMgrService.searchNickName ( userInfo,param.getPageNumber(),param.getPageSize());
    }

    /**
     * 根据用户最近登录时间搜索
     * @param userInfoVO
     * @param param
     * @return
     */
    @RequestMapping("/searchUserInfoByReTime")
    @ResponseBody
    public Result searchUserInfoByReTime(UserInfoVO userInfoVO, ManagerParam param){

        return userInfoMgrService.searchUserInfoByReTime(userInfoVO,param.getPageNumber(),param.getPageSize());
    }

    /**
     * 用户反馈
     * @param param
     * @return
     */
    @RequestMapping("/findAllFeedBack")
    @ResponseBody
    public Result findAllFeedBack (ManagerParam param){

        return userInfoMgrService.findAllFeedBack(param.getPageNumber(),param.getPageSize());
    }

    /**
     * 反馈内容
     * @param feedBack
     * @return
     */
    @RequestMapping("/updateFeedBack")
    @ResponseBody
    public Result updateFeedBack(FeedBack feedBack){

        return userInfoMgrService.updateFeedBack(feedBack);
    }

}
