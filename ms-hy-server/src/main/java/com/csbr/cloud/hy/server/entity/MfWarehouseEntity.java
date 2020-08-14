package com.csbr.cloud.hy.server.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  仓库资料表
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mf_warehouse")
@ApiModel(value = "仓库资料实体")
public class MfWarehouseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统唯一标识
     */
    @TableId(value = "guid")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /**
     * 所属公司GUID
     */
    @ApiModelProperty("所属公司GUID")
    private String corpGuid;

    /**
     * 所属公司名称
     */
    @ApiModelProperty("所属公司名称")
    private String corpName;

    /**
     * 所属物流中心GUID
     */
    @ApiModelProperty("所属物流中心GUID")
    private String logCenterGuid;

    /**
     * 所属物流中心名称
     */
    @ApiModelProperty("所属物流中心名称")
    private String logCenterName;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String chineseName;

    /**
     * 所属租户GUID
     */
    @ApiModelProperty("所属租户GUID")
    private String tenantGuid;

    /**
     * 助记码
     */
    @ApiModelProperty("助记码")
    private String helpCode;

    /**
     * 简称
     */
    @ApiModelProperty("简称")
    private String abbreviation;

    /**
     * 物流区域GUID
     */
    @ApiModelProperty("物流区域GUID")
    private String logAreaGuid;

    /**
     * 物流区域名称
     */
    @ApiModelProperty("物流区域名称")
    private String logAreaName;

    /**
     * 业务状态
Y 有效；S：停用；E：作废
     */
    @ApiModelProperty("业务状态 Y 有效；S：停用；E：作废")
    private String bizState;

    /**
     * 省份
     */
    @ApiModelProperty("省份")
    private String province;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String city;

    /**
     * 区/县/乡
     */
    @ApiModelProperty("区/县/乡")
    private String district;

    /**
     * 地点
     */
    @ApiModelProperty("地点")
    private String venue;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 负责人
     */
    @ApiModelProperty("负责人")
    private String mananger;

    /**
     * 库房信息
{Wlibrary:{Name:"整件库"，Area:10000},
 Slibrary:{Name:"拆零库"，Area:1500},
 Tlibrary:{Name:"立体库"，Area:5000},
 Clibrary:{Name:"冷库"，Area:500},
 Harea:{Name:"待验区"，Area:1000},
 Oarea:{Name:"出库区"，Area:3000}
}
     */
    @ApiModelProperty("库房信息")
    private String sHInfor;

    /**
     * 货位信息
{WAl:{Name:"整件货位"，Num:7321},
 SAL:{Name:"拆零货位"，Num:1500},
 Tlibrary:{Name:"立体库货位"，Num:10000}
}
     */
    @ApiModelProperty("货位信息")
    private String aLInfo;

    /**
     * 设备信息
{CLine:{Name:"输送线"，Num:3500},
 Stocker:{Name:"堆垛机"，Area:8},
 Sorter:{Name:"分拣机"，Area:10},
 PTrolley:{Name:"拣选台车"，Area:20},
 CHKtable:{Name:"复核台"，Area:10},
 EForklift:{Name:"电动叉车"，Area:3},
 HForklift:{Name:"液压叉车"，Area:12}
}
     */
    @ApiModelProperty("设备信息")
    private String eInfo;

    /**
     * 仓储总面积
     */
    @ApiModelProperty("仓储总面积")
    private BigDecimal totalAreaAge;

    /**
     * 仓储体积
     */
    @ApiModelProperty("仓储体积")
    private BigDecimal totalVolume;

    /**
     * 可租面积
     */
    @ApiModelProperty("可租面积")
    private BigDecimal canRentedArea;

    /**
     * 起租面积
     */
    @ApiModelProperty("起租面积")
    private BigDecimal minleasedrea;

    /**
     * 仓库类型
01平库；02高台库；03楼库；04地下仓库；05立体库；06坡道库；07气体库 
     */
    @ApiModelProperty("仓库类型 01平库；02高台库；03楼库；04地下仓库；05立体库；06坡道库；07气体库")
    private String warehouseType;

    /**
     * 租赁方式
1月租；2季租；3年租
     */
    @ApiModelProperty("租赁方式 1月租；2季租；3年租")
    private String leasingWay;

    /**
     * 租赁价格
     */
    @ApiModelProperty("租赁价格")
    private BigDecimal leasingPrice;

    /**
     * 月台
1无月台；2单面月台；3双面月台；4更多面月台
     */
    @ApiModelProperty("月台 1无月台；2单面月台；3双面月台；4更多面月台")
    private String platform;

    /**
     * 消防
1无；2甲类；3乙类；4丙类；5丁类；6戊类；7正在办理 
     */
    @ApiModelProperty("消防 1无；2甲类；3乙类；4丙类；5丁类；6戊类；7正在办理")
    private String fireFighting;

    /**
     * 结构
1砖瓦混合；2轻钢结构；3重钢结构；4钢混结构 
     */
    @ApiModelProperty("结构 1砖瓦混合；2轻钢结构；3重钢结构；4钢混结构")
    private String buildingStructure;

    /**
     * 层高
     */
    @ApiModelProperty("层高")
    private BigDecimal floorHeight;

    /**
     * 服务范围
仓储、运输、拣货、包装、加工
     */
    @ApiModelProperty("服务范围")
    private String serviceRange;

    /**
     * 擅长货类
快消产品、家用电器、办公/文仪、医疗器械、家具、五金/配件、服饰/鞋帽、体育/户外、家居日用、农副/粮油、护肤/化妆、食品/保健、酒水/饮料、其它原材料、汽配/轮胎
     */
    @ApiModelProperty("擅长货类")
    private String goodsCategory;

    /**
     * 安保系统
中央监控、保安人员、24小时巡逻
     */
    @ApiModelProperty("安保系统")
    private String securitySystem;

    /**
     * 消防系统
喷淋、烟感、消防栓、灭火器、消防警钟
     */
    @ApiModelProperty("消防系统")
    private String fireFightingSystem;

    /**
     * 仓库性质
1 自有库；2 外租库
     */
    @ApiModelProperty("仓库性质 1 自有库；2 外租库")
    private String warehouseNature;

    /**
     * 库容
     */
    @ApiModelProperty("库容")
    private BigDecimal capacity;

    /**
     * 库容单位
1 托盘；2 件数；3 货位
     */
    @ApiModelProperty("库容单位 1 托盘；2 件数；3 货位")
    private String capacityUnit;

    /**
     * 所属三方仓储商GUID
     */
    @ApiModelProperty("所属三方仓储商GUID")
    private String warehouseCorpGuid;

    /**
     * 所属三方仓储商名称
     */
    @ApiModelProperty("所属三方仓储商名称")
    private String warehouseCorpName;

    /**
     * 存储标准
01普通仓；02恒温仓；03冷藏仓；04冷冻仓；05多温仓；06露天堆场；
07有棚堆场；08危化品库；09医药库；10其他
     */
    @ApiModelProperty("存储标准")
    private String storageType;

    /**
     * 是否通过GSP验证
     */
    @ApiModelProperty("是否通过GSP验证")
    private String isGsp;

    /**
     * 是否通过GMP验证
     */
    @ApiModelProperty("是否通过GMP验证")
    private String isGmp;

    /**
     * 是否通过GAP验证
     */
    @ApiModelProperty("是否通过GAP验证")
    private String isGap;

    /**
     * 联系人
     */
    @ApiModelProperty("联系人")
    private String contacts;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String contactTel;

    /**
     * 通信地址
     */
    @ApiModelProperty("通信地址")
    private String mailaddr;

    /**
     * 日出库能力
件数
     */
    @ApiModelProperty("日出库能力 件数")
    private BigDecimal outBoundAbility;

    /**
     * 日入库能力
件数
     */
    @ApiModelProperty("日入库能力 件数")
    private BigDecimal inBoundAbility;

    /**
     * 仓库简介
     */
    @ApiModelProperty("仓库简介")
    private String warehouseIntroduction;

    /**
     * 仓库图片
{Original：{name：不含路径有文件后缀,
            path：纯地址,不含文件名
       }
 Thumbnail：{name：不含路径有文件后缀,
             path：纯地址,不含文件名
       }
 mobile：{name：不含路径有文件后缀,
         path：纯地址,不含文件名
       }
}
     */
    @ApiModelProperty("仓库图片")
    private String pictContent;

    /**
     * 数据完整度
     */
    @ApiModelProperty("数据完整度")
    private BigDecimal dataIntegrity;

    /**
     * 是否发布
Y 是；N 否
     */
    @ApiModelProperty("是否发布 Y 是；N 否")
    private String isPublish;

    /**
     * 浏览量
     */
    @ApiModelProperty("浏览量")
    private Integer browseNum;

    /**
     * 关注量
     */
    @ApiModelProperty("关注量")
    private Integer concernNum;

    /**
     * 品种数
     */
    @ApiModelProperty("品种数")
    private Integer varietiesCount;

    /**
     * 件数
     */
    @ApiModelProperty("件数")
    private Integer pkgs;

    /**
     * 是否三方仓
Y 是；N 否
     */
    @ApiModelProperty("是否三方仓 Y 是；N 否")
    private String threePartyWarehouse;

    /**
     * 数据来源
1 四方云；2 药链云
     */
    @ApiModelProperty("数据来源 1 四方云；2 药链云")
    private String dataSources;

    /**
     * 来源仓库Guid
     */
    @ApiModelProperty("来源仓库Guid")
    private String sourceWarehouseGuid;

    /**
     * 提货时限
     */
    @ApiModelProperty("提货时限")
    private Integer deliveryTimeLimit;

    /**
     * 来源
[
    {
        "Platform": "平台(01.医链云 02.药链云 03.四方云)",
        "PlatformType": "1.共有云 2.私有云",
        "ServiceObjects": "平台服务对象（传世、医药、七曜、新里程、哈药）",
        "DataSources": "WMS、TMS、SAP",
        "Guid": "平台唯一码"
    }
]
     */
    @ApiModelProperty("来源")
    private String applySource;

    /**
     * 备用字符1
     */
    private String varchar1;

    /**
     * 备用字符2
     */
    private String varchar2;

    /**
     * 备用字符3
     */
    private String varchar3;

    /**
     * 备用数字1
     */
    private BigDecimal num1;

    /**
     * 备用数字2
     */
    private BigDecimal num2;

    /**
     * 备用数字3
     */
    private BigDecimal num3;

    /**
     * 备用日期1
     */
    private Date date1;

    /**
     * 备用日期2
     */
    private Date date2;

    /**
     * 备用日期3
     */
    private Date date3;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;


}
