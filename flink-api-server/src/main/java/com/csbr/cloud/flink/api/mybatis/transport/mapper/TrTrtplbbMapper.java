package com.csbr.cloud.flink.api.mybatis.transport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrTrtplbb;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrTrtplbbSO;

/**
 * 物流业务单据汇总实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-14
 */

@Component
@Mapper
@Repository
public interface TrTrtplbbMapper extends BaseMapper<TrTrtplbb> {
    /**
     * 查询物流业务单据汇总信息.
     *
     * @param so 查询条件
     *
     * @return 物流业务单据汇总查询结果
     */
    List<TrTrtplbb> searchTrTrtplbbs(TrTrtplbbSO so);
}
