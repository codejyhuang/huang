package com.hrym.app.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

/**
 * Created by hrym13 on 2018/3/5.
 */
public class test {


    public static void main(String[] args) {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("TimerTask is called!");
            }
        };

        Timer timer = new Timer();
        /*
         * schedule 和 scheduleAtFixedRate 区别：
         *  可将schedule理解为scheduleAtFixedDelay，
         *  两者主要区别在于delay和rate
         *  1、schedule，如果第一次执行被延时（delay），
         *      随后的任务执行时间将以上一次任务实际执行完成的时间为准
         *  2、scheduleAtFixedRate，如果第一次执行被延时（delay），
         *      随后的任务执行时间将以上一次任务开始执行的时间为准（需考虑同步）
         *
         *  参数：1、任务体    2、延时时间（可以指定执行日期）3、任务执行间隔时间
         */
        // timer.schedule(task, 0, 1000 * 3);
        timer.scheduleAtFixedRate(task, 0, 1000 * 5);
    }
}
