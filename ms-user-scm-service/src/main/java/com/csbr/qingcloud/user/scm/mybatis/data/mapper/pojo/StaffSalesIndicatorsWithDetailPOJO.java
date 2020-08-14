package com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo;

import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrStaffSalesIndicatorSO;
import lombok.Data;

/**
 * @program: ms-user-scm-service
 * @description: 人员销售指标与明细显示对象
 * @author: yio
 * @create: 2020-08-13 17:22
 **/
@Data
public class StaffSalesIndicatorsWithDetailPOJO extends TrStaffSalesIndicatorSO {

    /** 商品GUID */
    private String goodsGuid;

    /** 商品编码 */
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 销售指标 */
    private Double salesIndicators;
}
