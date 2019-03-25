package com.login.service;

import com.login.entity.User;
import com.login.vo.LoginUserVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public interface UserService {

    LoginUserVo getByTicket(@NotBlank String ticket);

    User getByLoginName(@NotBlank String loginName);

    LoginUserVo getInfo(@NotBlank String userUid);

    User getInfo(LoginUserVo userVo);

    String login(@NotBlank String loginName, @NotBlank String password);

    void logout(@NotBlank String ticket);

}
