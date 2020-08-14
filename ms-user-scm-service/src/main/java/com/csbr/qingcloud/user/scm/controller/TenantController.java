package com.csbr.qingcloud.user.scm.controller;

import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantDetailVO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantVO;
import com.csbr.qingcloud.user.scm.service.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @program: ms-user-scm-service
 * @description: 企业控制器
 * @author: yio
 * @create: 2020-07-29 18:23
 **/
@RestController
@RequestMapping("/tenant")
@Api(tags = "企业接口")
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增企业接口")
    public CommonRes<Boolean> add(@RequestBody @Valid TenantAddDTO dto) {

        return tenantService.addTenant(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改企业接口")
    public CommonRes<Boolean> update(@RequestBody @Valid TenantUpdateDTO dto) {
        return tenantService.updateTenant(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除企业接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的企业关键字列表不能为空");
        }
        return tenantService.removeTenant(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取企业接口")
    public CommonRes<PageListVO<TenantVO>> getTenant(@RequestBody @Valid TenantQueryDTO dto) {

        return CommonRes.success(tenantService.getTenant(dto));

    }

    @GetMapping("/data/get-tenant-detail")
    @ApiOperation(value = "获取企业明细接口")
    public CommonRes<TenantDetailVO> getTenantDetail(String guid) {

        return CommonRes.success(tenantService.getTenantDetail(guid));

    }

    @GetMapping("/data/get-tenants-select")
    @ApiOperation(value = "获取企业备案信息")
    public CommonRes<List<Map<String, Object>>> getMfTenantsSelect(String guid) {

        if (StringUtils.isEmpty(guid)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "企业关键字不能为空");
        }
        List<Map<String, Object>> mfTenantsSelect = tenantService.getMfTenantsSelect(guid);
        return CommonRes.success(mfTenantsSelect);

    }

}
