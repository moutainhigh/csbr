package com.csbr.qingcloud.common.admin.cloud.domain.dto.platform;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.sql.Date;


/**
 * @program: auth
 * @description: 平台资料参数
 * @author: yio
 * @create: 2020-07-09 10:29
 **/

@Data
@ApiModel(value = "平台资料新增参数")
public class PlatformAddDTO {

    /** 名称 */
    @ApiModelProperty("名称")
    @NotBlank(message = "平台名称不能为空。")
    private String platformName;

    /** 域名 */
    @ApiModelProperty("域名")
    private String domainName;

    /** 平台Logo(图片地址) */
    @ApiModelProperty("平台Logo(图片地址)")
    private String platformLogo;

    /** 用户账号 */
    @ApiModelProperty("用户账号")
    private String logonUser;

    /** 密码 */
    @ApiModelProperty("密码")
    private String pwd;

    /** 域名有效时间 （yyyy-MM-dd）*/
    @ApiModelProperty("域名有效时间")
    private Date domainExpiryDate;

    /** 域名采购单位 */
    @ApiModelProperty("域名采购单位")
    private String domainSupplier;

    /** 服务器属性(1 公有云；2 专有云；3 私有云 默认 1) */
    @ApiModelProperty("服务器属性(1 公有云；2 专有云；3 私有云 默认 1)")
    @Pattern(regexp = "^1|2|3$", message = "服务器属性应该为1、2、3中的值。")
    private String serverAttribute="1";

    /** 服务器采购单位 */
    @ApiModelProperty("serverSupplier")
    private String serverSupplier;

    /** 服务器有效时间 */
    @ApiModelProperty("服务器有效时间（yyyy-MM-dd）")
    private Date serverExpiryDate;

    /** Https有效时间 */
    @ApiModelProperty("Https有效时间（yyyy-MM-dd）")
    private Date httpsExpiryDate;

    /** Https采购单位 */
    @ApiModelProperty("Https采购单位")
    private String httpsSupplier;

    /** 等保认证级别(1 一级；2 二级；3 三级；4 四级；5 五级) */
    @ApiModelProperty("等保认证级别(1 一级；2 二级；3 三级；4 四级；5 五级)")
    @Pattern(regexp = "^1|2|3|4|5$", message = "服务器属性应该为1、2、3、4、5中的值。")
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


}
