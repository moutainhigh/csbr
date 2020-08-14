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
 * 运输异常记录表
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tl_trans_excep_record")
@ApiModel(value = "运输异常记录实体")
public class TlTransExcepRecordEntity implements Serializable {

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
     * 所属承运商GUID
     */
    @ApiModelProperty("所属承运商GUID")
    private String carrierGuid;

    /**
     * 所属承运商名称
     */
    @ApiModelProperty("所属承运商名称")
    private String carrierName;

    /**
     * 业务单据唯一编号
     */
    @ApiModelProperty("业务单据唯一编号")
    private String bBillUid;

    /**
     * 记录日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("记录日期")
    private Date rDate;

    /**
     * 记录人
     */
    @ApiModelProperty("记录人")
    private String rStaff;

    /**
     * 反馈人
     */
    @ApiModelProperty("反馈人")
    private String reportName;

    /**
     * 异常发生时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("异常发生时间")
    private Date occurDateTime;

    /**
     * 异常发生地点（省/市/区）
     */
    @ApiModelProperty("异常发生地点（省/市/区）")
    private String occurVenue;

    /**
     * 货主名称
     */
    @ApiModelProperty("货主名称")
    private String cargoOwnerName;

    /**
     * 业务单号
     */
    @ApiModelProperty("业务单号")
    private String bBillNo;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 司机
     */
    @ApiModelProperty("司机")
    private String driver;

    /**
     * 异常类型
     */
    @ApiModelProperty("异常类型")
    private String tEType;

    /**
     * 异常信息
     */
    @ApiModelProperty("异常信息")
    private String information;

    /**
     * 异常备注
     */
    @ApiModelProperty("异常备注")
    private String exceptionMemo;

    /**
     * 异常情况图片
     */
    @ApiModelProperty("异常情况图片")
    private String pict;

    /**
     * 责任归属
1 仓库原因；2运输原因；3 货主原因；4 收货方原因
     */
    @ApiModelProperty("责任归属 1 仓库原因；2运输原因；3 货主原因；4 收货方原因")
    private String accountability;

    /**
     * 异常解决方案
     */
    @ApiModelProperty("异常解决方案")
    private String solutions;

    /**
     * 异常解决时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("异常解决时间")
    private Date solutionTime;

    /**
     * 解决人
     */
    @ApiModelProperty("解决人")
    private String solutionName;

    /**
     * 解决耗时
单位是分钟
     */
    @ApiModelProperty("解决耗时 单位是分钟")
    private Integer timeConsuming;

    /**
     * 解决备注
     */
    @ApiModelProperty("解决备注")
    private String solutionMemo;

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
