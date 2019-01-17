/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-31 下午12:03
 * LastModified: 18-5-31 下午12:03
 */

package com.ejyi.demo.springboot.server.web;

import com.ejyi.demo.springboot.server.web.controller.DemoController;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @author tree.yu
 * @version 1.0
 * @description 使用MockMVC进行单元测试
 * @create 2018-05-31 12:03
 */
public class DemoControllerTestByMockMVC extends BaseTest{

    private final static Logger logger = LoggerFactory.getLogger(DemoControllerTestByMockMVC.class);


    @LocalServerPort
    private int port;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private DemoController demoController;

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void testQuery() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/demo/v1/demo/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();
    }


    @Test
    public void testQuery2() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/demo/v1/demo/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();
    }
}
