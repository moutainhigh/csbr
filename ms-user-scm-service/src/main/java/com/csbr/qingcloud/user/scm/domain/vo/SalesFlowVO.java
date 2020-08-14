package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 销售流向显示对象
 * @author: yio
 * @create: 2020-08-07 14:41
 **/
@Data
@ApiModel("销售流向显示对象")
public class SalesFlowVO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    @TableId
    private String guid;

    /** 核算金额 */
    @ApiModelProperty("核算金额")
    private Double accountingAmount;

    /** 核算单价 */
    @ApiModelProperty("核算单价")
    private Double accountingPrice;

    /** 公司名称 */
    @ApiModelProperty("公司名称")
    private String corpName;

    /** 购入客户GUID */
    @ApiModelProperty("购入客户GUID")
    private String customerGuid;

    /** 购入客户编码 */
    @ApiModelProperty("购入客户编码")
    private String customerCode;

    /** 购入客户名称 */
    @ApiModelProperty("购入客户名称")
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
    /** 地点(省/市/区 文本) */
    @ApiModelProperty("地点(省/市/区 文本)")
    private String venue;

    /** 商品编码 */
    @ApiModelProperty("商品编码")
    private String goodsCode;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /** 规格 */
    private String goodsSpec;


    /** 批号 */
    @ApiModelProperty("批号")
    private String lot;

    /** 所属经理GUID */
    @ApiModelProperty("所属经理GUID")
    private String managerGuid;

    /** 所属经理工号 */
    @ApiModelProperty("所属经理工号")
    private String managerJobNumber;

    /** 所属经理姓名 */
    @ApiModelProperty("所属经理姓名")
    private String managerName;

    /** 所属组织GUID */
    @ApiModelProperty("所属组织GUID")
    private String organisationGuid;

    /** 所属组织编码 */
    @ApiModelProperty("所属组织编码")
    private String organisationCode;

    /** 所属组织名称 */
    @ApiModelProperty("所属组织名称")
    private String organisationName;

    /** 出库金额 */
    private Double outAmount;

    /** 出库单价 */
    @ApiModelProperty("出库单价")
    private Double outPrice;


    /** 数量 */
    @ApiModelProperty("数量")
    private Double qty;

    /** 销售日期 */
    @ApiModelProperty("销售日期")
    private Timestamp salesDate;

    /** 所属人员GUID */
    @ApiModelProperty("所属人员GUID")
    private String staffGuid;

    /** 所属人员工号 */
    @ApiModelProperty("所属人员工号")
    private String staffJobNumber;

    /** 所属人员姓名 */
    @ApiModelProperty("所属人员姓名")
    private String staffName;

    /** 终端属性 */
    @ApiModelProperty("终端属性")
    private String terminalProperty;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /** 修改姓名 */
    @ApiModelProperty("修改姓名")
    private String updateUserName;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createUserId;

    /** 创建人姓名 */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;
}
