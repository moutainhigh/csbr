package com.csbr.cloud.flink.api.mybatis.hospital.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.hospital.entity.Trsupplierpo;
import com.csbr.cloud.flink.api.mybatis.hospital.mapper.so.TrsupplierpoSO;
import com.csbr.cloud.flink.api.mybatis.hospital.mapper.TrsupplierpoMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface TrsupplierpoService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<Trsupplierpo> getList(TrsupplierpoSO so);
}