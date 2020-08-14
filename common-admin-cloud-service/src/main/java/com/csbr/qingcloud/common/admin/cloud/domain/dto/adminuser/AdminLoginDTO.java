package com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: common-admin-cloud-service
 * @description:
 * @author: Huanglh
 * @create: 2020-07-23 17:41
 **/
@Data
@ApiModel(value = "管理员登陆")
public class AdminLoginDTO {
    /** 登录账号 */
    @ApiModelProperty("登录账号")
    private String logonUser;

    /** 密码 */
    @ApiModelProperty("密码")
    private String pwd;

    /** 验证码 */
    @ApiModelProperty("验证码")
    private String validateCode;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;
}
