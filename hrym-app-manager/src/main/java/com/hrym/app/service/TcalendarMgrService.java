package com.hrym.app.service;

import com.hrym.rpc.app.dao.model.VO.DateVO;
import com.hrym.rpc.app.util.Result;

import java.util.List;

/**
 * Created by hrym13 on 2017/12/26.
 */
public interface TcalendarMgrService {

    /**
     * 日历显示
     * @param page
     * @param rows
     * @return
     */
    Result findAllTcalend(Integer page,Integer rows);

    /**
     * 日历修改
     * @param tcalendar
     * @return
     */
    Result updateAllTcalend(DateVO tcalendar);

    /**
     * 日历添加
     * @param tcalendar
     * @return
     */
    Result insertAllTcalend(DateVO tcalendar);

    /**
     * 删除
     * @param Id
     * @return
     */
    Result deleteAllTcalend(Integer Id);

    /**
     * 根据ID查找内容
     * @param Id
     * @return
     */
    DateVO FindTcalendById(Integer Id);
}
