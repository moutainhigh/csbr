
package com.csbr.cloud.flink.api.mybatis.transport.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 物流网点实体.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Data
public class TrMflogisticsoutlet {
    
    /** 系统唯一标识 */
    private String guid;
        
    /** 所属租户GUID */
    private String tenantGuid;
        
    /** 所属租户名称 */
    private String tenantName;
        
    /** 名称 */
    private String chineseName;
        
    /** 助记码 */
    private String helpCode;
        
    /** 省份 */
    private String province;
        
    /** 城市 */
    private String city;
        
    /** 区/县/乡 */
    private String district;
        
    /** 地点 */
    private String venue;
        
    /** 地址 */
    private String address;
        
    /** 联系人 */
    private String contacts;
        
    /** 联系电话 */
    private String contactTel;
        
    /** 备用字符1 */
    private String varchar1;
        
    /** 备用字符2 */
    private String varchar2;
        
    /** 备用字符3 */
    private String varchar3;
        
    /** 备用字符4 */
    private String varchar4;
        
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
        
    /** 创建人ID */
    private String createUserId;
        
    /** 创建人姓名 */
    private String createUserName;
        
    /** 修改人ID */
    private String updateUserId;
        
    /** 修改人姓名 */
    private String updateUserName;
        
    /** 数据版本号 */
    private String dataVersion;
        
    /** 是否删除 */
    private String isDeleted;
        
}