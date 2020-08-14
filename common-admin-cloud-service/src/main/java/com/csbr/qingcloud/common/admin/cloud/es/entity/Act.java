package com.csbr.qingcloud.common.admin.cloud.es.entity;

import com.csbr.cloud.es.annotation.ESFiled;
import com.csbr.cloud.es.enums.AnalyzerType;
import com.csbr.cloud.es.enums.FieldType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangheng
 * @date 2020/7/20 16:16
 */
@Data
public class Act {
    private static final long serialVersionUID = 1L;

    @ESFiled(type = FieldType.INTEGER)
    private Integer actId;

    /**
     * 任务标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String taskId;

    /**
     * 流程定义标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String procDefId;

    /**
     * 流程实例标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String procInstId;

    /**
     * 子流程实例标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String subInstId;

    /**
     * 节点定义标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String nodeId;

    /**
     * 节点定义名称
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String nodeName;

    /**
     * 业务主键标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String bizId;

    /**
     * 参与者标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String partId;
    /**
     * 参与者姓名
     */
    @ESFiled(type = FieldType.TEXT)
    private String partName;
    /**
     * 部门id
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String unitId;
    /**
     * 部门名称
     */
    @ESFiled(type = FieldType.TEXT)
    private String unitName;

    /**
     * 角色标识
     */
    @ESFiled(type = FieldType.KEYWORD)
    private String roleId;

    /**
     * 角色名称
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String roleName;

    /**
     * 上一活动标识
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String preActId;

    /**
     * 上一活动参与者标识
     */
    private String prePartId;

    /**
     * 上一活动参与者名称
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String prePartName;

    /**
     * 上一活动定义标识
     */
    private String preNodeId;

    /**
     * 上一活动定义名称
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String preNodeName;

    /**
     * 上一活动意见
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String preOpinion;

    /**
     * 上一活动操作项名称
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String preActionName;

    /**
     * 上一活动操作项显示名称
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String preActionLabel;

    /**
     * 创建时间
     */
    @ESFiled(type = FieldType.DATE)
    private Date createTime;

    /**
     * 截止时间
     */
    @ESFiled(type = FieldType.DATE)
    private Date deadLine;

    /**
     * 警告时间
     */
    @ESFiled(type = FieldType.DATE)
    private Date warningTime;

    /**
     * 结束时间
     */
    @ESFiled(type = FieldType.DATE)
    private Date endTime;

    /**
     * 活动红绿灯
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer actTimeStateId;

    /**
     * 活动时限
     */
    @ESFiled(type = FieldType.DOUBLE)
    private BigDecimal timeLimit;

    /**
     * 计时单位
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer timeUnit;

    /**
     * 活动时限分钟
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer timeLimitM;

    /**
     * 已用时
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer actUsedTime;

    /**
     * 剩余时
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer actRemainTime;

    /**
     * 活动时限信息
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String actLimitInfo;

    /**
     * 活动已用时间字符串
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String actUsedTimeChar;

    /**
     * 活动剩余时间字符串
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String actRemainTimeChar;

    /**
     * 累计计时标志
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer timeAddUpFlag;

    /**
     * 暂停前节点用时
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer actUsedTimeBeforeStop;

    /**
     * 恢复计时时间
     */
    @ESFiled(type = FieldType.DATE)
    private Date actRestart;

    /**
     * 已读标志
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer readFlag;

    /**
     * 已读时间
     */
    @ESFiled(type = FieldType.DATE)
    private Date readTime;

    /**
     * 批转意见
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String transOpinion;

    /**
     * 操作项名称
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String actionName;

    /**
     * 操作项显示名称
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String actionLabel;

    /**
     * 活动属性id
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer actPropertyId;

    /**
     * 活动属性名称
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String actPropertyName;

    /**
     * 抄送参与者
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String ccPart;

    /**
     * 抄送参与者名称
     */
    @ESFiled(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String ccPartName;

    /**
     * 抄送标志
     */
    @ESFiled(type = FieldType.INTEGER)
    private Integer ccFlag;

}
