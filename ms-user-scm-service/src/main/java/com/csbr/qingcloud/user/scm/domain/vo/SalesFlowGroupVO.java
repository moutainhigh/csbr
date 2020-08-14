package com.csbr.qingcloud.user.scm.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 销售流向分组显示对象
 * @author: yio
 * @create: 2020-08-13 09:56
 **/
@Data
@ApiModel("销售流向分组显示对象")
public class SalesFlowGroupVO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 销售日期 */
    @ApiModelProperty("销售日期")
    private Timestamp salesDate;

    /** 购入客户编码 */
    private String customerCode;

    /** 购入客户名称 */
    private String customerName;

    /** 公司名称 */
    private String corpName;

    /** 所属经理工号 */
    private String managerJobNumber;

    /** 所属经理姓名 */
    private String managerName;

    /** 所属人员工号 */
    private String staffJobNumber;

    /** 所属人员姓名 */
    private String staffName;

    /** 省份(编号) */
    private String province;

    /** 城市(编号) */
    private String city;

    /** 区/县/乡(编号) */
    private String district;

    /** 地点(省/市/区 文本) */
    private String venue;

    /** 品规数 */
    @ApiModelProperty("品规数")
    private Integer goodsSpecNum;

    /** 批号数 */
    @ApiModelProperty("批号数")
    private Integer lotNum;

    /** 总数量 */
    @ApiModelProperty("总数量")
    private Double totalQty;

    /** 核算总金额 */
    @ApiModelProperty("核算总金额")
    private Double totalAccountingAmount;

    /** 销售流向商品集合 */
    private List<SalesFlowGoodsVO> salesFlowGoodsVOs;


}
