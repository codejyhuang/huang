package com.hrym.auth.controller;

import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.rpc.app.common.constant.RecordParam;
import com.hrym.rpc.app.common.constant.TaskParam;
import com.hrym.rpc.auth.api.HistoryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mj on 2017/12/29.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class HistoryRecordController extends BaseController {

    @Autowired
    private HistoryRecordService historyRecordService;

    @RequestMapping(value = "/record")
    @ResponseBody
    public BaseResult record(@RequestBody RecordParam param){
        String cmd = param.getCmd();

        if ("recentRecord".equals(cmd)){
            return getRecentRecordList(param);
        }
        if ("recordDetail".equals(cmd)){
            return getRecordDetail(param);
        }
        return new BaseResult(BaseConstants.GWSCODE4007,BaseConstants.GWSMSG4007,null);
    }

    /**
     * 最近完成的功课历史数据列表
     * @return
     */
    public BaseResult getRecentRecordList(RecordParam param) {

        return historyRecordService.getRecentRecordList(param);
    }

    /**
     * 功课历史详情数据
     * @param param
     * @return
     */
    public BaseResult getRecordDetail(RecordParam param) {

        return historyRecordService.getRecordDetail(param);
    }


}
