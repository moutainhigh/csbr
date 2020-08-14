
package com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 数据权限实体.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfDataPermission extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 平台GUID */
    private String platformGuid;

    
    /** 数据权限名称 */
    private String dataPermissionName;

    /** 权限类型(F 固定；C 自定义) */
    private String dataPermissionType;

    /** 数据库对象名 */
    private String schemaName;

    /** 字段 */
    private String field;

    /** 参数名(会话中数据权限实体参数名，登录时给当前参数赋值) */
    private String paramName;

    /** SQL脚本 */
    private String sqlScript;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改时间 */
    private Timestamp updateTime;

}