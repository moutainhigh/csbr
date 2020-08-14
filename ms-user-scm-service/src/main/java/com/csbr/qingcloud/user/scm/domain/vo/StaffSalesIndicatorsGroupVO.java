package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 人员销售指标分组显示对象
 * @author: yio
 * @create: 2020-08-13 18:18
 **/
@Data
@ApiModel("人员销售指标分组显示对象")
public class StaffSalesIndicatorsGroupVO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    @TableId
    private String guid;

    /** 开始指标对应的月份（yyyyMM）*/
    @ApiModelProperty("开始指标对应的月份（yyyyMM")
    private String yearMonth;
    /** 指标使用开始日期 */
    @ApiModelProperty("指标使用开始日期")
    private Timestamp startDate;

    /** 指标使用截止日期 */
    @ApiModelProperty("指标使用截止日期")
    private Timestamp endDate;


    /** 工号 */
    @ApiModelProperty("工号")
    private String jobNumber;

    /** 姓名 */
    @ApiModelProperty("姓名")
    private String name;


    /** 职位 */
    @ApiModelProperty("职位")
    private String position;


    /** 所属组织GUID */
    @ApiModelProperty("所属组织GUID")
    private String organisationGuid;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createUserId;

    /** 创建人姓名 */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /** 修改姓名 */
    @ApiModelProperty("修改姓名")
    private String updateUserName;

    /** 合计销售指标 */
    @ApiModelProperty("合计销售指标")
    private Double totalSalesIndicators;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 人员指标明细集合 */
    private List<StaffSalesIndicatorsDetailVO> staffSalesIndicatorsDetailVOs;


}
