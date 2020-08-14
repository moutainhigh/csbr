
package com.csbr.qingcloud.user.scm.mybatis.data.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 销售流量明细实体.
 *
 * @author Huanglh
 * @since 2020-08-13
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class TrSalesFlowDetail extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 企业GUID */
    private String tenantGuid;
    
    /** 销售流量GUID */
    private String salesFlowGuid;
    
    /** 商品编码 */
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 批号 */
    private String lot;

    /** 规格 */
    private String goodsSpec;

    /** 数量 */
    private Double qty;

    /** 核算单价 */
    private Double accountingPrice;

    /** 出库单价 */
    private Double outPrice;

    /** 核算金额 */
    private Double accountingAmount;

    /** 出库金额 */
    private Double outAmount;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改时间 */
    private Timestamp updateTime;

}