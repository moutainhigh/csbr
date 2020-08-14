package com.csbr.qingcloud.user.scm.domain.dto.customer;

import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-user-scm-service
 * @description: 客户查询参数
 * @author: yio
 * @create: 2020-08-06 13:43
 **/
@Data
@ApiModel("客户查询参数")
public class CustomerQueryDTO extends BasePageDTO {

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 客户编码 */
    @ApiModelProperty("客户编码")
    @LikeQuery
    private String customerCode;

    /** 客户名称 */
    @ApiModelProperty("客户名称")
    @LikeQuery
    private String customerName;
}
