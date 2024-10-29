package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明这是一个控制器类
 */
@RestController
public class DemoController {
    /**
     * 浏览器访问hello方法,在页面显示Hello World
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return "Hello World";
    }
}
