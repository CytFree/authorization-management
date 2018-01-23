package com.cyt.auth.manage.common.util;

import com.cyt.auth.manage.common.enums.WebResponseCodeEnum;

import java.util.HashMap;

/**
 * Web响应信息
 *
 * @author CaoYangTao
 * @date 2018年01月23日 20:48
 * @desc
 */
public class WebResponse extends HashMap<String, Object> {
    private static final String RETURN_CODE = "code";
    private static final String RETURN_MSG = "msg";

    public WebResponse() {
        put(RETURN_CODE, WebResponseCodeEnum.SUCCESS.getCode());
    }

    public WebResponse(int code, String msg) {
        put(RETURN_CODE, code);
        put(RETURN_MSG, msg);
    }

    public static WebResponse success(){
        return new WebResponse();
    }

    public static WebResponse success(String msg){
        WebResponse response = new WebResponse();
        response.put(RETURN_MSG, msg);
        return response;
    }

    public static WebResponse error(){
        WebResponse response = new WebResponse();
        response.put(RETURN_CODE, WebResponseCodeEnum.ERROR.getCode());
        return response;
    }

    public static WebResponse error(String msg){
        WebResponse response = new WebResponse();
        response.put(RETURN_CODE, WebResponseCodeEnum.ERROR.getCode());
        response.put(RETURN_MSG, msg);
        return response;
    }

    public static WebResponse getResponse(int code, String msg) {
        return new WebResponse(code, msg);
    }

    /**
     * 重写put方法
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public WebResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
