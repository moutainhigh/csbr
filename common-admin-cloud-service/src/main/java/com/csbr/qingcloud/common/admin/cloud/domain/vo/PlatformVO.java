package com.csbr.qingcloud.common.admin.cloud.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @program: auth
 * @description: 获取平台信息的返回值对象
 * @author: yio
 * @create: 2020-07-09 17:08
 **/
@Data
@ApiModel("获取平台信息的返回值对象")
public class PlatformVO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 名称 */
    @ApiModelProperty("名称")
    private String platformName;

    /** 域名 */
    @ApiModelProperty("域名")
    private String domainName;

    /** 平台Logo(图片地址) */
    @ApiModelProperty("平台Logo(图片地址)")
    private String platformLogo;

    /** 域名有效时间 */
    @ApiModelProperty("域名有效时间")
    private Date domainExpiryDate;

    /** 域名采购单位 */
    @ApiModelProperty("域名采购单位")
    private String domainSupplier;

    /** 服务器属性(1 公有云；2 专有云；3 私有云 默认 1) */
    @ApiModelProperty("服务器属性(1 公有云；2 专有云；3 私有云 默认 1)")
    private String serverAttribute;

    /** 服务器采购单位 */
    @ApiModelProperty("服务器采购单位")
    private String serverSupplier;

    /** 服务器有效时间 */
    @ApiModelProperty("服务器有效时间")
    private Date serverExpiryDate;

    /** Https有效时间 */
    @ApiModelProperty("Https有效时间")
    private Date httpsExpiryDate;

    /** Https采购单位 */
    @ApiModelProperty("Https采购单位")
    private String httpsSupplier;

    /** 等保认证级别(1 一级；2 二级；3 三级；4 四级；5 五级) */
    @ApiModelProperty("等保认证级别(1 一级；2 二级；3 三级；4 四级；5 五级)")
    private String itSecurityLevel;

    /** 等保认证机构 */
    @ApiModelProperty("等保认证机构")
    private String itSecurityNcb;

    /** 等保备案机构 */
    @ApiModelProperty("等保备案机构")
    private String itSecurityFilingOrg;

    /** 等保证照([路径1,路径2]) */
    @ApiModelProperty("等保证照([路径1,路径2])")
    private String itSecurityPicture;

    /** 是否删除(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否删除(Y 是；N 否 默认 N)")
    private String isDeleted;

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
}
