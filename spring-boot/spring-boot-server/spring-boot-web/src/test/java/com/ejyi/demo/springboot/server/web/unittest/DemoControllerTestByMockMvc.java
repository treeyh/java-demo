package com.ejyi.demo.springboot.server.web.unittest;

import com.ejyi.demo.springboot.server.model.DemoModel;
import com.ejyi.demo.springboot.server.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2019-01-17 8:25 PM
 */
public class DemoControllerTestByMockMvc  extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testQuery() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/demo/v1/demo/2").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testAdd() throws Exception {
        Long time = System.currentTimeMillis();
        DemoModel demoModel = new DemoModel(null, time.toString(), 34.1D, (byte) 1, new Date(), new Date());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/demo/v1/demo")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JsonUtils.toJson(demoModel)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andReturn().getResponse().getContentAsString();
    }
}
