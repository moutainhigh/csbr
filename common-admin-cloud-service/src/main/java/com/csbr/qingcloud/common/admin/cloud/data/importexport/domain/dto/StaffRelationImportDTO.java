package com.csbr.qingcloud.common.admin.cloud.data.importexport.domain.dto;

import com.csbr.qingcloud.common.admin.cloud.data.importexport.annotations.ExcelCellNum;
import lombok.Data;

/**
 * @program: common-admin-cloud-service
 * @description: 人员从属关系资料导入
 * @author: Huanglh
 * @create: 2020-08-03 10:39
 **/
@Data
public class StaffRelationImportDTO extends BaseTenantDTO {
    /** 工号 */
    @ExcelCellNum(number = 0)
    private String jobNumber;

    /** 姓名 */
    @ExcelCellNum(number = 1)
    private String staffName;

    /** 上级人员工号 */
    @ExcelCellNum(number = 2)
    private String leaderJobNumber;

    /** 上级人员姓名 */
    @ExcelCellNum(number = 3)
    private String leaderStaffName;
}
