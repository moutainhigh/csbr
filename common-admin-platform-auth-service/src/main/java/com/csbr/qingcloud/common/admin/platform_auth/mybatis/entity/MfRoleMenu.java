
package com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 角色菜单实体.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfRoleMenu extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 角色Guid */
    private String roleGuid;

    /** 菜单GUID */
    private String menuGuid;

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