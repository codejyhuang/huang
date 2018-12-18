package com.hrym.wechat.smallProgram;

import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/17.
 */
public interface CountRecordService {
    /**
     * 录入上报数
     *
     * @param param
     */
    void insertAllCount(CountRecordParam param);

    /**
     * 多版本计数报数页面展示
     *
     * @param param
     * @return
     */
    List<Map<String, Object>> findScheduleTypeId(CountRecordParam param);


    /**
     * 报数历史记录单版本
     *
     * @param param
     * @return
     */
    Map<String, Object> findAllCountRecord(CountRecordParam param);

    /**
     * 报数历史记录多版本
     *
     * @param param
     * @return
     */
    Map<String, Object> findCountRecordByTypeId(CountRecordParam param);

    /**
     * 今日上报数
     *
     * @param param
     * @return
     */
    Integer findTodayCount(CountRecordParam param);

    /**
     * 删除历史记录
     *
     * @param param
     */
    void deleteCountRecord(CountRecordParam param);
}
