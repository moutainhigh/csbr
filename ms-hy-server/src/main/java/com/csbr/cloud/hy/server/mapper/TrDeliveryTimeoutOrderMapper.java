package com.csbr.cloud.hy.server.mapper;

import com.csbr.cloud.hy.server.entity.TrDeliveryTimeoutOrderEntity;
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
public interface TrDeliveryTimeoutOrderMapper extends BaseMapper<TrDeliveryTimeoutOrderEntity> {

    List<Map<String,Object>> trDeliveryTimeoutOrderJob(Map<String,Object> param);

}
