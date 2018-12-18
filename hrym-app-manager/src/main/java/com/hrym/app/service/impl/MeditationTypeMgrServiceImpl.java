package com.hrym.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrym.app.dao.MeditationScheduleMgrMapper;
import com.hrym.app.dao.MeditationTypeMgrMapper;
import com.hrym.app.service.MeditationTypeMgrService;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.util.DateUtil;
import com.hrym.rpc.app.util.Result;
import com.hrym.rpc.wechat.dao.model.MeditationType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;

/**
 * Created by hrym13 on 2018/5/1.
 */
@Service
public class MeditationTypeMgrServiceImpl implements MeditationTypeMgrService {

    @Autowired
    private MeditationTypeMgrMapper typeMgrMapper;
    @Autowired
    private MeditationScheduleMgrMapper scheduleMgrMapper;

    /**
     * 查找共修类型列表
     *
     * @return
     */
    @Override
    public Result findAllMeditation(Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        PageHelper.startPage(page, rows);
        List<MeditationType> list = typeMgrMapper.findAllMeditation();

        for (MeditationType type : list) {
            if (null != type.getCreatedTime()) {
                String createdTime = DateUtil.timestampToDates(type.getCreatedTime(), TIME_PATTON_DEFAULT);
                type.setCreatedTimes(createdTime);
            }
            if (null != type.getUpdateTime()) {
                String updateTime = DateUtil.timestampToDates(type.getUpdateTime(), TIME_PATTON_DEFAULT);
                type.setUpdateTimes(updateTime);
            }
        }

        PageInfo pageInfo = new PageInfo(list);
        long total = pageInfo.getTotal();

        return new Result(code, message, total, list);
    }

    /**
     * 共修活动了类型录入
     *
     * @param type
     */
    @Override
    public Result insertMeditation(MeditationType type) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        type.setCreatedTime(DateUtil.currentSecond());
        type.setUpdateTime(DateUtil.currentSecond());

        if (StringUtils.isNotBlank(type.getStartTimes())) {

            int startTime = DateUtil.getDateToLinuxTime(type.getStartTimes(), TIME_PATTON_DEFAULT);
            type.setStartTime(startTime);
        }
        if (StringUtils.isNotBlank(type.getExpectTimes())) {

            int expectTime = DateUtil.getDateToLinuxTime(type.getExpectTimes(), TIME_PATTON_DEFAULT);
            type.setExpectTime(expectTime);
        }

        typeMgrMapper.insertMeditation(type);
        return new Result(code, message, null);
    }

    /**
     * 共修活动了类型更新
     *
     * @param type
     */
    @Override
    public Result updateMeditation(MeditationType type) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        type.setUpdateTime(DateUtil.currentSecond());

        if (StringUtils.isNotBlank(type.getStartTimes())) {

            int startTime = DateUtil.getDateToLinuxTime(type.getStartTimes(), TIME_PATTON_DEFAULT);
            type.setStartTime(startTime);
        }
        if (StringUtils.isNotBlank(type.getExpectTimes())) {

            int expectTime = DateUtil.getDateToLinuxTime(type.getExpectTimes(), TIME_PATTON_DEFAULT);
            type.setExpectTime(expectTime);
        }
        typeMgrMapper.updateMeditation(type);
        return new Result(code, message, null);
    }

    /**
     * 共修活动了类型删除
     *
     * @param id
     */

    @Override
    public Result deleteMeditation(Integer id) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        typeMgrMapper.deleteMeditation(id);
        scheduleMgrMapper.deleteMedScheduleByTypeId(id);
        return new Result(code, message, null);
    }

    /**
     * 根据ID查找对应的内容
     *
     * @param id
     * @return
     */
    @Override
    public MeditationType findMeditationById(Integer id) {

        MeditationType type = typeMgrMapper.findMeditationById(id);
        if (null != type.getStartTime()) {
            String startTimes = DateUtil.timestampToDates(type.getStartTime(), TIME_PATTON_DEFAULT);
            type.setStartTimes(startTimes);
        }
        if (null != type.getExpectTime()) {
            String expectTimes = DateUtil.timestampToDates(type.getExpectTime(), TIME_PATTON_DEFAULT);
            type.setExpectTimes(expectTimes);
        }

        return type;
    }

    /**
     * 查找所有的共修类型
     *
     * @return
     */
    @Override
    public Result findAllMeditationType() {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        List<MeditationType> list = typeMgrMapper.findAllMeditation();

        return new Result(code, message, list);
    }

    /**
     * 根据功课类型名称查找共修类型列表
     *
     * @param meditationTypeName
     * @return
     */
    @Override
    public Result findMeditationByName(String meditationTypeName, Integer page, Integer rows) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        if (StringUtils.isNotBlank(meditationTypeName)) {
            List<MeditationType> typeList = typeMgrMapper.findMeditationByName(meditationTypeName);
            PageHelper.startPage(page, rows);

            for (MeditationType type : typeList) {

                if (null != type.getCreatedTime()) {

                    String createdTime = DateUtil.timestampToDates(type.getCreatedTime(), TIME_PATTON_DEFAULT);
                    type.setCreatedTimes(createdTime);
                }
                if (null != type.getUpdateTime()) {

                    String updateTime = DateUtil.timestampToDates(type.getUpdateTime(), TIME_PATTON_DEFAULT);
                    type.setUpdateTimes(updateTime);
                }
            }
            PageInfo pageInfo = new PageInfo(typeList);
            long total = pageInfo.getTotal();

            return new Result(code, message, total, typeList);
        } else {
            return findAllMeditation(page, rows);
        }
    }

    /**
     * 查找用户总人数
     *
     * @return
     */
    @Override
    public Result findAllWechatUserCount() {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        MeditationType userCount = typeMgrMapper.findAllWechatUserCount();

        return new Result(code, message, userCount);
    }
}
