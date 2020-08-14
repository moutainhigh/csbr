package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantDetailVO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantVO;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenant;
import com.github.pagehelper.PageInfo;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @program: ms-user-scm-service
 * @description: 企业服务
 * @author: yio
 * @create: 2020-07-29 10:51
 **/
public interface TenantService {

    /**
     * 新增企业
     * @param dto
     * @return
     */
    CommonRes<Boolean> addTenant(@Valid TenantAddDTO dto);

    /**
     * 修改企业
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateTenant(TenantUpdateDTO dto);

    /**
     * 删除企业
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeTenant(List<String> guids);

    /**
     * 查询企业
     * @param dto
     * @return
     */
    PageListVO<TenantVO> getTenant(TenantQueryDTO dto);

    /**
     * 查询企业明细
     * @param guid
     * @return
     */
    TenantDetailVO getTenantDetail(String guid);

    /**
     * 查询企业备案
     */
    List<Map<String, Object>> getMfTenantsSelect(String guid);




}
