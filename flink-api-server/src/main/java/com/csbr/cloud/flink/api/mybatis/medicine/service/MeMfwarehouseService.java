package com.csbr.cloud.flink.api.mybatis.medicine.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.MeMfwarehouse;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MeMfwarehouseSO;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.MeMfwarehouseMapper;

/**
 * 仓库资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface MeMfwarehouseService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<MeMfwarehouse> getList(MeMfwarehouseSO so);
}