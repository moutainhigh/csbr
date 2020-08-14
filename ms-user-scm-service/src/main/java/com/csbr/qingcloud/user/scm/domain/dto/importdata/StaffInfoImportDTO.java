package com.csbr.qingcloud.user.scm.domain.dto.importdata;

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
    private String jobNumber;

    /** 姓名 */
    private String staffName;

    /** 省份(编号) */
    private String province;

    /** 城市(编号) */
    private String city;

    /** 区/县/乡(编号) */
    private String district;

    /** 手机号 */
    private String mobileNo;

    /** 性别(M 男；F 女) */
    private String sex;

    /** 身份证 */
    private String idCode;

    /** 地址 */
    private String address;

    /** 职位 */
    private String positionName;

    /** 用户Guid */
    private String userGuid;
}
