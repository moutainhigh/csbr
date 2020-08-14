package com.csbr.qingcloud.user.scm.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchant;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfMerchantSO;

import java.util.List;
import java.util.Map;

/**
 * 商户资料实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface MfMerchantMapper extends BaseMapper<MfMerchant> {
    /**
     * 分页查询商户资料信息.
     *
     * @param so 查询条件
     *
     * @return 商户资料查询结果
     */
    <D extends BasePageDTO> IPage<MfMerchant> mapperPageMfMerchants(Page<?> page, D so);

    /**
     * 商户注册审批查询
     */
    IPage<List<Map<String,Object>>> getRegisterPageList(Page<?> page,Map<String,Object> param);
}
