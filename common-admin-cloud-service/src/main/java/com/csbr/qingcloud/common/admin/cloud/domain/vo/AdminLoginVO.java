package com.csbr.qingcloud.common.admin.cloud.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: common-admin-cloud-service
 * @description:
 * @author: Huanglh
 * @create: 2020-07-24 14:35
 **/
@Data
@ApiModel(value = "平台用户新增参数")
public class AdminLoginVO {
    @ApiModelProperty("用户guid")
    private String userGuid;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("角色列表")
    private List<String> roleGuidList;

    @ApiModelProperty("服务列表")
    private List<String> serviceList;
}
