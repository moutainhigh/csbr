package com.csbr.cloud.flink.api.mybatis.medicine.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.Mftransroute;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MftransrouteSO;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.MftransrouteMapper;

/**
 * 配送路线资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface MftransrouteService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<Mftransroute> getList(MftransrouteSO so);
}