package com.ejyi.demo.springboot.server.web.unittest;

import com.ejyi.demo.springboot.server.model.DemoModel;
import com.ejyi.demo.springboot.server.model.enums.ResultEnum;
import com.ejyi.demo.springboot.server.model.result.CallResult;
import com.ejyi.demo.springboot.server.service.DemoService;
import com.ejyi.demo.springboot.server.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

/**
 * @author tree
 * @version 1.0
 * @description 描述
 * @create 2019-01-17 8:26 PM
 */
public class DemoControllerTestByMockService extends BaseTest {


    @SpyBean
    private DemoService demoService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void test01Query() throws Exception {

        DemoModel demoModel = new DemoModel();
        demoModel.setId(100L);
        demoModel.setCode("100");
        demoModel.setUpdateTime(new Date());
        demoModel.setStatus((byte)1);
        demoModel.setScore(1.1D);
        demoModel.setCreateTime(new Date());
        BDDMockito.given(this.demoService.queryById(demoModel.getId())).willReturn(CallResult.makeCallResult(true, ResultEnum.SUCCESS, demoModel, null));


        CallResult<DemoModel> demoModelCallResult = this.demoService.queryById(100L);

        System.out.println(JsonUtils.toJson(demoModelCallResult));

        Assert.assertTrue(demoModelCallResult.isSuccess());
        Assert.assertTrue(demoModelCallResult.getBusinessResult().getCode().equals("100"));

        demoModelCallResult = this.demoService.queryById(1L);

        System.out.println(JsonUtils.toJson(demoModelCallResult));
        Assert.assertTrue(demoModelCallResult.isSuccess());
    }


    @Test
    public void test02Query() throws Exception {

        DemoModel demoModel = new DemoModel();
        demoModel.setId(100L);
        demoModel.setCode("100");
        demoModel.setUpdateTime(new Date());
        demoModel.setStatus((byte)1);
        demoModel.setScore(1.1D);
        demoModel.setCreateTime(new Date());
        BDDMockito.given(this.demoService.queryById(demoModel.getId())).willReturn(CallResult.makeCallResult(true, ResultEnum.SUCCESS, demoModel, null));


        this.mockMvc.perform(MockMvcRequestBuilders.get("/demo/v1/demo/100").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.code").value("100"))
                .andReturn().getResponse().getContentAsString();


        this.mockMvc.perform(MockMvcRequestBuilders.get("/demo/v1/demo/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andReturn().getResponse().getContentAsString();
    }

}
