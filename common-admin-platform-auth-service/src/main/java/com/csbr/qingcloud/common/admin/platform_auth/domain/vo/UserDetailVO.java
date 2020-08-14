package com.csbr.qingcloud.common.admin.platform_auth.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 用户明细
 * @author: yio
 * @create: 2020-07-29 13:39
 **/
@Data
@ApiModel("获取用户明细对象")
public class UserDetailVO extends UserVO {

    /**
     * 应用端列表
     */
    @ApiModelProperty("应用端列表")
    private List<AppSideRoleVO> appSideRoleVOs;
}
