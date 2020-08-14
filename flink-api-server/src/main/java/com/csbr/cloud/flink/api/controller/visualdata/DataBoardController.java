package com.csbr.cloud.flink.api.controller.visualdata;

import com.csbr.cloud.flink.api.mybatis.basedata.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: flink-api-service
 * @description: 数据看板专用
 * @author: Huanglh
 * @create: 2020-01-15 16:16
 **/
@RestController
@RequestMapping("/data")
@Api(tags = "数据看板专用接口")
public class DataBoardController {
    @Autowired
    private CsbrSumInfoService csbrSumInfoService;
    @Autowired
    private CsGoodsRegionSumService csGoodsRegionSumService;

    @Autowired
    private CusRelationService cusRelationService;

    @Autowired
    private PCustomerTypeSumService pCustomerTypeSumService;

    @Autowired
    private PGoodsRegionSumService pGoodsRegionSumService;
    @Autowired
    private PMasterRelationService pMasterRelationService;
    @Autowired
    private PPurchaseGoodsSumService pPurchaseGoodsSumService;


    @Autowired
    private PTransrouteSumService pTransrouteSumService;



}
