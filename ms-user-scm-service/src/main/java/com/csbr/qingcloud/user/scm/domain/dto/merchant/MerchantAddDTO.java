package com.csbr.qingcloud.user.scm.domain.dto.merchant;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.qingcloud.user.scm.util.EnterpriseCateEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.sql.Date;

/**
 * @program: ms-user-scm-service
 * @description: 商户新增参数
 * @author: yio
 * @create: 2020-07-30 09:24
 **/
@Data
@ApiModel("商户新增参数")
public class MerchantAddDTO {

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    @NotBlank(message = "企业名称不能为空。")
    private String merchantName;

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
     * 业务状态(Y 有效；S：停用)
     */
    @ApiModelProperty("业务状态(Y 有效；S：停用)")
    @Pattern(regexp = "^Y|S$", message = "状态应该为Y、S中的值。")
    private String bizState = "Y";


    /**
     * 统一社会信用代码
     */
    @ApiModelProperty("统一社会信用代码")
    private String companySocialId;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String contactTel;

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
     * 性别(M 男；F 女)
     */
    @ApiModelProperty("性别(M 男；F 女)")
    @Pattern(regexp = "^M|F$", message = "性别应该为M、F中的值。")
    private String sex;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String idCode;

    /**
     * 身份证照片({front:正面,back:背面})
     */
    @ApiModelProperty("身份证照片({front:正面,back:背面})")
    private String idPictContent;


    /**
     * 证照编号
     */
    @ApiModelProperty("证照编号")
    private String licenseGuid;

    /**
     * 经营者
     */
    @ApiModelProperty("经营者")
    private String manager;


    /**
     * 证照照片({Original：{name：不含路径有文件后缀, path：纯地址,不含文件名}Thumbnail：{name：不含路径有文件后缀,path：纯地址,不含文件名}mobile：{name：不含路径有文件后缀,path：纯地址,不含文件名} })
     */
    @ApiModelProperty("证照照片({Original：{name：不含路径有文件后缀, path：纯地址,不含文件名}Thumbnail：{name：不含路径有文件后缀,path：纯地址,不含文件名}mobile：{name：不含路径有文件后缀,path：纯地址,不含文件名} })")
    private String pictContent;

    /**
     * 证照开始日期
     */
    @ApiModelProperty("证照开始日期（yyyy-MM-dd）")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    /**
     * 证照结束日期
     */
    @ApiModelProperty("证照结束日期（yyyy-MM-dd）")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


    /**
     * 是否自由人(Y 是；N 否 默认 Y)
     */
    @ApiModelProperty("是否自由人(Y 是；N 否 默认 Y)")
    private String isFreeMan;

    /**
     * 合作企业GUID
     */
    @ApiModelProperty("合作企业GUID")
    private String coOperativesGuid;

    /**
     * 合作企业名称
     */
    @ApiModelProperty("合作企业名称")
    private String coOperativesName;


}
