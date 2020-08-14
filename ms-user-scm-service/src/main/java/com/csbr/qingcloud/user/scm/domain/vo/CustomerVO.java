package com.csbr.qingcloud.user.scm.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 客户显示对象
 * @author: yio
 * @create: 2020-08-06 15:37
 **/
@Data
@ApiModel("客户显示对象")
public class CustomerVO {
    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 客户编码 */
    @ApiModelProperty("客户编码")
    private String customerCode;

    /** 客户名称 */
    @ApiModelProperty("客户名称")
    private String customerName;

    /** 省份(编号) */
    @ApiModelProperty("省份(编号)")
    private String province;

    /** 城市(编号) */
    @ApiModelProperty("城市(编号)")
    private String city;

    /** 区/县/乡(编号) */
    @ApiModelProperty("区/县/乡(编号)")
    private String district;

    /** 地址 */
    @ApiModelProperty("地址")
    private String address;

    /** 加盟门店数量 */
    @ApiModelProperty("加盟门店数量")
    private Integer affiliatedShopNum;

    /** 直营门店数量 */
    @ApiModelProperty("直营门店数量")
    private Integer directStoresNum;

    /** 所属人员GUID */
    @ApiModelProperty("所属人员GUID")
    private String staffGuid;

    /** 所属人员工号 */
    @ApiModelProperty("所属人员工号")
    private String jobNumber;

    /** 所属人员姓名 */
    @ApiModelProperty("所属人员姓名")
    private String staffName;

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;

    /** 终端属性 */
    @ApiModelProperty("终端属性")
    private String terminalProperty;

    /** 地点(省/市/区 文本) */
    @ApiModelProperty("地点(省/市/区 文本)")
    private String venue;

    /** 联系人 */
    @ApiModelProperty("联系人")
    private String contacts;

    /** 联系电话 */
    @ApiModelProperty("联系电话")
    private String contactTel;

    /** 是否删除(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否删除(Y 是；N 否 默认 N)")
    private String isDeleted;


    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createUserId;

    /** 创建人姓名 */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /** 修改姓名 */
    @ApiModelProperty("修改姓名")
    private String updateUserName;
}
