package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 角色菜单查询实体.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Data
@ApiModel(value = "角色菜单查询实体")
public class MfRoleMenuSO extends BasePageDTO {

    /** 角色Guid */
    @ApiModelProperty("角色Guid")
    private String roleGGuid;

    /** 菜单GUID */
    @ApiModelProperty("菜单GUID")
    private String menuGGuid;

}
