package com.ejyi.demo.springboot.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class WebEurekaApplication {

    public static void main( String[] args )
    {
        SpringApplication.run(WebEurekaApplication.class, args);
    }

}