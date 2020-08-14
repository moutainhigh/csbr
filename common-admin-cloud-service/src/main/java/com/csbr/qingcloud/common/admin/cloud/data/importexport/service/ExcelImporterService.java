package com.csbr.qingcloud.common.admin.cloud.data.importexport.service;

import com.csbr.cloud.common.response.CommonRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: common-admin-cloud-service
 * @description: excel files import data
 * @author: Huanglh
 * @create: 2020-07-31 13:44
 **/
public interface ExcelImporterService {
    /**
     * 导入产品资料
     *
     * @param file
     * @return
     */
    CommonRes importProductInfo(MultipartFile file, String tenantGuid);

    /**
     * 导入客户资料
     *
     * @param file
     * @return
     */
    CommonRes importCustomInfo(MultipartFile file, String tenantGuid);

    /**
     * 导入人员从属关系资料
     *
     * @param file
     * @return
     */
    CommonRes importStaffRelation(MultipartFile file, String tenantGuid);

    /**
     * 导入人员资料
     *
     * @param file
     * @return
     */
    CommonRes importStaffInfo(MultipartFile file, String tenantGuid);

    /**
     * 导入销售流向
     *
     * @param file
     * @return
     */
    CommonRes importSaleFlow(MultipartFile file, String tenantGuid);

    /**
     * 导入销售指标
     *
     * @param file
     * @return
     */
    CommonRes importSaleIndicator(MultipartFile file, String tenantGuid);
}
