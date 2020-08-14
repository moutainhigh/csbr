package com.csbr.cloud.flink.api.mybatis.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.csbr.cloud.flink.api.mybatis.hospital.entity.Trsupplierpo;
import com.csbr.cloud.flink.api.mybatis.hospital.mapper.so.TrsupplierpoSO;

/**
 * 实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Component
@Mapper
@Repository
public interface TrsupplierpoMapper extends BaseMapper<Trsupplierpo> {
    /**
     * 查询信息.
     *
     * @param so 查询条件
     *
     * @return 查询结果
     */
    List<Trsupplierpo> searchTrsupplierpos(TrsupplierpoSO so);
}
