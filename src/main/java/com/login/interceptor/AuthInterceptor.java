package com.login.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.entity.User;
import com.login.exception.AuthException;
import com.login.exception.BizException;
import com.login.service.UserService;
import com.login.utils.Constants;
import com.login.utils.CtxUtils;
import com.login.utils.ResEnv;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // 获取ticket
            String ticket = CtxUtils.getTicket(request);

            if (StringUtils.isBlank(ticket)) {
                throw new AuthException("用户登录凭证为空");
            }

            User user = userService.getByTicket(ticket);
            if (user == null) {
                throw new AuthException("用户未登录或已过期");
            }
        } catch (BizException | AuthException e) {
            returnJson(request, response, new ResEnv<>(Constants.RES_STATUS_UN_AUTH, e.getMessage()));
        } catch (Exception e) {
            logger.error("验证用户登录时出错", e);
            returnJson(request, response, new ResEnv<>(Constants.RES_STATUS_UN_AUTH, "验证用户登录时出错"));
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * 将错误信息返回给调用者
     *
     * @param request
     * @param response
     * @param obj
     * @throws IOException
     */
    private void returnJson(HttpServletRequest request, HttpServletResponse response, Object obj) throws IOException {
        if (obj == null) {
            return;
        }
        response.setContentType("application/json; charset=UTF-8");
        String json = objectMapper.writeValueAsString(obj);
        String callback = request.getParameter("callback");
        if (StringUtils.isNotBlank(callback)) {
            json = String.format("%s(%s)", callback, json);
        }
        response.getWriter().write(json);
    }

}
