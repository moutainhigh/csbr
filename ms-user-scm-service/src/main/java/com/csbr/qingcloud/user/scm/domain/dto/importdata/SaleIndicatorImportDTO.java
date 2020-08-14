package com.csbr.qingcloud.user.scm.domain.dto.importdata;

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
    private String customerCode;

    /** 客户名称 */
    private String customerName;

    /** 终端属性 */
    private String terminalProperty;

    /** 负责人工号 */
    private String jobNumber;

    /** 负责人名称 */
    private String name;

    /** 职位 */
    private String position;

    /** 商品编码 */
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 销售指标 */
    private Double salesIndicators;
}
