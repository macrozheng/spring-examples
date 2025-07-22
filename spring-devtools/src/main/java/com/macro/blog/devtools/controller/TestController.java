package com.macro.blog.devtools.controller;

import com.macro.blog.devtools.common.api.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther macrozheng
 * @description SpringBoot Dev Tools测试
 * @date 2025/7/22
 * @github https://github.com/macrozheng
 */
@Tag(name = "TestController", description = "SpringBoot Dev Tools测试")
@Controller
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "测试修改")
    @RequestMapping(value = "/first", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult first() {
        String message = "返回消息（远程调试666）";
        return CommonResult.success(null,message);
    }
}
