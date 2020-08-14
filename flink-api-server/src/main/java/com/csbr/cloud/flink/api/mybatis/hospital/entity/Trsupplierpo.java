
package com.csbr.cloud.flink.api.mybatis.hospital.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 实体.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Data
public class Trsupplierpo {
    
    /** 系统唯一标识 */
    private String guid;
        
    /** 单据编码 */
    private String billNo;
        
    /** 采购计划单GUID */
    private String medPlanGuid;
        
    /** 采购计划单名称 */
    private String medPlanName;
        
    /** 采购计划单编号 */
    private String medPlanBillNo;
        
    /** 配送商采购订单GUID */
    private String distrbPoGuid;
        
    /** 配送商采购订单名称 */
    private String distrbPoName;
        
    /** 配送商采购订单编号 */
    private String distrbPoBillNo;
        
    /** 单据状态 */
    private String billState;
        
    /** 单据类型 */
    private String billType;
        
    /** 供应商GUID */
    private String supplierGuid;
        
    /** 供应商名称 */
    private String supplierName;
        
    /** 业务员GUID */
    private String buyerGuid;
        
    /** 业务员名称 */
    private String buyerName;
        
    /** 配送商GUID */
    private String distrbGuid;
        
    /** 配送商名称 */
    private String distrbName;
        
    /** 医疗机构GUID */
    private String medGuid;
        
    /** 医疗机构名称 */
    private String medName;
        
    /** 订货科室Guid */
    private String medDeptGuid;
        
    /** 订货科室名称 */
    private String medDeptName;
        
    /** 订货库房Guid */
    private String medStoreGuid;
        
    /** 订货库房名称 */
    private String medStoreName;
        
    /** 是否优先 */
    private String firstFlag;
        
    /** 订货日期 */
    private Timestamp billDate;
        
    /** 预计送货日期 */
    private Timestamp preDeliverDate;
        
    /** 省份 */
    private String province;
        
    /** 城市 */
    private String city;
        
    /** 区/县/乡 */
    private String district;
        
    /** 收货地址 */
    private String address;
        
    /** 要求到货日期 */
    private Timestamp planEndDate;
        
    /** 明细条数 */
    private Integer countSum;
        
    /** 明细金额 */
    private Double detailSum;
        
    /** 操作人GUID */
    private String operaterGuid;
        
    /** 操作人员姓名 */
    private String operaterName;
        
    /** 制单日期 */
    private Timestamp inputDate;
        
    /** 备注 */
    private String memo;
        
    /** 备用字符1 */
    private String varchar1;
        
    /** 备用字符2 */
    private String varchar2;
        
    /** 备用字符3 */
    private String varchar3;
        
    /** 备用数字1 */
    private Double num1;
        
    /** 备用数字2 */
    private Double num2;
        
    /** 备用数字3 */
    private Double num3;
        
    /** 备用日期1 */
    private Timestamp date1;
        
    /** 备用日期2 */
    private Timestamp date2;
        
    /** 备用日期3 */
    private Timestamp date3;
        
    /** CreateTime */
    private Timestamp createTime;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
    /** 是否补单  0 是  1 否 */
    private String supplement;
        
    /** AcceptanceDate */
    private Timestamp acceptanceDate;
        
    /** 结算方式(1 消耗后结算；2 到货结算；3 出库结算) */
    private String settleMethod;
        
}