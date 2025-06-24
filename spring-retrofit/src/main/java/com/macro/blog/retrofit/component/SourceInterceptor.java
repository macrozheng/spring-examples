package com.macro.blog.retrofit.component;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.GlobalInterceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @auther macrozheng
 * @description 全局拦截器，给请求添加source头
 * @date 2025/6/24
 * @github https://github.com/macrozheng
 */
@Component
public class SourceInterceptor implements GlobalInterceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newReq = request.newBuilder()
                .addHeader("source", "retrofit")
                .build();
        return chain.proceed(newReq);
    }
}
