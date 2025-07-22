package com.macro.blog.devtools.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther macrozheng
 * @description SpringDoc API文档相关配置
 * @date 2025/7/22
 * @github https://github.com/macrozheng
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI mallTinyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Meilisearch API")
                        .description("Meilisearch搜索功能演示")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/macrozheng/mall-learning")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot实战电商项目mall（60K+Star）全套文档")
                        .url("https://www.macrozheng.com"));
    }

}

