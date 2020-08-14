
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
 * 人员实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfStaff extends BaseDO {
    
    /** 业务状态(Y 有效；S：停用；默认 Y) */
    private String bizState;

    /** 城市(编号) */
    private String city;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 备用日期1 */
    private Timestamp date1;

    /** 备用日期2 */
    private Timestamp date2;

    /** 备用日期3 */
    private Timestamp date3;

    /** 区/县/乡(编号) */
    private String district;

    /** 电子邮件 */
    private String email;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 助记码 */
    private String helpCode;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 是否企业初始人员(Y 是；N 否 默认 N) */
    private String isTenantInit;

    /** 工号 */
    private String jobNumber;

    /** 所属上级GUID */
    private String leaderGuid;
    
    /** 登录账号 */
    private String logonUser;

    /** 商户GUID */
    private String merchantGuid;
    
    /** 备用数字1 */
    private Double num1;

    /** 备用数字2 */
    private Double num2;

    /** 备用数字3 */
    private Double num3;

    /** 所属组织GUID */
    private String organisationGuid;

    /** 省份(编号) */
    private String province;

    /** 备注 */
    private String remark;

    /** 性别(M 男；F 女) */
    private String sex;

    /** 姓名 */
    private String staffName;

    /** 人员类型(1 组织内成员；2 备案成员；默认 1) */
    private String staffType;

    /** 企业GUID */
    private String tenantGuid;
    
    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

    /** 用户Guid */
    private String userGuid;
    
    /** 备用字符1 */
    private String varchar1;

    /** 备用字符2 */
    private String varchar2;

    /** 备用字符3 */
    private String varchar3;

    /** 备用字符4 */
    private String varchar4;

    /** 地点(省/市/区 文本) */
    private String venue;

    /** 微信号 */
    private String weChatNo;

    /** 微信OPENID */
    private String weChatOpentId;

    /** 手机号 */
    private String mobileNo;

}