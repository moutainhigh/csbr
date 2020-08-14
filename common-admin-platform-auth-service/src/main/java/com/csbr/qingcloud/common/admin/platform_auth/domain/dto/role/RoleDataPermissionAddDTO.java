package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 角色数据权限参数
 * @author: yio
 * @create: 2020-07-23 16:20
 **/
@Data
@ApiModel("角色数据权限新增参数")
public class RoleDataPermissionAddDTO {
    /** 角色GUID */
    @ApiModelProperty("角色GUID")
    @NotBlank(message = "角色GUID不能为空。")
    private String roleGuid;

    /** 数据权限GUID列表 */
    @ApiModelProperty("数据权限GUID列表")
    @NotBlank(message = "数据权限GUID列表不能为空。")
    private List<String> dataPermissionGuids;

    /** 自定义数据权限模型 */
    @ApiModelProperty("自定义数据权限模型")
    private String customDataPermissionGuid;

    /** 自定义数据权限数据 */
    private String customData;

}
