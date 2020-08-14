package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 应用端新增参数
 * @author: yio
 * @create: 2020-07-21 09:27
 **/
@Data
@ApiModel("应用端新增参数")
public class AppSideAddDTO {

    /** 应用端名称 */
    @ApiModelProperty("应用端名称")
    @NotBlank(message = "应用端名称不能为空。")
    private String appSideName;

    /** 标识符 */
    @ApiModelProperty("标识符")
    private String identifier;

    /** 应用形式(01 PC；02 微信；03 APP) */
    @ApiModelProperty("应用形式")
    @Pattern(regexp = "^01|02|03$", message = "应用形式应该为01、02、03中的值。")
    private String appType;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    @NotBlank(message = "平台关键字不能为空。")
    private String platformGuid;


}
