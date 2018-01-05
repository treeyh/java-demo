package com.ejyi.demo.springboot.consul.service.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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


    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b){
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a+b;
        String s = "/add, host:"+instance.getHost() + ", service_id:" + instance.getServiceId() + "; result : "+r+"; port:" + port;
        logger.info(s);
        return s;
    }

    @RequestMapping(value = "/get/{serviceName}", method = RequestMethod.GET)
    public List<ServiceInstance> get(@PathVariable String serviceName){

        List<ServiceInstance> ls = client.getInstances(serviceName);

        return ls;
    }

    @RequestMapping(value = "/geturl/{serviceName}", method = RequestMethod.GET)
    public String getUrl(@PathVariable String serviceName){

        return loadBalancer.choose("consul-server").getUri().toString();
    }

    @RequestMapping(value = "/checkUser", method = RequestMethod.GET)
    public Map<String, Object> checkUser(@RequestParam Integer val){
        Map<String,Object> map = new HashMap<>();

        if(val > 1000){
            map.put("code", 403);
            map.put("msg", "check user error");
        }else{
            map.put("code", 200);
            map.put("msg", "ok");
        }
        return map;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public Map<String, Object> getUser(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer age){
        Map<String,Object> map = new HashMap<>();

        Map<String, Object> map2 = new HashMap<>();

        map2.put("id", id);
        map2.put("name", name);
        map2.put("age", age);
        map2.put("score", RandomUtils.nextInt(1000));
        map2.put("nowTime", new Date());

        map.put("code", 200);
        map.put("msg", "ok");
        map.put("date", map2);

        return map;
    }



}

