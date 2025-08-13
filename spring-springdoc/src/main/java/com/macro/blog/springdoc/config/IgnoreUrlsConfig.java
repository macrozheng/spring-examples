package com.macro.blog.springdoc.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther macrozheng
 * @description 用于配置白名单资源路径
 * @date 2018/11/5
 * @github https://github.com/macrozheng
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
@Component
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();

}
