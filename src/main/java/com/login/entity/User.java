package com.login.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户实体类
 */
@Getter
@Setter
@ToString
public class User implements Serializable {

    private String uid;

    private String loginName;

    private String password;

}
