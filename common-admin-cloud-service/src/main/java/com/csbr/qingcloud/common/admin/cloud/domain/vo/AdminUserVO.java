package com.csbr.qingcloud.common.admin.cloud.domain.vo;

import com.csbr.cloud.mybatis.annotations.LikeQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * @program: ms-common-admin-cloud-service
 * @description: 获取平台用户返回的对象
 * @author: yio
 * @create: 2020-07-20 13:29
 **/
@Data
@ApiModel("获取平台用户的返回值对象")
public class AdminUserVO {

    @ApiModelProperty("系统唯一标识")
    private String Guid;

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    private String logonUser;

    /** 密码 */
    @ApiModelProperty("密码")
    private String pwd;

    /** 姓名 */
    @ApiModelProperty("姓名")
    private String name;

    /** 业务状态(Y 有效；N：无效) */
    @ApiModelProperty("状态（Y 有效；N：无效；默认 Y）")
    private String bizState;

    /** 手机号 */
    @ApiModelProperty("手机号")
    private String mobileNo;

    /** 服务标识 */
    @ApiModelProperty("服务标识")
    private String serviceSign;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

    @ApiModelProperty("平台名称")
    private String platformName;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 锁定(Y 是；N 否) */
    @ApiModelProperty("锁定(Y 是；N 否)")
    private String isLocked;

    /** 登录失败次数 */
    @ApiModelProperty("登录失败次数")
    private Integer loginErrorCount;

    /** 最后登录时间 */
    @ApiModelProperty("最后登录时间")
    private Timestamp lastLoginTime;

    /** 是否删除(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否删除(Y 是；N 否 默认 N)")
    private String isDeleted;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createUserId;

    /** 创建人姓名 */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /** 修改姓名 */
    @ApiModelProperty("修改姓名")
    private String updateUserName;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

}
