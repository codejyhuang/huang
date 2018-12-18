package com.hrym.app.controller;

import com.hrym.common.util.DateUtil;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


import static com.hrym.common.util.DateUtil.TIME_PATTON_DEFAULT;


/**
 * Created by hrym13 on 2017/11/10.
 */

//表示使用Spring Test组件进行单元测试;
@RunWith(SpringJUnit4ClassRunner.class)
//指定Bean的配置文件信息
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-jdbc.xml",
        "classpath:spring/applicationContext.xml",
        "classpath:springMVC-servlet.xml",
})
//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Rollback(value = true)
@Transactional

//使用这个Annotate会在跑单元测试的时候真实的启动一个web服务，然后开始调用Controller的Rest API，待单元测试跑完之后再将web服务停掉;
@WebAppConfiguration(value = "src/main/webapp")
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {


    @Autowired
    private WebApplicationContext wac;
    //加载初始化
    protected MockMvc mockMvc;
    protected MockHttpSession session;

    public static Logger log = Logger.getLogger(Test.class);
    @Before
    public void testBefore() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.session = new MockHttpSession();

        int startTime;
        String startTimeis;
        startTime=(DateUtil.currentSecond());
        startTimeis=DateUtil.timestampToDates(startTime,TIME_PATTON_DEFAULT);
        System.out.println(" testBefore : "+startTimeis);
        System.out.println( "\n");
    }

    @Test
    public void testUp() {
        System.out.println(" Hello!Test");
    }


    @After
    public void testAfter() throws Exception{
        int endTime;
        String endTimeis;
        endTime=(DateUtil.currentSecond());
        endTimeis=DateUtil.timestampToDates(endTime,TIME_PATTON_DEFAULT);
        System.out.println(" testAfter : "+endTimeis);
        System.out.println("\n");


    }

}