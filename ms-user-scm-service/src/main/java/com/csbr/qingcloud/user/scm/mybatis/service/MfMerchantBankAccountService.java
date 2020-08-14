package com.csbr.qingcloud.user.scm.mybatis.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchantBankAccount;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfMerchantBankAccountSO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfMerchantBankAccountMapper;

/**
 * 商户银行账号业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface MfMerchantBankAccountService extends CsbrService<MfMerchantBankAccount> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}