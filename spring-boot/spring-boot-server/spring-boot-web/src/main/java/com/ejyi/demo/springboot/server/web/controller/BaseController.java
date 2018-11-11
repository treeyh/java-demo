/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: controller基础类
 * Version: 1.0
 * Date: 18-5-8 下午8:00
 * LastModified: 18-5-8 下午8:00
 */

package com.ejyi.demo.springboot.server.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


}
