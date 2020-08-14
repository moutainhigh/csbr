package com.csbr.cloud.flink.api.mybatis.transport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMflogisticsoutlet;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMflogisticsoutletSO;

/**
 * 物流网点实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Component
@Mapper
@Repository
public interface TrMflogisticsoutletMapper extends BaseMapper<TrMflogisticsoutlet> {
    /**
     * 查询物流网点信息.
     *
     * @param so 查询条件
     *
     * @return 物流网点查询结果
     */
    List<TrMflogisticsoutlet> searchTrMflogisticsoutlets(TrMflogisticsoutletSO so);
}
