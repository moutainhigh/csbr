package com.csbr.cloud.flink.api.mybatis.transport.service;

import java.util.List;

import com.csbr.cloud.flink.api.model.response.visualdata.FlinkSupportTrCheckTrtplbbRes;
import com.csbr.cloud.flink.api.mybatis.transport.entity.TrTrtplbb;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrTrtplbbSO;

/**
 * 物流业务单据汇总业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-14
 */

public interface TrTrtplbbService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<TrTrtplbb> getList(TrTrtplbbSO so);

    /**
     * 检查和 Trtplbb 表的关联情况
     *
     * @param so
     * @return
     */
    FlinkSupportTrCheckTrtplbbRes checkTrtplbb(TrTrtplbbSO so);

    /**
     * 检查 Trtplbb 表 isMedOrder 是否为 Y
     *
     * @param so
     * @return
     */
    Boolean checkTrtplbbMedOrder(TrTrtplbbSO so);
}