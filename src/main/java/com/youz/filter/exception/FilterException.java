package com.youz.filter.exception;

import java.io.Serializable;

public class FilterException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public FilterException(String msg) {super(msg); this.errorMessage = msg;}

    public FilterException(String code, String msg) {
        super(msg);
        this.errorCode = code;
        this.errorMessage = msg;
    }

    private String errorCode;

    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
