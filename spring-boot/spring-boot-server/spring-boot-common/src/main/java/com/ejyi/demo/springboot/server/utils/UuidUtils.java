/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: UUID工具类
 * Version: 1.0
 * Date: 18-5-8 下午7:58
 * LastModified: 18-5-8 下午7:58
 */

package com.ejyi.demo.springboot.server.utils;

import java.util.UUID;

public class UuidUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getNewId(){

        String newId = System.currentTimeMillis() +
                (Long.valueOf(Math.abs(UUID.randomUUID().getMostSignificantBits())).toString().substring(0, 6));
//        return Long.parseLong(newId);

        return newId;
    }

    public static Long getNewIdByLong(){

        String newId = System.currentTimeMillis() +
                (Long.valueOf(Math.abs(UUID.randomUUID().getMostSignificantBits())).toString().substring(0, 6));
        return Long.parseLong(newId);
    }
}
