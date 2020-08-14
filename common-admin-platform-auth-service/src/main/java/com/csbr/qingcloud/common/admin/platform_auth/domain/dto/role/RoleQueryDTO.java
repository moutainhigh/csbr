package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role;

import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @program: common-admin-platform-auth-service
 * @description: 角色查询参数
 * @author: yio
 * @create: 2020-07-22 15:30
 **/
@Data
@ApiModel("角色查询参数")
public class RoleQueryDTO extends BasePageDTO {

    /** 应用端GUID */
    @ApiModelProperty("应用端GUID")
    @NotBlank(message = "应用端GUID不能为空。")
    private String appSideGuid;

    /** 角色名称 */
    @ApiModelProperty("角色名称")
    @LikeQuery
    private String roleName;

    /** 业务状态(Y 有效；S 停用) */
    @ApiModelProperty("业务状态(Y 有效；S 停用)")
    @Pattern(regexp = "^Y|S$", message = "业务状态应该为Y、S中的值。")
    private String bizState;

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;

}
