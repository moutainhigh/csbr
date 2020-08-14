
package com.csbr.qingcloud.user.scm.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 岗位实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfPosition extends BaseDO {
    
    /** 业务状态(Y 有效；S：停用；默认 Y) */
    private String bizState;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 顺序 */
    private Integer orderNum;

    /** 岗位 */
    private String positionName;

    /** 岗位人数 */
    private Integer positionPeopleNum;

    /** 企业GUID */
    private String tenantGuid;
    
    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

}