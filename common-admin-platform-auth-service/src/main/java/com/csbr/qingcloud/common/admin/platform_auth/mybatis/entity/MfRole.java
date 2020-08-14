
package com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 角色实体.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfRole extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 平台GUID */
    private String platformGuid;

    /** 应用端GUID */
    private String appSideGuid;

    /** 企业GUID */
    private String tenantGuid;

    /** 角色名称 */
    private String roleName;

    /** 序号 */
    private Integer orderNum;

    /** 业务状态(Y 有效；S：停用；) */
    private String bizState;

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

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

}