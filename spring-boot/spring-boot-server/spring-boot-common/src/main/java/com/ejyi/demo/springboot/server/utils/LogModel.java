/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: 应用日志对象
 * Version: 1.0
 * Date: 18-5-9 上午11:34
 * LastModified: 18-5-9 上午11:34
 */

package com.ejyi.demo.springboot.server.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LogModel {
    private Map<String, Object> datas = new HashMap();
    private String method;
    private long start = 0L;
    private final AtomicInteger serialId = new AtomicInteger(0);

    private LogModel(String name) {
        this.start = System.currentTimeMillis();
        this.method = name + "#" + this.start + "#";
        this.datas.put("_method", this.method);
    }

    public static LogModel newLogModel(String method) {
        return new LogModel(method);
    }

    public LogModel setResultMessage(long result, String message) {
        this.addMetaData("_result", result).addMetaData("_message", message);
        return this;
    }

    public LogModel addMetaData(String key, Object value) {
        if (value != null) {
            this.datas.put(key, value);
        } else {
            this.datas.put(key, "");
        }
        return this;
    }

    public LogModel addMetaDataError(Object value){
        return this.addMetaData("error", value);
    }

    public LogModel addMetaDataResult(Object value){
        return this.addMetaData("return", value);
    }

    public LogModel addMetaDataTraceId(Object value){
        return this.addMetaData("_traceId", value);
    }

    public LogModel delMetaData(String key) {
        if (key != null) {
            this.datas.remove(key);
        }

        return this;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap();
        Iterator var2 = this.datas.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var2.next();
            map.put(entry.getKey(), entry.getValue());
        }

        return map;
    }

    public String toJson(boolean purge) {
        try {
            this.datas.put("_serialId", this.serialId.incrementAndGet());
            if (purge) {
                this.datas.put("handleCost", System.currentTimeMillis() - this.start);
                JSONObject ja = (JSONObject)JSONObject.toJSON(this.datas);
                this.purge();
                return ja.toString();
            } else {
                Map<String, Object> map = this.toMap();
                map.put("handleCost", System.currentTimeMillis() - this.start);
                JSONObject ja = (JSONObject)JSONObject.toJSON(map);
                return ja.toString();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            return "{data:error}";
        }
    }

    private void purge() {
        this.datas.clear();
        this.datas.put("_method", this.method);
    }

    public String endJson() {
        return this.toJson(true);
    }

    public String toJson() {
        return this.toJson(true);
    }
}
