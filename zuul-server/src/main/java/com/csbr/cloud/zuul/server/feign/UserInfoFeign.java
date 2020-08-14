package com.csbr.cloud.zuul.server.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用认证中心的微服务的api
 */
@FeignClient(value = "user-auth-service", url = "localhost:9001")
public interface UserInfoFeign {

    /**
     * 前端登录
     */
    @RequestMapping(value = "/login/login-verify",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject appLogin(@RequestBody JSONObject jsonObject);

    /**
     * 根据角色id获取菜单权限
     */
    @RequestMapping(value = "/user/findCodesByRole",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    String findCodesByRole(@RequestParam("username") String roleId);

    /**
     * 触发fegin
     */
    @RequestMapping(value = "/trigger/feign",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void triggerFeign();


}
