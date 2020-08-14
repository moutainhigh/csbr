package com.csbr.cloud.hy.server.mapper;

import com.csbr.cloud.hy.server.entity.TrInBoundResultDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-18
 */
@Component
@Mapper
@Repository
public interface TrInBoundResultDetailMapper extends BaseMapper<TrInBoundResultDetailEntity> {

}
