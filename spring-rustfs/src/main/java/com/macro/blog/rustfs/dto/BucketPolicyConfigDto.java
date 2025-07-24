package com.macro.blog.rustfs.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @auther macrozheng
 * @description RustFS Bucket访问策略配置
 * @date 2025/7/23
 * @github https://github.com/macrozheng
 */
@Data
@EqualsAndHashCode
@Builder
public class BucketPolicyConfigDto {
    private String ID;
    private String Version;
    private List<Statement> Statement;

    @Data
    @EqualsAndHashCode
    @Builder
    public static class Statement {
        private String Effect;
        private Principal Principal;
        private String[] Action;
        private String[] Resource;

    }

    @Data
    @EqualsAndHashCode
    @Builder
    public static class Principal{
        private String[] AWS;
    }
}
