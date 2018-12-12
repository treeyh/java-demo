package com.ejyi.demo.springboot.server.web.mock;

import com.ejyi.demo.springboot.server.model.IpModel;
import com.ejyi.demo.springboot.server.model.enums.ResultEnum;
import com.ejyi.demo.springboot.server.model.result.CallResult;
import com.ejyi.demo.springboot.server.service.DemoService;
import com.ejyi.demo.springboot.server.utils.JsonUtils;
import com.ejyi.demo.springboot.server.web.WebApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletContext;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-11 6:11 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WebApplication.class)
//@JsonTest
public class DemoControllerTestByMockJson {

    @SpyBean
    private DemoService demoService;

    private JacksonTester<CallResult<IpModel>> jacksonTester;

    @Before
    public void befor(){
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }


    @Test
    public void testQuery() throws Exception {

        IpModel ipModel = new IpModel();
        ipModel.setCity("testCity");
        ipModel.setCountry("testCountry");
        BDDMockito.given(this.demoService.queryByIp("8.8.8.8")).willReturn(CallResult.makeCallResult(true, ResultEnum.SUCCESS, ipModel, null));

        CallResult<IpModel> ipModelCallResult = this.demoService.queryByIp("8.8.8.8");

        System.out.println(JsonUtils.toJson(ipModelCallResult));


        Assertions.assertThat(this.jacksonTester.write(ipModelCallResult)).hasJsonPathBooleanValue("@.success", true);
        Assertions.assertThat(this.jacksonTester.write(ipModelCallResult)).extractingJsonPathStringValue("@.resultEnum").isEqualTo("SUCCESS");

        ipModelCallResult = this.demoService.queryByIp("8.8.8.7");

        String json = JsonUtils.toJson(ipModelCallResult);
        System.out.println(json);

        Assertions.assertThat(this.jacksonTester.write(ipModelCallResult)).hasJsonPathBooleanValue("success", true);

        Assertions.assertThat(ipModelCallResult).hasFieldOrPropertyWithValue("success", true);
        Assertions.assertThat(ipModelCallResult.getBusinessResult()).hasFieldOrPropertyWithValue("city", null);
//        Assertions.assertThat(ipModelCallResult).is

    }


}
