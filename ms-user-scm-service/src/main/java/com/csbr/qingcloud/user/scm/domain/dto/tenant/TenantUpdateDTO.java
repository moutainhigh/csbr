package com.csbr.qingcloud.user.scm.domain.dto.tenant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 企业信息修改参数
 * @author: yio
 * @create: 2020-07-29 11:58
 **/
@Data
@ApiModel("企业信息修改参数")
public class TenantUpdateDTO extends TenantBaseDTO{

    /** 系统唯一标识 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /**
     * 证照
     */
    @Valid
    @ApiModelProperty("证照")
    private List<TenantLicUpdateDTO> tenantLicUpdateDTOs;

    /**
     * 需要移除的证照GUID列表
     */
    @ApiModelProperty("需要移除的证照GUID列表")
    private List<String> delTenantLicGuids;

}
