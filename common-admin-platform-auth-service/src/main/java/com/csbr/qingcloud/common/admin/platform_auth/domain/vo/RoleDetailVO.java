package com.csbr.qingcloud.common.admin.platform_auth.domain.vo;

import com.csbr.cloud.mybatis.entity.HierarchicalVO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfMenu;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 角色功能明细
 * @author: yio
 * @create: 2020-07-23 11:07
 **/
public class RoleDetailVO extends HierarchicalVO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

    /** 应用端GUID */
    @ApiModelProperty("应用端GUID")
    private String appSideGuid;

    /** 角色名称 */
    @ApiModelProperty("角色名称")
    private String roleName;

    /** 序号 */
    @ApiModelProperty("序号")
    private Integer orderNum;

    /** 业务状态(Y 有效；S：停用；) */
    @ApiModelProperty("业务状态(Y 有效；S：停用；)")
    private String bizState;

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


    /** 角色功能列表 */
    @ApiModelProperty("角色功能列表")
    private List<MfMenu> roleMenus;
}
