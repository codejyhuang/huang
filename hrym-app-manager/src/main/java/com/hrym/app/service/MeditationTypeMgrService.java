package com.hrym.app.service;

import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.wechat.dao.model.MeditationType;

import java.util.List;

/**
 * Created by hrym13 on 2018/5/1.
 */
public interface MeditationTypeMgrService {

    /**
     * 查找共修类型列表
     * @return
     */
    Result findAllMeditation(Integer page,Integer rows);

    /**
     * 共修活动了类型录入
     * @param type
     */
    Result  insertMeditation(MeditationType type);

    /**
     * 共修活动了类型更新
     * @param type
     */
    Result updateMeditation(MeditationType type);


    /**
     * 共修活动了类型删除
     * @param id
     */
    Result deleteMeditation(Integer id);

    /**
     * 根据ID查找对应的内容
     * @param id
     * @return
     */
    MeditationType findMeditationById(Integer id);

    /**
     * 查找所有的共修类型
     * @return
     */
    Result findAllMeditationType();

    /**
     * 根据功课类型名称查找共修类型列表
     * @param meditationTypeName
     * @return
     */
    Result findMeditationByName( String meditationTypeName,Integer page, Integer rows);

    /**
     * 查找总用户量
     * @return
     */
    Result findAllWechatUserCount();
}
