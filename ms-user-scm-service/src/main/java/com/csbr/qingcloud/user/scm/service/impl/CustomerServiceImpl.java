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
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerVO;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfCustomer;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.MfCustomerSO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.MfCustomerService;
import com.csbr.qingcloud.user.scm.mybatis.service.MfStaffService;
import com.csbr.qingcloud.user.scm.mybatis.service.MfTenantService;
import com.csbr.qingcloud.user.scm.service.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 客户服务
 * @author: yio
 * @create: 2020-08-06 16:34
 **/
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private MfCustomerService mfCustomerService;

    @Autowired
    private MfStaffService mfStaffService;

    @Autowired
    private MfTenantService mfTenantService;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    private void checkDuplicate(String tenantGuid,String customerCode,String customerName,String guid){
        //查看编码与名称是否重复
        LambdaQueryWrapper<MfCustomer> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfCustomer::getTenantGuid,tenantGuid)
                .and(qw->qw.eq(MfCustomer::getCustomerCode,customerCode)
                    .or().eq(MfCustomer::getCustomerName,customerName))
                .select(MfCustomer::getGuid,MfCustomer::getCustomerCode,MfCustomer::getCustomerName);
        MfCustomer ent=mfCustomerService.getOne(queryWrapper);
        if (ent!=null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
            if(StringUtils.isNotEmpty(ent.getCustomerCode())){
                throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("客户编码(%s)已存在。", customerCode));

            }
            if(StringUtils.isNotEmpty(ent.getCustomerName())){
                throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("客户名称(%s)已存在。", customerName));

            }

        }


    }
    public void checkExists(CustomerAddDTO dto){

        //检查tenantGuid是否存在
        mfTenantService.checkTenantExistsByGuid(dto.getTenantGuid());
        //检查staffGuid是否存在
        mfStaffService.checkStaffExistsByGuid(dto.getStaffGuid());

    }
    /**
     * 岗位新增服务
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addCustomer(@Valid CustomerAddDTO dto) {
        //查看岗位名称是否重复
        checkDuplicate(dto.getTenantGuid(),dto.getCustomerCode(),dto.getCustomerName(),null);
        checkExists(dto);
        MfCustomer customer = csbrBeanUtil.convert(dto, MfCustomer.class);
        mfCustomerService.csbrAddEntity(customer);
        boolean flag = mfCustomerService.save(customer);


        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("客户(%s)新增失败。", dto.getCustomerName()));
        }
        return CommonRes.success(flag);
    }


    /**
     * 修改客户
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateCustomer(CustomerUpdateDTO dto) {
        //查找更新的数据是否存在
        if(!mfCustomerService.isExistsData(Arrays.asList(dto.getGuid()), MfCustomer.class)){
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("客户(%s)不存在，无法更新。", dto.getCustomerName()));
        }
        //查看客户是否重复
        checkDuplicate(dto.getTenantGuid(),dto.getCustomerCode(),dto.getCustomerName(),dto.getGuid());

        checkExists(dto);

        //更新数据
        LambdaUpdateWrapper<MfCustomer> updateWrapper = mfCustomerService.csbrUpdateWrapper(MfCustomer.class);
        updateWrapper.eq(MfCustomer::getGuid, dto.getGuid());

        MfCustomer customer = csbrBeanUtil.convert(dto, MfCustomer.class);
        boolean flag = mfCustomerService.update(customer, updateWrapper);


        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("客户(%s)更新失败。", dto.getCustomerName()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 移除客户
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeCustomer(List<String> guids) {
        //查找更新的数据是否存在
        if (!mfCustomerService.isExistsData(guids, MfCustomer.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<MfCustomer> updateWrapper = mfCustomerService.csbrUpdateWrapper(MfCustomer.class);
        updateWrapper.in(MfCustomer::getGuid, guids);
        boolean flag =  mfCustomerService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }


    /**
     * 查询客户
     * @param dto
     * @return
     */
    @Override
    public PageListVO<CustomerVO> getCustomer(CustomerQueryDTO dto) {
        PageListVO<MfCustomer> pageListVO = mfCustomerService.getCustomersWithStaff(
                csbrBeanUtil.convert(dto,MfCustomerSO.class));

        PageListVO<CustomerVO> vo= csbrBeanUtil.convert(pageListVO, PageListVO.class);
        vo.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),CustomerVO.class));
        return vo;
    }
}
