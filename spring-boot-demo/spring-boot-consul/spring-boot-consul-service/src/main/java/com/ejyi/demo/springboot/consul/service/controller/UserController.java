package com.ejyi.demo.springboot.consul.service.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/user")
public class UserController {


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
