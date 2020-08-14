package com.csbr.qingcloud.user.scm.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfInsure;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfInsureSO;

/**
 * 保险资料实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface MfInsureMapper extends BaseMapper<MfInsure> {
    /**
     * 分页查询保险资料信息.
     *
     * @param so 查询条件
     *
     * @return 保险资料查询结果
     */
    <D extends BasePageDTO> IPage<MfInsure> mapperPageMfInsures(Page<?> page, D so);
}
