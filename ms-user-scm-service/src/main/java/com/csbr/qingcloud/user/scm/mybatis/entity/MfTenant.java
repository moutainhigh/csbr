
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
 * 会员资料实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfTenant extends BaseDO {
    
    /** 简称 */
    private String abbreviation;

    /** 地址 */
    private String address;

    /** 开户银行 */
    private String bank;

    /** 帐号 */
    private String bankAccount;

    /** 业务状态(Y 有效；S：停用) */
    private String bizState;

    /** 城市(编号) */
    private String city;

    /** 联系电话 */
    private String contactTel;

    /** 联系人 */
    private String contacts;

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

    /** 企业分类(00 平台；0101 医药生产；010203 COS企业；0901 个体商户 对应mf_enterprise_cate表中的值) */
    private String enterpriseCate;

    /** 收款单位 */
    private String enterpriseName;

    /** 账号有效期至 */
    private Timestamp expireDate;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 是否三证合一(Y 是；N 否) */
    private String isLicThreeToOne;

    /** 备用数字1 */
    private Double num1;

    /** 备用数字2 */
    private Double num2;

    /** 备用数字3 */
    private Double num3;

    /** 法人代表 */
    private String personIncharge;

    /** 企业图标 */
    private String pictContent;

    /** 省份(编号) */
    private String province;

    /** 帐号开始日期 */
    private Timestamp startDate;

    /** 纳税人识别码 */
    private String taxpayerId;

    /** 名称 */
    private String tenantName;

    /** 修改时间 */
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

    /** 地点(省/市/区 文本) */
    private String venue;

}