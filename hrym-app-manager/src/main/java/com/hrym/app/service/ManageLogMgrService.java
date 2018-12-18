package com.hrym.app.service;

import com.hrym.rpc.app.util.Result;

/**
 * Created by hrym13 on 2018/2/2.
 * 后台日志
 */
public interface ManageLogMgrService {

    /**
     * 查找所有的日志
     * @param page
     * @param rows
     * @return
     */
    Result findAllManageLog(Integer page,Integer rows);
}
