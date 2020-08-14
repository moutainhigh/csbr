package com.csbr.cloud.hy.server.mapper;

import com.csbr.cloud.hy.server.entity.TrOutBoundResultEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
public interface TrOutBoundResultMapper extends BaseMapper<TrOutBoundResultEntity> {

    /**
     * 查询近3、7、14、30天的日均销量
     */
     Map<String,Object> getWmsDms(Map<String,Object> param);
}
