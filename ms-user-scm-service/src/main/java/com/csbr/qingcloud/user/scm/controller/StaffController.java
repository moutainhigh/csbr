package com.csbr.qingcloud.user.scm.controller;

import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.StaffAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.StaffQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.StaffUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.PositionVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffVO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantDetailVO;
import com.csbr.qingcloud.user.scm.service.PositionService;
import com.csbr.qingcloud.user.scm.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 人员控制器
 * @author: yio
 * @create: 2020-07-31 13:31
 **/
@RestController
@RequestMapping("/staff")
@Api(tags = "人员接口")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增人员接口")
    public CommonRes<Boolean> add(@RequestBody @Valid StaffAddDTO dto) {

        return staffService.addStaff(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改人员接口")
    public CommonRes<Boolean> update(@RequestBody @Valid StaffUpdateDTO dto) {
        return staffService.updateStaff(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除人员接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的人员关键字列表不能为空");
        }
        return staffService.removeStaff(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取人员接口")
    public CommonRes<PageListVO<StaffVO>> getStaff(@RequestBody @Valid StaffQueryDTO dto) {

        return CommonRes.success(staffService.getStaff(dto));

    }

    @GetMapping("/data/get-staff-detail")
    @ApiOperation(value = "获取人员明细接口")
    public CommonRes<StaffVO> getStaffDetail(String guid) {

        return CommonRes.success(staffService.getStaffDetail(guid));

    }

    @PostMapping("/data/add-curentstaff-to-redis")
    @ApiOperation(value = "将当前企业的登录用户添加到redis")
    public CommonRes addCurrentStaffInfoToRedis(String tenantGuid) {
        if (StringUtils.isEmpty(tenantGuid)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "企业关键字不能为空");
        }
        try {
            staffService.addCurrentStaffInfoToRedis(tenantGuid);
        } catch (Exception e) {
            throw new CsbrSystemException(SystemError.ERROR, String.format("出现系统错误（%s）。", e.getMessage()));
        }
        return CommonRes.success();
    }
}
