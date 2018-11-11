/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: 返回状态枚举
 * Version: 1.0
 * Date: 18-5-8 下午6:17
 * LastModified: 18-5-8 下午6:17
 */

package com.ejyi.demo.springboot.server.model.enums;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public enum ResultEnum {
    SUCCESS(200, "SUCCESS", "成功"),
    TOKEN_ERROR(401, "TOKEN_ERROR", "token错误"),
    PATH_NOT_FOUND(404, "PATH_NOT_FOUND", "请求地址不存在"),

    PARAM_ERROR(111222001, "PARAM_ERROR", "参数异常"),
    PARAM_ERROR_NAME(111222002, "PARAM_ERROR_NAME", "%s 参数异常"),
    PARAM_RANGE_ERROR(111222003, "PARAM_RANGE_ERROR", "%s 参数允许输入范围为%s-%s"),
    PARAM_ERROR_NAME_MSG(111222004, "PARAM_ERROR_NAME_MSG", "%s 参数错误应为%s"),
    ACTIVEINFO_NOT_EXIST(111222011, "ACTIVEINFO_NOT_EXIST", "ActiveInfo对象不存在"),
    ACTIVEINFO_EXIST(111222012, "ACTIVEINFO_EXIST", "ActiveInfo对象编号已存在"),

    IPINFO_NOT_EXIST(111222004, "IPINFO_NOT_EXIST", "ip信息不存在"),

    SYS_ERROR(111222998, "SYS_ERROR", "系统异常"),
    FAILURE(111222999, "FAILURE", "业务失败");


    private int code;
    private String msg;
    private String desc;

    ResultEnum(int code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public static ResultEnum valueOf(int code) {
        ResultEnum[] enums = values();
        if (enums == null || enums.length == 0) {
            return FAILURE;
        }
        for (ResultEnum _enu : enums) {
            if (code == _enu.getCode()) {
                return _enu;
            }
        }

        return FAILURE;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return this.msg;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        try {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("code", code)
                    .append("msg", msg)
                    .append("desc", desc)
                    .toString();
        } catch (Exception e) {
            // NOTICE: 这样做的目的是避免由于toString()的异常导致系统异常终止
            // 大部分情况下，toString()用在日志输出等调试场景
            return super.toString();
        }
    }


}
