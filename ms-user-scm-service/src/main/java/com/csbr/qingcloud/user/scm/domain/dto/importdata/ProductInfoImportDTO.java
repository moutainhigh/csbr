package com.csbr.qingcloud.user.scm.domain.dto.importdata;

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
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 规格 */
    private String goodsSpec;

    /** 计量单位 */
    private String unitStyle;

    /** 生产厂商 */
    private String producer;

    /** 大包装含量 */
    private Double bigUnitQty;

    /** 单价 */
    private Double price;
}