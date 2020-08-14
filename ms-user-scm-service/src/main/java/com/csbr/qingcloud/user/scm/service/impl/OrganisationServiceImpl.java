package com.csbr.qingcloud.user.scm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.HierarchicalVO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.util.ListToHierarchical;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.MerchantVO;
import com.csbr.qingcloud.user.scm.domain.vo.OrganisationHierarchicalVO;
import com.csbr.qingcloud.user.scm.domain.vo.OrganisationVO;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfOrganisation;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfStaff;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenant;
import com.csbr.qingcloud.user.scm.mybatis.service.MfOrganisationService;
import com.csbr.qingcloud.user.scm.mybatis.service.MfTenantService;
import com.csbr.qingcloud.user.scm.service.OrganisationService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 组织服务
 * @author: yio
 * @create: 2020-07-30 17:57
 **/
@Service
public class OrganisationServiceImpl implements OrganisationService {

    @Autowired
    private MfOrganisationService mfOrganisationService;
    @Autowired
    private MfTenantService mfTenantService;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Autowired
    private ListToHierarchical listToHierarchical;

    private void checkDuplicate(String tenantGuid,String getorganisationName,String getorganisationCode,String guid)
    {
        //查看组织名称是否重复
        LambdaQueryWrapper<MfOrganisation> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfOrganisation::getTenantGuid,tenantGuid)
                .and(qw->qw.eq(MfOrganisation::getOrganisationName, getorganisationName)
                        .or().eq(MfOrganisation::getOrganisationCode,getorganisationCode))
                .select(MfOrganisation::getGuid);
        MfOrganisation ent=mfOrganisationService.getOne(queryWrapper);
        if (ent!=null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("组织名称(%s)或编码(%s)已存在。", getorganisationName,getorganisationCode));
        }

    }
    private void checkExists(OrganisationAddDTO dto){

        //检查tenantGuid是否存在
        mfTenantService.checkTenantExistsByGuid(dto.getTenantGuid());


    }
    /**
     * 添加组织资料
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addOrganisation(@Valid OrganisationAddDTO dto) {
        //查看组织名称是否重复
        checkDuplicate(dto.getTenantGuid(),dto.getOrganisationName(),dto.getOrganisationCode(),null);
        checkExists(dto);
        MfOrganisation organisation = csbrBeanUtil.convert(dto, MfOrganisation.class);
        mfOrganisationService.csbrAddEntity(organisation);
        boolean flag = mfOrganisationService.save(organisation);


        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("组织资料(%s)新增失败。", dto.getOrganisationName()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 修改组织资料
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateOrganisation(OrganisationUpdateDTO dto) {
        //查找更新的数据是否存在
        if(!mfOrganisationService.isExistsData(Arrays.asList(dto.getGuid()),MfOrganisation.class)){
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("组织资料(%s)不存在，无法更新。", dto.getOrganisationName()));
        }
        //查看组织名称是否重复
        checkDuplicate(dto.getTenantGuid(),dto.getOrganisationName(),dto.getOrganisationCode(),dto.getGuid());
        checkExists(dto);
        //更新数据
        LambdaUpdateWrapper<MfOrganisation> updateWrapper = mfOrganisationService.csbrUpdateWrapper(MfOrganisation.class);
        updateWrapper.eq(MfOrganisation::getGuid, dto.getGuid());

        MfOrganisation organisation = csbrBeanUtil.convert(dto, MfOrganisation.class);
        boolean flag = mfOrganisationService.update(organisation, updateWrapper);


        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("组织资料(%s)更新失败。", dto.getOrganisationName()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 移除组织资料
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeOrganisation(List<String> guids) {
        //查找更新的数据是否存在
        if (!mfOrganisationService.isExistsData(guids, MfOrganisation.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<MfOrganisation> updateWrapper = mfOrganisationService.csbrUpdateWrapper(MfOrganisation.class);
        updateWrapper.in(MfOrganisation::getGuid, guids);
        boolean flag =  mfOrganisationService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }

    /**
     * 获取列表分页数据
     * @param dto
     * @return
     */
    @Override
    public PageListVO<OrganisationVO> getOrganisation(OrganisationQueryDTO dto) {
        PageListVO<MfOrganisation> lst = mfOrganisationService.csbrPageList(dto
                , mfOrganisationService.csbrQueryWrapper(dto, MfOrganisation.class));

        PageListVO<OrganisationVO> pageListVO= csbrBeanUtil.convert(lst,PageListVO.class);
        pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),OrganisationVO.class));
        return pageListVO;
    }

    /**
     * 获取组织层级结构
     * @param tenantGuid
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public List<OrganisationHierarchicalVO> getOrganisationHierarchical(String tenantGuid) throws InvocationTargetException, IllegalAccessException {
        if (StringUtils.isEmpty(tenantGuid)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, "企业Guid不能为空。");
        }

        //查询角色数据
        LambdaQueryWrapper<MfOrganisation> queryWrapper=Wrappers.lambdaQuery();
        queryWrapper.eq(MfOrganisation::getTenantGuid,tenantGuid);
        queryWrapper.orderByAsc(MfOrganisation::getParentGuid,MfOrganisation::getOrderNum,MfOrganisation::getCreateTime);

        List<MfOrganisation> lst=mfOrganisationService.list(queryWrapper);

        return listToHierarchical.getHierarchical(lst,null,null,OrganisationHierarchicalVO.class);

    }

    /**
     * 获取指定组织guid的层级结构
     * @param vos
     * @param organisationGuid
     * @return
     */
    @Override
    public OrganisationHierarchicalVO getAssignOrganisationHierarchical(List<? extends HierarchicalVO> vos, String organisationGuid){
        if(CollectionUtils.isNotEmpty(vos)) {
            OrganisationHierarchicalVO organisationHierarchicalVO=null;
            for (HierarchicalVO vo : vos) {
                organisationHierarchicalVO=(OrganisationHierarchicalVO)vo;
                if (organisationHierarchicalVO.getGuid().equals(organisationGuid)) {
                    return organisationHierarchicalVO;
                } else {
                    getAssignOrganisationHierarchical(vo.getChildren(), organisationGuid);
                }

            }
        }
        return null;
    }

    /**
     * 通过组织层级对象获取子GUID列表
     * @param vo
     * @return
     */
    @Override
    public List<String> getChildOrganisationsByHierarchical(OrganisationHierarchicalVO vo){
        OrganisationHierarchicalVO organisationHierarchicalVO=null;
        if(CollectionUtils.isEmpty(vo.getChildren())){
            return null;
        }
        List<String> lst=new ArrayList<>();
        for (HierarchicalVO hierarchicalVO:vo.getChildren()) {
            organisationHierarchicalVO=(OrganisationHierarchicalVO)hierarchicalVO;
            lst.add(organisationHierarchicalVO.getGuid());
            lst.addAll(getChildOrganisationsByHierarchical(organisationHierarchicalVO));
        }
        return lst;
    }
}
