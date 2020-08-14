package com.csbr.cloud.hy.server.mapper;

import com.csbr.cloud.hy.server.entity.TrPlanSalesEntity;
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
public interface TrPlanSalesMapper extends BaseMapper<TrPlanSalesEntity> {

    //查询计划销量
    List<Map<String,Object>> getSalesAccuracy(Map<String,Object> param);

}
