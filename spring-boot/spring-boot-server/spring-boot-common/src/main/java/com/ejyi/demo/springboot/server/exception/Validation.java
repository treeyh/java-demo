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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validation {

    private List<ErrorMessage> errorList = new ArrayList<>();

    public static Validation newValidation(){
        return new Validation();
    }

    public Validation() {

    }

    public Validation(ResultEnum error, Object... msgs) {
        errorList.add(new ErrorMessage(error, msgs));
    }

    public Validation addError(ResultEnum error, Object... msgs) {
        errorList.add(new ErrorMessage(error, msgs));
        return this;
    }

    public Validation addError(Boolean isAddToErrorList, ResultEnum error, Object... msgs) {
        if (isAddToErrorList) {
            errorList.add(new ErrorMessage(error, msgs));
        }
        return this;
    }

    public Validation addError(ResultEnum error) {
        errorList.add(new ErrorMessage(error));
        return this;
    }

    public Validation addError(Validation v) {
        for (ErrorMessage em : v.errorList) {
            errorList.add(em);
        }
        return this;
    }

    public boolean isValid() {
        return errorList.size() == 0;
    }

    public void isValidThrowException() {
        if (!this.isValid()) {
            throw new SysErrorException(this);
        }
    }

    public String getErrorMsg() {
        if (null == errorList || 0 >= errorList.size()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (ErrorMessage error : errorList) {
            builder.append(error.toString() + ",");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public Integer getErrorCode() {
        if (null == errorList || 0 >= errorList.size()) {
            return -1;
        }

        return errorList.get(0).getError().getCode();
    }

    public static boolean isInteger(String str) {
        return str != null && Pattern.matches("^\\d+$", str);
    }

    public static boolean isNumber(String str) {
        return str != null && Pattern.matches("^\\d+(.\\d+)?$", str);
    }
}
