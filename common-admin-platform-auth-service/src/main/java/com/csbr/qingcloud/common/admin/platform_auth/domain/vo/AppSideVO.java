package com.csbr.qingcloud.common.admin.platform_auth.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 应用端查询结果
 * @author: yio
 * @create: 2020-07-21 09:57
 **/
@Data
@ApiModel("获取应用端的返回值对象")
public class AppSideVO {
    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 应用端名称 */
    @ApiModelProperty("应用端名称")
    private String appSideName;

    /** 标识符 */
    @ApiModelProperty("标识符")
    private String identifier;

    /** 应用形式(01 PC；02 微信；03 APP) */
    @ApiModelProperty("应用形式(01 PC；02 微信；03 APP)")
    private String appType;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;


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

    /** 是否删除(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否删除(Y 是；N 否 默认 N)")
    private String isDeleted;

}
