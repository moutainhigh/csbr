package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.qingcloud.user.scm.domain.dto.importdata.*;

import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 导入数据服务
 * @author: Huanglh
 * @create: 2020-08-03 14:58
 **/
public interface ImportDataService {
    // region user部分

    /**
     * 导入人员资料
     *
     * @param dtos
     * @return
     */
    CommonRes importStaff(List<StaffInfoImportDTO> dtos);

    /**
     * 导入人员从属关系资料
     *
     * @param dtos
     * @return
     */
    CommonRes importStaffRelation(List<StaffRelationImportDTO> dtos);
    // endregion

    // region data部分(后期会拆分出去)

    /**
     * 导入产品资料
     *
     * @param dtos
     * @return
     */
    CommonRes importGoods(List<ProductInfoImportDTO> dtos);

    /**
     * 导入客户资料
     *
     * @param dtos
     * @return
     */
    CommonRes importCustomer(List<CustomInfoImportDTO> dtos);

    /**
     * 导入销售流向
     *
     * @param dtos
     * @return
     */
    CommonRes importSaleFlow(List<SaleFlowImportDTO> dtos);

    /**
     * 导入客户销售指标
     *
     * @param dtos
     * @return
     */
    CommonRes importCustomerSalesIndicator(List<SaleIndicatorImportDTO> dtos);
    // endregion
}
