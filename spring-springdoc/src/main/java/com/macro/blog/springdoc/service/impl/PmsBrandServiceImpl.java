package com.macro.blog.springdoc.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.json.JSONUtil;
import com.macro.blog.springdoc.domain.PmsBrand;
import com.macro.blog.springdoc.service.PmsBrandService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @auther macrozheng
 * @description PmsBrandService实现类
 * @date 2025/8/12
 * @github https://github.com/macrozheng
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    private List<PmsBrand> brandList;

    @PostConstruct
    private void initData(){
        ClassPathResource resource = new ClassPathResource("json/brands.json");
        String jsonStr = IoUtil.read(resource.getStream(), Charset.forName("UTF-8"));
        brandList = JSONUtil.toList(jsonStr, PmsBrand.class);
    }

    @Override
    public List<PmsBrand> listAll() {
        return brandList;
    }

    @Override
    public int create(PmsBrand brand) {
        if(this.detail(brand.getId())==null){
            brandList.add(brand);
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Long id, PmsBrand brand) {
        PmsBrand findBrand = this.detail(id);
        if(findBrand !=null){
            brand.setId(id);
            brandList.remove(findBrand);
            brandList.add(brand);
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Long id) {
        PmsBrand findBrand = this.detail(id);
        if(findBrand !=null){
            brandList.remove(findBrand);
            return 1;
        }
        return 0;
    }

    @Override
    public List<PmsBrand> list(int pageNum, int pageSize) {
        return brandList.stream()
                .skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public PmsBrand detail(Long id) {
        Optional<PmsBrand> brandOptional = brandList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
        return brandOptional.orElse(null);
    }
}
