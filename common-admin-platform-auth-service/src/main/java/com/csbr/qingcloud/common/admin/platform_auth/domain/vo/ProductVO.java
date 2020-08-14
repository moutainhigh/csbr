package com.csbr.qingcloud.common.admin.platform_auth.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 应用端查询结果
 * @author: yio
 * @create: 2020-07-21 10:58
 **/
@Data
@ApiModel("获取产品的返回值对象")
public class ProductVO {
    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 产品名 */
    @ApiModelProperty("产品名")
    private String productName;

    /** 负责人 */
    @ApiModelProperty("负责人")
    private String mananger;

    /** 负责人电话 */
    @ApiModelProperty("负责人电话")
    private String manangerContactTel;

    /** 运营方 */
    @ApiModelProperty("运营方")
    private String operationParty;

    /** 运营联系人 */
    @ApiModelProperty("运营联系人")
    private String operationContacts;

    /** 运营方联系电话 */
    @ApiModelProperty("运营方联系电话")
    private String operationContactTel;

    /** 上线时间 */
    @ApiModelProperty("上线时间")
    private Timestamp upTime;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGGuid;

    /** 平台名称 */
    @ApiModelProperty("平台名称")
    private String platformGName;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createUserId;

    /** 创建人姓名 */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /** 修改姓名 */
    @ApiModelProperty("修改姓名")
    private String updateUserName;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    /** 是否删除(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否删除(Y 是；N 否 默认 N)")
    private String isDeleted;
}
