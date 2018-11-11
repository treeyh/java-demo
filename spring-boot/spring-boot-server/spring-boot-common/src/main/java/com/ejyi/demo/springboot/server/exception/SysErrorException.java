/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-8 下午6:40
 * LastModified: 18-5-8 下午6:40
 */

package com.ejyi.demo.springboot.server.exception;

import com.ejyi.demo.springboot.server.model.enums.ResultEnum;

public class SysErrorException extends RuntimeException {
    private int code = -1;

    private ResultEnum resultEnum;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SysErrorException(String message) {
        super(message);
    }

    public SysErrorException(ResultEnum error, Object... args) {
        super(String.format(error.getDesc(), args));
        this.resultEnum = error;
        this.code = error.getCode();
    }

    public SysErrorException(Validation validation) {
        super(validation.getErrorMsg());
        this.code = validation.getErrorCode();
    }

    public int getCode() {
        return this.code;
    }

    public ResultEnum getResultEnum() {
        return this.resultEnum;
    }
}
