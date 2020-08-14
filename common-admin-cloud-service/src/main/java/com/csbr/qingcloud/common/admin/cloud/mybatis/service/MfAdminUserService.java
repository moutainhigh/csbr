package com.csbr.qingcloud.common.admin.cloud.mybatis.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.common.admin.cloud.mybatis.entity.MfAdminUser;

/**
 * 平台用户业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-15
 */

public interface MfAdminUserService extends CsbrService<MfAdminUser> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);

    /**
     * 查询平台用户，带平台名称
     * @param so
     * @param <D>
     * @return
     */
    <D extends BasePageDTO> PageListVO getDataWithPlatformName(D so);

}