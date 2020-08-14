
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
 * 产品信息实体.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfProduct extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 产品名 */
    private String productName;

    /** 负责人 */
    private String mananger;

    /** 负责人电话 */
    private String manangerContactTel;

    /** 运营方 */
    private String operationParty;

    /** 运营联系人 */
    private String operationContacts;

    /** 运营方联系电话 */
    private String operationContactTel;

    /** 上线时间 */
    private Timestamp upTime;

    /** 平台GUID */
    private String platformGuid;

    
    /** 业务状态(Y 有效；S：停用；E：作废 默认 Y) */
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