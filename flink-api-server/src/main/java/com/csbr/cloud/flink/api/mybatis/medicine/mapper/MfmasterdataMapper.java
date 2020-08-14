package com.csbr.cloud.flink.api.mybatis.medicine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.Mfmasterdata;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MfmasterdataSO;

/**
 * 主数据资料实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Component
@Mapper
@Repository
public interface MfmasterdataMapper extends BaseMapper<Mfmasterdata> {
    /**
     * 查询主数据资料信息.
     *
     * @param so 查询条件
     *
     * @return 主数据资料查询结果
     */
    List<Mfmasterdata> searchMfmasterdatas(MfmasterdataSO so);
}
