package com.csbr.cloud.orderlogistics.feign;

import com.csbr.cloud.common.config.FastCallFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhangheng
 * @date 2019/11/6 16:29
 */
@FeignClient(value = "order-business-server", url = "localhost:8082", configuration = FastCallFeignConfiguration.class)
public interface DispatchOrderFeign {
}
