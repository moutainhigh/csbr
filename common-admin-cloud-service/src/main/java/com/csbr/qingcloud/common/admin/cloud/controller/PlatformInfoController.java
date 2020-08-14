package com.csbr.qingcloud.common.admin.cloud.controller;

import com.csbr.qingcloud.common.admin.cloud.domain.dto.platform.PlatformAddDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.platform.PlatformQueryDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.platform.PlatformUpdateDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.vo.PlatformVO;
import com.csbr.qingcloud.common.admin.cloud.service.PlatformInfoService;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: auth
 * @description: 平台资料信息
 * @author: yio
 * @create: 2020-07-09 10:29
 **/
@RestController
@RequestMapping("/platform")
@Api(tags = "平台资料接口")
public class PlatformInfoController {
    @Autowired
    private PlatformInfoService platformInfoService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增平台资料接口")
    public CommonRes<Boolean> add(@RequestBody PlatformAddDTO dto) {
        return platformInfoService.addPlatformInfo(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "更新平台资料接口")
    public CommonRes<Boolean> update(@RequestBody PlatformUpdateDTO dto) {
        return platformInfoService.updatePlatformInfo(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除平台资料接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的平台资料关键字列表不能为空");

        }
        return platformInfoService.removePlatformInfo(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取平台资料接口")
    public CommonRes<PageListVO<PlatformVO>> getPaltformInfo(@RequestBody @Valid PlatformQueryDTO dto) {
        return CommonRes.success(platformInfoService.getPlatformInfo(dto));

    }

    @GetMapping("/test")
    @ApiOperation(value = "测试接口")
    public CommonRes test() {
//        CommonRes res;
//        try {
//            res = platformInfoService.test();
//        } catch (CsbrBaseException ex) {
//            res = GlobalExceptionHandler.Csbr500ExceptionHandler(ex);
//        }

        return platformInfoService.test();
    }
}
