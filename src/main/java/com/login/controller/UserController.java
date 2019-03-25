package com.login.controller;

import com.login.annotation.LoginUser;
import com.login.entity.User;
import com.login.service.UserService;
import com.login.utils.ResEnv;
import com.login.vo.LoginUserVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/getInfo")
    public ResEnv<User> getInfo(@LoginUser LoginUserVo userVo) {
        return ResEnv.success(userService.getInfo(userVo));
    }

}
