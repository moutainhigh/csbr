package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: auth
 * @description: 菜单修改参数类
 * @author: yio
 * @create: 2020-07-13 17:56
 **/
@Data
@ApiModel("菜单修改参数")
public class MenuUpdateDTO extends MenuAddDTO{

    /** 更新的条件 */
    @ApiModelProperty("系统唯一标识")
    @NotBlank(message = "Guid不能为空。")
    private String guid;
}
