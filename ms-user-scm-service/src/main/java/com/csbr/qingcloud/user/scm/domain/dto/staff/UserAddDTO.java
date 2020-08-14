package com.csbr.qingcloud.user.scm.domain.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 用户新增参数
 * @author: yio
 * @create: 2020-07-28 13:45
 **/
@Data
@ApiModel("用户新增参数")
public class UserAddDTO {

    /** 身份证号码 */
    @ApiModelProperty("身份证号码")
    private String idCode;

    /** 是否初始账号(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否初始账号(Y 是；N 否 默认 N)")
    @Pattern(regexp = "^Y|N$", message = "是否初始账号应该为Y、N中的值。")
    private String isInit="N";

    /** 锁定(Y 是；N 否) */
    @ApiModelProperty("锁定(Y 是；N 否)")
    @Pattern(regexp = "^Y|N$", message = "锁定应该为Y、N中的值。")
    private String isLocked="N";

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    @NotBlank(message = "登录账号不能为空。")
    private String logonUser;

    /** 密码 */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空。")
    private String pwd;

    /** 手机号 */
    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空。")
    private String mobileNo;

    /** 姓名 */
    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不能为空。")
    private String name;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

    /** 注册码 */
    @ApiModelProperty("注册码")
    private String registrationCode;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 服务标识 */
    @ApiModelProperty("服务标识")
    private String serviceSign;

    /** 业务状态(Y 有效；S 停用) */
    @ApiModelProperty("业务状态(Y 有效；S 停用)")
    @Pattern(regexp = "^Y|S$", message = "状态应该为Y、S中的值。")
    private String bizState="Y";

    /** 角色GUID */
    @ApiModelProperty("角色GUID")
    private List<String> roleGuids;

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;


}
