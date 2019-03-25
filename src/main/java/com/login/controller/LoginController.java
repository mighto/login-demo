package com.login.controller;

import com.login.service.UserService;
import com.login.utils.CtxUtils;
import com.login.utils.ResEnv;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录
 *
 * @author mistaker
 */
@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public ResEnv<String> login(String loginName, String password) {
        return ResEnv.success(userService.login(loginName, password));
    }

    @RequestMapping("/logout")
    public ResEnv<String> logout() {
        userService.logout(CtxUtils.getTicket());
        return ResEnv.success();
    }

}
