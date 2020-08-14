package com.csbr.qingcloud.user.scm.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 组织架构信息查询实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@ApiModel(value = "组织架构信息查询实体")
public class MfOrganisationSO extends BasePageDTO {

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;

}
