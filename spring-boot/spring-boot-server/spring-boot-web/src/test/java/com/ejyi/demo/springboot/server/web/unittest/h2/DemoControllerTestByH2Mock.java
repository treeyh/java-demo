package com.ejyi.demo.springboot.server.web.unittest.h2;

import com.github.database.rider.core.api.dataset.DataSet;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author tree
 * @version 1.0
 * @description 描述
 * @create 2019-01-17 8:27 PM
 */
public class DemoControllerTestByH2Mock extends BaseH2MockTest {



    private final static Logger logger = LoggerFactory.getLogger(DemoControllerTestByH2Mock.class);

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void test01Query() throws Exception{

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/demo/v1/demo/1", String.class);

        System.out.println("-----------------------------------------DemoControllerTestByH2Mock.testQuery::"+responseEntity);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).contains("\"code\":200").contains("\"id\":1");
    }

    @Test
    @Sql(value = {"/db/data/init_active_info.sql"})
    public void test02Query() throws Exception{

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/demo/v1/demo/3", String.class);

        System.out.println("-----------------------------------------DemoControllerTestByH2Mock.testQuery1::"+responseEntity);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).contains("\"code\":200").contains("\"id\":3");
    }

//    @Test
//    @Sql(value = {"/db/data/init_active_info.sql"})
//    public void test03Query() throws Exception{
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/demo/v1/demo/4").contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(4))
//                .andReturn().getResponse().getContentAsString();
//    }



    @Test
    @DataSet(value = {"db/data/active_info.yml"})
    public void test04Query() throws Exception{
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/demo/v1/demo/8", String.class);

        System.out.println("-----------------------------------------DemoControllerTestByH2Mock.testQuery2::"+responseEntity);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).contains("\"code\":200").contains("\"id\":8");

    }

    @Test
    @DataSet(value = {"db/data/active_info.yml"})
    public void test05Query() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/demo/v1/demo/9").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(9))
                .andReturn().getResponse().getContentAsString();

    }
}
