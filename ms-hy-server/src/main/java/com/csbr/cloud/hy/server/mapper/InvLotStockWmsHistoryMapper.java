package com.csbr.cloud.hy.server.mapper;

import com.csbr.cloud.hy.server.entity.InvLotStockWmsHistoryEntity;
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
 * @since 2020-05-08
 */
@Component
@Mapper
@Repository
public interface InvLotStockWmsHistoryMapper extends BaseMapper<InvLotStockWmsHistoryEntity> {

    /**
     * 根据仓库汇总查询库存
     */
    List<Map<String,Object>> getLotStockWms(Map<String,Object> param);
}
