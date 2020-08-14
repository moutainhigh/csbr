package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 企业查询结果
 * @author: yio
 * @create: 2020-07-29 15:24
 **/
@Data
@ApiModel("获取企业的返回值对象")
public class TenantVO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 简称 */
    @ApiModelProperty("简称")
    private String abbreviation;

    /** 地址 */
    @ApiModelProperty("地址")
    private String address;

    /** 开户银行 */
    @ApiModelProperty("开户银行")
    private String bank;

    /** 帐号 */
    @ApiModelProperty("帐号")
    private String bankAccount;

    /** 业务状态(Y 有效；S：停用) */
    @ApiModelProperty("业务状态(Y 有效；S：停用)")
    private String bizState;

    /** 城市(编号) */
    @ApiModelProperty("城市(编号)")
    private String city;

    /** 联系电话 */
    @ApiModelProperty("联系电话")
    private String contactTel;

    /** 联系人 */
    @ApiModelProperty("联系人")
    private String contacts;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createUserId;

    /** 创建人姓名 */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /** 区/县/乡(编号) */
    @ApiModelProperty("区/县/乡(编号)")
    private String district;

    /** 企业分类(00 平台；0101 医药生产；010203 COS企业；0901 个体商户 对应mf_enterprise_cate表中的值) */
    @ApiModelProperty("企业分类(00 平台；0101 医药生产；010203 COS企业；0901 个体商户 对应mf_enterprise_cate表中的值)")
    private String enterpriseCate;

    /** 收款单位 */
    @ApiModelProperty("收款单位")
    private String enterpriseName;

    /** 账号有效期至 */
    @ApiModelProperty("账号有效期至")
    private Date expireDate;

    /** 是否删除(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否删除(Y 是；N 否 默认 N)")
    private String isDeleted;

    /** 是否三证合一(Y 是；N 否) */
    @ApiModelProperty("是否三证合一(Y 是；N 否)")
    private String isLicThreeToOne;

    /** 法人代表 */
    @ApiModelProperty("法人代表")
    private String personIncharge;

    /** 企业图标 */
    @ApiModelProperty("企业图标")
    private String pictContent;

    /** 省份(编号) */
    @ApiModelProperty("省份(编号)")
    private String province;

    /** 帐号开始日期 */
    @ApiModelProperty("帐号开始日期")
    private Date startDate;

    /** 纳税人识别码 */
    @ApiModelProperty("纳税人识别码")
    private String taxpayerId;

    /** 名称 */
    @ApiModelProperty("名称")
    private String tenantName;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /** 修改姓名 */
    @ApiModelProperty("修改姓名")
    private String updateUserName;


    /** 地点(省/市/区 文本) */
    @ApiModelProperty("地点(省/市/区 文本)")
    private String venue;
}
