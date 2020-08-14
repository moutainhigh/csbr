package com.csbr.cloud.camunda.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootApplication
//@EnableProcessApplication
@EnableFeignClients
//@EnableEurekaClient
@EnableSwagger2
@RefreshScope
public class CamundabpmApplication {

//	@Autowired
//	private RuntimeService runtimeService;

	public static void main(String[] args) {
		SpringApplication.run(CamundabpmApplication.class, args);
	}

//	@EventListener
//	private void processPostDeploy(PostDeployEvent event) {
//		runtimeService.startProcessInstanceByKey("Process_1");
//		HashMap<String, Object> variables = new HashMap<String, Object>();
//		variables.put("assigneeList030", Arrays.asList("admin", "demo"));
//		variables.put("assigneeList040", Arrays.asList("admin", "demo"));
//		variables.put("starter","demo");
//		variables.put("amount","980");
//		runtimeService.startProcessInstanceByKey("Process_Demo1",variables);
//	}

	//关闭spring boot jackson的FAIL_ON_EMPTY_BEANS
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}

	//Camunda CORS Filter in Spring Boot Application
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
