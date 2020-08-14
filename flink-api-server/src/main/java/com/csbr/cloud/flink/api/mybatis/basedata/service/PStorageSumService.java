package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PStorageSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PStorageSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PStorageSumMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface PStorageSumService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<PStorageSum> getList(PStorageSumSO so);

    /**
     * 获取最新的数据
     *
     * @param so
     * @return
     */
    PStorageSum getNewest(PStorageSumSO so);
}