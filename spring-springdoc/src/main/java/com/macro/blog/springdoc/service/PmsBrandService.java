package com.macro.blog.springdoc.service;


import com.macro.blog.springdoc.domain.PmsBrand;

import java.util.List;

/**
 * @auther macrozheng
 * @description PmsBrandService
 * @date 2025/8/12
 * @github https://github.com/macrozheng
 */
public interface PmsBrandService {
    List<PmsBrand> listAll();

    int create(PmsBrand brand);

    int update(Long id, PmsBrand brand);

    int delete(Long id);

    List<PmsBrand> list(int pageNum, int pageSize);

    PmsBrand detail(Long id);
}
