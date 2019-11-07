package com.csbr.cloud.orderlogistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csbr.cloud.orderlogistics.entity.DispatchOrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangheng
 * @date 2019/11/6 16:28
 */
@Component
@Mapper
@Repository
public interface DispatchOrderDetailMapper extends BaseMapper<DispatchOrderDetail> {

    /**
     * 查询测试
     */
     List<DispatchOrderDetail> getDispatchOrderDetailList();

}
