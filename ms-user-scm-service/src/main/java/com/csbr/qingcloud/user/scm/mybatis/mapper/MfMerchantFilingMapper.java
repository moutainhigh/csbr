package com.csbr.qingcloud.user.scm.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchantFiling;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfMerchantFilingSO;

import java.util.List;
import java.util.Map;

/**
 * 商户备案实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-31
 */

@Component
@Mapper
@Repository
public interface MfMerchantFilingMapper extends BaseMapper<MfMerchantFiling> {
    /**
     * 分页查询商户备案信息.
     *
     * @param so 查询条件
     *
     * @return 商户备案查询结果
     */
    <D extends BasePageDTO> IPage<MfMerchantFiling> mapperPageMfMerchantFilings(Page<?> page, D so);

    /**
     * 商户备案查询
     */
    IPage<List<Map<String,Object>>> getFilingPageList(Page<?> page,Map<String,Object> param);
}
