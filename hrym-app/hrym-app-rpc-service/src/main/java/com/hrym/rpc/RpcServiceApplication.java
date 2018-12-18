package com.hrym.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 业务程序入口
 *
 * Created by hong on 2017/6/22.
 */
public class RpcServiceApplication {
    private static Logger _log = LoggerFactory.getLogger(RpcServiceApplication.class);

    public static void main(String[] args) {
        _log.info(">>>>> hrym-app-rpc-service 正在启动 <<<<<");
        new ClassPathXmlApplicationContext("classpath:spring/*.xml");
        _log.info(">>>>> hrym-app-rpc-service 启动完成 <<<<<");
    }
}
