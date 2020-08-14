package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PTransrouteSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PTransrouteSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PTransrouteSumMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface PTransrouteSumService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<PTransrouteSum> getList(PTransrouteSumSO so);
}