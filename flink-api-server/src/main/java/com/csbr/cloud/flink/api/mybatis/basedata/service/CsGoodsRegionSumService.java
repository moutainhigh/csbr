package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CsGoodsRegionSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CsGoodsRegionSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.CsGoodsRegionSumMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface CsGoodsRegionSumService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<CsGoodsRegionSum> getList(CsGoodsRegionSumSO so);
}