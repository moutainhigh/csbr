package com.csbr.qingcloud.user.scm.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 平台会员企业证照查询实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@ApiModel(value = "平台会员企业证照查询实体")
public class MfTenantLicSO extends BasePageDTO {

    /** 会员GUID */
    @ApiModelProperty("会员GUID")
    private String tenantGuid;

}
