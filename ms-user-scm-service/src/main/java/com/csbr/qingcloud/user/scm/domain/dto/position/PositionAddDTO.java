package com.csbr.qingcloud.user.scm.domain.dto.position;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 岗位新增参数
 * @author: yio
 * @create: 2020-07-31 10:48
 **/
@Data
@ApiModel("岗位新增参数")
public class PositionAddDTO {

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 岗位 */
    @ApiModelProperty("岗位")
    @NotBlank(message = "岗位名称不能为空。")
    private String positionName;

    /** 顺序 */
    @ApiModelProperty("顺序")
    private Integer orderNum;


    /** 业务状态(Y 有效；S：停用；默认 Y) */
    @ApiModelProperty("业务状态(Y 有效；S：停用；默认 Y)")
    @Pattern(regexp = "^Y|S$", message = "状态应该为Y、S中的值。")
    private String bizState="Y";
}
