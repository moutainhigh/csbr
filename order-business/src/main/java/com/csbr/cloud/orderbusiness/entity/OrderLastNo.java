package com.csbr.cloud.orderbusiness.entity;

import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;

/**
 * @author zhangheng
 * @date 2019/11/6 15:18
 */

@Data
public class OrderLastNo extends BaseDO {

    private String orderType;

    private String orderNo;

}
