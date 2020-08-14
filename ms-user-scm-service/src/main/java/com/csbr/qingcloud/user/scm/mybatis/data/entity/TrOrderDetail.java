
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
 * 订单明细实体.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class TrOrderDetail extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 订单GUID */
    private String orderGuid;
    
    /** 产品GUID */
    private String goodsGuid;
    
    /** 产品编码 */
    private String goodsCode;

    /** 产品名称 */
    private String goodsName;
    /** 规格 */
    private String goodsSpec;

    /** 单位 */
    private String unitStyle;

    /** 装箱量 */
    private Double bigUnitQty;

    /** 生产厂商 */
    private String producer;

    /** 数量 */
    private Double qty;

    /** 开票价 */
    private Double price;

    /** 金额 */
    private Double amount;

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

    /** 更新时间 */
    private Timestamp updateTime;

}