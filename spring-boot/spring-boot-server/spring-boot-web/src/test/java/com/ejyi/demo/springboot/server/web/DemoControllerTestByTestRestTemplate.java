package com.ejyi.demo.springboot.server.web;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-12-11 6:04 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerTestByTestRestTemplate {
//
//    @Autowired
//    private WebTestClient webTestClient;

    @Autowired
    private TestRestTemplate restTemplate;

//    @Test
//    public void testQuery() throws Exception {
//        this.webTestClient.get().uri("/").exchange().expectStatus().isOk()
//                .expectBody(String.class).isEqualTo("OK");
//
//    }

    @Test
    public void testQuery2() throws Exception {
        System.out.println("running....");
        Thread.sleep(10000L);
        String body = this.restTemplate.getForObject("/", String.class);
        System.out.println(body);
        Assertions.assertThat(body).isEqualTo("OK");

    }
}
