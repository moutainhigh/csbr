package com.csbr.qingcloud.user.scm.mybatis.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo.CustomerWithStaffPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfCustomer;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.MfCustomerMapper;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.MfCustomerSO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.MfCustomerService;

/**
 * 客户资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfCustomerServiceImpl extends CsbrServiceImpl<MfCustomerMapper, MfCustomer> implements MfCustomerService {

    /** 客户资料数据持久化对象 */
    @Autowired
    private MfCustomerMapper mfCustomerMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfCustomer> iPage = mfCustomerMapper.mapperPageMfCustomers(page, so);
        return new PageListVO().build(iPage);
    }

    @Override
    public <D extends BasePageDTO> PageListVO getCustomersWithStaff(MfCustomerSO so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<CustomerWithStaffPOJO> iPage = mfCustomerMapper.getCustomersWithStaff(page, so);
        return new PageListVO().build(iPage);
    }


}