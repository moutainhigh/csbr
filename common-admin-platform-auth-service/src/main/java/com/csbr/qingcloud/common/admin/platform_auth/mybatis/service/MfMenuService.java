package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfMenu;

/**
 * 菜单业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

public interface MfMenuService extends CsbrService<MfMenu> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}