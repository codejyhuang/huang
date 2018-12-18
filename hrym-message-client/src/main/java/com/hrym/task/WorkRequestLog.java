package com.hrym.task;

import com.hrym.common.message.RequestLog;
import com.hrym.common.util.DateUtil;
import com.hrym.mapper.MessageMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mj on 2018/4/13.
 */
@Component
public class WorkRequestLog {

    @Autowired
    private MessageMapper mapper;

    //处理请求日志
    public void dealRequestLog(RequestLog log){

        Integer currentTime = DateUtil.currentSecond();
        String date = DateUtil.timestampToDates(currentTime,DateUtil.DATA_PATTON_YYYYMMDD3);
        String tableName = "v_request_log_data_"+date;
        tableName = mapper.findTable(tableName);
        if (StringUtils.isNotBlank(tableName)){
            mapper.saveRequestLog(log,tableName);
        }
    }
}
