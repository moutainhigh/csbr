package com.csbr.qingcloud.user.scm.domain.dto.organisation;

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
 * @description: 组织资料新增参数
 * @author: yio
 * @create: 2020-07-30 16:14
 **/
@Data
@ApiModel("组织资料新增参数")
public class OrganisationAddDTO {

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 组织编码 */
    @ApiModelProperty("组织编码")
    @NotBlank(message = "组织编码不能为空。")
    private String organisationCode;

    /** 组织名称 */
    @ApiModelProperty("组织名称")
    @NotBlank(message = "组织名称不能为空。")
    private String organisationName;

    /** 联系电话 */
    @ApiModelProperty("联系电话")
    private String contactTel;

    /** 省份(编号) */
    @ApiModelProperty("省份(编号)")
    private String province;

    /** 城市(编号) */
    @ApiModelProperty("城市(编号)")
    private String city;

    /** 区/县/乡(编号) */
    @ApiModelProperty("区/县/乡(编号)")
    private String district;

    /** 地点(省/市/区 文本) */
    @ApiModelProperty("地点(省/市/区 文本)")
    private String venue;

    /** 地址 */
    @ApiModelProperty("地址")
    private String address;

    /** 业务状态(Y 有效；S：停用；默认 Y) */
    @ApiModelProperty("业务状态(Y 有效；S：停用；默认 Y)")
    @Pattern(regexp = "^Y|S$", message = "状态应该为Y、S中的值。")
    private String bizState;


    /**
     * 上级组织GUID
     */
    @ApiModelProperty("上级组织GUID")
    private String parentGuid;

    /**
     * 序号
     */
    @ApiModelProperty("序号")
    private String orderNum;

}
