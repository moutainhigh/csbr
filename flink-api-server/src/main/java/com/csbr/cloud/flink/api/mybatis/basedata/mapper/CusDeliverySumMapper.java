package com.csbr.cloud.flink.api.mybatis.basedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.csbr.cloud.flink.api.mybatis.basedata.model.DeliveryDetailsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CusDeliverySum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CusDeliverySumSO;

/**
 * 实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Component
@Mapper
@Repository
public interface CusDeliverySumMapper extends BaseMapper<CusDeliverySum> {
    /**
     * 查询信息.
     *
     * @param so 查询条件
     *
     * @return 查询结果
     */
    List<CusDeliverySum> searchCusDeliverySums(CusDeliverySumSO so);

    /**
     * 查询最后一条数据
     *
     * @param so
     * @return
     */
    CusDeliverySum searchCusDeliverySum(CusDeliverySumSO so);

    /**
     * 查询运力明细
     * @return
     */
    List<DeliveryDetailsDTO> searchDeliveryDetails();
}
