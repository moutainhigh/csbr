package com.csbr.qingcloud.common.admin.cloud.data.importexport.domain.dto;

import com.csbr.qingcloud.common.admin.cloud.data.importexport.annotations.ExcelCellNum;
import lombok.Data;

/**
 * @program: common-admin-cloud-service
 * @description: 销售指标导入
 * @author: Huanglh
 * @create: 2020-08-03 13:39
 **/
@Data
public class SaleIndicatorImportDTO extends BaseTenantDTO {
    /** 客户编码 */
    @ExcelCellNum(number = 0)
    private String customerCode;

    /** 客户名称 */
    @ExcelCellNum(number = 1)
    private String customerName;

    /** 终端属性 */
    @ExcelCellNum(number = 2)
    private String terminalProperty;

    /** 负责人工号 */
    @ExcelCellNum(number = 3)
    private String jobNumber;

    /** 负责人名称 */
    @ExcelCellNum(number = 4)
    private String name;

    /** 职位 */
    @ExcelCellNum(number = 5)
    private String position;

    /** 商品编码 */
    @ExcelCellNum(number = 6)
    private String goodsCode;

    /** 商品名称 */
    @ExcelCellNum(number = 7)
    private String goodsName;

    /** 销售指标 */
    @ExcelCellNum(number = 8)
    private Double salesIndicators;
}
