package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.VO.SplashScreemVO;
import com.hrym.rpc.app.util.Result;

/**
 * Created by hrym13 on 2018/4/13.
 */
public interface SplashScreemMgrService {

    /**
     * 闪屏页列表
     * @return
     */
    Result findALLSScreem(Integer page,Integer rows);
    /**
     * 闪屏页录入
     * @param screemVO
     */
    Result insertSScreem(SplashScreemVO screemVO);

    /**
     * 闪屏更新
     * @param screemVO
     */
    Result updateSScreem(SplashScreemVO screemVO);

    /**
     * 闪屏删除
     * @param id
     */
    Result deleteSScreem(Integer id);

    /**
     * 闪屏编辑
     * @param id
     * @return
     */
    SplashScreemVO findSScreemById(Integer id);

}
