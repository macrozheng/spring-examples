package com.macro.blog.retrofit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther macrozheng
 * @description 登录请求参数
 * @date 2025/6/24
 * @github https://github.com/macrozheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class LoginParam {
    private String username;
    private String password;
}
