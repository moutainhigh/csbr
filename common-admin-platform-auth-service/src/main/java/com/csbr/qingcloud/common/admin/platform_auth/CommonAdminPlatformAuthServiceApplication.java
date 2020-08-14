package com.csbr.qingcloud.common.admin.platform_auth;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * nacos配置中心服务
 */
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
@RefreshScope
//@EnableDiscoveryClient
@EnableEurekaClient
@EnableSwagger2
@RestController
@Slf4j
public class CommonAdminPlatformAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonAdminPlatformAuthServiceApplication.class, args);
        log.info("[========================>>>>>>>>>>>>>>服务已启动<<<<<<<<<<<<<<========================]");
    }

}
