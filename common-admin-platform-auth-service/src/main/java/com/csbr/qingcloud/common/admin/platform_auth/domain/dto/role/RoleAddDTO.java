package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 角色新增参数
 * @author: yio
 * @create: 2020-07-22 15:02
 **/
@Data
@ApiModel("角色新增参数")
public class RoleAddDTO {

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;


    /** 应用端GUID */
    @ApiModelProperty("应用端GUID")
    @NotBlank(message = "应用端GUID不能为空。")
    private String appSideGuid;


    /** 角色名称 */
    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空。")
    private String roleName;

    /** 序号 */
    @ApiModelProperty("序号")
    private Integer orderNum;

    /** 业务状态(Y 有效；S：停用；) */
    @ApiModelProperty("业务状态(Y 有效；S：停用；)")
    @Pattern(regexp = "^Y|S$", message = "业务状态应该为Y、S中的值。")
    private String bizState="Y";

    /** 角色对应功能菜单列表 */
    @ApiModelProperty("角色对应功能菜单列表")
    private List<String> menuGuids;

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;
}
