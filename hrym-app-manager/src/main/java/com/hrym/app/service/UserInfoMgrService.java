package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.VO.UserInfoVO;
import com.hrym.rpc.app.dao.model.task.FeedBack;
import com.hrym.rpc.app.dao.model.task.UserInfo;
import com.hrym.rpc.app.util.Result;

import java.util.Map;

/**
 * Created by hrym13 on 2017/9/7.
 */
public interface UserInfoMgrService {
    /**
     * 用户详情
     */
    Map findAllUserlist(UserInfo userInfo);

    /**
     * 用户列表
     * @param page
     * @param rows
     * @return
     */
    Result findAllUser(Integer page,Integer rows,Integer created_time);

    /**
     * 实名认证审核
     * @param page
     * @param rows
     * @return
     */
    Result certification(Integer page, Integer rows);

    /**
     * 去审核认证
     * @param userInfo
     * @return
     */
    Result updateCertification(UserInfo userInfo);

    /**
     * 用户称呼搜索
     * @param userInfo
     * @return
     */
    Result searchNickName(UserInfo userInfo, Integer page,Integer rows);

    /**
     * 用户最近登录时间
     * @param userInfoVO
     * @return
     */
    Result searchUserInfoByReTime(UserInfoVO userInfoVO, Integer page, Integer rows);

    /**
     * 用户反馈
     * @param page
     * @param rows
     * @return
     */
    Result findAllFeedBack(Integer page,Integer rows);

    /**
     * 反馈处理
     * @param feedBack
     * @return
     */
    Result updateFeedBack(FeedBack feedBack);
}
