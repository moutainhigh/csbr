package com.csbr.qingcloud.user.scm.mybatis.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenant;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfTenantSO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfTenantMapper;

import java.util.List;
import java.util.Map;

/**
 * 会员资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface MfTenantService extends CsbrService<MfTenant> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);

    /**
     * 查询企业备案
     * @param param
     * @return
     */
    List<Map<String,Object>> getMfTenantsSelect(Map<String,Object> param);

    /**
     * 检查企业是否存在
     * @param tenantGuid
     */
    void checkTenantExistsByGuid(String tenantGuid);
}