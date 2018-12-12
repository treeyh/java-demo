package com.ejyi.demo.springboot.server.web.mock;

import com.ejyi.demo.springboot.server.model.IpModel;
import com.ejyi.demo.springboot.server.model.enums.ResultEnum;
import com.ejyi.demo.springboot.server.model.result.CallResult;
import com.ejyi.demo.springboot.server.service.DemoService;
import com.ejyi.demo.springboot.server.utils.JsonUtils;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-11 6:05 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DemoControllerTestByMock2 {

    @SpyBean
    private DemoService demoService;

    @Test
    public void testQuery() throws Exception {

        IpModel ipModel = new IpModel();
        ipModel.setCity("testCity");
        ipModel.setCountry("testCountry");
        BDDMockito.given(this.demoService.queryByIp("8.8.8.8")).willReturn(CallResult.makeCallResult(true, ResultEnum.SUCCESS, ipModel, null));

        CallResult<IpModel> ipModelCallResult = this.demoService.queryByIp("8.8.8.8");

        System.out.println(JsonUtils.toJson(ipModelCallResult));


        Assertions.assertThat(ipModelCallResult).hasFieldOrPropertyWithValue("success", true);
        Assertions.assertThat(ipModelCallResult.getBusinessResult()).hasFieldOrPropertyWithValue("city",  "testCity");


        ipModelCallResult = this.demoService.queryByIp("8.8.8.7");

        System.out.println(JsonUtils.toJson(ipModelCallResult));

        Assertions.assertThat(ipModelCallResult).hasFieldOrPropertyWithValue("success", true);
        Assertions.assertThat(ipModelCallResult.getBusinessResult()).hasFieldOrPropertyWithValue("city",  "testCity");
//        Assertions.assertThat(ipModelCallResult).is

    }





}