package com.macro.blog.springdoc.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.macro.blog.springdoc.common.utils.JwtTokenUtil;
import com.macro.blog.springdoc.domain.AdminUserDetails;
import com.macro.blog.springdoc.service.UmsAdminService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther macrozheng
 * @description 用户信息管理Sevice实现类
 * @date 2025/8/12
 * @github https://github.com/macrozheng
 */
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    /**
     * 存放默认用户信息
     */
    private List<AdminUserDetails> adminUserDetailsList = new ArrayList<>();
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init(){
        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .authorityList(CollUtil.toList("ROLE_ADMIN"))
                .build());
        adminUserDetailsList.add(AdminUserDetails.builder()
                .username("macro")
                .password(passwordEncoder.encode("123456"))
                .authorityList(CollUtil.toList("ROLE_USER"))
                .build());
    }
    @Override
    public AdminUserDetails getAdminByUsername(String username) {
        List<AdminUserDetails> findList = adminUserDetailsList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(findList)){
            return findList.get(0);
        }
        return null;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = getAdminByUsername(username);
            if(userDetails==null){
                return token;
            }
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
}
