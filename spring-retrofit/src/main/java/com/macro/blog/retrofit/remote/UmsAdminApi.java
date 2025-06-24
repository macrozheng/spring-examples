package com.macro.blog.retrofit.remote;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.macro.blog.retrofit.common.api.CommonResult;
import com.macro.blog.retrofit.domain.LoginParam;
import com.macro.blog.retrofit.domain.LoginResult;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @auther macrozheng
 * @description 定义Http接口，用于调用远程的UmsAdmin服务
 * @date 2025/6/24
 * @github https://github.com/macrozheng
 */
@RetrofitClient(baseUrl = "${remote.baseUrl}")
public interface UmsAdminApi {

    @POST("admin/login")
    CommonResult<LoginResult> login(@Body LoginParam loginParam);
}
