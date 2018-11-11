/*
 *
 *  * ProjectName: springboot-parent
 *  * Author: tree.yu
 *  * Description: 模型类转换工具类
 *  * Version: 1.0
 *  * Date: 18-5-9 上午10:25
 *  * LastModified: 18-5-9 上午10:25
 *
 */

package com.ejyi.demo.springboot.server.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Collection;
import java.util.List;

public class ClazzConverterUtils {

    public static <T1, T2> T1 converterClass(T2 srcClazz, Class<T1> dstClazz) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(srcClazz);
        return jsonObject == null ? null : JSONObject.toJavaObject(jsonObject, dstClazz);
    }

    public static <T1, T2> Collection<T1> converterClass(Collection<T2> srcClazzCollection, Class<T1> dstClazz) {
        JSONArray jsonArray = (JSONArray)JSONObject.toJSON(srcClazzCollection);
        return jsonArray == null ? null : JSONArray.parseArray(jsonArray.toJSONString(), dstClazz);
    }

    public static <T1, T2> T1[] converterClass(T2[] srcClazzArray, Class<T1> dstClazz) {
        JSONArray jsonArray = (JSONArray)JSONObject.toJSON(srcClazzArray);
        if (jsonArray == null) {
            return null;
        } else {
            List<T1> result = JSONArray.parseArray(jsonArray.toJSONString(), dstClazz);
            return result == null ? null : (T1[]) result.toArray();
        }
    }
}
