package com.csbr.cloud.orderbusiness.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csbr.cloud.orderbusiness.entity.BusinessOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author zhangheng
 * @date 2019/11/6 15:28
 */
@Component
@Mapper
@Repository
public interface BusinessOrderMapper extends BaseMapper<BusinessOrder> {
}
