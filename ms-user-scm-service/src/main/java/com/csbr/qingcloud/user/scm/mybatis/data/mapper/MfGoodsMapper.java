package com.csbr.qingcloud.user.scm.mybatis.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfGoods;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.MfGoodsSO;

/**
 * 商品资料实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface MfGoodsMapper extends BaseMapper<MfGoods> {
    /**
     * 分页查询商品资料信息.
     *
     * @param so 查询条件
     *
     * @return 商品资料查询结果
     */
    <D extends BasePageDTO> IPage<MfGoods> mapperPageMfGoods(Page<?> page, D so);
}
