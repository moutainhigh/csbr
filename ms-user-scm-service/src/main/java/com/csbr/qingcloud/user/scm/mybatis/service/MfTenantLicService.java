package com.csbr.qingcloud.user.scm.mybatis.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenantLic;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfTenantLicSO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfTenantLicMapper;

/**
 * 平台会员企业证照业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface MfTenantLicService extends CsbrService<MfTenantLic> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}