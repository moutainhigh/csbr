
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
 * 客户资料实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfCustomer extends BaseDO {
    
    /** 地址 */
    private String address;

    /** 加盟门店数量 */
    private Integer affiliatedShopNum;

    /** 城市(编号) */
    private String city;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 客户编码 */
    private String customerCode;

    /** 客户名称 */
    private String customerName;

    /** 直营门店数量 */
    private Integer directStoresNum;

    /** 区/县/乡(编号) */
    private String district;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 省份(编号) */
    private String province;

    /** 所属人员GUID */
    private String staffGuid;
    
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

    /** 地点(省/市/区 文本) */
    private String venue;

    /** 联系人 */
    private String contacts;

    /** 联系电话 */
    private String contactTel;

    /** 业务状态(Y 有效；S：停用) */
    private String bizState;
}