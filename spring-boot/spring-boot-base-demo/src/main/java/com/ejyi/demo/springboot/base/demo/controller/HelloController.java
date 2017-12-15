package com.ejyi.demo.springboot.base.demo.controller;

import com.ejyi.demo.common.error.ServiceError;
import com.ejyi.demo.common.error.Validation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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





}

