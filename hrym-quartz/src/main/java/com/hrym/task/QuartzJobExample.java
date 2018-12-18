package com.hrym.task;

import com.hrym.mapper.TestMapper;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mj on 2018/5/3.
 */
@Component
public class QuartzJobExample implements Job {

    @Autowired
    private TestMapper testMapper;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

//        testMapper.update();

//        JobDetail detail = context.getJobDetail();
//        String name = detail.getJobDataMap().getString("name");
//        System.out.println("★★★★★★★★★★★say hello to " + name + " at " + new Date());
    }
}
