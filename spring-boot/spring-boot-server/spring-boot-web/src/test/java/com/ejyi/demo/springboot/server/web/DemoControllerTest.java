/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-30 下午6:29
 * LastModified: 18-5-30 下午6:29
 */

package com.ejyi.demo.springboot.server.web;


import com.ejyi.demo.springboot.server.model.DemoModel;
import com.ejyi.demo.springboot.server.model.result.ReturnResult;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


/**
 * @author tree.yu
 * @version 1.0
 * @description 单元测试，需要启动web容器进行测试
 * @create 2018-05-30 18:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerTest {


    private final static Logger logger = LoggerFactory.getLogger(DemoControllerTest.class);


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testQuery1() throws Exception {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/demo/v1/demo/1", String.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).contains("\"code\":200").contains("\"id\":1");

    }

    @Test
    public void testQuery2() throws Exception {

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/demo/v1/demo/2", String.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //故意设置异常
        Assertions.assertThat(responseEntity.getBody()).contains("\"code\":200").contains("\"id\":3");

    }

    @Test
    public void testAdd() throws Exception {

        DemoModel demoModel = new DemoModel(12L, "code222", 34.1D, (byte) 1, new Date(), new Date());

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/demo/v1/demo", demoModel, String.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).contains("\"code\":200");

    }

}
