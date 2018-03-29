package com.ejyi.demo.springboot.base1.error;

public enum ServiceError {

    SUCCESS(200, "OK"), //1000
    FAILURE(999, "操作失败"), //1001


    PARAM_ERROR(601, "%s 参数错误"),
    PARAM_ILLEGAL(602, "参数非法 %s"),

    USER_LOGIN_FAIL(1001, "用户登录失败"),;




    private final int    code;

    private final String msg;

    ServiceError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    public void throwException(Object... args) {
        throw new ServiceException(this, args);
    }

    public ServiceException returnException(Object... args) {
        return new ServiceException(this, args);
    }
}

