package com.ejyi.demo.common.http;

import com.ejyi.demo.common.helper.IpHelper;
import com.ejyi.demo.common.helper.StringHelper;
import lombok.Data;

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

    public static void setMethod(String method){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return;
        }
        httpContextInfo.setMethod(method);
    }

    public static String getMethod(){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return null;
        }
        return httpContextInfo.getMethod();
    }

    public static void setArgs(Object[] args){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return;
        }
        httpContextInfo.setArgs(args);
    }

    public static Object[] getArgs(){
        HttpContextInfo httpContextInfo = httpContextInfoThreadLocal.get();
        if(null == httpContextInfo){
            return null;
        }
        return httpContextInfo.getArgs();
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


    @Data
    private static class HttpContextInfo{

        private HttpServletRequest request;

        private HttpServletResponse response;

        private Long startTime;

        private Long endTime;

        private String method;

        private String traceId;

        private String ip;

        private Object[] args;

        private Map<String, Object> data;

        public HttpContextInfo(HttpServletRequest request, HttpServletResponse response){

            this.data = new HashMap<>();
            reset(request, response);
        }

        public void reset(HttpServletRequest request, HttpServletResponse response){
            this.ip = IpHelper.getIpAddr(request);

            String trace = request.getHeader(HttpContext.TRACE_ID_KEY);
            if(StringHelper.isEmpty(trace)){
                trace = this.ip + "_" + StringHelper.getUuid();
            }
            response.setHeader(HttpContext.TRACE_ID_KEY, trace);

            this.request = request;
            this.response = response;
            this.startTime = System.currentTimeMillis();
            this.endTime = 0L;
            this.method = StringHelper.EMPTY;
            this.args = null;
            this.traceId = trace;
            this.data.clear();
        }
    }
}
