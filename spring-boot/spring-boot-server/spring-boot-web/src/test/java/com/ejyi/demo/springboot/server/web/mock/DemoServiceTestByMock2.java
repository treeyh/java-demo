package com.ejyi.demo.springboot.server.web.mock;

import com.ejyi.demo.springboot.server.model.IpModel;
import com.ejyi.demo.springboot.server.model.enums.ResultEnum;
import com.ejyi.demo.springboot.server.model.result.CallResult;
import com.ejyi.demo.springboot.server.service.DemoService;
import com.ejyi.demo.springboot.server.utils.JsonUtils;
import com.ejyi.demo.springboot.server.web.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-11 5:55 PM
 */
@AutoConfigureMockMvc
public class DemoServiceTestByMock2 extends BaseTest {

    @SpyBean
    private DemoService demoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testQuery() throws Exception {

        IpModel ipModel = new IpModel();
        ipModel.setCity("testCity");
        ipModel.setCountry("testCountry");
        BDDMockito.given(this.demoService.queryByIp("8.8.8.8")).willReturn(CallResult.makeCallResult(true, ResultEnum.SUCCESS, ipModel, null));

        CallResult<IpModel> ipModelCallResult = this.demoService.queryByIp("8.8.8.8");

        System.out.println(JsonUtils.toJson(ipModelCallResult));

        Assert.assertTrue(ipModelCallResult.isSuccess());
        Assert.assertTrue(ipModelCallResult.getBusinessResult().getCity().equals("testCity"));

        ipModelCallResult = this.demoService.queryByIp("8.8.8.7");

        System.out.println(JsonUtils.toJson(ipModelCallResult));

        Assert.assertTrue(ipModelCallResult.isSuccess());

//        Assert.assertTrue(ipModelCallResult.getBusinessResult().getCity().equals("testCity"));
//        Assertions.assertThat(ipModelCallResult).is
    }



    @Test
    public void testQuery2() throws Exception {

        IpModel ipModel = new IpModel();
        ipModel.setCity("testCity");
        ipModel.setCountry("testCountry");
        BDDMockito.given(this.demoService.queryByIp("8.8.8.8")).willReturn(CallResult.makeCallResult(true, ResultEnum.SUCCESS, ipModel, null));


        this.mockMvc.perform(MockMvcRequestBuilders.get("/demo/v1/ip?ip=8.8.8.8").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.country").value("testCountry"))
                .andReturn().getResponse().getContentAsString();

//        Assert.assertTrue(ipModelCallResult.getBusinessResult().getCity().equals("testCity"));
//        Assertions.assertThat(ipModelCallResult).is
    }

}
