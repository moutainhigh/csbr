package com.csbr.qingcloud.common.admin.cloud.data.importexport.domain.dto;

import com.csbr.qingcloud.common.admin.cloud.data.importexport.annotations.ExcelCellNum;
import lombok.Data;

/**
 * @program: common-admin-cloud-service
 * @description: 产品资料文档导入
 * @author: Huanglh
 * @create: 2020-07-31 13:52
 **/
@Data
public class ProductInfoImportDTO extends BaseTenantDTO {
    /** 商品编码 */
    @ExcelCellNum(number = 0)
    private String goodsCode;

    /** 商品名称 */
    @ExcelCellNum(number = 1)
    private String goodsName;

    /** 规格 */
    @ExcelCellNum(number = 2)
    private String goodsSpec;

    /** 计量单位 */
    @ExcelCellNum(number = 3)
    private String unitStyle;

    /** 生产厂商 */
    @ExcelCellNum(number = 4)
    private String producer;

    /** 大包装含量 */
    @ExcelCellNum(number = 5)
    private Double bigUnitQty;

    /** 单价 */
    @ExcelCellNum(number = 6)
    private Double price;
}