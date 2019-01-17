package com.ejyi.demo.springboot.server.web;

import com.ejyi.demo.springboot.server.model.DemoModel;
import com.ejyi.demo.springboot.server.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-11 6:03 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DemoControllerTestByMockMvc2 {

    @Autowired
    private MockMvc mockMvc;

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
    public void testAdd() throws Exception {
        DemoModel demoModel = new DemoModel(12L, "code333", 34.1D, (byte) 1, new Date(), new Date());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/demo/v1/demo")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtils.toJson(demoModel)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();
    }
}