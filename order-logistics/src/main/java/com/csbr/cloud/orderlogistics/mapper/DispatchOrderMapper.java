package com.csbr.cloud.orderlogistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csbr.cloud.orderlogistics.entity.DispatchOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author zhangheng
 * @date 2019/11/6 16:27
 */
@Component
@Mapper
@Repository
public interface DispatchOrderMapper extends BaseMapper<DispatchOrder> {
}
