package com.csbr.qingcloud.user.scm.domain.dto.position;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-user-scm-service
 * @description: 岗位查询参数
 * @author: yio
 * @create: 2020-07-31 10:49
 **/
@Data
@ApiModel("岗位查询参数")
public class PositionQueryDTO extends BasePageDTO {
    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 岗位 */
    @ApiModelProperty("岗位")
    private String positionName;
}


