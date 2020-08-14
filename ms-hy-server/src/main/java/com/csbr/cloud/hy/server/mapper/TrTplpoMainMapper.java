package com.csbr.cloud.hy.server.mapper;

import com.csbr.cloud.hy.server.entity.TrTplpoMainEntity;
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
public interface TrTplpoMainMapper extends BaseMapper<TrTplpoMainEntity> {

    //查询订单主表信息
    List<Map<String,Object>> getPoMainSelect(Map<String,Object> param);


    Map<String,Object> getPoMainInfo(Map<String,Object> param);

    List<Map<String,Object>> getTimeoutOrderSelect(Map<String,Object> param);

    Map<String,Object> getTrTplpoMainInfo(Map<String,Object> param);

    //每日订单时效查询
    List<Map<String,Object>> trDailyOrderTimeLimitJob(Map<String,Object> param);

    //状态!=81
    List<Map<String,Object>> getPoMainAndBbList(Map<String,Object> param);

}
