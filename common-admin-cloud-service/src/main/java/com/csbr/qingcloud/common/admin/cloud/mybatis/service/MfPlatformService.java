package com.csbr.qingcloud.common.admin.cloud.mybatis.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.common.admin.cloud.mybatis.entity.MfPlatform;

/**
 * 平台资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-15
 */

public interface MfPlatformService extends CsbrService<MfPlatform> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}