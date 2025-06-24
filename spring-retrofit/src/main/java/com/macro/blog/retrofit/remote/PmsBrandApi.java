package com.macro.blog.retrofit.remote;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.Intercept;

import com.macro.blog.retrofit.common.api.CommonPage;
import com.macro.blog.retrofit.common.api.CommonResult;
import com.macro.blog.retrofit.component.TokenInterceptor;
import com.macro.blog.retrofit.domain.PmsBrand;
import retrofit2.http.*;

/**
 * @auther macrozheng
 * @description 定义Http接口，用于调用远程的PmsBrand服务
 * @date 2025/6/24
 * @github https://github.com/macrozheng
 */
@RetrofitClient(baseUrl = "${remote.baseUrl}")
@Intercept(handler = TokenInterceptor.class, include = "/brand/**")
public interface PmsBrandApi {
    @GET("brand/list")
    CommonResult<CommonPage<PmsBrand>> list(@Query("pageNum") Integer pageNum, @Query("pageSize") Integer pageSize);

    @GET("brand/{id}")
    CommonResult<PmsBrand> detail(@Path("id") Long id);

    @POST("brand/create")
    CommonResult create(@Body PmsBrand pmsBrand);

    @POST("brand/update/{id}")
    CommonResult update(@Path("id") Long id, @Body PmsBrand pmsBrand);

    @GET("brand/delete/{id}")
    CommonResult delete(@Path("id") Long id);
}
