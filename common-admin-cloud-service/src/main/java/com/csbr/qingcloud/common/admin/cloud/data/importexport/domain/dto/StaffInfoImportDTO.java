package com.csbr.qingcloud.common.admin.cloud.data.importexport.domain.dto;

import com.csbr.qingcloud.common.admin.cloud.data.importexport.annotations.ExcelCellNum;
import lombok.Data;

/**
 * @program: common-admin-cloud-service
 * @description: 人员资料导入
 * @author: Huanglh
 * @create: 2020-08-03 10:45
 **/
@Data
public class StaffInfoImportDTO extends BaseTenantDTO {
    /** 工号 */
    @ExcelCellNum(number = 0)
    private String jobNumber;

    /** 姓名 */
    @ExcelCellNum(number = 1)
    private String staffName;

    /** 省份(编号) */
    @ExcelCellNum(number = 2)
    private String province;

    /** 城市(编号) */
    @ExcelCellNum(number = 3)
    private String city;

    /** 区/县/乡(编号) */
    @ExcelCellNum(number = 4)
    private String district;

    /** 手机号 */
    @ExcelCellNum(number = 5)
    private String mobileNo;

    /** 性别(M 男；F 女) */
    @ExcelCellNum(number = 6)
    private String sex;

    /** 身份证 */
    @ExcelCellNum(number = 8)
    private String idCode;

    /** 地址 */
    @ExcelCellNum(number = 9)
    private String address;

    /** 职位 */
    @ExcelCellNum(number = 10)
    private String positionName;
}
