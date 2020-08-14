package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu;

import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: auth
 * @description: 菜单查询参数类
 * @author: yio
 * @create: 2020-07-13 17:56
 **/
@Data
@ApiModel("菜单查询参数")
public class MenuQueryDTO  {

    @ApiModelProperty("系统唯一标识")
    private String guid;

    @ApiModelProperty("菜单名称")
    @LikeQuery
    private String menuName;

    @ApiModelProperty("上级菜单Guid")
    private String parentGuid;


    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

    /** 应用端GUID */
    @ApiModelProperty("应用端GUID")
    @NotBlank(message = "应用端GUID不能为空。")
    private String appSideGuid;

    /** 产品GUID */
    @ApiModelProperty("产品GUID")
    @NotBlank(message = "产品GUID不能为空。")
    private String productGuid;
}
