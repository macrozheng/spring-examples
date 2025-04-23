package com.macro.blog.meilisearch.config;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther macrozheng
 * @description Meilisearch配置类
 * @date 2025/4/18
 * @github https://github.com/macrozheng
 */
@Configuration
public class MeilisearchConfig {

    @Value("${meilisearch.host}")
    private String MEILISEARCH_HOST;

    @Bean
    public Client searchClient(){
        return new Client(new Config(MEILISEARCH_HOST,null));
    }
}
