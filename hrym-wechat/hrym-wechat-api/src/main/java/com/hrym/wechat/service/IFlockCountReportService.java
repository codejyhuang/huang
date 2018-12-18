package com.hrym.wechat.service;

import com.hrym.wechat.smallProgram.FlockCountReportParam;

import java.util.Map;

/**
 * Created by hrym13 on 2018/11/9.
 */
public interface IFlockCountReportService {
    
    /**
     * 报数
     * @param param
     * @return
     */
    Map<String, Object> flockCountReport(FlockCountReportParam param);
    
    /**
     * 历史数据导入
     * @param param
     */
    void importHistory(FlockCountReportParam param);

}
