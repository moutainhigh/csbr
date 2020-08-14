package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 通过用户GUID获取角色名的参数
 * @author: yio
 * @create: 2020-08-04 14:41
 **/
@Data
@ApiModel("通过用户GUID获取角色名的参数")
public class UserRoleNameDTO {
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    @ApiModelProperty("用户GUID列表")
    @NotBlank(message = "用户GUID列表不能为空。")
    private List<String> userGuids;
}
