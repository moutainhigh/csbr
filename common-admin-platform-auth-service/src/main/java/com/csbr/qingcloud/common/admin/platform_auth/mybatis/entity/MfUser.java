
package com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 用户实体.
 *
 * @author Huanglh
 * @since 2020-07-27
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfUser extends BaseDO {
    
    /** 业务状态(Y 有效；S 停用) */
    private String bizState;

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

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 身份证号码 */
    private String idCode;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 是否初始账号(Y 是；N 否 默认 N) */
    private String isInit;

    /** 锁定(Y 是；N 否) */
    private String isLocked;

    /** 最后登录时间 */
    private Timestamp lastLoginTime;

    /** 登录失败次数 */
    private Integer loginErrorCount;

    /** 登录账号 */
    private String logonUser;

    /** 手机号 */
    private String mobileNo;

    /** 姓名 */
    private String name;

    /** 备用数字1 */
    private Double num1;

    /** 备用数字2 */
    private Double num2;

    /** 备用数字3 */
    private Double num3;

    /** 平台GUID */
    private String platformGuid;

    /** 密码 */
    private String pwd;

    /** 注册码 */
    private String registrationCode;

    /** 备注 */
    private String remark;

    /** 服务标识 */
    private String serviceSign;

    /** 修改时间 */
    @Version
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

    /** 备用字符1 */
    private String varchar1;

    /** 备用字符2 */
    private String varchar2;

    /** 备用字符3 */
    private String varchar3;

    /** 备用字符4 */
    private String varchar4;

}