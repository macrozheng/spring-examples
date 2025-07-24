package com.macro.blog.rustfs.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

/**
 * @auther macrozheng
 * @description RustFS配置类
 * @date 2025/7/23
 * @github https://github.com/macrozheng
 */
@Configuration
public class RustFSConfig {

    @Value("${rustfs.endpoint}")
    private String ENDPOINT;
    @Value("${rustfs.accessKey}")
    private String ACCESS_KEY;
    @Value("${rustfs.secretKey}")
    private String SECRET_KEY;

    @Bean
    public S3Client s3Client(){
        // 初始化 S3 客户端
        return S3Client.builder()
                .endpointOverride(URI.create(ENDPOINT)) // RustFS 地址
                .region(Region.US_EAST_1) // 可写死，RustFS 不校验 region
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)))
                .forcePathStyle(true) // 关键配置！RustFS 需启用 Path-Style
                .build();
    }
}
