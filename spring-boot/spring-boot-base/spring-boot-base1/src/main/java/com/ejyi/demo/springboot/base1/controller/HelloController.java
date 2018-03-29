package com.ejyi.demo.springboot.base1.controller;

import com.ejyi.demo.springboot.base1.error.ServiceError;
import com.ejyi.demo.springboot.base1.error.Validation;
import com.ejyi.demo.springboot.base1.po.ActiveInfo;
import com.ejyi.demo.springboot.base1.service.ActiveInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/service")
@Api(value = "hello world api")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${service.hello.name}")
    private String helloName;

    @Autowired
    private ActiveInfoService activeInfoService;

    @ApiOperation(value = "hello", notes = "hello")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "int", paramType = "query")
    })
    @RequestMapping(value="/hello", method= RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String hello(Integer age){
        logger.info("hello into.");

        Validation validation = new Validation();
        validation.addError(age <= 0, ServiceError.PARAM_ERROR, "age");
        validation.isValidThrowException();

        logger.info("hello run.");
        String str = "hello " + helloName + "; age " + age +".";
        return str;
    }



    @ApiOperation(value = "addActiveInfo", notes = "addActiveInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "编号", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "size", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = true, dataType = "int", paramType = "query")
    })
    @RequestMapping(value="/addActiveInfo", method= RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ActiveInfo addActiveInfo(String code, Integer size, Integer type){
        ActiveInfo activeInfo = activeInfoService.addActiveInfo(code, size, 1.01, type, 1);

        return activeInfo;
    }



}

