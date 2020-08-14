package com.csbr.qingcloud.user.scm.domain.dto.importdata;

import lombok.Data;

/**
 * @program: common-admin-cloud-service
 * @description: 客户资料导入
 * @author: Huanglh
 * @create: 2020-08-03 09:50
 **/
@Data
public class CustomInfoImportDTO extends BaseTenantDTO {
    /** 客户编码 */
    private String customerCode;

    /** 客户名称 */
    private String customerName;

    /** 直营门店数量 */
    private Integer directStoresNum;

    /** 加盟门店数量 */
    private Integer affiliatedShopNum;

    /** 省份(编号) */
    private String province;

    /** 城市(编号) */
    private String city;

    /** 区/县/乡(编号) */
    private String district;

    /** 地址 */
    private String address;

    /** 联系人 */
    private String contacts;

    /** 联系电话 */
    private String contactTel;

    /** 终端属性 */
    private String terminalProperty;

    /** 工号 */
    private String jobNumber;

    /** 姓名 */
    private String staffName;
}
