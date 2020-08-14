package com.csbr.cloud.flink.api.controller.visualdata;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CsbrSumInfoSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.CsbrSumInfoService;
import com.csbr.cloud.flink.api.mybatis.hospital.mapper.so.TrsupplierpoSO;
import com.csbr.cloud.flink.api.mybatis.hospital.service.TrsupplierpoService;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MfvehicleSO;
import com.csbr.cloud.flink.api.mybatis.medicine.service.MfvehicleService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: flink-api-service
 * @description:
 * @author: Huanglh
 * @create: 2020-01-09 14:03
 **/
@RestController
@RequestMapping("/demo")
@Api(tags = "demo接口")
@RefreshScope
public class DemoController {
    // base data
    @Autowired
    private CsbrSumInfoService csbrSumInfoService;

    // hospital
    @Autowired
    private TrsupplierpoService trsupplierpoService;

    // medicine
    @Autowired
    private MfvehicleService mfvehicleService;


    @GetMapping("/get-list")
    @ApiOperation(value = "多数据源获取链表测试")
    public CommonRes getList() {
        List<List> res = new ArrayList<>();
        PageHelper.startPage(1,5);
        res.add(csbrSumInfoService.getList(new CsbrSumInfoSO()));
        PageHelper.startPage(1,5);
        res.add(trsupplierpoService.getList(new TrsupplierpoSO()));
        PageHelper.startPage(1,5);
        res.add(mfvehicleService.getList(new MfvehicleSO()));

        return CommonRes.ok(res);
    }
}
