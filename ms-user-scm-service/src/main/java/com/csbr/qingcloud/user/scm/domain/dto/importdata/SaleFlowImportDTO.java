package com.csbr.qingcloud.user.scm.domain.dto.importdata;

import lombok.Data;

import java.sql.Date;

/**
 * @program: common-admin-cloud-service
 * @description: 销售流向导入
 * @author: Huanglh
 * @create: 2020-08-03 12:21
 **/
@Data
public class SaleFlowImportDTO extends BaseTenantDTO {
    /** 销售日期 */
    private Date salesDate;

    /** 公司名称 */
    private String corpName;

    /** 购入客户名称 */
    private String customerName;

    /** 终端属性 */
    private String terminalProperty;

    /** 商品名称 */
    private String goodsName;

    /** 规格 */
    private String goodsSpec;

    /** 数量 */
    private Double qty;

    /** 核算单价 */
    private Double accountingPrice;

    /** 出库单价 */
    private Double outPrice;

    /** 核算金额 */
    private Double accountingAmount;

    /** 出库金额 */
    private Double outAmount;

    /** 批号 */
    private String lot;

    /** 省份(编号) */
    private String province;

    /** 城市(编号) */
    private String city;

    /** 区/县/乡(编号) */
    private String district;

    /** 所属人员工号 */
    private String staffJobNumber;

    /** 所属人员姓名 */
    private String staffName;

    /** 所属经理工号 */
    private String managerJobNumber;

    /** 所属经理姓名 */
    private String managerName;
}
