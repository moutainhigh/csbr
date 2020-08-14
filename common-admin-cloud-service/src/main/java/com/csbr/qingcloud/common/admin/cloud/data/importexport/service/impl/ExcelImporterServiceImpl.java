package com.csbr.qingcloud.common.admin.cloud.data.importexport.service.impl;

import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CommonUtil;
import com.csbr.cloud.distributelock.annotation.DistributedLock;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.domain.dto.*;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.feign.UserScmFeign;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.service.ExcelImporterService;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.util.ImportExportUtil;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.util.ImportExportUtilFactory;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @program: common-admin-cloud-service
 * @description:
 * @author: Huanglh
 * @create: 2020-07-31 14:08
 **/
@Service
public class ExcelImporterServiceImpl implements ExcelImporterService {
    @Autowired
    private ImportExportUtilFactory ieFactory;
    @Autowired
    private UserScmFeign userScmFeign;

    @Override
    @DistributedLock(lockName = "import_goods")
    @GlobalTransactional
    public CommonRes importProductInfo(MultipartFile file, String tenantGuid) {
        return userScmFeign.importGoods(this.getDtoList(file, ProductInfoImportDTO.class, tenantGuid));
    }

    /**
     * 导入客户资料
     *
     * @param file
     * @return
     */
    @Override
    @DistributedLock(lockName = "import_custom")
    public CommonRes importCustomInfo(MultipartFile file, String tenantGuid) {
        return userScmFeign.importCustom(this.getDtoList(file, CustomInfoImportDTO.class, tenantGuid));
    }

    /**
     * 导入人员从属关系资料
     *
     * @param file
     * @return
     */
    @Override
    @DistributedLock(lockName = "import_staff_relation")
    public CommonRes importStaffRelation(MultipartFile file, String tenantGuid) {
        return userScmFeign.importStaffRelation(this.getDtoList(file, StaffRelationImportDTO.class, tenantGuid));
    }

    /**
     * 导入人员资料
     *
     * @param file
     * @return
     */
    @Override
    @DistributedLock(lockName = "import_staff")
    public CommonRes importStaffInfo(MultipartFile file, String tenantGuid) {
        return userScmFeign.importStaff(this.getDtoList(file, StaffInfoImportDTO.class, tenantGuid));
    }

    /**
     * 导入销售流向
     *
     * @param file
     * @return
     */
    @Override
    @DistributedLock(lockName = "import_sale_flow")
    public CommonRes importSaleFlow(MultipartFile file, String tenantGuid) {
        return userScmFeign.importSaleFlow(this.getDtoList(file, SaleFlowImportDTO.class, tenantGuid));
    }

    /**
     * 导入销售指标
     *
     * @param file
     * @return
     */
    @Override
    @DistributedLock(lockName = "import_sale_indicator")
    public CommonRes importSaleIndicator(MultipartFile file, String tenantGuid) {
        return userScmFeign.importCustomerSalesIndicator(this.getDtoList(file, SaleIndicatorImportDTO.class, tenantGuid));
    }

    // region 私有方法

    /**
     * 获取DTO列表
     *
     * @param file
     * @param clazz
     * @param <T>
     * @return
     */
    private <T extends BaseTenantDTO> List<T> getDtoList(MultipartFile file, Class<T> clazz, String tenantGuid) {
        // 获取文件后缀名
        String suffix = CommonUtil.getSuffix(file.getOriginalFilename());

        // 从工厂获取导入导出工具
        ImportExportUtil ieUtil = ieFactory.getExcelUtil(suffix);

        // 导入数据到DTO
        List<T> importDTOS;
        try {
            importDTOS = ieUtil.importDataToDto(file.getInputStream(), clazz, 1);

            for (T importDTO : importDTOS) {
                importDTO.setTenantGuid(tenantGuid);
            }
        } catch (IOException e) {
            throw new CsbrSystemException(SystemError.IMPORT_ERROR, e.getMessage());
        }

        return importDTOS;
    }
    // endregion
}
