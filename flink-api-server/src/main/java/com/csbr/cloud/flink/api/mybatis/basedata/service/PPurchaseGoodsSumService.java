package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PPurchaseGoodsSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PPurchaseGoodsSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PPurchaseGoodsSumMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface PPurchaseGoodsSumService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<PPurchaseGoodsSum> getList(PPurchaseGoodsSumSO so);
}