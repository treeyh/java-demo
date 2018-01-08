package com.ejyi.demo.springboot.base.demo.error;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validation {

    private List<ServiceErrorMessage> errorList = new ArrayList<>();

    public Validation() {

    }

    public Validation(ServiceError error, Object... msgs) {
        errorList.add(new ServiceErrorMessage(error, msgs));
    }

    public Validation addError(ServiceError error, Object... msgs) {
        errorList.add(new ServiceErrorMessage(error, msgs));
        return this;
    }

    public Validation addError(Boolean isAddToErrorList, ServiceError error, Object... msgs) {
        if (isAddToErrorList)
            errorList.add(new ServiceErrorMessage(error, msgs));
        return this;
    }

    public Validation addError(ServiceError error) {
        errorList.add(new ServiceErrorMessage(error));
        return this;
    }

    public Validation addError(Validation v) {
        for (ServiceErrorMessage em : v.errorList) {
            errorList.add(em);
        }
        return this;
    }

    public boolean isValid() {
        return errorList.size() == 0;
    }

    public void isValidThrowException() {
        if(!this.isValid())
            throw new ServiceException(this);
    }

    public String getErrorMsg() {
        if(null == errorList || 0 >= errorList.size())
            return "";

        StringBuilder builder = new StringBuilder();
        for (ServiceErrorMessage error : errorList) {
            builder.append(error.toString() + ",");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public Integer getErrorCode() {
        if(null == errorList || 0 >= errorList.size())
            return -1;

        return errorList.get(0).getError().code();
    }

    public static boolean isInteger(String str) {
        return str != null && Pattern.matches("^\\d+$", str);
    }

    public static boolean isNumber(String str) {
        return str != null && Pattern.matches("^\\d+(.\\d+)?$", str);
    }
}