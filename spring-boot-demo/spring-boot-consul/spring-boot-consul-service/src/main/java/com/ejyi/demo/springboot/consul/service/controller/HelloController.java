package com.ejyi.demo.springboot.consul.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/service")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);


    @RequestMapping(value="/hello", method= RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String hello(Integer age){
        logger.info("hello into.");
        return "hello "+age;
    }

    @RequestMapping("/hi")
    public String home() {
        return "hello ConsulServer ";
    }


    @Autowired
    private DiscoveryClient client;

    @Value("${server.port}")
    private Integer port;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b){
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a+b;
        String s = "/add, host:"+instance.getHost() + ", service_id:" + instance.getServiceId() + "; result : "+r+"; port:" + port;
        logger.info(s);
        return s;
    }

    @RequestMapping(value = "/del/{serviceName}", method = RequestMethod.GET)
    public List<ServiceInstance> del(@PathVariable String serviceName){

        List<ServiceInstance> ls = client.getInstances(serviceName);

        return ls;
    }
}

