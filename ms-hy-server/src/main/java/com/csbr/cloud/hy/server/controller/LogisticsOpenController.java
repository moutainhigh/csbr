package com.csbr.cloud.hy.server.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.service.LogisticsOpenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author zhangheng
 * @date 2020/6/28 17:42
 */
@RestController
@RequestMapping("/ms-hy-server")
@Api(tags = {"三方物流跟踪信息"})
public class LogisticsOpenController {

    @Autowired
    private LogisticsOpenService logisticsOpenService;


    /**
     * 三方物流跟踪信息
     */
    @SentinelResource(value = "sentile-rule")
    @PostMapping("logistics-open")
    @ApiOperation(value = "三方物流跟踪信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "waybillNo", dataType = "String", value = "运单号"),
            @ApiImplicitParam(paramType = "query", required = true, name = "type", dataType = "String", value = "第三方快递 SF:顺丰 ZTO:中通 STO:申通 YD:韵达 YTO:圆通 JD:京东"),
            @ApiImplicitParam(paramType = "query", required = false, name = "checkPhoneNo", dataType = "String", value = "顺丰快递-电话号码验证 不在月结卡号下的单需要输入（电话号码尾号后4位）"),
    })
    public CommonRes logisticsOpenInfo(
            @RequestParam(value = "waybillNo",required = true) String waybillNo,
            @RequestParam(value = "type",required = true) String type,
            @RequestParam(value = "checkPhoneNo",required = false) String checkPhoneNo,
            HttpServletRequest request
    ){
        CommonRes commonRes = null;
        switch(type){
            case "SF" :
                //顺丰
                commonRes = logisticsOpenService.getSfLogisticsOpenInfo(waybillNo,checkPhoneNo);
                break;
            case "ZTO" :
                //中通
                commonRes = logisticsOpenService.getZtoLogisticsOpenInfo(waybillNo);
                break;
            case "STO" :
                //申通
                commonRes = logisticsOpenService.getStoLogisticsOpenInfo(waybillNo);
                break;
            case "YD" :
                //韵达
                break;
            case "YTO" :
                //圆通
                commonRes = logisticsOpenService.getYtoLogisticsOpenInfo(waybillNo);
                break;
            case "JD" :
                //京东
                break;

            default :
                //语句
        }

        return commonRes;
    }
}
