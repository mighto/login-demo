package com.login.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    private static Logger logger = LoggerFactory.getLogger(Constants.class);

    private Constants() {
    }

    /**
     * 认证票据
     */
    public static final String TICKET = "ticket";

    /**
     * 登录用户ticket和userId缓存名称
     */
    public static final String LOGIN_USER_CACHE_NAME = "loginUserCache";

    /**
     * 默认成功消息
     */
    public static final String DEF_SUCCESS_MSG = "操作成功";

    /**
     * 默认失败消息
     */
    public static final String DEF_FAIL_MSG = "操作失败";

    /**
     * 返回状态：成功
     */
    public static final int RES_STATUS_SUCCESS = 200;

    /**
     * 返回状态：参数异常
     */
    public static final int RES_STATUS_INVALID_PARAM = 400;

    /**
     * 返回代码： 未验证
     */
    public static final int RES_STATUS_UN_AUTH = 401;

    /**
     * 返回状态：不存在
     */
    public static final int RES_STATUS_NOT_FOUND = 404;

    /**
     * 返回状态：失败
     */
    public static final int RES_STATUS_ERROR = 503;

    /**
     * 返回状态：服务异常
     */
    public static final int RES_STATUS_SERVER_ERROR = 500;

}
