package com.csbr.cloud.hy.server.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试读取远程配置中的配置
 */

@RestController
public class TestController {

//    @Value("${profile}")
//    private String from;
//
//    @RequestMapping("/profile")
//    public String from() {
//        return this.from;
//    }
//    @Value("${nacos.config}")
//    private String config;

//    @GetMapping("/getValue")
//    @SentinelResource(value = "getValue")
//    public String getValue() {
//        return config;
//    }
//
//    @GetMapping("/hello")
//    public String hello() {
//        return "didispace.com";
//    }
}
