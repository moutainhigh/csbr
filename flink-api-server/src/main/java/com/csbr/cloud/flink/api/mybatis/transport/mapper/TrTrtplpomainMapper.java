package com.csbr.cloud.flink.api.mybatis.transport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrTrtplpomain;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrTrtplpomainSO;

/**
 * 物流订单主实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Component
@Mapper
@Repository
public interface TrTrtplpomainMapper extends BaseMapper<TrTrtplpomain> {
    /**
     * 查询物流订单主信息.
     *
     * @param so 查询条件
     *
     * @return 物流订单主查询结果
     */
    List<TrTrtplpomain> searchTrTrtplpomains(TrTrtplpomainSO so);
}
