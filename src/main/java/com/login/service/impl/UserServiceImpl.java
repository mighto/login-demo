package com.login.service.impl;

import com.login.cache.CacheService;
import com.login.entity.User;
import com.login.exception.BizException;
import com.login.service.UserService;
import com.login.utils.Constants;
import com.login.utils.SysUtils;
import com.login.vo.LoginUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Value("${login.username}")
    private String username;
    @Value("${login.password}")
    private String loginPass;
    @Resource
    private CacheService cacheService;

    @Override
    public LoginUserVo getByTicket(String ticket) {
        User user = cacheService.get(Constants.LOGIN_USER_CACHE_NAME, ticket);
        LoginUserVo vo = new LoginUserVo();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    @Override
    public User getByLoginName(String loginName) {
        User user = new User();
        // todo 查询数据库获取用户信息
        if (loginName.equals(username)) {
            user.setLoginName(username);
            user.setUid(SysUtils.uid());
        }
        return user;
    }

    @Override
    public LoginUserVo getInfo(String userUid) {
        LoginUserVo vo = new LoginUserVo();
        // todo 设置权限，组织机构等属性
        return vo;
    }

    @Override
    public User getInfo(LoginUserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        return user;
    }

    @Override
    public String login(String loginName, String password) {
        User user = getByLoginName(loginName);
        if (user == null) {
            throw new BizException("登录的用户不存在");
        }
        // todo 验证密码
        if (!loginName.equals(username) || !password.equals(loginPass)) {
            throw new BizException("密码不正确");
        } else {
            user.setPassword(loginPass);
        }
        String newTicket = SysUtils.uid();
        cacheService.put(Constants.LOGIN_USER_CACHE_NAME, newTicket, user);
        String oldTicket = cacheService.get(Constants.LOGIN_USER_CACHE_NAME, user);
        if (StringUtils.isNotBlank(oldTicket)) {
            // 旧令牌失效
            cacheService.evict(Constants.LOGIN_USER_CACHE_NAME, oldTicket);
        }
        cacheService.put(Constants.LOGIN_USER_CACHE_NAME, user, newTicket);
        return newTicket;
    }

    @Override
    public void logout(String ticket) {
        User user = cacheService.get(Constants.LOGIN_USER_CACHE_NAME, ticket);
        if (user != null) {
            cacheService.evict(Constants.LOGIN_USER_CACHE_NAME, user);
        }
        cacheService.evict(Constants.LOGIN_USER_CACHE_NAME, ticket);
    }

}
