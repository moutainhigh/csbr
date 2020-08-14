package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.datapermission;

import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: common-admin-platform-auth-service
 * @description: 数据权限查询参数
 * @author: yio
 * @create: 2020-07-23 16:02
 **/
@Data
@ApiModel("数据权限查询参数")
public class DataPermissionQueryDTO extends BasePageDTO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 应用端名称 */
    @ApiModelProperty("数据权限名称")
    @LikeQuery
    private String dataPermissionName;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;
}
