package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user;

import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * @program: common-admin-platform-auth-service
 * @description: 用户查询参数
 * @author: yio
 * @create: 2020-07-28 13:47
 **/
@Data
@ApiModel("用户修改参数")
public class UserQueryDTO extends BasePageDTO {
    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 身份证号码 */
    @ApiModelProperty("身份证号码")
    private String idCode;

    /** 是否初始账号(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否初始账号(Y 是；N 否 默认 N)")
    @Pattern(regexp = "^Y|N$", message = "是否初始账号应该为Y、N中的值。")
    private String isInit;

    /** 锁定(Y 是；N 否) */
    @ApiModelProperty("锁定(Y 是；N 否)")
    @Pattern(regexp = "^Y|N$", message = "锁定应该为Y、N中的值。")
    private String isLocked;

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    @LikeQuery
    private String logonUser;

    /** 手机号 */
    @ApiModelProperty("手机号")
    @LikeQuery
    private String mobileNo;

    /** 姓名 */
    @ApiModelProperty("姓名")
    @LikeQuery
    private String name;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

    /** 注册码 */
    @ApiModelProperty("注册码")
    private String registrationCode;

    /** 业务状态(Y 有效；S 停用) */
    @ApiModelProperty("业务状态(Y 有效；S 停用)")
    @Pattern(regexp = "^Y|S$", message = "业务状态应该为Y、S中的值。")
    private String bizState;

}
