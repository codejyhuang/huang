package com.hrym.wechat.service;

import com.hrym.wechat.smallProgram.FlockCountReportParam;
import com.hrym.wechat.smallProgram.ItemParam;
import com.hrym.wechat.smallProgram.QueryObjectParam;
import com.hrym.wechat.smallProgram.WechatFlockParam;

import java.util.Map;

public interface IItemLessonService {

    /**
     * 新建共修群   功课列表
     * @param qo
     * @return
     */
    Map<String,Object> listByQo(QueryObjectParam qo);


    /**
     * 管理共修群   功课列表
     * @param qo
     * @return
     */
    Map<String,Object> queryByQo(QueryObjectParam qo);

    /**
     * 群功课详细信息
     * @param param
     * @return
     */
    Map<String,Object> flockAssignmentMessage(ItemParam param);


    /**
     * 我的功课首页
     * @param param
     * @return
     */
    Map<String,Object> selfItem(ItemParam param);

    /**
     * 我的功课  添加task  功课列表
     * @param qo
     * @return
     */
    Map<String,Object> selfItemList(QueryObjectParam qo);

    /**
     * 添加我的功课  task
     * @param param
     */
    void addTask(ItemParam param);


    /**
     * 删除task
     * @param param
     */
    void removeTask(ItemParam param);


    /**
     * 自修详情 卡片数据信息
     * @param param
     * @return
     */
    Map<String,Object> selfRepairCard(ItemParam param);

    /**
     * 自修详情  历史记录
     * @param param
     * @return
     */
    Map<String,Object> selfRepairHistory(ItemParam param);

    /**
     * 逻辑删除 record
     * @param param
     * @return
     */
    void removeSelfRepairHistory(ItemParam param);

    /**
     * 共修详情  用户群列表
     * @param param
     * @return
     */
    Map<String,Object> itemFlockList(ItemParam param);


    /**
     * 共修详情  共修群功课详情
     * @param param
     * @return
     */
    Map<String,Object> flockItemMessageCard(ItemParam param);

    /**
     * 共修详情 共修群功课  可点赞动态
     * @param
     * @return
     */
    Map<String,Object> flockItemMessageRecord(ItemParam param);

    /**
     * 共修详情 点赞用户列表
     * @param
     * @return
     */
    Map<String,Object> queryLikeMember(ItemParam param);


    /**
     * 点赞取消 赞操作
     * @param param
     */
    void parseRecord(ItemParam param);
    
    
    /**
     * 功课排序
     * @param param
     */
    void updateitemListOrder(WechatFlockParam param);


}
