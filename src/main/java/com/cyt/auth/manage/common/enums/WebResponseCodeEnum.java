package com.cyt.auth.manage.common.enums;

/**
 * @author CaoYangTao
 * @date 2018年01月23日 20:56
 * @desc
 */
public enum  WebResponseCodeEnum {
    /**
     * web请求相应成功
     */
    SUCCESS(200),

    /**
     * web请求相应失败
     */
    ERROR(500),

    /**
     * 业务异常
     */
    BIZ_ERROR(700);

    private final  int code;

    public int getCode() {
        return code;
    }

    WebResponseCodeEnum(int code) {
        this.code = code;
    }
}
