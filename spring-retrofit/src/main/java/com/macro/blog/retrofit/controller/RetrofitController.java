package com.macro.blog.retrofit.controller;

import com.macro.blog.retrofit.common.api.CommonPage;
import com.macro.blog.retrofit.common.api.CommonResult;
import com.macro.blog.retrofit.component.TokenHolder;
import com.macro.blog.retrofit.domain.LoginParam;
import com.macro.blog.retrofit.domain.LoginResult;
import com.macro.blog.retrofit.domain.PmsBrand;
import com.macro.blog.retrofit.remote.PmsBrandApi;
import com.macro.blog.retrofit.remote.UmsAdminApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther macrozheng
 * @description Retrofit远程调用测试接口
 * @date 2025/6/24
 * @github https://github.com/macrozheng
 */
@Tag(name = "RetrofitController", description = "Retrofit远程调用测试接口")
@RestController
@RequestMapping("/retrofit")
public class RetrofitController {

    @Autowired
    private UmsAdminApi umsAdminApi;
    @Autowired
    private PmsBrandApi pmsBrandApi;
    @Autowired
    private TokenHolder tokenHolder;

    @Operation(summary = "调用远程登录接口获取token")
    @PostMapping(value = "/admin/login")
    public CommonResult<LoginResult> login(@RequestParam String username, @RequestParam String password) {
        CommonResult<LoginResult> result = umsAdminApi.login(new LoginParam(username,password));
        LoginResult loginResult = result.getData();
        if (result.getData() != null) {
            tokenHolder.putToken(loginResult.getTokenHead() + " " + loginResult.getToken());
        }
        return result;
    }

    @Operation(summary = "调用远程接口分页查询品牌列表")
    @GetMapping(value = "/brand/list")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        return pmsBrandApi.list(pageNum, pageSize);
    }

    @Operation(summary = "调用远程接口获取指定id的品牌详情")
    @GetMapping(value = "/brand/{id}")
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return pmsBrandApi.detail(id);
    }

    @Operation(summary = "调用远程接口添加品牌")
    @PostMapping(value = "/brand/create")
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        return pmsBrandApi.create(pmsBrand);
    }

    @Operation(summary = "调用远程接口更新指定id品牌信息")
    @PostMapping(value = "/brand/update/{id}")
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand) {
        return pmsBrandApi.update(id,pmsBrand);
    }

    @Operation(summary = "调用远程接口删除指定id的品牌")
    @GetMapping(value = "/delete/{id}")
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        return  pmsBrandApi.delete(id);
    }
}
