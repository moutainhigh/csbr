package com.csbr.cloud.flink.api.mybatis.basedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PPurchaseGoodsSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PPurchaseGoodsSumSO;

/**
 * 实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Component
@Mapper
@Repository
public interface PPurchaseGoodsSumMapper extends BaseMapper<PPurchaseGoodsSum> {
    /**
     * 查询信息.
     *
     * @param so 查询条件
     *
     * @return 查询结果
     */
    List<PPurchaseGoodsSum> searchPPurchaseGoodsSums(PPurchaseGoodsSumSO so);

    /**
     * 查询最后一条数据
     *
     * @param so
     * @return
     */
    PPurchaseGoodsSum searchPPurchaseGoodsSum(PPurchaseGoodsSumSO so);
}
