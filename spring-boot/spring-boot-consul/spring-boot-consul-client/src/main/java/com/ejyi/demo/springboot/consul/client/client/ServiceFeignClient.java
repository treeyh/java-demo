package com.ejyi.demo.springboot.consul.client.client;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "spring-boot-consul-service",
        value = "spring-boot-consul-service")
public interface ServiceFeignClient {


    @RequestMapping(method = RequestMethod.GET, value = "/service/hi")
    public String hi();

    @RequestMapping(method = RequestMethod.GET, value = "/service/add")
    public String add(@RequestParam(name = "a") Integer a, @RequestParam(name = "b") Integer b);

    @RequestMapping(method = RequestMethod.GET, value = "/get/{serviceName}")
    public List<ServiceInstance> get(@PathVariable(name = "serviceName") String serviceName);


    @RequestMapping(value = "/geturl/{serviceName}", method = RequestMethod.GET)
    public String getUrl(@PathVariable(name = "serviceName") String serviceName);
}
