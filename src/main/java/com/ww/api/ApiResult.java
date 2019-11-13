package com.ww.api;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 接口返回的结果
 *
 * @author: Sun
 * @create: 2019-11-13 14:11
 * @version: v1.0
 */
@Slf4j
@Data
public class ApiResult<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;
    /**
     * 错误消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;


    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> resultInfo = new ApiResult<>();
        resultInfo.setCode(ApiStatus.OK.getCode());
        resultInfo.setMessage(ApiStatus.OK.getMsg());
        resultInfo.setData(data);
        log.info("[Api] [Result] [{}]", resultInfo.toString());
        return resultInfo;
    }

    public static <T> ApiResult<T> success() {
        ApiResult<T> resultInfo = new ApiResult<>();
        resultInfo.setCode(ApiStatus.OK.getCode());
        resultInfo.setMessage(ApiStatus.OK.getMsg());
        resultInfo.setData(null);
        log.info("[Api] [Result] [{}]", resultInfo.toString());
        return resultInfo;
    }

    public static <T> ApiResult<T> fail(int code, String msg) {
        ApiResult<T> resultInfo = new ApiResult<>();
        resultInfo.setCode(code);
        resultInfo.setMessage(msg);
        log.info("[Api] [Result] [{}]", resultInfo.toString());
        return resultInfo;
    }

    public static <T> ApiResult<T> fail(ApiStatus apiStatus) {
        ApiResult<T> resultInfo = new ApiResult<>();
        resultInfo.setCode(apiStatus.getCode());
        resultInfo.setMessage(apiStatus.getMsg());
        log.info("[Api] [Result] [{}]", resultInfo.toString());
        return resultInfo;
    }

}
