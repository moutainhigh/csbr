package com.csbr.qingcloud.common.admin.platform_auth.domain.vo;

import com.csbr.cloud.mybatis.entity.HierarchicalVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 菜单查询结果
 * @author: yio
 * @create: 2020-07-21 13:46
 **/
@Data
@ApiModel("获取菜单的返回值对象")
public class MenuVO extends HierarchicalVO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

    /** 平台名称 */
    @ApiModelProperty("平台名称")
    private String platformName;

    /** 应用端GUID */
    @ApiModelProperty("应用端GUID")
    private String appSideGuid;

    /** 应用端名称 */
    @ApiModelProperty("应用端名称")
    private String appSideName;

    /** 产品GUID */
    @ApiModelProperty("产品GUID")
    private String productGuid;


    /** 上级菜单Guid */
    @ApiModelProperty("上级菜单Guid")
    private String parentGuid;


    /** 菜单名称 */
    @ApiModelProperty("菜单名称")
    private String menuName;

    /** 路由地址 */
    @ApiModelProperty("路由地址")
    private String path;

    /** 组件路径 */
    @ApiModelProperty("组件路径")
    private String component;

    /** 请求方式(GET；POST；PUT；DELETE) */
    @ApiModelProperty("请求方式(GET；POST；PUT；DELETE)")
    private String action;

    /** 图标 */
    @ApiModelProperty("图标")
    private String icon;

    /** 序号 */
    @ApiModelProperty("序号")
    private Integer orderNum;

    /** 菜单类型(M 目录；C 菜单；P 页面；F 按钮) */
    @ApiModelProperty("菜单类型(M 目录；C 菜单；P 页面；F 按钮)")
    private String menuType;

    /** 是否可见(Y 是；N 否 默认 Y) */
    @ApiModelProperty("是否可见(Y 是；N 否 默认 Y)")
    private String visible;

    /** 业务状态(Y 有效；S：停用；) */
    @ApiModelProperty("业务状态(Y 有效；S：停用；)")
    private String bizState;

    /** 是否外链(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否外链(Y 是；N 否 默认 N)")
    private String isExternalLink;

    /** 权限标识 */
    @ApiModelProperty("权限标识")
    private String perms;

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
