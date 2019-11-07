package com.csbr.cloud.orderlogistics;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableSwagger2
@EnableFeignClients//开启使用Feign调用不同微服务的api
public class OrderLogisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderLogisticsApplication.class, args);
    }

//    @Bean
//    public GlobalTransactionScanner globalTransactionScanner() {
//        GlobalTransactionScanner globalTransactionScanner = new GlobalTransactionScanner("order-logistics-server",
//                "my_test_tx_group");
//        return globalTransactionScanner;
//    }
}
