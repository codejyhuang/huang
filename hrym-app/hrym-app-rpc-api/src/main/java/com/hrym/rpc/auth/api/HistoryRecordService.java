package com.hrym.rpc.auth.api;

import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.RecordParam;

/**
 * 功课历史数据
 * Created by mj on 2017/12/29.
 */
public interface HistoryRecordService {

    /**
     * 最近完成的功课历史数据列表
     * @return
     */
    BaseResult getRecentRecordList(RecordParam param);

    /**
     * 功课历史详情数据
     * @param param
     * @return
     */
    BaseResult getRecordDetail(RecordParam param);
}
