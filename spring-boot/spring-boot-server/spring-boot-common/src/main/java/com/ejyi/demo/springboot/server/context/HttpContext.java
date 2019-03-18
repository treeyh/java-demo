/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: http上下文环境
 * Version: 1.0
 * Date: 18-5-8 下午7:57
 * LastModified: 18-5-8 下午7:57
 */

package com.ejyi.demo.springboot.server.context;

import com.ejyi.demo.springboot.server.utils.IpUtils;
import com.ejyi.demo.springboot.server.utils.UuidUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class HttpContext {

    private static InheritableThreadLocal<HttpContextInfo> httpContextInfoThreadLocal = new InheritableThreadLocal<>();

    public static final String TRACE_ID_KEY = "_traceId";

    /**
     * 开始接受请求
     * @param request
     * @param response
     */
    public static void start(HttpServletRequest request, HttpServletResponse response){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();

        if(null == httpContextInfo){
            httpContextInfoThreadLocal.set(new HttpContextInfo(request, response));
        }else{
            httpContextInfoThreadLocal.get().reset(request, response);
        }
    }

    /**
     * 返回请求的request对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return null;
        }
        return httpContextInfo.getRequest();
    }

    /**
     * 返回请求的response对象
     * @return
     */
    public static HttpServletResponse getResponse(){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return null;
        }
        return httpContextInfo.getResponse();
    }

    public static String getIp(){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return null;
        }
        return httpContextInfo.getIp();
    }

    /**
     * 保存上下文对象
     * @param key
     * @param obj
     */
    public static void put(String key, Object obj){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return;
        }
        httpContextInfo.getData().put(key, obj);
    }

    /**
     * 获取上下文对象
     * @param key
     * @return
     */
    public static Object get(String key){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return null;
        }
        return httpContextInfo.getData().getOrDefault(key, null);
    }

    /**
     * 获得请求开始时间
     * @return
     */
    public static Long getStartTime(){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return null;
        }
        return httpContextInfo.getStartTime();

    }

    /**
     * 获得请求结束时间
     * @return
     */
    public static Long getEndTime(){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return null;
        }
        return  httpContextInfo.getEndTime();
    }


    public static String getTraceId(){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return null;
        }
        return  httpContextInfo.getTraceId();
    }

    /**
     * 设置请求结束
     * @return 请求时长,毫秒
     */
    public static Long finish(){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return 0L;
        }
        httpContextInfo.setEndTime(System.currentTimeMillis());
        return httpContextInfo.getEndTime() - httpContextInfo.getStartTime();
    }

    /**
     * 判断请求是否结束
     * @return
     */
    public static Boolean isFinish(){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null != httpContextInfo){
            return httpContextInfo.getEndTime() > 0;
        }
        return false;
    }

    private static class HttpContextInfo{

        private HttpServletRequest request;

        private HttpServletResponse response;

        private Long startTime;

        private Long endTime;

        private String traceId;

        private String ip;

        private Map<String, Object> data;

        public HttpContextInfo(HttpServletRequest request, HttpServletResponse response){

            this.data = new HashMap<>();
            reset(request, response);
        }

        public void reset(HttpServletRequest request, HttpServletResponse response){
            String trace = null;
            if(null != request) {
                trace = request.getHeader(HttpContext.TRACE_ID_KEY);
                this.ip = IpUtils.getRequestIpAddress(request);
            }else{
                this.ip = "";
            }

            if (StringUtils.isEmpty(trace)) {
                trace = IpUtils.getServerIpAddress() + "_" + UuidUtils.getUUID();
            }

            if(null !=response) {
                response.setHeader(HttpContext.TRACE_ID_KEY, trace);
            }
            MDC.put("sysTraceId", trace);

            this.request = request;
            this.response = response;
            this.startTime = System.currentTimeMillis();
            this.endTime = 0L;
            this.traceId = trace;
            this.data.clear();
        }

        public HttpServletRequest getRequest() {
            return request;
        }

        public HttpServletResponse getResponse() {
            return response;
        }

        public Long getStartTime() {
            return startTime;
        }

        public Long getEndTime() {
            return endTime;
        }

        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }

        public String getTraceId() {
            return traceId;
        }

        public String getIp() {
            return ip;
        }

        public Map<String, Object> getData() {
            return data;
        }
    }
}

