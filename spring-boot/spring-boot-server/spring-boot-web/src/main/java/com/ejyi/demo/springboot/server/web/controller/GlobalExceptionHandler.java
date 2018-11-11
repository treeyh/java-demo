/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-8 下午8:00
 * LastModified: 18-5-8 下午8:00
 */

package com.ejyi.demo.springboot.server.web.controller;


import com.ejyi.demo.springboot.server.exception.SysErrorException;
import com.ejyi.demo.springboot.server.model.enums.ResultEnum;
import com.ejyi.demo.springboot.server.model.result.ReturnResult;
import com.ejyi.demo.springboot.server.context.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReturnResult<Object> defaultErrorHandler(final HttpServletRequest req, HttpServletResponse resp, final Exception e) throws Exception {
        /*  使用response返回    */
        resp.setStatus(HttpStatus.OK.value()); //设置状态码
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        resp.setCharacterEncoding("UTF-8"); //避免乱码
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");

        ResultEnum resultEnu = null;
        String cause = null;
        if(e instanceof SysErrorException){
            SysErrorException ne = (SysErrorException)e;
            cause = ne.getMessage();

            logger.error("_trace_id:"+ HttpContext.getTraceId()+";msg:" + ne.getMessage(), ne);
            return new ReturnResult<Object>(ne.getCode(), ne.getMessage(), null);
        }else if(e instanceof NoHandlerFoundException){
            NoHandlerFoundException ne = (NoHandlerFoundException)e;
            cause = ne.getMessage();

            logger.error("_trace_id:"+ HttpContext.getTraceId()+";Path Not Found!" + ne.getMessage(), ne);
            resultEnu = ResultEnum.PATH_NOT_FOUND;
        } else if(e instanceof BindException) {
            BindException be = (BindException)e;
            cause = be.getMessage();
            resultEnu = ResultEnum.PARAM_ERROR;
            logger.error("_trace_id:"+ HttpContext.getTraceId()+";ExceptionHandler param error"+ e.getMessage(), e);
        }else {
            logger.error("_trace_id:"+ HttpContext.getTraceId()+";ExceptionHandler sys exception"+ e.getMessage(), e);
            resultEnu = ResultEnum.SYS_ERROR;
        }
        return new ReturnResult<Object>(resultEnu.getCode(), resultEnu.getDesc(), cause);
    }

    public void writeErrorLog(final String message, final Throwable t) {
        if (logger.isErrorEnabled()) {
            logger.error(message, t);
        }
    }

    public void writeErrorLog(final String message) {
        if (logger.isErrorEnabled()) {
            logger.error(message);
        }
    }
}


