package com.csbr.qingcloud.common.admin.cloud.data.importexport.controller;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.service.ExcelImporterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: common-admin-cloud-service
 * @description: excel 数据导入
 * @author: Huanglh
 * @create: 2020-07-31 15:33
 **/
@RestController
@RequestMapping("/excel")
@Api(tags = "Excel操作接口")
public class ExcelController {

    @Autowired
    private ExcelImporterService excelImporterService;

    @PostMapping("/import/product-info")
    @ApiOperation("导入产品资料")
    @ApiImplicitParams(
            {@ApiImplicitParam(value = "导入的excel文件", name = "file", dataTypeClass = MultipartFile.class),
                    @ApiImplicitParam(value = "企业GUID", name = "tenantGuid", dataTypeClass = String.class)}
    )
    public CommonRes importProductInfo(@RequestParam MultipartFile file, @RequestParam String tenantGuid) {
        return excelImporterService.importProductInfo(file, tenantGuid);
    }

    @PostMapping("/import/custom-info")
    @ApiOperation("导入客户资料")
    @ApiImplicitParams(
            {@ApiImplicitParam(value = "导入的excel文件", name = "file", dataTypeClass = MultipartFile.class),
                    @ApiImplicitParam(value = "企业GUID", name = "tenantGuid", dataTypeClass = String.class)}
    )
    public CommonRes importCustomInfo(@RequestParam MultipartFile file, @RequestParam String tenantGuid) {
        return excelImporterService.importCustomInfo(file, tenantGuid);
    }

    @PostMapping("/import/staff-relation")
    @ApiOperation("导入人员从属关系资料")
    @ApiImplicitParams(
            {@ApiImplicitParam(value = "导入的excel文件", name = "file", dataTypeClass = MultipartFile.class),
                    @ApiImplicitParam(value = "企业GUID", name = "tenantGuid", dataTypeClass = String.class)}
    )
    public CommonRes importStaffRelation(@RequestParam MultipartFile file, @RequestParam String tenantGuid) {
        return excelImporterService.importStaffRelation(file, tenantGuid);
    }

    @PostMapping("/import/staff-info")
    @ApiOperation("导入人员资料")
    @ApiImplicitParams(
            {@ApiImplicitParam(value = "导入的excel文件", name = "file", dataTypeClass = MultipartFile.class),
                    @ApiImplicitParam(value = "企业GUID", name = "tenantGuid", dataTypeClass = String.class)}
    )
    public CommonRes importStaffInfo(@RequestParam MultipartFile file, @RequestParam String tenantGuid) {
        return excelImporterService.importStaffInfo(file, tenantGuid);
    }

    @PostMapping("/import/sale-flow")
    @ApiOperation("导入销售流向")
    @ApiImplicitParams(
            {@ApiImplicitParam(value = "导入的excel文件", name = "file", dataTypeClass = MultipartFile.class),
                    @ApiImplicitParam(value = "企业GUID", name = "tenantGuid", dataTypeClass = String.class)}
    )
    public CommonRes importSaleFlow(@RequestParam MultipartFile file, @RequestParam String tenantGuid) {
        return excelImporterService.importSaleFlow(file, tenantGuid);
    }

    @PostMapping("/import/sale-indicator")
    @ApiOperation("导入销售指标")
    @ApiImplicitParams(
            {@ApiImplicitParam(value = "导入的excel文件", name = "file", dataTypeClass = MultipartFile.class),
                    @ApiImplicitParam(value = "企业GUID", name = "tenantGuid", dataTypeClass = String.class)}
    )
    public CommonRes importSaleIndicator(@RequestParam MultipartFile file, @RequestParam String tenantGuid) {
        return excelImporterService.importSaleIndicator(file, tenantGuid);
    }
}
