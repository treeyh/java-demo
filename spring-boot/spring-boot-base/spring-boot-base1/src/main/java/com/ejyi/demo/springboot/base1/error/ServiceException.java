package com.ejyi.demo.springboot.base1.error;

public class ServiceException extends RuntimeException {
    private int code = -1;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(ServiceError error, Object... args) {
        super(String.format(error.msg(), args));
        this.code = error.code();
    }

    public ServiceException(Validation validation) {
        super(validation.getErrorMsg());
        this.code = validation.getErrorCode();
    }

    public int getCode() {
        return this.code;
    }
}
