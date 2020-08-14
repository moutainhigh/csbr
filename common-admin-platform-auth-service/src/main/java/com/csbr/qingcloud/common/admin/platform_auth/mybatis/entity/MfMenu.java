
package com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 菜单实体.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfMenu extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 平台GUID */
    private String platformGuid;

    /** 应用端GUID */
    private String appSideGuid;

    /** 产品GUID */
    private String productGuid;

    
    /** 上级菜单Guid */
    private String parentGuid;

    
    /** 菜单名称 */
    private String menuName;

    /** 路由地址 */
    private String path;

    /** 组件路径 */
    private String component;

    /** 请求方式(GET；POST；PUT；DELETE) */
    private String action;

    /** 图标 */
    private String icon;

    /** 序号 */
    private Integer orderNum;

    /** 菜单类型(M 目录；C 菜单；P 页面；F 按钮) */
    private String menuType;

    /** 是否可见(Y 是；N 否 默认 Y) */
    private String visible;

    /** 业务状态(Y 有效；S：停用；) */
    private String bizState;

    /** 是否外链(Y 是；N 否 默认 N) */
    private String isExternalLink;

    /** 权限标识 */
    private String perms;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改时间 */
    private Timestamp updateTime;

}