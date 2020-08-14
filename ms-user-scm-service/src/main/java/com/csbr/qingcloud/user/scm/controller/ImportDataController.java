package com.csbr.qingcloud.user.scm.controller;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.qingcloud.user.scm.domain.dto.importdata.*;
import com.csbr.qingcloud.user.scm.service.ImportDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 导入数据控制器
 * @author: Huanglh
 * @create: 2020-08-03 14:53
 **/
@RestController
@RequestMapping("/import-data")
@Api(tags = "导入数据控制器")
public class ImportDataController {

    @Autowired
    private ImportDataService importDataService;

    // region user部分
    @PostMapping("/staff")
    @ApiOperation("导入人员资料")
    public CommonRes importStaff(@RequestBody List<StaffInfoImportDTO> dtos) {
        return importDataService.importStaff(dtos);
    }

    @PostMapping("/staff-relation")
    @ApiOperation("导入人员从属关系资料")
    public CommonRes importStaffRelation(@RequestBody List<StaffRelationImportDTO> dtos) {
        return importDataService.importStaffRelation(dtos);
    }
    // endregion

    // region data部分(后期会拆分出去)
    @PostMapping("/goods")
    @ApiOperation("导入产品资料")
    public CommonRes importGoods(@RequestBody List<ProductInfoImportDTO> dtos) {
        return importDataService.importGoods(dtos);
    }

    @PostMapping("/custom")
    @ApiOperation("导入客户资料")
    public CommonRes importCustom(@RequestBody List<CustomInfoImportDTO> dtos) {
        return importDataService.importCustomer(dtos);
    }

    @PostMapping("/sale-flow")
    @ApiOperation("导入销售流向")
    public CommonRes importSaleFlow(@RequestBody List<SaleFlowImportDTO> dtos) {
        return importDataService.importSaleFlow(dtos);
    }

    @PostMapping("/customer-sale-indicator")
    @ApiOperation("导入客户销售指标")
    public CommonRes importCustomerSalesIndicator(@RequestBody List<SaleIndicatorImportDTO> dtos) {
        return importDataService.importCustomerSalesIndicator(dtos);
    }
    // endregion

}
