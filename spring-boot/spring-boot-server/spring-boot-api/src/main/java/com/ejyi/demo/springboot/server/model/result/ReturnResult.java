/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-8 下午6:15
 * LastModified: 18-5-8 下午6:15
 */

package com.ejyi.demo.springboot.server.model.result;


import com.ejyi.demo.springboot.server.model.enums.ResultEnum;

import java.io.Serializable;


public class ReturnResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private T data;


    public ReturnResult(int code, String msg){
        this(code, msg, null);
    }

    public ReturnResult(ResultEnum resultEnum, Object... args){

        this(resultEnum.getCode(), String.format(resultEnum.getDesc(), args), null);
    }

    public ReturnResult(ResultEnum resultEnum, T data){
        this(resultEnum.getCode(), resultEnum.getDesc(), data);
    }

    public ReturnResult(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ReturnResult success(){
        return new ReturnResult(ResultEnum.SUCCESS);
    }

    public static <T>ReturnResult<T> success(T data){
        return new ReturnResult<T>(ResultEnum.SUCCESS, data);
    }

    public static ReturnResult error(Integer code, String msg){
        return new ReturnResult(code, msg);
    }


    public static ReturnResult error(ResultEnum resultEnum, Object... args){
        return new ReturnResult(resultEnum, args);
    }
}