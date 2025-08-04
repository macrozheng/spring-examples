package com.macro.blog.mqtt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther macrozheng
 * @description 静态网页controller
 * @date 2025/8/1
 * @github https://github.com/macrozheng
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
