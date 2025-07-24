package com.macro.blog.rustfs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @auther macrozheng
 * @description RustFS文件上传返回结果
 * @date 2025/7/23
 * @github https://github.com/macrozheng
 */
@Data
public class RustFSUploadResult {
    @Schema(title = "文件访问URL")
    private String url;
    @Schema(title = "文件名称")
    private String name;
}
