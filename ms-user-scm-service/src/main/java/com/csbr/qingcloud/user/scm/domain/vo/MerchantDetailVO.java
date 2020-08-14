package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.qingcloud.user.scm.domain.dto.merchant.MerchantAddDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.sql.Date;

/**
 * @program: ms-user-scm-service
 * @description: 商户显示对象明细
 * @author: yio
 * @create: 2020-07-30 10:54
 **/
@Data
@ApiModel("商户显示对象明细")
public class MerchantDetailVO extends MerchantVO {

    /**
     * 统一社会信用代码
     */
    @ApiModelProperty("统一社会信用代码")
    private String companySocialId;

    /**
     * 性别(M 男；F 女)
     */
    @ApiModelProperty("性别(M 男；F 女)")
    @Pattern(regexp = "^M|F$", message = "性别应该为M、F中的值。")
    private String sex;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String idCode;

    /**
     * 身份证照片({front:正面,back:背面})
     */
    @ApiModelProperty("身份证照片({front:正面,back:背面})")
    private String idPictContent;


    /**
     * 证照编号
     */
    @ApiModelProperty("证照编号")
    private String licenseNum;

    /**
     * 证照照片({Original：{name：不含路径有文件后缀, path：纯地址,不含文件名}Thumbnail：{name：不含路径有文件后缀,path：纯地址,不含文件名}mobile：{name：不含路径有文件后缀,path：纯地址,不含文件名} })
     */
    @ApiModelProperty("证照照片({Original：{name：不含路径有文件后缀, path：纯地址,不含文件名}Thumbnail：{name：不含路径有文件后缀,path：纯地址,不含文件名}mobile：{name：不含路径有文件后缀,path：纯地址,不含文件名} })")
    private String pictContent;

    /**
     * 证照开始日期
     */
    @ApiModelProperty("证照开始日期（yyyy-MM-dd）")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    /**
     * 证照结束日期
     */
    @ApiModelProperty("证照结束日期（yyyy-MM-dd）")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 审批状态(W 待提交 A 审批中 Y 已通过 R 驳回)
     */
    private String approvalState;

    /**
     * 最后审批时间
     */
    private Timestamp lastApprovalTime;

    /**
     * 最后审批人
     */
    private String lastApprover;

    /**
     * 最后审批人GUID
     */
    private String lastApproverGuid;

    /**
     * 流程ID
     */
    private String procDefId;


    /**
     * 驳回原因
     */
    private String rejectReason;

    /**
     * 企业分类(00 平台；0101 医药生产；010203 COS企业；0901 个体商户 对应mf_enterprise_cate表中的值)
     */
    private String enterpriseCate;

    /**
     * 地址
     */
    private String address;

    /**
     * 修改时间
     */
    private Timestamp updateTime;

    /**
     * 修改人
     */
    private String updateUserId;

    /**
     * 修改姓名
     */
    private String updateUserName;
    /**
     * 创建人
     */
    private String createUserId;

    private String isDeleted;

}
