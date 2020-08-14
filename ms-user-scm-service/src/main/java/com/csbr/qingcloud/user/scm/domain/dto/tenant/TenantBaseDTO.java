package com.csbr.qingcloud.user.scm.domain.dto.tenant;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.qingcloud.user.scm.util.EnterpriseCateEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.List;


/**
 * @program: ms-user-scm-service
 * @description: 企业信息基本维护参数
 * @author: yio
 * @create: 2020-07-29 10:55
 **/
@Data
@ApiModel("企业信息基本维护参数")
public class TenantBaseDTO {
    /**
     * 名称
     */
    @ApiModelProperty("名称")
    @NotBlank(message = "企业名称不能为空。")
    private String tenantName;
    /**
     * 简称
     */
    @ApiModelProperty("简称")
    private String abbreviation;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 开户银行
     */
    @ApiModelProperty("开户银行")
    private String bank;

    /**
     * 帐号
     */
    @ApiModelProperty("帐号")
    private String bankAccount;

    /**
     * 业务状态(Y 有效；S：停用)
     */
    @ApiModelProperty("业务状态(Y 有效；S：停用)")
    @Pattern(regexp = "^Y|S$", message = "业务状态应该为Y、S中的值。")
    private String bizState = "Y";

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String contactTel;

    /**
     * 联系人
     */
    @ApiModelProperty("联系人")
    private String contacts;

    /**
     * 省份(编号)
     */
    @ApiModelProperty("省份(编号)")
    private String province;

    /**
     * 城市(编号)
     */
    @ApiModelProperty("城市(编号)")
    private String city;

    /**
     * 区/县/乡(编号)
     */
    @ApiModelProperty("区/县/乡(编号)")
    private String district;

    /**
     * 地点(省/市/区 文本)
     */
    @ApiModelProperty("地点(省/市/区 文本)")
    private String venue;

    /**
     * 企业分类(00 平台；0101 医药生产；010203 COS企业； 对应mf_enterprise_cate表中的值)
     */
    @ApiModelProperty("企业分类(00 平台；0101 医药生产；010203 COS企业；对应mf_enterprise_cate表中的值)")
    @NotBlank(message = "企业类型不能为空。")
    @Pattern(regexp = "^00|0101|010203$", message = "企业类型应该为00、0101、010203中的值。")
    private String enterpriseCate;

    /**
     * 收款单位
     */
    @ApiModelProperty("收款单位")
    private String enterpriseName;

    /**
     * 账号有效期至
     */
    @ApiModelProperty("账号有效期至（yyyy-MM-dd）")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;

    /**
     * 是否三证合一(Y 是；N 否)
     */
    @ApiModelProperty("是否三证合一(Y 是；N 否)")
    @Pattern(regexp = "^Y|N$", message = "是否三证合一应该为Y、N中的值。")
    private String isLicThreeToOne = "Y";

    /**
     * 法人代表
     */
    @ApiModelProperty("法人代表")
    private String personIncharge;

    /**
     * 企业图标
     */
    @ApiModelProperty("企业图标")
    private String pictContent;

    /**
     * 帐号开始日期
     */
    @ApiModelProperty("帐号开始日期（yyyy-MM-dd）")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 纳税人识别码
     */
    @ApiModelProperty("纳税人识别码")
    private String taxpayerId;



}
