package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.HierarchicalVO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.MerchantVO;
import com.csbr.qingcloud.user.scm.domain.vo.OrganisationHierarchicalVO;
import com.csbr.qingcloud.user.scm.domain.vo.OrganisationVO;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 组织服务
 * @author: yio
 * @create: 2020-07-30 17:58
 **/
public interface OrganisationService {

    /**
     * 新增组织
     * @param dto
     * @return
     */
    CommonRes<Boolean> addOrganisation(@Valid OrganisationAddDTO dto);

    /**
     * 修改组织
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateOrganisation(OrganisationUpdateDTO dto);

    /**
     * 删除组织
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeOrganisation(List<String> guids);

    /**
     * 查询组织
     * @param dto
     * @return
     */
    PageListVO<OrganisationVO> getOrganisation(OrganisationQueryDTO dto);

    /**
     * 查询企业明细
     * @param tenantGuid
     * @return
     */
    List<OrganisationHierarchicalVO> getOrganisationHierarchical(String tenantGuid) throws InvocationTargetException, IllegalAccessException;

    /**
     * 获取指定组织guid的层级结构
     * @param vos
     * @param organisationGuid
     * @return
     */
     OrganisationHierarchicalVO getAssignOrganisationHierarchical(List<? extends HierarchicalVO> vos, String organisationGuid);

    /**
     * 通过组织层级对象获取子GUID列表
     * @param vo
     * @return
     */
    List<String> getChildOrganisationsByHierarchical(OrganisationHierarchicalVO vo);
}
