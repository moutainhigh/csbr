
package com.csbr.qingcloud.user.scm.mybatis.data.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 举证窜货申请实体.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class TrFleeingGoodsApply extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 企业GUID */
    private String tenantGuid;
    
    /** 审批编号 */
    private String applyCode;

    /** 申请人ID */
    private String staffGuid;
    
    /** 申请人(发起人) */
    private String name;

    /** 申请时间 */
    private Timestamp applyTime;

    /** 岗位 */
    private String positionGuid;
    
    /** 组织GUID */
    private String organisationGuid;
    
    /** 省份(编号) */
    private String province;

    /** 城市(编号) */
    private String city;

    /** 区/县/乡(编号) */
    private String district;

    /** 地点(省/市/区 文本) */
    private String venue;

    /** 事件概述 */
    private String eventSummary;

    /** 情况说明 */
    private String situation;

    /** 附件([{name:,path:},{name:,path:}]) */
    private String attach;

    /** 审批状态(N 初始 A 审批中 Y 已通过 R 驳回) */
    private String approvalState;

    /** 最后审批人GUID */
    private String lastApproverGuid;
    
    /** 最后审批人 */
    private String lastApprover;

    /** 最后审批时间 */
    private Timestamp lastApprovalTime;

    /** 下一个审批人GUID(流程下一个要审批的人) */
    private String nextApproverGuid;
    
    /** 下一个审批人 */
    private String nextApprover;

    /** 审批意见 */
    private String approvalSuggest;

    /** 流程ID */
    private String procDefId;

    /** 任务ID */
    private String taskId;

    /** 备注 */
    private String memo;

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

    /** 更新时间 */
    private Timestamp updateTime;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

}