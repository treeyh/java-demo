package com.ejyi.demo.springboot.shardingjdbc.controller;

import com.ejyi.demo.springboot.shardingjdbc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/parking/v1")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = {"/demo"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public String demo(String a) {

        demoService.demoUser();
        return "ok";
    }
}
