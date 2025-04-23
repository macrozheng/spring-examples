package com.macro.blog.meilisearch.common.api;

/**
 * @auther macrozheng
 * @description API返回码接口
 * @date 2025/4/18
 * @github https://github.com/macrozheng
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
