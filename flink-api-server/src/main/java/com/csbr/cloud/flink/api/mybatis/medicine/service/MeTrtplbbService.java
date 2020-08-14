package com.csbr.cloud.flink.api.mybatis.medicine.service;

import java.util.List;

import com.csbr.cloud.flink.api.model.response.visualdata.FlinkSupportMeCheckTrtplbbRes;
import com.csbr.cloud.flink.api.mybatis.medicine.entity.MeTrtplbb;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MeTrtplbbSO;

/**
 * 物流业务单据汇总业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-14
 */

public interface MeTrtplbbService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<MeTrtplbb> getList(MeTrtplbbSO so);

    /**
     * 检查和 Trtplbb 表的关联情况
     *
     * @param so
     * @return
     */
    FlinkSupportMeCheckTrtplbbRes checkTrtplbb(MeTrtplbbSO so);

    /**
     * 检查 Trtplbb 表 isMedOrder 是否为 Y
     *
     * @param so
     * @return
     */
    Boolean checkTrtplbbMedOrder(MeTrtplbbSO so);
}