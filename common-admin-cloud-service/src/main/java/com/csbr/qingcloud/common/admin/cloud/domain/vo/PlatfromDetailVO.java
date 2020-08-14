package com.csbr.qingcloud.common.admin.cloud.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: common-admin-cloud-service
 * @description: 平台明细信息
 * @author: yio
 * @create: 2020-07-24 09:49
 **/
@Data
@ApiModel("平台明细信息对象")
public class PlatfromDetailVO extends PlatformVO{
    /** 用户账号 */
    @ApiModelProperty("用户账号")
    private String logonUser;


}
