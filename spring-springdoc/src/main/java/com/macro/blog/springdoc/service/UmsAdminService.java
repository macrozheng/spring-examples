package com.macro.blog.springdoc.service;


import com.macro.blog.springdoc.domain.AdminUserDetails;

/**
 * @auther macrozheng
 * @description 后台用于管理Service
 * @date 2025/8/12
 * @github https://github.com/macrozheng
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取用户信息
     */
    AdminUserDetails getAdminByUsername(String username);

    /**
     * 用户名密码登录
     */
    String login(String username, String password);
}
