/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: 应用常量
 * Version: 1.0
 * Date: 18-5-8 下午6:40
 * LastModified: 18-5-8 下午6:40
 */

package com.ejyi.demo.springboot.server.constants;

public class AppConstants {

    /**
     * 默认分页页码
     */
    public static final Long DEFAULT_PAGE_NUM = 1L;

    /**
     * 默认分页size
     */
    public static final Long DEFAULT_PAGE_SIZE = 10L;

    /**
     * 最大分页size
     */
    public static final Long MAX_PAGE_SIZE = 1000L;

    /**
     * 缓存key前缀
     */
    public static final String CACHE_KEY_PRE = "ejyi:demo:springboot:";

    /**
     * 缓存超时时间
     */
    public static final Long CACHE_TIME_OUT = 1 * 60L;

    /**
     * 服务运行状态：
     * 0：服务正在启动中，不可用；
     * 1：服务启动完毕，可以接收请求；
     * 2：服务准备关闭，不接收新请求；
     */
    public static volatile int SERVICE_RUN_STATUS = 0;

    /**
     * 服务不可用返回http状态码
     */
    public static final int SERVICE_NOT_ACTIVE_HTTP_STATUS = 406;

}
