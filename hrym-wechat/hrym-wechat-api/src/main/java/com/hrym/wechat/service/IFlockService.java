package com.hrym.wechat.service;

import com.hrym.wechat.smallProgram.ItemParam;
import com.hrym.wechat.smallProgram.FlockRecordParam;
import com.hrym.wechat.smallProgram.WechatFlockParam;
import com.hrym.wechat.smallProgram.WechatFlockUserParam;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;

public interface IFlockService {

    /**
     * 首页共修群列表
     * @param uuid
     * @return
     */
    Map<String,Object> listFlockByUuid(Integer uuid);

    /**
     * 创建共修群
     * @param param
     */
    Map<String,Object> createFlock(WechatFlockParam param);

    /**
     * 查询共修群页面详细信息
     * @param param
     * @return
     */
    Map<String,Object> selectById(WechatFlockParam param);

    /**
     * 是否加群
     * @param param
     * @return
     */
    Map<String,Object> invitePage(WechatFlockUserParam param);

    //群功课统计
    List flockStatistic(FlockRecordParam param);

    //根据群ID查找群功课
    List flockItemList(FlockRecordParam param);


    /**
     * 查询共修群简介 隐私状态 回向
     * @param param
     * @return
     */
    Map<String,Object> queryFlockIntro(WechatFlockParam param);

    /**
     * 编辑 / 更新 群简介 群隐私 回向
     * @param param
     */
    void updateFlock(WechatFlockParam param);


    /**
     * 添加群功课
     * @param param
     */
    void addItem(WechatFlockParam param);

    /**
     * 删除群功课
     * @param param
     */
    Map<String,Object> removeFlockLesson(WechatFlockParam param);
    
    /**
     * 共修首页报数《查找所有群功课，去重》
     * @param param
     * @return
     */
    List queryFlockItemNameList(WechatFlockParam param);
    
    /**
     * 统计数据时创建日期和结束时间
     * @param param
     * @return
     */
    Map<String,Object> flockStatisticTimes(WechatFlockParam param);
    
    /**
     * 设置功课简介、隐私、量词
     * @param param
     */
    void flockItemSetting(ItemParam param);
    
    /**
     * 查找功课简介、隐私、量词
     * @param param
     */
    Map<String,Object> queryFlockItemSetting(ItemParam param);
    
    /**
     * 可加入共修列表
     * @param param
     * @return
     */
    Map<String, Object> queryJoinFlockList(WechatFlockParam param);
    
    /**
     * 共修群排序
     * @param param
     */
    void updateFlockUserOrder(WechatFlockParam param);
    
    /**
     * 备份数据
     * @return
     */
    Map<String,Object> backItemDatas(Integer userId);


//    void test();

}
