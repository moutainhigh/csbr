
package com.csbr.qingcloud.common.admin.cloud.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 平台用户实体.
 *
 * @author Huanglh
 * @since 2020-07-15
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfAdminUser extends BaseDO {
    
    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 登录账号 */
    private String logonUser;

    /** 密码 */
    private String pwd;

    /** 注册码 */
    private String registrationCode;

    /** 姓名 */
    private String name;

    /** 业务状态(Y 有效；N：无效) */
    private String bizState;

    /** 手机号 */
    private String mobileNo;

    /** 服务标识 */
    private String serviceSign;

    /** 平台GUID */
    private String platformGuid;

    /** 平台名称 */
    private String platformName;

    /** 平台初始化 */
    private String isInit;

    /** 备注 */
    private String remark;

    /** 锁定(Y 是；N 否) */
    private String isLocked;

    /** 登录失败次数 */
    private Integer loginErrorCount;

    /** 最后登录时间 */
    private Timestamp lastLoginTime;

    /** 备用字符1 */
    private String varchar1;

    /** 备用字符2 */
    private String varchar2;

    /** 备用字符3 */
    private String varchar3;

    /** 备用字符4 */
    private String varchar4;

    /** 备用数字1 */
    private Double num1;

    /** 备用数字2 */
    private Double num2;

    /** 备用数字3 */
    private Double num3;

    /** 备用日期1 */
    private Timestamp date1;

    /** 备用日期2 */
    private Timestamp date2;

    /** 备用日期3 */
    private Timestamp date3;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

}