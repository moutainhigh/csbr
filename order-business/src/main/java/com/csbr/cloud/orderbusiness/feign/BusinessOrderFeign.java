package com.csbr.cloud.orderbusiness.feign;

import com.csbr.cloud.common.config.FastCallFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangheng
 * @date 2019/11/6 15:35
 */

@FeignClient(value = "order-business-server", url = "localhost:8763", configuration = FastCallFeignConfiguration.class)
public interface BusinessOrderFeign {
}
