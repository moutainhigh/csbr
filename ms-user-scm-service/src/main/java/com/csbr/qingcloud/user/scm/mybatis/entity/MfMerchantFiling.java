
package com.csbr.qingcloud.user.scm.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 商户备案实体.
 *
 * @author Huanglh
 * @since 2020-07-31
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfMerchantFiling extends BaseDO {
    
    /** 审批状态(A 审批中 Y 已通过 R 驳回) */
    private String approvalState;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 最后审批时间 */
    private Timestamp lastApprovalTime;

    /** 最后审批人 */
    private String lastApprover;

    /** 最后审批人GUID */
    private String lastApproverGuid;
    
    /** 商户GUID */
    private String merchantGuid;
    
    /** 流程ID */
    private String procDefId;

    /** 任务id **/
    private String taskId;

    /** 驳回原因 */
    private String rejectReason;

    /** 企业GUID */
    private String tenantGuid;
    
    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

}