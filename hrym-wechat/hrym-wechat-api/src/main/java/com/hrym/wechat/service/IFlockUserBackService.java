package com.hrym.wechat.service;

import com.hrym.wechat.smallProgram.FlockRecordParam;
import com.hrym.wechat.smallProgram.FlockUserBackParam;

import java.util.Map;

/**
 * Created by hrym13 on 2018/11/28.
 */
public interface IFlockUserBackService {
    
    /**
     * 上次特别回向
     *
     * @param param
     * @return
     */
    Map<String, Object> lastSpecialBack(FlockUserBackParam param);
    
    /**
     * 共修回向列表
     *
     * @param param
     * @return
     */
    Map<String, Object> queryDedicationVersesList(FlockUserBackParam param);
    
    /**
     * 我的特别回向详情
     *
     * @param param
     * @return
     */
    Map<String, Object> querySpecialBackDetails(FlockUserBackParam param);
    
    /**
     * 编辑特别回向文
     *
     * @param param
     */
    void updateSpecialBack(FlockUserBackParam param);
    
    /**
     * 功课特别回向录入
     *
     * @param param
     * @return
     */
    void insertSpecialBack(FlockUserBackParam param);

//    /**
//     * 群回向详情
//     *
//     * @param param
//     * @return
//     */
//    Map<String, Object> queryFlockBackDetails(FlockUserBackParam param);
//
    
    /**
     * 群回向详情dejson
     *
     * @param param
     * @return
     */
    Map<String, Object> queryDedicationVerses(FlockUserBackParam param);
    
    /**
     * 群特别回向
     *
     * @param param
     * @return
     */
    Map<String, Object> queryDedicationVersesRecordList(FlockUserBackParam param);
    
    /**
     * 特别回向删除
     *
     * @param param
     */
    void deleteSpecialBack(FlockUserBackParam param);
    
    /**
     * 报数的功课回向
     *
     * @param param
     * @return
     */
    Map<String, Object> queryCountDirection(FlockUserBackParam param);
    
    /**
     * 定时任务
     *
     * @return
     */
    void testList();
    
    void testS();
    
}
