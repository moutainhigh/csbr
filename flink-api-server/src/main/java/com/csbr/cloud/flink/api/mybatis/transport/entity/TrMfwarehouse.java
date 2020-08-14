
package com.csbr.cloud.flink.api.mybatis.transport.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 仓库资料实体.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Data
public class TrMfwarehouse {
    
    /** 系统唯一标识 */
    private String guid;
        
    /** 所属租户GUID */
    private String tenantGuid;
        
    /** 所属租户名称 */
    private String tenantName;
        
    /** 所属公司GUID */
    private String corpGuid;
        
    /** 所属公司名称 */
    private String corpName;
        
    /** 所属三方仓储商GUID */
    private String warehouseCorpGuid;
        
    /** 所属三方仓储商名称 */
    private String warehouseCorpName;
        
    /** 所属物流中心GUID */
    private String logCenterGuid;
        
    /** 所属物流中心名称 */
    private String logCenterName;
        
    /** 名称 */
    private String chineseName;
        
    /** 助记码 */
    private String helpCode;
        
    /** 简称 */
    private String abbreviation;
        
    /** 物流区域GUID */
    private String logAreaGuid;
        
    /** 物流区域名称 */
    private String logAreaName;
        
    /** 业务状态(Y 有效；S：停用；E：作废) */
    private String bizState;
        
    /** 省份 */
    private String province;
        
    /** 城市 */
    private String city;
        
    /** 区/县/乡 */
    private String district;
        
    /** 地址 */
    private String address;
        
    /** 负责人 */
    private String mananger;
        
    /** 库房信息({Wlibrary:{Name:整件库，Area:10000},Slibrary:{Name:拆零库，Area:1500},Tlibrary:{Name:立体库，Area:5000},Clibrary:{Name:冷库，Area:500},Harea:{Name:待验区，Area:1000},Oarea:{Name:出库区，Area:3000} }) */
    private String sHInfor;
        
    /** 货位信息({WAl:{Name:整件货位，Num:7321},SAL:{Name:拆零货位，Num:1500},Tlibrary:{Name:立体库货位，Num:10000} }) */
    private String aLInfo;
        
    /** 设备信息({CLine:{Name:输送线，Num:3500},Stocker:{Name:堆垛机，Area:8},Sorter:{Name:分拣机，Area:10},PTrolley:{Name:拣选台车，Area:20},CHKtable:{Name:复核台，Area:10},EForklift:{Name:电动叉车，Area:3},HForklift:{Name:液压叉车，Area:12} }) */
    private String einfo;
        
    /** TotalAreaAge */
    private Double totalAreaAge;
        
    /** 仓库性质(1 自有库；2 外租库) */
    private String warehouseNature;
        
    /** 存储标准({StorageLevel:[{Ltemp:2,Utemp:8,Type:冷藏库,TotalAreaAge:100,AreaAge:100}, {Ltemp:-25,Utemp:-10,Type:冷冻库,TotalAreaAge:50,AreaAge:50}, {Ltemp:10,Utemp:20,Type:阴凉库,TotalAreaAge:1500,AreaAge:1500}, {Ltemp:10,Utemp:30,Type:常温库,TotalAreaAge:1000,AreaAge:1000}] }) */
    private String storageType;
        
    /** 是否通过GSP验证 */
    private String isgsp;
        
    /** 是否通过GMP验证 */
    private String isgmp;
        
    /** 是否通过GAP验证 */
    private String isgap;
        
    /** 联系人 */
    private String contacts;
        
    /** 联系电话 */
    private String contactTel;
        
    /** 通信地址 */
    private String mailaddr;
        
    /** 备用字符1 */
    private String varchar1;
        
    /** 备用字符2 */
    private String varchar2;
        
    /** 备用字符3 */
    private String varchar3;
        
    /** 备用字符4 */
    private String varchar4;
        
    /** Num1 */
    private Double num1;
        
    /** Num2 */
    private Double num2;
        
    /** Num3 */
    private Double num3;
        
    /** 备用日期1 */
    private Timestamp date1;
        
    /** 备用日期2 */
    private Timestamp date2;
        
    /** 备用日期3 */
    private Timestamp date3;
        
    /** 创建人ID */
    private String createUserId;
        
    /** 创建人姓名 */
    private String createUserName;
        
    /** UpdateUserId */
    private String updateUserId;
        
    /** UpdateUserName */
    private String updateUserName;
        
    /** 数据版本号 */
    private String dataVersion;
        
    /** IsDeleted */
    private String isDeleted;
        
    /** 省市区 */
    private String cityAreaName;
        
    /** CreateTime */
    private Timestamp createTime;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
    /** OutBoundAbility */
    private Double outBoundAbility;
        
    /** 日入库能力(件数) */
    private Double inBoundAblility;
        
    /** 地点 */
    private String venue;
        
    /** 仓库体积 */
    private Double totalVolume;
        
    /** Pkgs */
    private Integer pkgs;
        
    /** 库容单位(1 托盘；2 件数；3 货位) */
    private String capacityUnit;
        
    /** 库容 */
    private Double capacity;
        
    /** 授权状态(给总部授权 Y 已授权；N 未授权) */
    private String authorizationState;
        
    /** 数据来源(1 四方云；2 药链云) */
    private String dataSources;
        
    /** 来源仓库Guid */
    private String sourceWarehouseGuid;
        
    /** 来源仓库名称 */
    private String sourceWarehouseName;
        
}