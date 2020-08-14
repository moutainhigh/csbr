package com.csbr.qingcloud.user.scm.domain.dto.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 个人商户修改参数
 * @author: yio
 * @create: 2020-07-30 09:51
 **/
@Data
@ApiModel("商户新增参数")
public class MerchantUpdateDTO extends MerchantAddDTO {

    /** 系统唯一标识 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    @ApiModelProperty("审批状态")
    /** 审批状态(W 待提交 A 审批中 Y 已通过 R 驳回) */
    @Pattern(regexp = "^Y|A|R$", message = "审批状态应该为Y、A、R中的值。")
    private String approvalState;

    /** 流程id **/
    @ApiModelProperty("流程id")
    private String procDefId;

    /** 任务id **/
    @ApiModelProperty("任务id")
    private String taskId;





}
