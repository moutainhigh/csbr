
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
 * 销售流量实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class TrSalesFlow extends BaseDO {
    
    /** 核算金额 */
    private Double accountingAmount;

    /** 核算单价 */
    private Double accountingPrice;

    /** 城市(编号) */
    private String city;

    /** 公司名称 */
    private String corpName;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 购入客户GUID */
    private String customerGuid;

    /** 客户编码 */
    private  String customerCode;
    /** 购入客户名称 */
    private String customerName;

    /** 区/县/乡(编号) */
    private String district;

    /** 商品编码 */
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 规格 */
    private String goodsSpec;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 批号 */
    private String lot;

    /** 所属经理GUID */
    private String managerGuid;
    
    /** 所属经理工号 */
    private String managerJobNumber;

    /** 所属经理姓名 */
    private String managerName;

    /** 所属组织GUID */
    private String organisationGuid;

    /** 所属组织编码 */
    private String organisationCode;

    /** 所属组织名称 */
    private String organisationName;

    /** 出库金额 */
    private Double outAmount;

    /** 出库单价 */
    private Double outPrice;

    /** 省份(编号) */
    private String province;

    /** 数量 */
    private Double qty;

    /** 销售日期 */
    private Timestamp salesDate;

    /** 所属人员GUID */
    private String staffGuid;
    
    /** 所属人员工号 */
    private String staffJobNumber;

    /** 所属人员姓名 */
    private String staffName;

    /** 终端属性 */
    private String terminalProperty;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

    /** 地点(省/市/区 文本) */
    private String venue;

    /** 企业GUID */
    private String tenantGuid;

    /** 品规数 */
    private Integer goods_spec_num;

    /** 批号数 */
    private Integer lot_num;

    /** 总数量 */
    private Double total_qty;

    /** 总核算金额 */
    private Double total_accounting_amount;

}