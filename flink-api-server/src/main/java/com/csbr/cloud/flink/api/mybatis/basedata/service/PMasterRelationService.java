package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PMasterRelation;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PMasterRelationSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PMasterRelationMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface PMasterRelationService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<PMasterRelation> getList(PMasterRelationSO so);
}