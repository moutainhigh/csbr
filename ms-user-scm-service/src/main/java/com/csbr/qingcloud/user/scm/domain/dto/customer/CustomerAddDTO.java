package com.csbr.qingcloud.user.scm.domain.dto.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-user-scm-service
 * @description: 客户资料新增参数
 * @author: yio
 * @create: 2020-08-10 15:39
 **/
@Data
@ApiModel("客户资料新增参数")
public class CustomerAddDTO {

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 客户编码 */
    @ApiModelProperty("客户编码")
    @NotBlank(message = "客户编码不能为空。")
    private String customerCode;

    /** 客户名称 */
    @ApiModelProperty("客户名称")
    @NotBlank(message = "客户名称不能为空。")
    private String customerName;

    /** 加盟门店数量 */
    @ApiModelProperty("加盟门店数量")
    private Integer affiliatedShopNum;

    /** 直营门店数量 */
    @ApiModelProperty("直营门店数量")
    private Integer directStoresNum;

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
    /** 联系人 */
    @ApiModelProperty("联系人")
    private String contacts;

    /** 联系电话 */
    @ApiModelProperty("联系电话")
    private String contactTel;


    /** 所属人员GUID */
    @ApiModelProperty("所属人员GUID")
    private String staffGuid;

    /** 终端属性 */
    @ApiModelProperty("终端属性")
    private String terminalProperty;

}
