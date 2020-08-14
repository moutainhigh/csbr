package com.csbr.qingcloud.common.admin.cloud.domain.dto.platform;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: auth
 * @description: 平台信息修改参数
 * @author: yio
 * @create: 2020-07-09 15:46
 **/
@Data
@ApiModel(value = "平台资料修改参数")
public class PlatformUpdateDTO extends  PlatformAddDTO{

    /** 更新的条件 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;

}
