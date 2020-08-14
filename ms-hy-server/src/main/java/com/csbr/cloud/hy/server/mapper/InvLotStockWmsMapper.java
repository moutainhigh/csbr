package com.csbr.cloud.hy.server.mapper;

import com.csbr.cloud.hy.server.entity.InvLotStockWmsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Component
@Mapper
@Repository
public interface InvLotStockWmsMapper extends BaseMapper<InvLotStockWmsEntity> {

    /**
     * 库存差异查询
     */
    List<Map<String,Object>> getStockDifference(Map<String,Object> param);

    /**
     * 滞销库存查询
     * @param param
     * @return
     */
    List<Map<String,Object>> getUnsalableStock(Map<String,Object> param);
}
