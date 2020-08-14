package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 用户角色查询实体.
 *
 * @author Huanglh
 * @since 2020-07-27
 */

@Data
@ApiModel(value = "用户角色查询实体")
public class MfUserRoleSO extends BasePageDTO {

    /** 角色GUID */
    @ApiModelProperty("角色GUID")
    private String roleGuid;

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;

}
