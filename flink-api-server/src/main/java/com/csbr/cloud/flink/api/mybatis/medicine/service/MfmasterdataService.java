package com.csbr.cloud.flink.api.mybatis.medicine.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.Mfmasterdata;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MfmasterdataSO;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.MfmasterdataMapper;

/**
 * 主数据资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface MfmasterdataService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<Mfmasterdata> getList(MfmasterdataSO so);
}