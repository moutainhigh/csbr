package com.csbr.qingcloud.common.admin.platform_auth.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: common-admin-platform-auth-service
 * @description: 获取用户查询的返回值对象
 * @author: yio
 * @create: 2020-07-28 14:46
 **/
@Data
@ApiModel("获取用户查询的返回值对象")
public class UserVO  {

    /** 业务状态(Y 有效；S 停用) */
    @ApiModelProperty("业务状态(Y 有效；S 停用)")
    private String bizState;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createUserId;

    /** 创建人姓名 */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 身份证号码 */
    @ApiModelProperty("身份证号码")
    private String idCode;

    /** 是否删除(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否删除(Y 是；N 否 默认 N)")
    @TableLogic
    private String isDeleted;

    /** 是否初始账号(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否初始账号(Y 是；N 否 默认 N)")
    private String isInit;

    /** 锁定(Y 是；N 否) */
    @ApiModelProperty("锁定(Y 是；N 否)")
    private String isLocked;

    /** 最后登录时间 */
    @ApiModelProperty("最后登录时间")
    private Timestamp lastLoginTime;

    /** 登录失败次数 */
    @ApiModelProperty("登录失败次数")
    private Integer loginErrorCount;

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    private String logonUser;

    /** 手机号 */
    @ApiModelProperty("手机号")
    private String mobileNo;

    /** 姓名 */
    @ApiModelProperty("姓名")
    private String name;


    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

    /** 密码 */
    @ApiModelProperty("密码")
    private String pwd;

    /** 注册码 */
    @ApiModelProperty("注册码")
    private String registrationCode;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 服务标识 */
    @ApiModelProperty("服务标识")
    private String serviceSign;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /** 修改姓名 */
    @ApiModelProperty("修改姓名")
    private String updateUserName;


}
