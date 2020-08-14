package com.csbr.qingcloud.user.scm.mybatis.data.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 客户资料查询实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@ApiModel(value = "客户资料查询实体")
public class MfCustomerSO extends BasePageDTO {

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
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
