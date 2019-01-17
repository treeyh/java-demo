package com.ejyi.demo.springboot.server.web;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-11 4:45 PM
 */
public class DemoControllerTestByWebTestClient extends BaseTest{

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testQuery2() throws Exception {
        System.out.println("running....");
        Thread.sleep(10000L);
        String body = this.restTemplate.getForObject("/", String.class);
        System.out.println(body);
        Assertions.assertThat(body).isEqualTo("OK");

    }
}
