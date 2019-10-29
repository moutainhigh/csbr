package com.csbr.qingcloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * 配置中心服务
 */
@SpringBootApplication
@EnableConfigServer//开启config-server
@EnableDiscoveryClient//将其注册到服务中心
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);

    }
}
