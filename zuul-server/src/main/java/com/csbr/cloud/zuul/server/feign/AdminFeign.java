package com.csbr.cloud.zuul.server.feign;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.config.FastCallFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: zuul-server
 * @description: 管理员服务接口
 * @author: Huanglh
 * @create: 2020-07-29 10:53
 **/
@FeignClient(value = "ms-common-admin-cloud-service", configuration = FastCallFeignConfiguration.class)
public interface AdminFeign {
    /**
     * 前端登录
     */
    @RequestMapping(value = "/admin-user/login", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject addAdminLogin(@RequestBody JSONObject jsonObject);

    /**
     * 触发fegin
     */
    @RequestMapping(value = "/admin-user/trigger", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void triggerAdminFeign();
}
