package com.csbr.cloud.hy.server;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
public class MsHyServerApplication {

    private final static Logger logger= LoggerFactory.getLogger(MsHyServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MsHyServerApplication.class, args);

    }
}
