package com.csbr.qingcloud.user.scm.domain.dto.tenant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 企业信息新增参数
 * @author: yio
 * @create: 2020-07-29 11:57
 **/
@Data
@ApiModel("企业信息新增参数")
public class TenantAddDTO extends TenantBaseDTO{
    /**
     * 证照
     */
    @Valid
    @ApiModelProperty("证照")
    private List<TenantLicAddDTO> tenantLicAddDTOs;
}
