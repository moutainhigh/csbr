package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.MfCustomerInfo;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.MfCustomerInfoSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.MfCustomerInfoMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface MfCustomerInfoService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<MfCustomerInfo> getList(MfCustomerInfoSO so);

    /**
     * 检查用户是否已存在
     *
     * @param chineseName
     * @return 存在：true，不存在：false
     */
    Boolean checkExist(String chineseName);

    /**
     * 根据用户名称返回用户GUID
     *
     * @param chineseName
     * @return
     */
    String getGUIDByChineseName(String chineseName);
}