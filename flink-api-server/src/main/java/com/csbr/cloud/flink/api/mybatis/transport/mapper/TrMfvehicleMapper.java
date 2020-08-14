package com.csbr.cloud.flink.api.mybatis.transport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMfvehicle;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMfvehicleSO;

/**
 * 运输车辆资料实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Component
@Mapper
@Repository
public interface TrMfvehicleMapper extends BaseMapper<TrMfvehicle> {
    /**
     * 查询运输车辆资料信息.
     *
     * @param so 查询条件
     *
     * @return 运输车辆资料查询结果
     */
    List<TrMfvehicle> searchTrMfvehicles(TrMfvehicleSO so);
}
