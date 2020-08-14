package com.csbr.qingcloud.user.scm.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchantBankAccount;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfMerchantBankAccountMapper;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfMerchantBankAccountSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfMerchantBankAccountService;

/**
 * 商户银行账号业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfMerchantBankAccountServiceImpl extends CsbrServiceImpl<MfMerchantBankAccountMapper, MfMerchantBankAccount> implements MfMerchantBankAccountService {

    /** 商户银行账号数据持久化对象 */
    @Autowired
    private MfMerchantBankAccountMapper mfMerchantBankAccountMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfMerchantBankAccount> iPage = mfMerchantBankAccountMapper.mapperPageMfMerchantBankAccounts(page, so);
        return new PageListVO().build(iPage);
    }


}