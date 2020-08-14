package com.csbr.qingcloud.user.scm.domain.dto.importdata;

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
    private String jobNumber;

    /** 姓名 */
    private String staffName;

    /** 上级人员工号 */
    private String leaderJobNumber;

    /** 上级人员姓名 */
    private String leaderStaffName;
}
