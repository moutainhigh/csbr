package com.csbr.cloud.flink.api.mybatis.basedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.csbr.cloud.flink.api.mybatis.basedata.model.WarehouseDetailsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CusStorageSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CusStorageSumSO;

/**
 * 实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Component
@Mapper
@Repository
public interface CusStorageSumMapper extends BaseMapper<CusStorageSum> {
    /**
     * 查询信息.
     *
     * @param so 查询条件
     * @return 查询结果
     */
    List<CusStorageSum> searchCusStorageSums(CusStorageSumSO so);

    /**
     * 查询最后一条数据
     *
     * @param so
     * @return
     */
    CusStorageSum searchCusStorageSum(CusStorageSumSO so);

    /**
     * 查询分仓明细
     *
     * @return
     */
    List<WarehouseDetailsDTO> searchWarehouseDetails();
}
