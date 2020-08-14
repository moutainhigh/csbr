package com.csbr.qingcloud.user.scm.controller;

import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.OrganisationHierarchicalVO;
import com.csbr.qingcloud.user.scm.domain.vo.OrganisationVO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantDetailVO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantVO;
import com.csbr.qingcloud.user.scm.service.OrganisationService;
import com.csbr.qingcloud.user.scm.service.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 组织资料控制器
 * @author: yio
 * @create: 2020-07-31 10:33
 **/
@RestController
@RequestMapping("/organisation")
@Api(tags = "组织资料控制器")
public class OrganisationController {
    @Autowired
    private OrganisationService organisationService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增组织资料接口")
    public CommonRes<Boolean> add(@RequestBody @Valid OrganisationAddDTO dto) {

        return organisationService.addOrganisation(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改组织资料接口")
    public CommonRes<Boolean> update(@RequestBody @Valid OrganisationUpdateDTO dto) {
        return organisationService.updateOrganisation(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除组织资料接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的组织资料关键字列表不能为空");
        }
        return organisationService.removeOrganisation(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取组织资料接口")
    public CommonRes<PageListVO<OrganisationVO>> getOrganisation(@RequestBody @Valid OrganisationQueryDTO dto) {

        return CommonRes.success(organisationService.getOrganisation(dto));

    }

    @GetMapping("/data/get-organisation-detail")
    @ApiOperation(value = "获取组织资料层级")
    public CommonRes<List<OrganisationHierarchicalVO>> getOrganisationHierarchical(String tenantGuid) {
        try {
        return CommonRes.success(organisationService.getOrganisationHierarchical(tenantGuid));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new CsbrSystemException(SystemError.ERROR, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new CsbrSystemException(SystemError.ERROR, e.getMessage(), e);

        }
    }
}
