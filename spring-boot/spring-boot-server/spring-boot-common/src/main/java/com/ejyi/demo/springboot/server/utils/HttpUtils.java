/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-19 下午5:00
 * LastModified: 18-5-19 下午5:00
 */

package com.ejyi.demo.springboot.server.utils;

import com.alibaba.fastjson.JSON;
import com.ejyi.demo.springboot.server.context.HttpContext;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author tree.yu
 * @version 1.0
 * @description 描述
 * @create 2018-05-19 17:00
 */
public class HttpUtils {


    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)          // 设置读取超时时间
            .writeTimeout(3,TimeUnit.SECONDS)          // 设置写的超时时间
            .connectTimeout(1,TimeUnit.SECONDS)         // 设置连接超时时间
            .build();

    private static final String DEFAULT_ENCODE_TYPE = "UTF-8";

    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";

    /**
     * GET请求
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        return sendGet(url, null, null);
    }

    /**
     * GET请求
     * @param url
     * @param params url参数
     * @return
     */
    public static String sendGet(String url, Map<String, String> params) {
        return sendGet(url, params, null);
    }

    /**
     * GET请求，
     * @param url url
     * @param params url参数
     * @param headers 头信息
     * @return
     */
    public static String sendGet(String url, Map<String, String> params, Map<String, String> headers) {
        return http(GET, url, params, null,null, headers);
    }

    /**
     * POST请求
     * @param url url
     * @param datas body参数
     * @return
     */
    public static String sendPost(String url, Map<String, String> datas){
        return http(POST, url, null, datas, null, null);
    }

    /**
     * POST请求
     * @param url url
     * @param params url参数
     * @param datas body参数
     * @return
     */
    public static String sendPost(String url, Map<String, String> params, Map<String, String> datas){
        return http(POST, url, params, datas, null, null);
    }

    /**
     * POST请求
     * @param url url
     * @param params url参数
     * @param jsonBody body参数
     * @return
     */
    public static String sendPost(String url, Map<String, String> params, String jsonBody){
        return http(POST, url, params, null, jsonBody, null);
    }

    /**
     * POST请求
     * @param url url
     * @param params url参数
     * @param datas body参数
     * @param jsonBody body参数，json格式
     * @param headers http头信息
     * @return
     */
    public static String sendPost(String url, Map<String, String> params, Map<String, String> datas, String jsonBody, Map<String, String> headers){
        return http(POST, url, params, datas, jsonBody, headers);
    }

    /**
     * PUT请求
     * @param url url
     * @param datas body参数
     * @return
     */
    public static String sendPut(String url, Map<String, String> datas){
        return http(PUT, url, null, datas, null,  null);
    }

    /**
     * PUT请求
     * @param url url
     * @param params url参数
     * @param datas body参数
     * @return
     */
    public static String sendPut(String url, Map<String, String> params, Map<String, String> datas){
        return http(PUT, url, params, datas, null, null);
    }

    /**
     * PUT请求
     * @param url url
     * @param params url参数
     * @param jsonBody body参数
     * @return
     */
    public static String sendPut(String url, Map<String, String> params, String jsonBody){
        return http(PUT, url, params, null, jsonBody, null);
    }

    /**
     * PUT请求
     * @param url url
     * @param params url参数
     * @param datas body参数
     * @param jsonBody body参数
     * @param headers http头信息
     * @return
     */
    public static String sendPut(String url, Map<String, String> params, Map<String, String> datas, String jsonBody, Map<String, String> headers){
        return http(PUT, url, params, datas, jsonBody, headers);
    }

    /**
     * DELETE请求
     * @param url url
     * @param datas body参数
     * @return
     */
    public static String sendDelete(String url, Map<String, String> datas){
        return http(DELETE, url, null, datas, null, null);
    }

    /**
     * DELETE请求
     * @param url url
     * @param params url参数
     * @param datas body参数
     * @return
     */
    public static String sendDelete(String url, Map<String, String> params, Map<String, String> datas){
        return http(DELETE, url, params, datas, null, null);
    }

    /**
     * PUT请求
     * @param url url
     * @param params url参数
     * @param jsonBody body参数
     * @return
     */
    public static String sendDelete(String url, Map<String, String> params, String jsonBody){
        return http(DELETE, url, params, null, jsonBody, null);
    }

    /**
     * DELETE请求
     * @param url url
     * @param params url参数
     * @param datas body参数
     * @param jsonBody body参数
     * @param headers http头信息
     * @return
     */
    public static String sendDelete(String url, Map<String, String> params, Map<String, String> datas, String jsonBody, Map<String, String> headers){
        return http(DELETE, url, params, datas, jsonBody, headers);
    }

    /**
     * 模拟HTTP请求
     * @param method http模式
     * @param url url连接
     * @param params url参数
     * @param datas body数据
     * @param jsonBody body数据，json，与datas存在一个即可
     * @param headers http头信息
     * @return
     */
    private static String http(String method, String url, Map<String, String> params, Map<String, String> datas, String jsonBody, Map<String, String> headers) {
        logger.info("[traceId:{}] method:{} url:{} params:{} datas:{} jsonBody:{} headers:{}", HttpContext.getTraceId(),method, url,
                null == params ? "" : JSON.toJSONString(params), null == datas ? "" : JSON.toJSONString(datas) ,
                null == jsonBody ? "" : jsonBody, null == headers ? "" : JSON.toJSONString(headers));

        Request.Builder requestBuilder = null;
        //构造url和url参数
        if(null != params && params.size() > 0){
            HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(url).newBuilder();
            for(String str : params.keySet()){
                httpUrlBuilder.addEncodedQueryParameter(str, params.get(str));
            }
            requestBuilder = new Request.Builder().url(httpUrlBuilder.build());
        }else{
            requestBuilder = new Request.Builder().url(url);
        }

        //构造body参数
        FormBody.Builder formBody = new FormBody.Builder();
        if(null != datas && datas.size() > 0){
            for(String key : datas.keySet()){
                formBody.add(key, datas.get(key));
            }
        }
        RequestBody body = formBody.build();
        if(!StringUtils.isEmpty(jsonBody)){
            body = RequestBody.create(JSON_MEDIA_TYPE, jsonBody);
        }

        //区别不同的httpmethod
        if(POST.equals(method)){
            requestBuilder.post(body);
        }else if(PUT.equals(method)){
            requestBuilder.put(body);
        }else if(DELETE.equals(method)){
            requestBuilder.delete(body);
        }

        //添加头信息
        if(null != headers && headers.size() > 0){
            for(String key : headers.keySet()){
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        //http请求
        try {
            Long start = System.currentTimeMillis();
            Response response = httpClient.newCall(requestBuilder.build()).execute();
            Long time = System.currentTimeMillis() - start;
            if (response.isSuccessful()) {
                String result = response.body().string();
                logger.info("[traceId:{}] url:{} time:{}ms statuscode:{} response_result:{}",
                        HttpContext.getTraceId(), url, time.toString(), response.code(), result);
                return result;
            } else {
                logger.error("[traceId:{}] url:{} time:{}ms statuscode:{}" ,
                        HttpContext.getTraceId(), url, time.toString(), response.code());
                return null;
            }
        } catch (IOException e) {
            logger.error(String.format("[traceId:%s] url:%s error:%s", HttpContext.getTraceId(), url, e), e);
            return null;
        }
    }


}

