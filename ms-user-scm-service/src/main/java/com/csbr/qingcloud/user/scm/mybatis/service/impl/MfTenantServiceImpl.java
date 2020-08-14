package com.csbr.qingcloud.user.scm.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenant;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfTenantMapper;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfTenantSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfTenantService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 会员资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfTenantServiceImpl extends CsbrServiceImpl<MfTenantMapper, MfTenant> implements MfTenantService {

    /** 会员资料数据持久化对象 */
    @Autowired
    private MfTenantMapper mfTenantMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfTenant> iPage = mfTenantMapper.mapperPageMfTenants(page, so);
        return new PageListVO().build(iPage);
    }

    /**
     * 查询企业备案
     *
     * @param param
     * @return
     */
    @Override
    public List<Map<String, Object>> getMfTenantsSelect(Map<String, Object> param) {
        return mfTenantMapper.getMfTenantsSelect(param);
    }

    /**
     * 检查企业GUID是否存在
     * @param tenantGuid
     */
    @Override
    public void checkTenantExistsByGuid(String tenantGuid) {

        //检查tenantGuid是否存在
        if (!this.isExistsData(Arrays.asList(tenantGuid), MfTenant.class)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, String.format("企业关键字(%s)不存在。", tenantGuid));
        }

    }

}