package com.csbr.qingcloud.common.admin.cloud.data.importexport.feign;

import com.csbr.cloud.common.config.FastCallFeignConfiguration;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.domain.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @program: common-admin-cloud-service
 * @description: ms-user-scm-service service interface
 * @author: Huanglh
 * @create: 2020-08-03 15:38
 **/
@FeignClient(value = "ms-user-scm-service", configuration = FastCallFeignConfiguration.class)
public interface UserScmFeign {
    /**
     * 导入产品资料
     *
     * @param dtos
     * @return
     */
    @RequestMapping(value = "/import-data/goods", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonRes importGoods(@RequestBody List<ProductInfoImportDTO> dtos);

    /**
     * 导入客户资料
     *
     * @param dtos
     * @return
     */
    @RequestMapping(value = "/import-data/custom", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonRes importCustom(@RequestBody List<CustomInfoImportDTO> dtos);

    /**
     * 导入销售流向
     *
     * @param dtos
     * @return
     */
    @RequestMapping(value = "/import-data/sale-flow", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonRes importSaleFlow(@RequestBody List<SaleFlowImportDTO> dtos);

    /**
     * 导入客户销售指标
     *
     * @param dtos
     * @return
     */
    @RequestMapping(value = "/import-data/customer-sale-indicator", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonRes importCustomerSalesIndicator(@RequestBody List<SaleIndicatorImportDTO> dtos);

    /**
     * 导入人员资料
     *
     * @param dtos
     * @return
     */
    @RequestMapping(value = "/import-data/staff", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonRes importStaff(@RequestBody List<StaffInfoImportDTO> dtos);

    /**
     * 导入人员从属关系资料
     *
     * @param dtos
     * @return
     */
    @RequestMapping(value = "/import-data/staff-relation", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonRes importStaffRelation(@RequestBody List<StaffRelationImportDTO> dtos);
}
