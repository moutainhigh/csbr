package com.csbr.qingcloud.user.scm.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 商户备案查询实体.
 *
 * @author Huanglh
 * @since 2020-07-31
 */

@Data
@ApiModel(value = "商户备案查询实体")
public class MfMerchantFilingSO extends BasePageDTO {

    /** 流程ID */
    @ApiModelProperty("流程ID")
    private String procDefId;

}
