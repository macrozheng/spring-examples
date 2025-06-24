package com.macro.blog.retrofit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther macrozheng
 * @description 登录返回结果
 * @date 2025/6/24
 * @github https://github.com/macrozheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResult {
    //登录token
    private String token;
    //登录token前缀
    private String tokenHead;
}
