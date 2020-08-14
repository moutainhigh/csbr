
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
 * 人员销售指标实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class TrStaffSalesIndicators extends BaseDO {
    
    /** 创建时间 */
    private Date createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 指标使用截止日期 */
    private Timestamp endDate;


    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 工号 */
    private String jobNumber;

    /** 姓名 */
    private String name;

    /** 职位 */
    private String position;

    /** 备注 */
    private String remark;

    /** 合计销售指标 */
    private Double totalSalesIndicators;

    /** 所属组织GUID */
    private String organisationGuid;

    /** 人员GUID */
    private String staffGuid;
    
    /** 指标使用开始日期 */
    private Timestamp startDate;

    /** 企业GUID */
    private String tenantGuid;
    
    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

}