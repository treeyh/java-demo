package com.ejyi.demo.springboot.base.demo.filter;

import com.ejyi.demo.common.helper.DateHelper;
import com.ejyi.demo.common.http.HttpContext;
import com.ejyi.demo.common.http.RequestWrapper;
import com.ejyi.demo.common.http.ResponseWrapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * request和response日志过滤器
 * @author yuhai18
 * @date 2017-09-11 15:29
 */
@Order(0)
@Component
public class LoggingFilter extends OncePerRequestFilter {

    protected static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    protected static final Logger rrlogger = LoggerFactory.getLogger("R.R.LOG");

    private static final String REQUEST_PREFIX = "Request|";
    private static final String RESPONSE_PREFIX = "Response|";

    private ThreadLocal<Date> startTimeThread = new ThreadLocal<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        // 记录请求开始时间
        startTimeThread.set(new Date(System.currentTimeMillis()));

        request = new RequestWrapper(request);
        response = new ResponseWrapper(response);

        HttpContext.start(request, response);

        try {
            filterChain.doFilter(request, response);
        } finally {
            rrLogger(request, (ResponseWrapper)response);
            //logRequest(request);
            //logResponse((ResponseWrapper)response);
        }

    }

    /**
     * 记录request和response日志
     * @param request
     * @param response
     */
    protected void rrLogger(final HttpServletRequest request, final ResponseWrapper response){
        StringBuilder msg = new StringBuilder();

        //构造request日志
        msg.append(REQUEST_PREFIX);
        msg.append("_traceId=").append(HttpContext.getTraceId()).append("|");
        msg.append("time=").append(DateHelper.getDateTimeByDate(startTimeThread.get())).append("|");
        if (StringUtils.isNotEmpty(request.getMethod())) {
            msg.append("method=").append(request.getMethod()).append("|");
        }else {
            msg.append("method=").append(HttpContext.getMethod()).append("|");
        }
        msg.append("ip=").append(HttpContext.getIp()).append("|");
        if (request.getContentType() != null) {
            msg.append("content_type=").append(request.getContentType()).append("|");
        }
        msg.append("uri=").append(request.getRequestURI());
        if (request.getQueryString() != null) {
            msg.append('?').append(request.getQueryString());
        }

        if (request instanceof RequestWrapper && !isMultipart(request) && !isBinaryContent(request)) {
            RequestWrapper requestWrapper = (RequestWrapper) request;
            try {
                String charEncoding = requestWrapper.getCharacterEncoding() != null ?
                        requestWrapper.getCharacterEncoding() : "UTF-8";
                msg.append("|body=").append(new String(requestWrapper.toByteArray(), charEncoding));
            } catch (UnsupportedEncodingException e) {
                logger.error("Failed to parse request payload", e);
            }

        }

        //构造response日志
        msg.append(System.lineSeparator());
        msg.append(RESPONSE_PREFIX);
        msg.append("_traceId=").append(HttpContext.getTraceId()).append("|");
        msg.append("time=").append(DateHelper.getDateTimeByDate(new Date(System.currentTimeMillis()))).append("|");
        msg.append("status=").append(HttpContext.getResponse().getStatus());
        try {
            msg.append("|body=").append(new String(response.toByteArray(), response.getCharacterEncoding()));
        } catch (UnsupportedEncodingException e) {
            logger.error("Failed to parse response payload", e);
        }


        rrlogger.info(msg.toString());
    }


    /**
     * 判断是否是二进制内容
     * @param request
     * @return
     */
    private boolean isBinaryContent(final HttpServletRequest request) {
        if (request.getContentType() == null) {
            return false;
        }
        return request.getContentType().startsWith("image") || request.getContentType().startsWith("video")
                || request.getContentType().startsWith("audio");
    }


    private boolean isMultipart(final HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
    }

}
