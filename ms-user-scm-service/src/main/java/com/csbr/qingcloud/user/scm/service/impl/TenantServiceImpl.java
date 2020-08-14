package com.csbr.qingcloud.user.scm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CommonUtil;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.*;
import com.csbr.qingcloud.user.scm.domain.vo.SalesFlowVO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantDetailVO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantLicVO;
import com.csbr.qingcloud.user.scm.domain.vo.TenantVO;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenant;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenantLic;
import com.csbr.qingcloud.user.scm.mybatis.service.MfTenantLicService;
import com.csbr.qingcloud.user.scm.mybatis.service.MfTenantService;
import com.csbr.qingcloud.user.scm.service.TenantService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: ms-user-scm-service
 * @description: 企业服务
 * @author: yio
 * @create: 2020-07-29 15:30
 **/
@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private MfTenantService mfTenantService;

    @Autowired
    private MfTenantLicService mfTenantLicService;

    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    private void checkDuplicate(String tenantName,String guid){
        //查看企业名称是否重复
        LambdaQueryWrapper<MfTenant> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfTenant::getTenantName, tenantName)
                .select(MfTenant::getGuid);
        MfTenant ent=mfTenantService.getOne(queryWrapper);
        if (ent!=null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("企业(%s)已存在。",tenantName));
        }
    }

    /**
     * 新增企业
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addTenant(@Valid TenantAddDTO dto) {


        //查看企业名称是否重复
        checkDuplicate(dto.getTenantName(),null);
        MfTenant tenant = csbrBeanUtil.convert(dto, MfTenant.class,true);
        mfTenantService.csbrAddEntity(tenant);
        tenant.setGuid(CommonUtil.newGuid());
        boolean flag = mfTenantService.save(tenant);

        //插入证照数据
        MfTenantLic tenantLic=null;
        List<MfTenantLic> lst=new ArrayList<>();
        if(CollectionUtils.isNotEmpty(dto.getTenantLicAddDTOs())) {
            for (TenantLicAddDTO lic : dto.getTenantLicAddDTOs()) {
                tenantLic = csbrBeanUtil.convert(lic, MfTenantLic.class);
                tenantLic.setTenantGuid(tenant.getGuid());
                lst.add(tenantLic);
            }
        }
        if(lst.size()>0){
            flag=flag&mfTenantLicService.saveBatch(lst);
        }

        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("企业(%s)新增失败。", dto.getTenantName()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 修改产品
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateTenant(TenantUpdateDTO dto) {

        //查找更新的数据是否存在

        if (!mfTenantService.isExistsData(Arrays.asList(dto.getGuid()), MfTenant.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("企业(%s)不存在，无法更新。", dto.getTenantName()));
        }

        //查看企业名称是否重复
        checkDuplicate(dto.getTenantName(),dto.getGuid());
        //更新数据
        LambdaUpdateWrapper<MfTenant> updateWrapper = mfTenantService.csbrUpdateWrapper(MfTenant.class);
        updateWrapper.eq(MfTenant::getGuid, dto.getGuid());

        MfTenant tenant = csbrBeanUtil.convert(dto, MfTenant.class,true);
        boolean flag = mfTenantService.update(tenant, updateWrapper);

        //查看更新的证照数据是否存在
        List<String> lst=dto.getTenantLicUpdateDTOs().stream().map(x->x.getGuid()).collect(Collectors.toList());
        LambdaQueryWrapper<MfTenantLic> queryWrapper=Wrappers.lambdaQuery();
        queryWrapper.in(MfTenantLic::getGuid,lst);
        int cnt=mfTenantLicService.count(queryWrapper);
        if(cnt!=lst.size()){
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, "更新的证照列表中，包含不存在的证照数据，请刷新后重试。");

        }
        //更新证照数据
        LambdaUpdateWrapper<MfTenantLic> licWrapper=null;
        for (TenantLicUpdateDTO updateDto:dto.getTenantLicUpdateDTOs()) {
            licWrapper=mfTenantLicService.csbrUpdateWrapper(MfTenantLic.class);
            licWrapper.eq(MfTenantLic::getGuid,updateDto.getGuid());

            MfTenantLic tenantLic=csbrBeanUtil.convert(updateDto,MfTenantLic.class);
            mfTenantLicService.update(tenantLic,licWrapper);
        }

        //删除需要移除的证照数据
        if(CollectionUtils.isNotEmpty(dto.getDelTenantLicGuids())){
            mfTenantLicService.removeByIds(dto.getDelTenantLicGuids());
        }

        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("企业(%s)更新失败。", dto.getTenantName()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 移除企业
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeTenant(List<String> guids) {
        //查找更新的数据是否存在
        if (!mfTenantService.isExistsData(guids, MfTenant.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<MfTenant> updateWrapper = mfTenantService.csbrUpdateWrapper(MfTenant.class);
        updateWrapper.in(MfTenant::getGuid, guids);
        boolean flag =  mfTenantService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }

    /**
     * 获取企业
     * @param dto
     * @return
     */
    @Override
    public PageListVO<TenantVO> getTenant(TenantQueryDTO dto) {
        PageListVO<MfTenant> lst = mfTenantService.csbrPageList(dto
                , mfTenantService.csbrQueryWrapper(dto, MfTenant.class));
        PageListVO<TenantVO> pageListVO= csbrBeanUtil.convert(lst,PageListVO.class);
        pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),TenantVO.class));
        return pageListVO;
    }

    /**
     * 获取企业明细
     * @param guid
     * @return
     */
    @Override
    public TenantDetailVO getTenantDetail(String guid) {
        if (StringUtils.isEmpty(guid)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "企业关键字不能为空");

        }

        MfTenant tenant=mfTenantService.getById(guid);
        TenantDetailVO vo=null;
        if(tenant!=null){
            vo=csbrBeanUtil.convert(tenant,TenantDetailVO.class);
            //查询企业对应的证照
            LambdaQueryWrapper<MfTenantLic> queryWrapper=Wrappers.lambdaQuery();
            queryWrapper.eq(MfTenantLic::getTenantGuid,guid);

            List<MfTenantLic> lst=mfTenantLicService.list(queryWrapper);
            List<TenantLicVO> tenantLicVOs=csbrBeanUtil.convert(lst, TenantLicVO.class);

            vo.setTenantLicVOs(tenantLicVOs);
        }
        else
        {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "企业数据不存在。");
        }
        return vo;
    }

    /**
     * 查询企业备案
     *
     * @param guid
     */
    @Override
    public List<Map<String, Object>> getMfTenantsSelect(String guid) {
        Map<String, Object> param = new HashMap<>();
        param.put("merchantGuid",guid);
        List<Map<String, Object>> mfTenantsSelect = mfTenantService.getMfTenantsSelect(param);
        return mfTenantsSelect;
    }



}
