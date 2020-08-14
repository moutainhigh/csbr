package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 移除用户的参数
 * @author: yio
 * @create: 2020-08-03 14:03
 **/
@Data
@ApiModel("移除用户的参数")
public class UserRemoveDTO
{
    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;

    /** 移除用户的GUID列表 */
    @ApiModelProperty("移除用户的GUID列表")
    private List<String> guids;
}
