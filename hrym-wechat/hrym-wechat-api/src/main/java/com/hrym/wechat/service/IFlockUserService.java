package com.hrym.wechat.service;

import com.hrym.wechat.smallProgram.WechatFlockParam;

import java.util.Map;

public interface IFlockUserService {

    /**
     * 共修群成员列表
     * @param param
     * @return
     */
    Map<String,Object> flockMember(WechatFlockParam param);

    /**
     * 加入共修群
     * @param param
     * @return
     */
    Map<String,Object> joinFlock(WechatFlockParam param);

    /**
     * 批量移除群成员
     * @param param
     */
    void removeFlockUser(WechatFlockParam param);

    /**
     * 退出共修群
     * @param param
     */
    void dropFlock(WechatFlockParam param);

    /**
     * 解散共修群
     * @param param
     */
     Map<String,Object> dissolveFlock(WechatFlockParam param);

    /**
     * 解散群  检查群成员是否为空
     * @param param
     * @return
     */
    Map<String, Object> checkFlockMember(WechatFlockParam param);
}
