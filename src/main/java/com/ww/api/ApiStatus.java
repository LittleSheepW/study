package com.ww.api;

/**
 * API 请求返回的状态码
 *
 * @author: Sun
 * @create: 2019-11-13 14:11
 * @version: v1.0
 */
public enum ApiStatus {


    /**
     * 一些未知异常情况，比如，解析失败等
     */
    UNKNOWN_ERROR(-1, "操作失败"),

    /**
     * 操作成功
     */
    OK(0, "成功"),
    ;

    private int code;
    private String msg;

    ApiStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
