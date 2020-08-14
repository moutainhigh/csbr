package com.csbr.qingcloud.user.scm.controller;

import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.OrganisationVO;
import com.csbr.qingcloud.user.scm.domain.vo.PositionVO;
import com.csbr.qingcloud.user.scm.service.OrganisationService;
import com.csbr.qingcloud.user.scm.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 岗位控制器
 * @author: yio
 * @create: 2020-07-31 13:31
 **/
@RestController
@RequestMapping("/position")
@Api(tags = "岗位接口")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增岗位接口")
    public CommonRes<Boolean> add(@RequestBody @Valid PositionAddDTO dto) {

        return positionService.addPosition(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改岗位接口")
    public CommonRes<Boolean> update(@RequestBody @Valid PositionUpdateDTO dto) {
        return positionService.updatePosition(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除岗位接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的企业关键字列表不能为空");
        }
        return positionService.removePosition(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取岗位接口")
    public CommonRes<PageListVO<PositionVO>> getPosition(@RequestBody @Valid PositionQueryDTO dto) {

        return CommonRes.success(positionService.getPosition(dto));

    }

}
