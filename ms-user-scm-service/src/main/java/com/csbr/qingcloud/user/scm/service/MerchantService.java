package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.merchant.MerchantAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.merchant.MerchantQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.merchant.MerchantUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.MerchantDetailVO;
import com.csbr.qingcloud.user.scm.domain.vo.MerchantVO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantDetailVO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 商户服务
 * @author: yio
 * @create: 2020-07-30 11:28
 **/
public interface MerchantService {

    /**
     * 新增商户
     * @param dto
     * @return
     */
    CommonRes<Boolean> addMerchant(@Valid MerchantAddDTO dto);

    /**
     * 修改商户
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateMerchant(MerchantUpdateDTO dto);

    /**
     * 删除商户
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeMerchant(List<String> guids);

    /**
     * 查询商户
     * @param dto
     * @return
     */
    PageListVO<MerchantVO> getMerchant(MerchantQueryDTO dto);

    /**
     * 查询企业明细
     * @param guid
     * @return
     */
    MerchantDetailVO getMerchantDetail(String guid);

}
