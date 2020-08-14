
package com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 用户角色实体.
 *
 * @author Huanglh
 * @since 2020-07-27
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfUserRole extends BaseDO {
    
    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 角色GUID */
    private String roleGuid;
    
    /** 企业GUID */
    private String tenantGuid;
    
    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

    /** 用户GUID */
    private String userGuid;
    
}