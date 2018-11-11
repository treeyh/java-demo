package com.ejyi.demo.springboot.server.web.filter;

import com.ejyi.demo.springboot.server.constants.AppConstants;
import com.ejyi.demo.springboot.server.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 余海
 * @version 1.0
 * @description 心跳接口支持
 * @create 2018-10-17 下午6:18
 */
@Order(2)
@Component
public class RequestURIFilter extends BaseFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestURIFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String fromIP = IpUtils.getRequestIpAddress(httpServletRequest);
        //获取相对URI
        String requestURI = httpServletRequest.getRequestURI();


        if("/".equals(requestURI) || "/checkHealth".equals(requestURI)){
            //心跳检测,直接返回
            if(AppConstants.SERVICE_RUN_STATUS != 1){
                //判断应用当前是否为不可用状态，如果是那么返回心跳 406 消息，从便于负载均衡摘除
                this.responseEnd(httpServletResponse, AppConstants.SERVICE_NOT_ACTIVE_HTTP_STATUS, "406");
                return;
            }

            this.responseEnd(httpServletResponse, "OK");
            return;
        }

        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
