package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.wechat.smallProgram.CountRecordParam;
import com.hrym.wechat.smallProgram.CountRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by hrym13 on 2018/4/17.
 */
@Controller
@RequestMapping("/hrym/program")
public class CountRecordController extends BaseController {

    @Autowired
    private CountRecordService recordService;


    @RequestMapping("/CountRecord")
    @Allowed
    @ResponseBody
    public BaseResult CountRecord(@RequestBody CountRecordParam param) {
        String cmd = param.getCmd();
        //计数上报单版本历史记录列表
        if ("findAllCountRecord".equals(cmd)) {
            return findAllCountRecord(param);
        }
        if ("insertAllCount".equals(cmd)) {
            return insertAllCount(param);
        }
        if ("findTodayCount".equals(cmd)) {
            return findTodayCount(param);
        }
        //计数上报多版本历史记录列表
        if ("findCountRecordByTypeId".equals(cmd)) {
            return findCountRecordByTypeId(param);
        }
        //多版本计数报数页面展示数据
        if ("findScheduleTypeId".equals(cmd)) {
            return findScheduleTypeId(param);
        }
        // 历史记录数单条删除
        if ("deleteCountRecord".equals(cmd)) {
            return deleteCountRecord(param);
        }
        return new BaseResult(BaseConstants.GWSCODE4007, BaseConstants.GWSMSG4007, null);
    }

    /**
     * 计数上报单版本历史记录列表
     *
     * @param param
     * @return
     */
    public BaseResult findAllCountRecord(CountRecordParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String, Object> list = recordService.findAllCountRecord(param);

        return new BaseResult(code, message, list);

    }

    /**
     * 计数上报多版本历史记录列表
     *
     * @param param
     * @return
     */
    public BaseResult findCountRecordByTypeId(CountRecordParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Map<String, Object> list = recordService.findCountRecordByTypeId(param);

        return new BaseResult(code, message, list);
    }

    /**
     * 计数存入
     *
     * @param param
     * @return
     */
    public BaseResult insertAllCount(CountRecordParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        recordService.insertAllCount(param);

        return new BaseResult(code, message, null);
    }

    /**
     * 计数上报页面展示数据
     *
     * @param param
     * @return
     */
    public BaseResult findScheduleTypeId(CountRecordParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<Map<String, Object>> list = recordService.findScheduleTypeId(param);

        return new BaseResult(code, message, list);
    }

    /**
     * 今日所报数
     *
     * @param param
     * @return
     */
    public BaseResult findTodayCount(CountRecordParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        Integer todaynumber = recordService.findTodayCount(param);

        return new BaseResult(code, message, todaynumber);

    }

    /**
     * 删除历史上报数记录
     *
     * @param param
     * @return
     */
    public BaseResult deleteCountRecord(CountRecordParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        recordService.deleteCountRecord(param);
        return new BaseResult(code, message, null);
    }
}
