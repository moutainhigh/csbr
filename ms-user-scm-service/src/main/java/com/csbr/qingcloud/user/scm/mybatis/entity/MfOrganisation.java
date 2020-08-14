
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
 * 组织架构信息实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfOrganisation extends BaseDO {

    /**
     * 地址
     */
    private String address;

    /**
     * 业务状态(Y 有效；S：停用；默认 Y)
     */
    private String bizState;

    /**
     * 城市(编号)
     */
    private String city;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 创建人
     */
    private String createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 区/县/乡(编号)
     */
    private String district;

    /**
     * 系统唯一标识
     */
    @TableId
    private String guid;

    /**
     * 是否删除(Y 是；N 否 默认 N)
     */
    @TableLogic
    private String isDeleted;

    /**
     * 组织编码
     */
    private String organisationCode;

    /**
     * 组织名称
     */
    private String organisationName;

    /**
     * 省份(编号)
     */
    private String province;

    /**
     * 企业GUID
     */
    private String tenantGuid;

    /**
     * 修改时间
     */
    private Timestamp updateTime;

    /**
     * 修改人
     */
    private String updateUserId;

    /**
     * 修改姓名
     */
    private String updateUserName;

    /**
     * 地点(省/市/区 文本)
     */
    private String venue;

    /**
     * 上级组织GUID
     */
    private String parentGuid;

    /**
     * 序号
     */
    private String orderNum;
}