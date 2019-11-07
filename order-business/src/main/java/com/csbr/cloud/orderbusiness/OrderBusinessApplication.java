package com.csbr.cloud.orderbusiness;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.csbr.cloud.distributelock.lock.DistributedLockTemplate;
import com.csbr.cloud.distributelock.lock.SingleDistributedLockTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
//@MapperScan("com.csbr.cloud.distributelock.lock")
@EnableSwagger2
@EnableFeignClients//开启使用Feign调用不同微服务的api
public class OrderBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderBusinessApplication.class, args);
    }

}
