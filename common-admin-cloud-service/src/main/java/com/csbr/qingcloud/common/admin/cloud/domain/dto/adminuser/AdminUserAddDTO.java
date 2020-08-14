package com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @program: ms-common-admin-cloud-service
 * @description: 添加用户参数
 * @author: yio
 * @create: 2020-07-20 11:46
 **/
@Data
@ApiModel(value = "平台用户新增参数")
public class AdminUserAddDTO {

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    @NotBlank(message = "登录账号不能为空。")
    private String logonUser;

    /** 密码 */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空。")
    private String pwd;

    /** 姓名 */
    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不能为空。")
    private String name;

    /** 业务状态(Y 有效；S：无效) */
    @ApiModelProperty("状态（Y 有效；S：停用；默认 Y）")
    @Pattern(regexp = "^Y|S$", message = "状态应该为Y、S中的值。")
    private String bizState="Y";

    /** 手机号 */
    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空。")
    private String mobileNo;

    /** 服务标识 */
    @ApiModelProperty("服务标识")
    private String serviceSign;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;


    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;


}
