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
}
