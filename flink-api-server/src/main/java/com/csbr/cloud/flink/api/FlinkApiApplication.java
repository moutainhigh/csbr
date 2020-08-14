package com.csbr.cloud.flink.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableCircuitBreaker
@EnableFeignClients//开启使用Feign调用不同微服务的api
@RefreshScope
@SpringBootApplication
public class FlinkApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(FlinkApiApplication.class, args);
    }

}
