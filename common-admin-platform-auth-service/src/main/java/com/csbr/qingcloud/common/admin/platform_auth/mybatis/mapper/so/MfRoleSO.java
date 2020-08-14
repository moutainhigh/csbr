package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 角色查询实体.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Data
@ApiModel(value = "角色查询实体")
public class MfRoleSO extends BasePageDTO {

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGGuid;

    /** 应用端GUID */
    @ApiModelProperty("应用端GUID")
    private String appSideGuGuid;

    /** 角色名称 */
    @ApiModelProperty("角色名称")
    private String roleName;

}
