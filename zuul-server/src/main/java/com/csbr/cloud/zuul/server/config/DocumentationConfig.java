package com.csbr.cloud.zuul.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/7/7 17:14
 * 网关访问子服务 需要添加
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Autowired
    private final RouteLocator routeLocator;

    public DocumentationConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList();
//        resources.add(swaggerResource("认证中心", "/user-auth-service/v2/api-docs", "1.0"));
//        resources.add(swaggerResource("网关服务", "/v2/api-docs", "1.0"));
        // 排除自身, 将其他的服务添加进去
//        discoveryClient.getServices().stream().filter(s -> !s.equals(applicationName)).forEach(name -> {
//            resources.add(swaggerResource(name, "/" + name + "/v2/api-docs", "2.0"));
//        });

        for (String s:discoveryClient.getServices()) {

            if(!s.equals("eureka-server")&&!s.equals("seata-server")){
                if(s.equals(applicationName)){
                    resources.add(swaggerResource(s,    "/v2/api-docs", "2.0"));
                }else{
                    resources.add(swaggerResource(s, "/" + s + "/v2/api-docs", "2.0"));
                }
            }
        }
        return resources;

//        List<SwaggerResource> resources = new ArrayList<>();
//        List<Route> routes = routeLocator.getRoutes();
//        System.err.println(Arrays.toString(routes.toArray()));
//        routes.forEach(route -> {
//            resources.add(swaggerResource(route.getId(), route.getFullPath().replace("/**", "/v2/api-docs"),"2.0"));
//            System.err.println(route.getFullPath().replace("/**", "/v2/api-docs"));
//        });
//        return resources;

    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
