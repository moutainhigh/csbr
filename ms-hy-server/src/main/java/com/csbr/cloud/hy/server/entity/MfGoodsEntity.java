package com.csbr.cloud.hy.server.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品资料表
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mf_goods")
@ApiModel(value = "商品资料实体")
public class MfGoodsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统唯一标识
     */
    @TableId(value = "guid")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /**
     * 所属租户GUID
     */
    @ApiModelProperty("所属租户GUID")
    private String tenantGuid;

    /**
     * 所属公司
     */
    @ApiModelProperty("所属公司")
    private String corpGuid;

    /**
     * 所属货主GUID
     */
    @ApiModelProperty("所属货主GUID")
    private String cargoOwnerGuid;

    /**
     * 所属货主名称
     */
    @ApiModelProperty("所属货主名称")
    private String cargoOwnerName;

    /**
     * 商品编码
     */
    @ApiModelProperty("商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /**
     * 通用名称
     */
    @ApiModelProperty("通用名称")
    private String genericName;

    /**
     * 化学名称
     */
    @ApiModelProperty("化学名称")
    private String chemicalName;

    /**
     * 规格
     */
    @ApiModelProperty("规格")
    private String goodsSpec;

    /**
     * 助记码
     */
    @ApiModelProperty("助记码")
    private String helpCode;

    /**
     * 生产厂商GUID
     */
    @ApiModelProperty("生产厂商GUID")
    private String producerGuid;

    /**
     * 生产厂商
     */
    @ApiModelProperty("生产厂商")
    private String producer;

    /**
     * 代理商/售后服务机构
进口商品必有
     */
    @ApiModelProperty("代理商/售后服务机构")
    private String agentName;

    /**
     * 代理商/售后服务机构GUID
进口商品必有
     */
    @ApiModelProperty("代理商/售后服务机构GUID")
    private String agentGuid;

    /**
     * 售后服务机构
进口商品必有
     */
    @ApiModelProperty("售后服务机构")
    private String afterSalesOrganizationName;

    /**
     * 售后服务机构GUID
进口商品必有
     */
    @ApiModelProperty("售后服务机构GUID")
    private String afterSalesOrganizationGuid;

    /**
     * 商品大类
     */
    @ApiModelProperty("商品大类")
    private String goodsCate;

    /**
     * 剂型
     */
    @ApiModelProperty("剂型")
    private String dosageForm;

    /**
     * 商品类别GUID
关联商品分类表
     */
    @ApiModelProperty("商品类别GUID")
    private String goodsTypeGuid;

    /**
     * 商品类别
     */
    @ApiModelProperty("商品类别")
    private String goodsType;

    /**
     * 重要等级
标☆表示，☆数量越多，等级越高，越重要
     */
    @ApiModelProperty("重要等级")
    private String grade;

    /**
     * 批准文号/注册证号
     */
    @ApiModelProperty("批准文号/注册证号")
    private String registKey;

    /**
     * 产地
     */
    @ApiModelProperty("产地")
    private String productionAddress;

    /**
     * 长
cm/厘米
     */
    @ApiModelProperty("长 cm/厘米")
    private BigDecimal length;

    /**
     * 宽
cm/厘米
     */
    @ApiModelProperty("宽 cm/厘米")
    private BigDecimal width;

    /**
     * 高
cm/厘米
     */
    @ApiModelProperty("高 cm/厘米")
    private BigDecimal height;

    /**
     * 毛重
KG/公斤
     */
    @ApiModelProperty("毛重 KG/公斤")
    private BigDecimal grossWeight;

    /**
     * 净重
KG/公斤
     */
    @ApiModelProperty("净重 KG/公斤")
    private BigDecimal netWeight;

    /**
     * 计量单位
     */
    @ApiModelProperty("计量单位")
    private String unitStyle;

    /**
     * 大包装单位
     */
    @ApiModelProperty("大包装单位")
    private String bigUnitStyle;

    /**
     * 大包装含量
     */
    @ApiModelProperty("大包装含量")
    private BigDecimal bigUnitQty;

    /**
     * 大包装条码
     */
    @ApiModelProperty("大包装条码")
    private String bigBcd;

    /**
     * 中包装单位
     */
    @ApiModelProperty("中包装单位")
    private String middleUnitStyle;

    /**
     * 中包装含量
     */
    @ApiModelProperty("中包装含量")
    private BigDecimal middleUnitQty;

    /**
     * 中包装条码
     */
    @ApiModelProperty("中包装条码")
    private String middleBcd;

    /**
     * 小包装单位
     */
    @ApiModelProperty("小包装单位")
    private String minUnitStyle;

    /**
     * 小包装含量
     */
    @ApiModelProperty("小包装含量")
    private BigDecimal minUnitQty;

    /**
     * 小包装条码
     */
    @ApiModelProperty("小包装条码")
    private String litBcd;

    /**
     * 批号必输检验
Y 需要；N 不需要
     */
    @ApiModelProperty("批号必输检验 Y 需要；N 不需要")
    private String isLotCtrlInput;

    /**
     * 业务状态
Y 有效；S：停用；E：作废
     */
    @ApiModelProperty("业务状态 Y 有效；S：停用；E：作废")
    private String bizState;

    /**
     * 生产来源
1 国产，2 进口
     */
    @ApiModelProperty("生产来源 1 国产，2 进口")
    private String importedFlag;

    /**
     * 器械类别
1 一类；2 二类；3 三类
     */
    @ApiModelProperty("器械类别 1 一类；2 二类；3 三类")
    private String itemCatagory;

    /**
     * 高值分类
1普耗 2 跟台高值 3备货高值
     */
    @ApiModelProperty("高值分类 1普耗 2 跟台高值 3备货高值")
    private String highValueCate;

    /**
     * 产品本位码
     */
    @ApiModelProperty("产品本位码")
    private String productStandardCode;

    /**
     * 器械产品DI
     */
    @ApiModelProperty("器械产品DI")
    private String specStandardCode;

    /**
     * 近效期天数
     */
    @ApiModelProperty("近效期天数")
    private BigDecimal nExpDays;

    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    private String brand;

    /**
     * 商品照片
{goodspict:
 [{filename:filename1，
   pictcont:{Original：{name：不含路径有文件后缀,
                        path：纯地址,不含文件名
                       }
             Thumbnail：{name：不含路径有文件后缀,
                         path：纯地址,不含文件名
                        }
             mobil：{name：不含路径有文件后缀,
                     path：纯地址,不含文件名
                    }
  },
  {filename:filename2，
   pictcont:{Original：{name：不含路径有文件后缀,
                        path：纯地址,不含文件名
                       }
             Thumbnail：{name：不含路径有文件后缀,
                         path：纯地址,不含文件名
                        }
             mobil：{name：不含路径有文件后缀,
                     path：纯地址,不含文件名
                    }
  },
  ......
 ]
}
     */
    @ApiModelProperty("商品照片")
    private String pictContent;

    /**
     * 单价
     */
    @ApiModelProperty("单价")
    private BigDecimal price;

    /**
     * 是否公开
如果公开，那么APP客户下单可已操作该商品；Y:公开 N:不公开
     */
    @ApiModelProperty("是否公开 Y:公开 N:不公开")
    private String isPublic;

    /**
     * 是否冷藏品
N:不是 Y:是
     */
    @ApiModelProperty("是否冷藏品 N:不是 Y:是")
    private String isCold;

    /**
     * 是否冷冻
N:不是 Y:是
     */
    @ApiModelProperty("是否冷冻 N:不是 Y:是")
    private String isFreeze;

    /**
     * 是否灭菌
Y:是 N:否
     */
    @ApiModelProperty("是否灭菌 Y:是 N:否")
    private String isSterilization;

    /**
     * 发票监管要求
1 无要求;2 一票制 3 二票制
     */
    @ApiModelProperty("发票监管要求 1 无要求;2 一票制 3 二票制")
    private String invoiceRequire;

    /**
     * 养护周期
     */
    @ApiModelProperty("养护周期")
    private Integer curingCycle;

    /**
     * 型号
     */
    @ApiModelProperty("型号")
    private String model;

    /**
     * 是否耗材包
Y 是；N 否
     */
    @ApiModelProperty("是否耗材包 Y 是；N 否")
    private String isConsumablePackage;

    /**
     * 税率
     */
    @ApiModelProperty("税率")
    private BigDecimal taxRate;

    /**
     * 存储条件
     */
    @ApiModelProperty("存储条件")
    private String storageType;

    /**
     * 运输要求
     */
    @ApiModelProperty("运输要求")
    private String transportRequire;

    /**
     * 包装要求
     */
    @ApiModelProperty("包装要求")
    private String packagingRequire;

    /**
     * 器械分类68码
     */
    @ApiModelProperty("器械分类68码")
    private String apparatus68Code;

    /**
     * 招标平台编码
     */
    @ApiModelProperty("招标平台编码")
    private String bidPlatformCode;

    /**
     * 中标流水号
     */
    @ApiModelProperty("中标流水号")
    private String winBidSeqNo;

    /**
     * 附加属性
     */
    @ApiModelProperty("附加属性")
    private String additionalAttribute;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

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
     * 备用字符4
     */
    private String varchar4;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date1;

    /**
     * 备用日期2
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date2;

    /**
     * 备用日期3
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date3;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
