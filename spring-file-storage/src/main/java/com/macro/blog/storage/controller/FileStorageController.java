package com.macro.blog.storage.controller;


import com.macro.blog.storage.common.api.CommonResult;
import com.macro.blog.storage.dto.FileStorageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther macrozheng
 * @description x-file-storage文件存储存储管理Controller
 * @date 2025/7/24
 * @github https://github.com/macrozheng
 */
@Controller
@Tag(name = "FileStorageController", description = "x-file-storage文件存储存储管理")
@RequestMapping("/storage")
public class FileStorageController {

    @Autowired
    private FileStorageService fileStorageService;//注入实列

    @Operation(summary = "文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public CommonResult<FileStorageResult> upload(@RequestPart("file") MultipartFile file) {
        FileInfo fileInfo = fileStorageService.of(file).upload();
        FileStorageResult result = new FileStorageResult();
        result.setName(fileInfo.getFilename());
        result.setUrl(fileInfo.getUrl());
        return CommonResult.success(result);
    }

    @Operation(summary = "文件删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("objectName") String objectName) {
        FileInfo fileInfo = new FileInfo().setFilename(objectName).setPlatform("amazon-s3-v2-1");
        fileStorageService.delete(fileInfo);
        return CommonResult.success(null);
    }

    @Operation(summary = "文件上传")
    @RequestMapping(value = "/upload-minio", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public CommonResult<FileStorageResult> uploadMinIO(@RequestPart("file") MultipartFile file) {
        FileInfo fileInfo = fileStorageService.of(file).setPlatform("minio-1").upload();
        FileStorageResult result = new FileStorageResult();
        result.setName(fileInfo.getFilename());
        result.setUrl(fileInfo.getUrl());
        return CommonResult.success(result);
    }

    @Operation(summary = "文件删除")
    @RequestMapping(value = "/delete-minio", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteMinio(@RequestParam("objectName") String objectName) {
        FileInfo fileInfo = new FileInfo().setFilename(objectName).setPlatform("minio-1");
        fileStorageService.delete(fileInfo);
        return CommonResult.success(null);
    }
}
