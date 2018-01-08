package com.ejyi.demo.springboot.base.demo.error;

public class ServiceErrorMessage {
    private ServiceError error;
    private Object[] msgs;

    public ServiceErrorMessage(ServiceError errorCode) {
        this.error = errorCode;
    }

    public ServiceErrorMessage(ServiceError errorCode, Object... msgs) {
        this.error = errorCode;

        this.msgs = msgs;
    }

    public ServiceError getError() {
        return error;
    }

    public Object[] getMsgs() {
        return msgs;
    }

    public String toString() {
        return String.format(error.msg(), msgs);
    }
}


