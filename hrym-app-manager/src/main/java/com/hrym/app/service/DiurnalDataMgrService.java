package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.system.DiurnalData;
import com.hrym.rpc.app.dao.model.system.UserData;
import com.hrym.rpc.app.util.Result;

import java.util.List;

/**
 * Created by hrym13 on 2018/3/9.
 */
public interface DiurnalDataMgrService {

    /**
     * 每日数据显示
     * @param page
     * @param rows
     * @return
     */

    Result findUserDataTable(Integer page, Integer rows);

    /**
     *根据表名查找对应的内容
     * @param tableName
     * @return
     */
    List findUserData(String tableName);

    /**
     * 今日登录人数
     * @param tableName
     * @return
     */
    DiurnalData findUserCount(String tableName);

    DiurnalData findAllCountCreatedTime(String tableName);

}
