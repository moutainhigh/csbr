package com.csbr.qingcloud.user.scm.mybatis.data.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrStaffSalesIndicatorsDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrStaffSalesIndicatorsDetailSO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrStaffSalesIndicatorsDetailMapper;

/**
 * 人员销售指标明细业务逻辑.
 *
 * @author Huanglh
 * @since 2020-08-13
 */

public interface TrStaffSalesIndicatorsDetailService extends CsbrService<TrStaffSalesIndicatorsDetail> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}