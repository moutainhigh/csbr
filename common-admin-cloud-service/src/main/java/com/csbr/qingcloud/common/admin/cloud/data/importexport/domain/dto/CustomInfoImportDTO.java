package com.csbr.qingcloud.common.admin.cloud.data.importexport.domain.dto;

import com.csbr.qingcloud.common.admin.cloud.data.importexport.annotations.ExcelCellNum;
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
    @ExcelCellNum(number = 0)
    private String customerCode;

    /** 客户名称 */
    @ExcelCellNum(number = 1)
    private String customerName;

    /** 直营门店数量 */
    @ExcelCellNum(number = 2)
    private Integer directStoresNum;

    /** 加盟门店数量 */
    @ExcelCellNum(number = 3)
    private Integer affiliatedShopNum;

    /** 省份(编号) */
    @ExcelCellNum(number = 4)
    private String province;

    /** 城市(编号) */
    @ExcelCellNum(number = 5)
    private String city;

    /** 区/县/乡(编号) */
    @ExcelCellNum(number = 6)
    private String district;

    /** 地址 */
    @ExcelCellNum(number = 7)
    private String address;

    /** 联系人 */
    @ExcelCellNum(number = 8)
    private String contacts;

    /** 联系电话 */
    @ExcelCellNum(number = 9)
    private String contactTel;

    /** 终端属性 */
    @ExcelCellNum(number = 10)
    private String terminalProperty;

    /** 工号 */
    @ExcelCellNum(number = 11)
    private String jobNumber;

    /** 姓名 */
    @ExcelCellNum(number = 12)
    private String staffName;
}
