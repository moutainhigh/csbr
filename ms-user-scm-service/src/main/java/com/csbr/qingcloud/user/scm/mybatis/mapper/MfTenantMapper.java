package com.csbr.qingcloud.user.scm.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenant;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfTenantSO;

import java.util.List;
import java.util.Map;

/**
 * 会员资料实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface MfTenantMapper extends BaseMapper<MfTenant> {
    /**
     * 分页查询会员资料信息.
     *
     * @param so 查询条件
     *
     * @return 会员资料查询结果
     */
    <D extends BasePageDTO> IPage<MfTenant> mapperPageMfTenants(Page<?> page, D so);

    List<Map<String,Object>> getMfTenantsSelect(Map<String,Object> param);
}
