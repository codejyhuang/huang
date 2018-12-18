package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrym.app.dao.MeditationScheduleMgrMapper;
import com.hrym.app.service.MeditationScheduleMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.wechat.dao.model.MeditationSchedule;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.hrym.common.util.DateUtil.DATE_PATTON_DEFAULT;
import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by hrym13 on 2018/5/1.
 */
@Service
public class MeditationScheduleMgrServiceImpl implements MeditationScheduleMgrService {

    @Autowired
    private MeditationScheduleMgrMapper scheduleMgrMapper;

    /**
     * 查找共修类型列表
     * @return
     */
    @Override
    public Result findAllMedSchedule(Integer meditationTypeId) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<MeditationSchedule> list = scheduleMgrMapper.findAllMedSchedule( meditationTypeId);

        for (MeditationSchedule me :list){

            if (null !=me.getCreatedTime()){
                String createdTime = DateUtil.timestampToDates(me.getCreatedTime(),DateUtil.TIME_PATTON_DEFAULT);
                me.setCreatedTimes(createdTime);
            }
            if (null !=me.getExpectTime()){
                String expectTime = DateUtil.timestampToDates(me.getExpectTime(),DateUtil.TIME_PATTON_DEFAULT);
                me.setExpectTimes(expectTime);
            }
            if(null !=me.getUpdateTime()){
                String updateTime = DateUtil.timestampToDates(me.getUpdateTime(),DateUtil.TIME_PATTON_DEFAULT);
                me.setUpdateTimes(updateTime);
            }
            if (null !=me.getStartTime()){
                String startTime = DateUtil.timestampToDates(me.getStartTime(),DateUtil.TIME_PATTON_DEFAULT);
                me.setStartTimes(startTime);
            }
            if (null !=me.getLawTime()){
                String startTime = DateUtil.timestampToDates(me.getLawTime(),DateUtil.TIME_PATTON_DEFAULT);
                me.setLawTimes(startTime);
            }

        }
        return new Result(code,message,list);
    }

    /**
     * 根据共修ID查找共修活动
     * @param id
     * @return
     */
    @Override
    public MeditationSchedule findMedAcheduleById(Integer id) {

        MeditationSchedule me = scheduleMgrMapper.findMedAcheduleById(id);

            if (null !=me.getCreatedTime()){
                String createdTime = DateUtil.timestampToDates(me.getCreatedTime(),DateUtil.TIME_PATTON_DEFAULT);
                me.setCreatedTimes(createdTime);
            }
            if (null !=me.getExpectTime()){
                String expectTime = DateUtil.timestampToDates(me.getExpectTime(),DateUtil.TIME_PATTON_DEFAULT);
                me.setExpectTimes(expectTime);
            }
            if(null !=me.getUpdateTime()){
                String updateTime = DateUtil.timestampToDates(me.getUpdateTime(),DateUtil.TIME_PATTON_DEFAULT);
                me.setUpdateTimes(updateTime);
            }
        if (null !=me.getStartTime()){
            String startTime = DateUtil.timestampToDates(me.getStartTime(),DateUtil.TIME_PATTON_DEFAULT);
            me.setStartTimes(startTime);
        }
        if (null !=me.getLawTime()){
            String startTime = DateUtil.timestampToDates(me.getLawTime(),DateUtil.TIME_PATTON_DEFAULT);
            me.setLawTimes(startTime);
        }

        return me;
    }

    /**
     * 删除共修内容列表
     * @param id
     * @return
     */
    @Override
    public Result deleteMedSchedule(Integer id) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (id != null){

            scheduleMgrMapper.deleteMedSchedule(id);
        }else {

            code = BaseConstants.GWSCODE3001;
            message = BaseConstants.GWSMSG3001;

        }
        return new Result(code,message,null);
    }

    /**
     * 更新共修列表内容
     * @param meditationSchedule
     * @return
     */
    @Override
    public Result updateMedSchedule(MeditationSchedule meditationSchedule) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        meditationSchedule.setUpdateTime(DateUtil.currentSecond());

        if (StringUtils.isNotBlank(meditationSchedule.getExpectTimes())){

            int expectTime = DateUtil.getDateToLinuxTime(meditationSchedule.getExpectTimes(),TIME_PATTON_DEFAULT);
            meditationSchedule.setExpectTime(expectTime);
        }
        if (StringUtils.isNotBlank(meditationSchedule.getStartTimes())){

            int startTime = DateUtil.getDateToLinuxTime(meditationSchedule.getStartTimes(),TIME_PATTON_DEFAULT);
            meditationSchedule.setStartTime(startTime);
        }
        if (StringUtils.isNotBlank(meditationSchedule.getLawTimes())){

            int lawTime = DateUtil.getDateToLinuxTime(meditationSchedule.getLawTimes(),TIME_PATTON_DEFAULT);
            meditationSchedule.setLawTime(lawTime);
        }
        if (meditationSchedule.getScheduleId() !=null){

            scheduleMgrMapper.updateMedSchedule(meditationSchedule);
        }else {

            code = BaseConstants.GWSCODE3001;
            message = BaseConstants.GWSMSG3001;

        }
        return new Result(code,message,null);
    }

    /**
     * 共修列表添加
     * @param meditationSchedule
     * @return
     */
    @Override
    public Result insertMedSchedule(MeditationSchedule meditationSchedule) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        meditationSchedule.setUpdateTime(DateUtil.currentSecond());
        meditationSchedule.setCreatedTime(DateUtil.currentSecond());

        if (StringUtils.isNotBlank(meditationSchedule.getExpectTimes())){

            int expectTime = DateUtil.getDateToLinuxTime(meditationSchedule.getExpectTimes(),TIME_PATTON_DEFAULT);
            meditationSchedule.setExpectTime(expectTime);
        }
        if (StringUtils.isNotBlank(meditationSchedule.getStartTimes())){

            int startTime = DateUtil.getDateToLinuxTime(meditationSchedule.getStartTimes(),TIME_PATTON_DEFAULT);
            meditationSchedule.setStartTime(startTime);
        }
        if (meditationSchedule.getRefreshTime() ==null ) {
            meditationSchedule.setRefreshTime(1);
        }

        if (StringUtils.isNotBlank(meditationSchedule.getLawTimes())){

            String d = DateUtil.getAfterAnyDay(meditationSchedule.getLawTimes(),meditationSchedule.getRefreshTime(),TIME_PATTON_DEFAULT);
            int lawTime = DateUtil.getDateToLinuxTime(d,TIME_PATTON_DEFAULT);

            meditationSchedule.setLawTime(lawTime);

        }
        if (null != meditationSchedule) {

            scheduleMgrMapper.insertMedSchedule(meditationSchedule);
        }else {

            code = BaseConstants.GWSCODE3001;
            message = BaseConstants.GWSMSG3001;

        }
        return new Result(code,message,null);
    }

    public static void main(String[] args) {


            String startTime = DateUtil.timestampToDates(DateUtil.currentSecond(),TIME_PATTON_DEFAULT);

        System.out.println(startTime);
//        String d = DateUtil.getDateAfterDay(DateUtil.currentSecond(),5);
        String d = DateUtil.getAfterAnyDay(startTime,5,TIME_PATTON_DEFAULT);
        System.out.println(d);
    }
}
