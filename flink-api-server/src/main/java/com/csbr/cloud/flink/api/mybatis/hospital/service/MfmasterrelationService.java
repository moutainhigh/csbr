package com.csbr.cloud.flink.api.mybatis.hospital.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.hospital.entity.Mfmasterrelation;
import com.csbr.cloud.flink.api.mybatis.hospital.mapper.so.MfmasterrelationSO;
import com.csbr.cloud.flink.api.mybatis.hospital.mapper.MfmasterrelationMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface MfmasterrelationService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<Mfmasterrelation> getList(MfmasterrelationSO so);
}