package com.hrym.app.controller;

import com.hrym.app.service.QrCodeMgrService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hrym13 on 2017/11/10.
 */
@Controller
public class QrCodeControllerTest  extends BaseTest {


    @Autowired
    private WebApplicationContext wac;
    @Mock
    private QrCodeMgrService qrCodeMgrService;

    @Before
    public void testBefore(){

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//       QrCodeController qrCodeController = new QrCodeController();
//        this.mockMvc = MockMvcBuilders.standaloneSetup(qrCodeController).build();

    }
//select
    @Test
    public void findAllQrCode() throws Exception {

        // 构造一个查询条件
//        Qrcode qrcode = new Qrcode();
//        when(qrCodeMgrService.findAllQrCode(1,10));
//        System.out.println(qrCodeMgrService);
       this.mockMvc.perform(post("/admin/findAllQrCode")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
               .param("pageNumber","1")
                .param("pageSize","10")//添加参数
        ).andExpect(status().isOk())//返回的状态是200
                .andExpect(MockMvcResultMatchers.handler().handlerType(QrCodeController.class))
                .andDo(print())         //打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println("--------返回的json = " + this);
        System.out.println(mockMvc);
//       验证
//        verify(qrCodeMgrService, times(1));
//        verify(mock).doSomething();
//        verifyNoMoreInteractions(qrCodeMgrService);
        }

    }