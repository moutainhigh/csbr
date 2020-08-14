package com.csbr.qingcloud.user.scm.mybatis.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrFleeingGoodsApply;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrFleeingGoodsApplySO;

/**
 * 举证窜货申请实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Component
@Mapper
@Repository
public interface TrFleeingGoodsApplyMapper extends BaseMapper<TrFleeingGoodsApply> {
    /**
     * 分页查询举证窜货申请信息.
     *
     * @param so 查询条件
     *
     * @return 举证窜货申请查询结果
     */
    <D extends BasePageDTO> IPage<TrFleeingGoodsApply> mapperPageTrFleeingGoodsApplies(Page<?> page, D so);
}
