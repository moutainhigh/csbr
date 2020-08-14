package com.csbr.qingcloud.common.admin.cloud.data.importexport.domain.dto;

import com.csbr.qingcloud.common.admin.cloud.data.importexport.annotations.ExcelCellNum;
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
    @ExcelCellNum(number = 0)
    private Date salesDate;

    /** 公司名称 */
    @ExcelCellNum(number = 1)
    private String corpName;

    /** 购入客户名称 */
    @ExcelCellNum(number = 2)
    private String customerName;

    /** 终端属性 */
    @ExcelCellNum(number = 3)
    private String terminalProperty;

    /** 商品名称 */
    @ExcelCellNum(number = 4)
    private String goodsName;

    /** 规格 */
    @ExcelCellNum(number = 5)
    private String goodsSpec;

    /** 数量 */
    @ExcelCellNum(number = 6)
    private Double qty;

    /** 核算单价 */
    @ExcelCellNum(number = 7)
    private Double accountingPrice;

    /** 出库单价 */
    @ExcelCellNum(number = 8)
    private Double outPrice;

    /** 核算金额 */
    @ExcelCellNum(number = 9)
    private Double accountingAmount;

    /** 出库金额 */
    @ExcelCellNum(number = 10)
    private Double outAmount;

    /** 批号 */
    @ExcelCellNum(number = 11)
    private String lot;

    /** 省份(编号) */
    @ExcelCellNum(number = 12)
    private String province;

    /** 城市(编号) */
    @ExcelCellNum(number = 13)
    private String city;

    /** 区/县/乡(编号) */
    @ExcelCellNum(number = 14)
    private String district;

    /** 所属人员工号 */
    @ExcelCellNum(number = 15)
    private String staffJobNumber;

    /** 所属人员姓名 */
    @ExcelCellNum(number = 16)
    private String staffName;

    /** 所属经理工号 */
    @ExcelCellNum(number = 17)
    private String managerJobNumber;

    /** 所属经理姓名 */
    @ExcelCellNum(number = 18)
    private String managerName;
}
