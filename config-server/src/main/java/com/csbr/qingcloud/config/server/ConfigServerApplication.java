package com.csbr.qingcloud.config.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * 配置中心服务
 */
@SpringBootApplication
@EnableConfigServer//开启config-server
//@EnableEurekaClient//将其注册到服务中心
public class ConfigServerApplication implements CommandLineRunner {

    private final static Logger logger= LoggerFactory.getLogger(ConfigServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i <99 ; i++) {
            logger.info("=========hello World ELK========");
        }

    }
}
