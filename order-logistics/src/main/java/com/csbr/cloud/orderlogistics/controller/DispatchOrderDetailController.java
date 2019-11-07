package com.csbr.cloud.orderlogistics.controller;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.orderlogistics.entity.DispatchOrderDetail;
import com.csbr.cloud.orderlogistics.service.DispatchOrderDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangheng
 * @date 2019/11/6 16:47
 */
@Api(value = "测试controller", tags = "测试操作接口")
@RestController
@RequestMapping("/order-logistics-server/dispatchOrderDetail")//窄化请求地址
public class DispatchOrderDetailController {

    @Autowired
    private DispatchOrderDetailService dispatchOrderDetailService;


    /**
     * 条件查询资源
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询列表", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CommonRes<List<DispatchOrderDetail>>> listResources() {
        return ResponseEntity.ok(CommonRes.ok((dispatchOrderDetailService.getDispatchOrderDetailList())));
    }

}
