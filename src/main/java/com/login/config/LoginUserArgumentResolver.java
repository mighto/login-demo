package com.login.config;

import com.login.annotation.LoginUser;
import com.login.service.UserService;
import com.login.utils.CtxUtils;
import com.login.vo.LoginUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getGenericParameterType() == LoginUserVo.class
                && methodParameter.getParameterAnnotation(LoginUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String ticket = CtxUtils.getTicket();
        if (StringUtils.isBlank(ticket)) {
            return null;
        }
        return userService.getByTicket(ticket);
    }
}
