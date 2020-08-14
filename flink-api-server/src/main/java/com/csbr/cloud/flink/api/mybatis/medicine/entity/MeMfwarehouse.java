
package com.csbr.cloud.flink.api.mybatis.medicine.entity;

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
public class MeMfwarehouse {
    
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
        
    /** 仓库体积 */
    private Double totalVolume;
        
    /** 可租面积 */
    private Double canRentedArea;
        
    /** 起租面积 */
    private Double minleasedrea;
        
    /** 仓库类型(01平库；02高台库；03楼库；04地下仓库；05立体库；06坡道库；07气体库 ) */
    private String wareHouseType;
        
    /** 租赁方式(1月租；2季租；3年租) */
    private String leasingWay;
        
    /** 租赁价格 */
    private Double leasingPrice;
        
    /** 月台(1无月台；2单面月台；3双面月台；4更多面月台) */
    private String platform;
        
    /** 消防(1无；2甲类；3乙类；4丙类；5丁类；6戊类；7正在办理 ) */
    private String fireFighting;
        
    /** 结构(1砖瓦混合；2轻钢结构；3重钢结构；4钢混结构 ) */
    private String buildingStructure;
        
    /** 层高 */
    private Double floorHeight;
        
    /** 服务范围(仓储、运输、拣货、包装、加工) */
    private String serviceRange;
        
    /** 擅长货类(快消产品、家用电器、办公/文仪、医疗器械、家具、五金/配件、服饰/鞋帽、体育/户外、家居日用、农副/粮油、护肤/化妆、食品/保健、酒水/饮料、其它原材料、汽配/轮胎) */
    private String goodsCategory;
        
    /** 安保系统(中央监控、保安人员、24小时巡逻) */
    private String securitySystem;
        
    /** 消防系统(喷淋、烟感、消防栓、灭火器、消防警钟) */
    private String fireFightingSystem;
        
    /** 仓库性质(1 自有库；2 外租库) */
    private String warehouseNature;
        
    /** 库容 */
    private Double capacity;
        
    /** 库容单位(1 托盘；2 件数；3 货位) */
    private String capacityUnit;
        
    /** 01普通仓；02恒温仓；03冷藏仓；04冷冻仓；05多温仓；06露天堆场；07有棚堆场；08危化品库；09医药库；10其他 11 阴凉仓 */
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
        
    /** 仓库简介 */
    private String warehouseIntroduction;
        
    /** 仓库图片 */
    private String pictContent;
        
    /** 数据完整度 */
    private Double dataIntegrity;
        
    /** 是否发布(Y 是；N 否) */
    private String isPublish;
        
    /** 浏览量 */
    private Integer browseNum;
        
    /** 关注量 */
    private Integer concernNum;
        
    /** 品种数 */
    private Integer varietiesCount;
        
    /** 件数 */
    private Integer pkgs;
        
    /** 地点 */
    private String venue;
        
    /** 是否三方仓(Y 是；N 否) */
    private String threePartyWarehouse;
        
    /** 数据来源(1 四方云；2 药链云) */
    private String dataSources;
        
    /** 来源仓库Guid */
    private String sourceWarehouseGuid;
        
    /** 来源仓库名称 */
    private String sourceWarehouseName;
        
}