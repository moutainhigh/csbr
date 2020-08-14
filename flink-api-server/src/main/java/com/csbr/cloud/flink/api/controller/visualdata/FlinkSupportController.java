package com.csbr.cloud.flink.api.controller.visualdata;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.flink.api.model.response.visualdata.FlinkSupportMeCheckTrtplbbRes;
import com.csbr.cloud.flink.api.model.response.visualdata.FlinkSupportTrCheckTrtplbbRes;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CusRelationSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.CusRelationService;
import com.csbr.cloud.flink.api.mybatis.basedata.service.MfCustomerInfoService;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MeMfvehicletypeSO;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MeTrtplbbSO;
import com.csbr.cloud.flink.api.mybatis.medicine.service.MeMfvehicletypeService;
import com.csbr.cloud.flink.api.mybatis.medicine.service.MeTrtplbbService;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMfvehicletypeSO;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrTrtplbbSO;
import com.csbr.cloud.flink.api.mybatis.transport.service.TrMfvehicletypeService;
import com.csbr.cloud.flink.api.mybatis.transport.service.TrTrtplbbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: flink-api-service
 * @description:
 * @author: Huanglh
 * @create: 2020-01-13 11:54
 **/
@RestController
@RequestMapping("/flink-api-service/flink-support")
@Api(tags = "对内支持flink的接口")
public class FlinkSupportController {
    @Autowired
    private MfCustomerInfoService mfCustomerInfoService;
    @Autowired
    private CusRelationService cusRelationService;
    @Autowired
    private MeMfvehicletypeService meMfvehicletypeService;
    @Autowired
    private MeTrtplbbService meTrtplbbService;
    @Autowired
    private TrMfvehicletypeService trMfvehicletypeService;
    @Autowired
    private TrTrtplbbService trTrtplbbService;

    @PostMapping("/cus/check-cus-exist")
    @ApiOperation(value = "检查用户是否已存在，存在 true，不存在 false")
    public CommonRes<Boolean> checkCustomerExist(@ApiParam("用户名称") @RequestBody String chineseName) {
        return CommonRes.ok(mfCustomerInfoService.checkExist(chineseName));
    }

    @PostMapping("/cus/get-cus-guid")
    @ApiOperation(value = "根据用户的名称返回用户GUID。查找不到用户，返回空字符串，并有提示")
    public CommonRes<String> getCustomerGUID(@ApiParam("用户名称") @RequestBody String chineseName) {
        String guid = mfCustomerInfoService.getGUIDByChineseName(chineseName);
        String msg = guid.length() == 0 ? "用户不存在！" : "";

        return CommonRes.ok(msg, guid);
    }

    @PostMapping("/cus/get-guid-by-source")
    @ApiOperation(value = "根据数据源用户GUID返回用户GUID。查找不到GUID，返回空字符串，并有提示")
    public CommonRes<String> getGUIDBySource(@RequestBody CusRelationSO so) {
        String guid = cusRelationService.getGUIDBySource(so);
        String msg = guid.length() == 0 ? "用户不存在！" : "";

        return CommonRes.ok(msg, guid);
    }

    @PostMapping("/join/me-check-vehicle-type")
    @ApiOperation(value = "关联查询：药链云 mfvehicle 表关联 mfvehicletype 是否存在数据。存在数据：true，不存在：false")
    public CommonRes<Boolean> meCheckVehicleType(@RequestBody MeMfvehicletypeSO so) {
        return CommonRes.ok(meMfvehicletypeService.checkVehicleType(so));
    }

    @PostMapping("/join/tr-check-vehicle-type")
    @ApiOperation(value = "关联查询：四方云 mfvehicle 表关联 mfvehicletype 是否存在数据。存在数据：true，不存在：false")
    public CommonRes<Boolean> trCheckVehicleType(@RequestBody TrMfvehicletypeSO so) {
        return CommonRes.ok(trMfvehicletypeService.checkVehicleType(so));
    }

    @PostMapping("/join/me-check-trtplbb")
    @ApiOperation(value = "关联查询：药链云 检查和 Trtplbb 表的关联情况")
    public CommonRes<FlinkSupportMeCheckTrtplbbRes> meCheckTrtplbb(@RequestBody MeTrtplbbSO so) {
        return CommonRes.ok(meTrtplbbService.checkTrtplbb(so));
    }

    @PostMapping("/join/tr-check-trtplbb")
    @ApiOperation(value = "关联查询：四方云 检查和 Trtplbb 表的关联情况")
    public CommonRes<FlinkSupportTrCheckTrtplbbRes> trCheckTrtplbb(@RequestBody TrTrtplbbSO so) {
        return CommonRes.ok(trTrtplbbService.checkTrtplbb(so));
    }

    @PostMapping("/join/me-check-trtplbb-ismedorder")
    @ApiOperation(value = "关联查询：药链云 检查 Trtplbb 表 isMedOrder 是否为 Y")
    public CommonRes<Boolean> meCheckTrtplbbIsmedorder(@RequestBody MeTrtplbbSO so) {
        return CommonRes.ok(meTrtplbbService.checkTrtplbbMedOrder(so));
    }

    @PostMapping("/join/tr-check-trtplbb-ismedorder")
    @ApiOperation(value = "关联查询：四方云 检查 Trtplbb 表 isMedOrder 是否为 Y")
    public CommonRes<Boolean> trCheckTrtplbbIsmedorder(@RequestBody TrTrtplbbSO so) {
        return CommonRes.ok(trTrtplbbService.checkTrtplbbMedOrder(so));
    }
}
