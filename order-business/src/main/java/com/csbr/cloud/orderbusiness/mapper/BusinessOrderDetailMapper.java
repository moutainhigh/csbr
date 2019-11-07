package com.csbr.cloud.orderbusiness.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csbr.cloud.orderbusiness.entity.BusinessOrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author zhangheng
 * @date 2019/11/6 15:29
 */
@Component
@Mapper
@Repository
public interface BusinessOrderDetailMapper extends BaseMapper<BusinessOrderDetail> {
}
