//package com.csbr.cloud.flinkserver;
//
//import com.csbr.cloud.flinkserver.task.CsbrSumAll;
//import org.springframework.boot.Banner;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//
//@SpringBootApplication
//@EnableFeignClients//开启使用Feign调用不同微服务的api
//@EnableCircuitBreaker//开启断路器功能
//public class FlinkServerApplication implements CommandLineRunner {
//
//    public static void main(String[] args) {
//        SpringApplication application = new SpringApplication(FlinkServerApplication.class);
////        SpringApplication.run(FlinkServerApplication.class, args);
//        application.setBannerMode(Banner.Mode.OFF);
//        application.run(args);
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        //测试
//        CsbrSumAll csbrSumAll = new CsbrSumAll();
//        csbrSumAll.getCsbrSumAll();
//    }
//}
