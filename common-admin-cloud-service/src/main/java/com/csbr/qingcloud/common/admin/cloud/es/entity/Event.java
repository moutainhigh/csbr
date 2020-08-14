package com.csbr.qingcloud.common.admin.cloud.es.entity;

import com.csbr.cloud.es.annotation.ESFiled;
import com.csbr.cloud.es.enums.AnalyzerType;
import com.csbr.cloud.es.enums.FieldType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangheng
 * @date 2020/7/20 16:13
 */
@Data
public class Event {

    private static final long serialVersionUID=1L;

    //索引名称
    private String idxName;

    @ESFiled(type = FieldType.KEYWORD)
    private Integer eventId;

    /**
     * 唯一标识码
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String uniqueCode;

    /**
     * 任务号
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String eventCode;

    /**
     * 事件来源编号
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer eventSrcId;

    /**
     * 事件来源名称
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String eventSrcName;

    /**
     * 来源分组
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String srcGroupCode;

    /**
     * 事件大类编码
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String eventTypeCode;

    /**
     * 事件大类名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String eventTypeName;

    /**
     * 事件小类编码
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String eventSubtypeCode;

    /**
     * 事件小类名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String eventSubtypeName;

    /**
     * 重要程度
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer eventGradeId;

    /**
     * 重要程度名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String eventGradeName;

    /**
     *紧急程度标识
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer eventUrgencyId;

    /**
     *紧急程度名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String eventUrgencyName;

    /**
     *事件级别标识
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer eventLevelId;

    /**
     *事件级别名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String eventLevelName;

    /**
     *事件升级标志
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer eventUpgradeFlag;

    /**
     *处置级别标识
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer dealLevelId;

    /**
     *处置级别标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String dealLevelName;

    /**
     *公众上报人名称
     */
    @ESFiled(type = FieldType.TEXT , analyzer = AnalyzerType.IK_SMART)
    private String publicReporterName;

    /**
     *公众上报人身份证号
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String publicReporterIdcard;

    /**
     *公众上报人联系方式
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String publicReporterTel;
    /**
     * 事件描述
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String eventDesc;
    /**
     * 地址
     */
    @ESFiled
    private String address;
    /**
     * 地区名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String areaRegionName;

    /**
     * 地区编码
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String areaRegionCode;

    /**
     * 社区名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String commRegionName;

    /**
     * 区编码
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String commRegionCode;

    /**
     * 街道名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String streetRegionName;

    /**
     * 街道编码
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String streetRegionCode;

    /**
     * 社区名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String regionName;

    /**
     * 社区编码
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String regionCode;

    /**
     * 经度
     */
    private BigDecimal coordX;

    /**
     * 纬度
     */
    private BigDecimal coordY;

    /**
     *坐标系
     */
    private String mapcoordinate;

    /**
     *网格员标识
     */
    private Integer griderId;

    /**
     *网格员标识
     */
    private String griderName;

    /**
     *网格员电话
     */
    private String griderPhone;

    /**
     *核实状态标识
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer verifyStateId;

    /**
     *核查状态标识
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer checkStateId;

    /**
     *事件建立时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     *流程结束时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    /**
     *延期天数
     */
    private Float postponedDays;

    /**
     *延期标志
     */
    private Integer postponedFlag;

    /**
     *受理时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date acceptTime;

    /**
     *立案时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date establishTime;

    /**
     *调度时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dispatchTime;

    /**
     *流程开始时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date procStartTime;

    /**
     *流程结束时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date procEndTime;

    /**
     *流程截止时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date procDeadLine;

    /**
     *流程警告时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date procWarningTime;

    /**
     *处置开始时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date funcBeginTime;

    /**
     *处置完成时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date funcFinishTime;

    /**
     *自处置标识
     */
    private Integer gridDealFlag;

    /**
     *跨网格标志
     */
    private Integer overGridFlag;

    /**
     *是否督办
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer pressFlag;

    /**
     *是否催办
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer hurryFlag;

    /**
     *超期标志
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer overtimeFlag;

    /**
     *活动属性
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer actPropertyId;

    /**
     *活动属性名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String actPropertyName;

    /**
     *流程实例标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String procInstId;

    /**
     *流程定义标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String procDefId;

    /**
     *事件状态
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer eventStateId;

    /**
     * 上一操作项
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String preActionName;

    /**
     * 登记人Id
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer registerId;

    /**
     * 登记人姓名
     */
    @ESFiled(analyzer = AnalyzerType.IK_SMART)
    private String registerName;

    /**
     * 回访标识：0-未回访 1-已回访 2-无法回访
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer visitorStateId;

    /**
     * 删除标识
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer deleteFlag;

    /**
     * 删除用户标识
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer deleteUserId;

    /**
     * 删除时间
     */
    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date deleteTime;

    /**
     * 是否下发督查
     * 0：否，1：是
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer overseerFlag;

    @ESFiled(type = FieldType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    @ESFiled(type = FieldType.OBJECT)
    private Act action;

}
