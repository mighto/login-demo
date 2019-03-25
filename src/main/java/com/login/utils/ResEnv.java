package com.login.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResEnv<T> {

    /**
     * 状态码
     */
    private int status;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 分页信息-记录总数
     */
    private Long total;

    /**
     * 分页信息-当前页码
     */
    private int pageNum;

    /**
     * 分页信息-每页条数
     */
    private int pageSize;

    /**
     * 分页信息-总页数
     */
    private int pages;

    public ResEnv() {
    }

    public ResEnv(int status) {
        this();
        this.status = status;
    }

    public ResEnv(int status, String message) {
        this(status);
        this.message = message;
    }

    public ResEnv(int status, String message, T data) {
        this(status, message);
        this.data = data;
    }

    public static <T> ResEnv<T> success(String msg, T object) {
        return new ResEnv<>(Constants.RES_STATUS_SUCCESS, msg, object);
    }

    public static <T> ResEnv<T> success(String msg) {
        return new ResEnv<>(Constants.RES_STATUS_SUCCESS, msg, null);
    }

    public static <T> ResEnv<T> success(T object) {
        return new ResEnv<>(Constants.RES_STATUS_SUCCESS, Constants.DEF_FAIL_MSG, object);
    }

    public static <T> ResEnv<T> success() {
        return success(Constants.DEF_SUCCESS_MSG);
    }

    public static <T> ResEnv<T> fail(String msg, int status) {
        return new ResEnv<>(status, msg, null);
    }

    public static <T> ResEnv<T> fail(String msg) {
        return fail(msg, Constants.RES_STATUS_ERROR);
    }

    public static <T> ResEnv<T> fail() {
        return fail(Constants.DEF_FAIL_MSG);
    }

}
