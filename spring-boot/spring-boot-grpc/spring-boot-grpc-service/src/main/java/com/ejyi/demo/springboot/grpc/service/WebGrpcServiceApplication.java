package com.ejyi.demo.springboot.grpc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class WebGrpcServiceApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(WebGrpcServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebGrpcServiceApplication.class, args);
    }

}
