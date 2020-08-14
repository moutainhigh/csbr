package com.csbr.qingcloud.user.scm.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 获取企业对象明细
 * @author: yio
 * @create: 2020-07-29 15:25
 **/
@Data
@ApiModel("获取企业的返回值对象")
public class TenantDetailVO extends TenantVO{

    /**
     * 证照列表
     */
    @ApiModelProperty("证照列表")
    private List<TenantLicVO> tenantLicVOs;
}
