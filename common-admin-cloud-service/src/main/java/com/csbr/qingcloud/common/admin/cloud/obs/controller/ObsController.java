package com.csbr.qingcloud.common.admin.cloud.obs.controller;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.qingcloud.common.admin.cloud.obs.service.ObsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: common-admin-cloud-service
 * @description: OBS service api
 * @author: Huanglh
 * @create: 2020-07-31 11:29
 **/
@RestController
@RequestMapping("/obs")
@Api(tags = "华为OBS服务接口")
public class ObsController {
    @Autowired
    private ObsService obsService;

    @PostMapping("/upload-image")
    @ApiOperation(value = "获取平台用户接口")
    public CommonRes uploadImage(@RequestParam MultipartFile image) {
        return obsService.uploadImage(image);
    }
}
