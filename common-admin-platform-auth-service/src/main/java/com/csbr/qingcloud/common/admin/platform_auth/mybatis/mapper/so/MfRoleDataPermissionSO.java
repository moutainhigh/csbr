package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 角色数据权限查询实体.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Data
@ApiModel(value = "角色数据权限查询实体")
public class MfRoleDataPermissionSO extends BasePageDTO {

    /** 角色GUID */
    @ApiModelProperty("角色GUID")
    private String roleGGuid;

    /** 数据权限Guid */
    @ApiModelProperty("数据权限Guid")
    private String dataPermissionGuGuid;

}
