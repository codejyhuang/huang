package com.hrym.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hrym13 on 2017/12/11.
 */
public class SystemControllerTest extends BaseTest {

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void testBefore(){

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//       QrCodeController qrCodeController = new QrCodeController();
//        this.mockMvc = MockMvcBuilders.standaloneSetup(qrCodeController).build();

    }
    @Test
    public void getNodeList() throws Exception {
        this.mockMvc.perform(post("/admin/findAllQrCode")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("pageNumber","1")
        ).andExpect(status().isOk())//返回的状态是200
                .andExpect(MockMvcResultMatchers.handler().handlerType(SystemController.class))
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = " + this);
    }

    @Test
    public void findMenuPageByUserId() throws Exception {
        this.mockMvc.perform(post("/admin/findMenuPageByUserId")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userId","1001")
        ).andExpect(status().isOk())//返回的状态是200
                .andExpect(MockMvcResultMatchers.handler().handlerType(SystemController.class))
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = " + this);
    }

}