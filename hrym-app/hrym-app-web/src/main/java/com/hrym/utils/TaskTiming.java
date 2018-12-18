package com.hrym.utils;

import com.hrym.rpc.auth.dao.mapper.TaskPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 设置定时器每天将今日提交总数清零
 * Created by mj on 2017/10/31.
 */
@Component
public class TaskTiming extends TimerTask {

    @Autowired
    private TaskPlanMapper taskPlanMapper;

    public void TimerTask() {

        taskPlanMapper.updateTodayCommitNum();
    }

    @Override
    public void run() {
        TimerTask();
    }
}
