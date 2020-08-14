package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @program: auth
 * @description: 菜单新增参数
 * @author: yio
 * @create: 2020-07-13 16:47
 **/
@Data
@ApiModel("菜单新增参数")
public class MenuAddDTO {


    /** 上级菜单Guid */
    @ApiModelProperty("上级菜单Guid")
    private String parentGuid;

    /** 菜单名称 */
    @ApiModelProperty("菜单名称")
    @NotBlank(message = "菜单名称不能为空。")
    private String menuName;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

    /** 应用端GUID */
    @ApiModelProperty("应用端GUID")
    @NotBlank(message = "应用端GUID不能为空。")
    private String appSideGuid;

    /** 产品GUID */
    @ApiModelProperty("产品GUID")
    private String productGuid;

    /** 路由地址 */
    @ApiModelProperty("路由地址")
    private String path;

    /** 组件路径 */
    @ApiModelProperty("组件路径")
    private String component;

    /** 请求方式(GET；POST；PUT；DELETE) */
    @ApiModelProperty("请求方式(GET；POST；PUT；DELETE)")
    @Pattern(regexp = "^GET|POST|PUT|DELETE$", message = "请求方式应该为GET、POST、PUT、DELETE中的值。")
    private String action;

    /** 图标 */
    @ApiModelProperty("图标")
    private String icon;

    /** 序号 */
    @ApiModelProperty("序号")
    private Integer orderNum;

    /** 菜单类型(M 目录；C 菜单；P 页面；F 按钮) */
    @NotBlank(message = "菜单类型不能为空。")
    @ApiModelProperty("是菜单类型(M 目录；C 菜单；P 页面；F 按钮)")
    @Pattern(regexp = "^M|C|P|F$", message = "菜单类型应该为M、C、P、F中的值。")
    private String menuType;

    /** 是否可见(Y 是；N 否 默认 Y) */
    @ApiModelProperty("是否可见(Y 是；N 否 默认 Y)")
    @Pattern(regexp = "^Y|N$", message = "是否可见应该为Y、N中的值。")
    private String visible="Y";

    /** 业务状态(Y 有效；S：停用；) */
    @ApiModelProperty("业务状态(Y 有效；S：停用)")
    @Pattern(regexp = "^Y|S$", message = "业务状态应该为Y、S中的值。")
    private String bizState="Y";

    /** 是否外链(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否外链(Y 是；N 否 默认 N) ")
    @Pattern(regexp = "^Y|N$", message = "业务状态应该为Y、N中的值。")
    private String isExternalLink="N";

}
