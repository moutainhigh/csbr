package com.csbr.cloud.flink.api.mybatis.medicine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.MeMfvehicletype;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MeMfvehicletypeSO;

/**
 * 运输车辆类型实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-14
 */

@Component
@Mapper
@Repository
public interface MeMfvehicletypeMapper extends BaseMapper<MeMfvehicletype> {
    /**
     * 查询运输车辆类型信息.
     *
     * @param so 查询条件
     *
     * @return 运输车辆类型查询结果
     */
    List<MeMfvehicletype> searchMeMfvehicletypes(MeMfvehicletypeSO so);
}
