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
 * 物流执行跟踪表
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tr_bb_tracing")
@ApiModel(value = "物流执行跟踪实体")
public class TrBbTracingEntity implements Serializable {

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
     * 所属公司GUID
     */
    @ApiModelProperty("所属公司GUID")
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
     * 业务单据GUID
     */
    @ApiModelProperty("业务单据GUID")
    private String bBillGuid;

    /**
     * 业务单据编号
     */
    @ApiModelProperty("业务单据编号")
    private String bBillNo;

    /**
     * 业务单据唯一编号
     */
    @ApiModelProperty("业务单据唯一编号")
    private String bBillUid;

    /**
     * 订单状态
10：创建订单
40：委托承运商:
41：承运商受理:
42：发货确认:
51：司机已受理；
52：提货交接；
53：物流中转交接；
81：到货签收
82: 回单确认
     */
    @ApiModelProperty("订单状态")
    private String state;

    /**
     * 交接件数
     */
    @ApiModelProperty("交接件数")
    private BigDecimal recPkgs;

    /**
     * 交接人
     */
    @ApiModelProperty("交接人")
    private String consignee;

    /**
     * 执行方名称
     */
    @ApiModelProperty("执行方名称")
    private String excutivePartyName;

    /**
     * 执行方角色
1 货主
2 总包方
3 仓储商
4 承运商
5 物流配送
6 网点
7 终端
     */
    @ApiModelProperty("执行方角色")
    private String excutivePartyRole;

    /**
     * 交接类型
1：提货交接；2：送货交接；3：签收交接；4：司机接收；5：回单确认
     */
    @ApiModelProperty("交接类型 1：提货交接；2：送货交接；3：签收交接；4：司机接收；5：回单确认")
    private String handoverType;

    /**
     * 接收方名称
     */
    @ApiModelProperty("接收方名称")
    private String rPName;

    /**
     * 接收方司机名称
     */
    @ApiModelProperty("接收方司机名称")
    private String rPDriverName;

    /**
     * 接收方车牌号
     */
    @ApiModelProperty("接收方车牌号")
    private String rPPlateNumber;

    /**
     * 接收方联系电话
     */
    @ApiModelProperty("接收方联系电话")
    private String pRContactsTel;

    /**
     * 交接照片
{HandoverPict:
   {BBill:"提货单签收照片路径",
    PlateNumber:"车牌号照片路径",
    Invoice:"发票照片路径"}
}
     */
    @ApiModelProperty("交接照片")
    private String handoverPict;

    /**
     * 交付方名称
     */
    @ApiModelProperty("交付方名称")
    private String dPName;

    /**
     * 物流运单号
如果多个运单号，请用半角逗号","进行分隔
     */
    @ApiModelProperty("物流运单号")
    private String tracingNos;

    /**
     * 执行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("执行时间")
    private Date excuteDate;

    /**
     * 交接情况
1:正常;2:部分交接;3:拒绝
     */
    @ApiModelProperty("交接情况 1:正常;2:部分交接;3:拒绝")
    private String handoverSituation;

    /**
     * 异常原因
     */
    @ApiModelProperty("异常原因")
    private String expReason;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String memo;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


}
