
package com.csbr.qingcloud.user.scm.mybatis.data.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 客户销售指标实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class TrCustomerSalesIndicators extends BaseDO {

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 客户编码 */
    private String customerCode;

    /** 客户Guid */
    private String customerGuid;

    /** 客户名称 */
    private String customerName;

    /** 指标使用截止日期 */
    private Timestamp endDate;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 所属组织GUID */
    private String organisationGuid;

    /** 负责人工号 */
    private String jobNumber;

    /** 负责人名称 */
    private String name;

    /** 职位 */
    private String position;

    /** 备注 */
    private String remark;

    /** 合计销售指标 */
    private Double totalSalesIndicators;

    /** 负责人GUID */
    private String staffGuid;

    /** 指标使用开始日期 */
    private Timestamp startDate;

    /** 企业GUID */
    private String tenantGuid;

    /** 终端属性 */
    private String terminalProperty;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

}