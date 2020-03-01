package com.dky.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局异常包装类
 * Created by liwen on 2017/2/28.
 */
public class CustomException extends Exception {

    private String message;
    private Exception exception;
    protected static final Logger logger = null;
    public CustomException(String message) {
        super(message);
        this.message = message;
    }
    public CustomException(Exception e) {
        super(e);
        exception = e;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Logger getLogger() {
        return LoggerFactory.getLogger(CustomException.class);
    }
}
