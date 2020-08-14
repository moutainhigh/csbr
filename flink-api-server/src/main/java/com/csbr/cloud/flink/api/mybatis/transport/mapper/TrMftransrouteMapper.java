package com.csbr.cloud.flink.api.mybatis.transport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMftransroute;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMftransrouteSO;

/**
 * 配送路线资料实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Component
@Mapper
@Repository
public interface TrMftransrouteMapper extends BaseMapper<TrMftransroute> {
    /**
     * 查询配送路线资料信息.
     *
     * @param so 查询条件
     *
     * @return 配送路线资料查询结果
     */
    List<TrMftransroute> searchTrMftransroutes(TrMftransrouteSO so);
}
