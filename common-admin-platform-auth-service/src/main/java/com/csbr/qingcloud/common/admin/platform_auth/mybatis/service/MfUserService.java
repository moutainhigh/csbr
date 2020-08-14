package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfUser;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so.MfUserSO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.MfUserMapper;

/**
 * 用户业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-27
 */

public interface MfUserService extends CsbrService<MfUser> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}