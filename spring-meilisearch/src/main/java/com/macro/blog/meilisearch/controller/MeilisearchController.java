package com.macro.blog.meilisearch.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.macro.blog.meilisearch.common.api.CommonResult;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.SearchRequest;
import com.meilisearch.sdk.model.SearchResult;
import com.meilisearch.sdk.model.Searchable;
import com.meilisearch.sdk.model.Settings;
import com.meilisearch.sdk.model.TaskInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @auther macrozheng
 * @description Meilisearch搜索功能Controller
 * @date 2025/4/18
 * @github https://github.com/macrozheng
 */
@RestController
@Tag(name = "MeilisearchController",description = "Meilisearch搜索功能")
@RequestMapping("/meilisearch")
public class MeilisearchController {

    @Value("${meilisearch.index}")
    private String MEILISEARCH_INDEX;

    @Autowired
    private Client searchClient;

    @Operation(summary = "创建索引并导入商品数据")
    @GetMapping("/createIndex")
    public CommonResult createIndex(){
        ClassPathResource resource = new ClassPathResource("json/products.json");
        String jsonStr = IoUtil.read(resource.getStream(), Charset.forName("UTF-8"));
        Index index = searchClient.index(MEILISEARCH_INDEX);
        TaskInfo info = index.addDocuments(jsonStr, "id");
        return CommonResult.success(info);
    }

    @Operation(summary = "刪除商品索引")
    @GetMapping("/deleteIndex")
    public CommonResult deleteIndex(){
        TaskInfo info = searchClient.deleteIndex(MEILISEARCH_INDEX);
        return CommonResult.success(info);
    }

    @Operation(summary = "获取索引设置")
    @GetMapping("/getSettings")
    public CommonResult getSettings(){
        Settings settings = searchClient.index(MEILISEARCH_INDEX).getSettings();
        return CommonResult.success(settings);
    }

    @Operation(summary = "修改索引设置")
    @GetMapping("/updateSettings")
    public CommonResult updateSettings(){
        Settings settings = new Settings();
        settings.setFilterableAttributes(new String[]{"productCategoryName"});
        settings.setSortableAttributes(new String[]{"price"});
        TaskInfo info = searchClient.index(MEILISEARCH_INDEX).updateSettings(settings);
        return CommonResult.success(info);
    }

    @Operation(summary = "根据关键字分页搜索商品")
    @GetMapping(value = "/search")
    @ResponseBody
    public CommonResult search(@RequestParam(required = false) String keyword,
                               @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                               @RequestParam(required = false) String productCategoryName,
                               @RequestParam(required = false,value = "0->按价格升序；1->按价格降序") Integer order) {
        SearchRequest.SearchRequestBuilder searchBuilder = SearchRequest.builder();
        searchBuilder.page(pageNum);
        searchBuilder.hitsPerPage(pageSize);
        if(StrUtil.isNotEmpty(keyword)){
            searchBuilder.q(keyword);
        }
        if(StrUtil.isNotEmpty(productCategoryName)){
            searchBuilder.filter(new String[]{"productCategoryName="+productCategoryName});
        }
        if(order!=null){
            if(order==0){
                searchBuilder.sort(new String[]{"price:asc"});
            }else if(order==1){
                searchBuilder.sort(new String[]{"price:desc"});
            }
        }
        Searchable searchable = searchClient.index(MEILISEARCH_INDEX).search(searchBuilder.build());
        return CommonResult.success(searchable);
    }
}
