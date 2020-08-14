package com.csbr.qingcloud.user.scm.domain.dto.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.sql.Date;

/**
 * @program: ms-user-scm-service
 * @description: 商户审批参数
 * @author: yio
 * @create: 2020-07-30 13:34
 **/
@Data
@ApiModel("商户审批参数")
public class MerchantCheckDTO {

    /** 系统唯一标识 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 审批状态(W 待提交 A 审批中 Y 已通过 R 驳回) */
    @ApiModelProperty("审批状态(W 待提交 A 审批中 Y 已通过 R 驳回)")
    private String approvalState;

    /** 流程ID */
    @ApiModelProperty("流程ID")
    private String procDefId;

    /** 驳回原因 */
    @ApiModelProperty("驳回原因")
    private String rejectReason;
    /** 最后审批时间 */
    @ApiModelProperty("最后审批时间")
    private Date lastApprovalTime;

    /** 最后审批人 */
    @ApiModelProperty("最后审批人")
    private String lastApprover;

    /** 最后审批人GUID */
    @ApiModelProperty("最后审批人GUID")
    private String lastApproverGuid;
}
