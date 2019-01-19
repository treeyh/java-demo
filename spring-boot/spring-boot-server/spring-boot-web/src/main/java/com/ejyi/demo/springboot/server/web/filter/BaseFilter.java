package com.ejyi.demo.springboot.server.web.filter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tree
 * @version 1.0
 * @description Filter 基类
 * @create 2018-10-17 下午6:20
 */
public abstract class BaseFilter implements Filter {

    protected void responseEnd(HttpServletResponse response, String msg) throws IOException {
        this.responseEnd(response, 200, msg);
    }

    protected void responseEnd(HttpServletResponse response, Integer httpStatus, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(httpStatus);
        response.getWriter().append(msg).flush();
    }
}
