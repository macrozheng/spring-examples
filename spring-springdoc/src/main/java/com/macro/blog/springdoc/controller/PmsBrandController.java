package com.macro.blog.springdoc.controller;

import com.macro.blog.springdoc.common.api.CommonResult;
import com.macro.blog.springdoc.domain.PmsBrand;
import com.macro.blog.springdoc.service.PmsBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @auther macrozheng
 * @description
 * @date 2025/8/12
 * @github https://github.com/macrozheng
 */
@Tag(name = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    @Operation(summary = "获取所有品牌列表",description = "需要登录后访问")
    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> listAll() {
        return CommonResult.success(brandService.listAll());
    }

    @Operation(summary = "添加品牌")
    @PostMapping("/create")
    public CommonResult create(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = brandService.create(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }

    @Operation(summary = "更新指定id品牌信息")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody PmsBrand brand) {
        CommonResult commonResult;
        int count = brandService.update(id, brand);
        if (count == 1) {
            commonResult = CommonResult.success(brand);
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }

    @Operation(summary = "删除指定id的品牌")
    @GetMapping("/delete/{id}")
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = brandService.delete(id);
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @Operation(summary = "分页查询品牌列表")
    @GetMapping("/list")
    public CommonResult<List<PmsBrand>> list(@RequestParam(value = "pageNum", defaultValue = "1")
                                             @Parameter(description = "页码") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "3")
                                             @Parameter(description = "每页数量") Integer pageSize) {
        List<PmsBrand> brandList = brandService.list(pageNum, pageSize);
        return CommonResult.success(brandList);
    }

    @Operation(summary = "获取指定id的品牌详情")
    @GetMapping("/{id}")
    public CommonResult<PmsBrand> detail(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.detail(id));
    }
}
