package com.netty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 　　* @author EinIce
 * 　　* @description: TODO 用户表
 * 　　* @date 2022/5/7
 *
 */
@Setter
@Getter
@ToString
public class User {
    private String nickname;
    private String account;
    private String password;
    private String roleId;
}
