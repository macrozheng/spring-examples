package com.macro.blog.storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @auther macrozheng
 * @description 文件上传返回结果
 * @date 2025/7/24
 * @github https://github.com/macrozheng
 */
@Data
public class FileStorageResult {
    @Schema(title = "文件访问URL")
    private String url;
    @Schema(title = "文件名称")
    private String name;
}
