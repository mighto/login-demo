package com.login.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CtxUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getTicket() {
        return getTicket(getRequest());
    }

    public static String getTicket(HttpServletRequest request) {
        String key = Constants.TICKET;
        // 从请求中获取ticket
        String ticket = request.getParameter(key);
        if (StringUtils.isNotBlank(ticket)) {
            return ticket;
        }
        // 从cookie中获取ticket
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), key)) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (StringUtils.isNotBlank(ticket)) {
            return ticket;
        }
        // 从session中获取ticket
        ticket = (String) request.getSession().getAttribute(key);
        if (StringUtils.isNotBlank(ticket)) {
            return ticket;
        } else {
            return null;
        }
    }

}
