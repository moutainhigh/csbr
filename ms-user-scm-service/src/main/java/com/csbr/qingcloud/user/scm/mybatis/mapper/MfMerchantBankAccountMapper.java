package com.csbr.qingcloud.user.scm.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchantBankAccount;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfMerchantBankAccountSO;

/**
 * 商户银行账号实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface MfMerchantBankAccountMapper extends BaseMapper<MfMerchantBankAccount> {
    /**
     * 分页查询商户银行账号信息.
     *
     * @param so 查询条件
     *
     * @return 商户银行账号查询结果
     */
    <D extends BasePageDTO> IPage<MfMerchantBankAccount> mapperPageMfMerchantBankAccounts(Page<?> page, D so);
}
