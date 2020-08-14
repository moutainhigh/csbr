
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
 * 订单实体.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class TrOrder extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 所属租户GUID */
    private String tenantGuid;
    
    /** 订单编号 */
    private String orderNo;

    /** 制单时间 */
    private Timestamp inputTime;

    /** 组织GUID */
    private String organisationGuid;

    /** 客户GUID */
    private String customerGuid;
    
    /** 客户编号 */
    private String customerCode;

    /** 客户联系人 */
    private String customerName;

    /** 客户联系电话 */
    private String contactTel;

    /** 客户所在省(编号) */
    private String province;

    /** 客户所在市(编号) */
    private String city;

    /** 客户所在区(编号) */
    private String district;

    /** 客户所在地(省/市/区 文本) */
    private String venue;

    /** 客户地址 */
    private String address;

    /** 客户终端属性 */
    private String terminalProperty;

    /** 品规数 */
    private Integer goodsSpecNum;

    /** 总数量 */
    private Double totalQty;

    /** 总金额 */
    private Double totalAmount;

    /** 明细条数 */
    private Integer detailNum;

    /** 备注 */
    private String memo;

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

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

}