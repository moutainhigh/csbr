package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CusRelation;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CusRelationSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.CusRelationMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface CusRelationService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<CusRelation> getList(CusRelationSO so);

    /**
     * 根据数据源用户GUID返回用户GUID
     *
     * @param so
     * @return
     */
    String getGUIDBySource(CusRelationSO so);
}