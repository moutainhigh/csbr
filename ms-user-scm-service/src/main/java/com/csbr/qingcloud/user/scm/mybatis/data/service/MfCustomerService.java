package com.csbr.qingcloud.user.scm.mybatis.data.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfCustomer;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.MfCustomerSO;

/**
 * 客户资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface MfCustomerService extends CsbrService<MfCustomer> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);

    /**
     * 获取分页得客户资料携带所属人员名称
     * @param so
     * @param <D>
     * @return
     */
    <D extends BasePageDTO> PageListVO getCustomersWithStaff(MfCustomerSO so);
}