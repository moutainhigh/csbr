
package com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 角色数据权限实体.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfRoleDataPermission extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 角色GUID */
    private String roleGuid;

    /** 角色名称 */
    private String roleName;
    
    /** 数据权限Guid */
    private String dataPermissionGuid;

    /** 自定义数据权限数据 */
    private String customData;

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