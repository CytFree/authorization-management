package com.cyt.auth.manage.common.exception;

/**
 * 自定义业务异常
 *
 * @author CaoYangTao
 * @date 2018/1/22  17:52
 */
public class BizServiceException extends RuntimeException {
    private String errorCode;

    public BizServiceException(String message) {
        super(message);
    }

    public BizServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BizServiceException(Throwable cause) {
        super(cause);
    }

    public BizServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizServiceException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
