package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.hrym.app.dao.TcalendarMgrMapper;
import com.hrym.app.service.TcalendarMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.PageInfo;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.dao.model.VO.DateVO;
import com.hrym.rpc.app.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by hrym13 on 2017/12/27.
 */
@Service
public class TcalendarMgrServiceImpl implements TcalendarMgrService {

    @Autowired
    private TcalendarMgrMapper tcalendarMgrMapper;

    /**
     * 日历显示
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllTcalend(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        PageHelper.startPage(page,rows);
        List<DateVO> tcalend = tcalendarMgrMapper.findAllTcalend();

        List<Map<String,Object>> list = new ArrayList<>();
        PageInfo pageInfo  = new PageInfo(tcalend);

        for (DateVO t :tcalend){
            Map<String,Object> map = new HashMap<>();
            map.put("Id",t.getId());
            map.put("datePic",t.getDatePic());
            if(null !=t.getStartTime()){
                map.put("startTime", DateUtil.timestampToDates(t.getStartTime(),TIME_PATTON_DEFAULT));
            }
            if (null !=t.getEndTime()){
                map.put("endTime",DateUtil.timestampToDates(t.getEndTime(),TIME_PATTON_DEFAULT));
            }

            list.add(map);
        }


        long total = pageInfo.getTotal();

        return new Result(code,message,total,list);
    }

    /**
     * 日历修改
     * @param tcalendar
     * @return
     */
    @Override
    public Result updateAllTcalend(DateVO tcalendar) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

            //时间string转int
            if (StringUtils.isNotBlank(tcalendar.getEndTimes())){
                int endTime = DateUtil.getDateToLinuxTime(tcalendar.getEndTimes(),TIME_PATTON_DEFAULT);
                tcalendar.setEndTime(endTime);
            }
            if (StringUtils.isNotBlank(tcalendar.getStartTimes())){
                int startTime = DateUtil.getDateToLinuxTime(tcalendar.getStartTimes(),TIME_PATTON_DEFAULT);
                tcalendar.setStartTime(startTime);
            }
            tcalendarMgrMapper.updateByPrimaryKey(tcalendar);
            return new Result(code,message,null);

    }

    /**
     * 日历添加
     * @param tcalendar
     * @return
     */
    @Override
    public Result insertAllTcalend(DateVO tcalendar) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        //时间string转int
        if (StringUtils.isNotBlank(tcalendar.getEndTimes())){
            int endTime = DateUtil.getDateToLinuxTime(tcalendar.getEndTimes(),TIME_PATTON_DEFAULT);
           tcalendar.setEndTime(endTime);
        }
        if (StringUtils.isNotBlank(tcalendar.getStartTimes())){
            int startTime = DateUtil.getDateToLinuxTime(tcalendar.getStartTimes(),TIME_PATTON_DEFAULT);
            tcalendar.setStartTime(startTime);
        }
        tcalendarMgrMapper.insert(tcalendar);
        return new Result(code,message,null);
    }

    /**
     * 删除
     * @param Id
     * @return
     */
    @Override
    public Result deleteAllTcalend(Integer Id) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        tcalendarMgrMapper.deleteByPrimaryKey(Id);
        return new Result(code,message,null);
    }

    /**
     * 根据ID查找内容
     * @param Id
     * @return
     */
    @Override
    public DateVO FindTcalendById(Integer Id) {

        DateVO tcalendar = tcalendarMgrMapper.selectByPrimaryKey(Id);
        if (null != tcalendar.getStartTime()){
            String startTimeStr = DateUtil.timestampToDates(tcalendar.getStartTime(),TIME_PATTON_DEFAULT);
            tcalendar.setStartTimes(startTimeStr);
        }
        if (null != tcalendar.getEndTime()){
            String endTimeStr = DateUtil.timestampToDates(tcalendar.getEndTime(),TIME_PATTON_DEFAULT);
            tcalendar.setEndTimes(endTimeStr);
        }

        return tcalendar;
    }
}
