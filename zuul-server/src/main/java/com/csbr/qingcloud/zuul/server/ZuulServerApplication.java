package com.csbr.qingcloud.zuul.server;

import com.csbr.qingcloud.zuul.server.filter.error.ErrorFilter;
//import com.csbr.qingcloud.zuul.server.filter.post.LoginResponseFilter;
import com.csbr.qingcloud.zuul.server.filter.pre.PreFilter;
import com.csbr.qingcloud.zuul.server.filter.routing.RoutingFilter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@SpringBootApplication
@EnableCircuitBreaker//开启断路器功能
@EnableZuulProxy
@EnableFeignClients//开启使用Feign调用不同微服务的api
@EnableSwagger2
public class ZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServerApplication.class, args);
	}


    //实例化过滤器

	//pre
	@Bean
	public ZuulFilter perFiler() {
		return new PreFilter();
	}

	//post
//	@Bean
//	public ZuulFilter postFiler() {
//		return new LoginResponseFilter();
//	}

	//error
	@Bean
	public ZuulFilter errorFiler() {
		return new ErrorFilter();
	}

	//routing
	@Bean
	public ZuulFilter routingFiler() {
		return new RoutingFilter();
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedOrigins("*")
//						.allowedMethods("PUT", "DELETE","GET","POST")
//						.allowedHeaders("*")
//						.exposedHeaders("access-control-allow-headers",
//								"access-control-allow-methods",
//								"access-control-allow-origin",
//								"access-control-max-age",
//								"X-Frame-Options")
//						.allowCredentials(false).maxAge(3600);
//			}
//		};
//
//	}


}