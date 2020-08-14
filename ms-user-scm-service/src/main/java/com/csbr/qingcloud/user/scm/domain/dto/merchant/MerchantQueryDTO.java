package com.csbr.qingcloud.user.scm.domain.dto.merchant;

import com.csbr.cloud.mybatis.annotations.CompareQuery;
import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.enums.CompareQueryEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @program: ms-user-scm-service
 * @description: 商户查询参数
 * @author: yio
 * @create: 2020-07-30 10:20
 **/
@Data
@ApiModel("商户查询参数")
public class MerchantQueryDTO extends BasePageDTO {
    /** 系统唯一标识 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;


    /**
     * 名称
     */
    @ApiModelProperty("名称")
    @NotBlank(message = "企业名称不能为空。")
    @LikeQuery
    private String merchantName;

    /**
     * 经营者
     */
    @ApiModelProperty("经营者")
    @LikeQuery
    private String manager;

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

    /** 开始创建时间 */
    @ApiModelProperty("开始创建时间（yyyy-MM-dd HH:mm:ss）")
    @CompareQuery(sign = CompareQueryEnum.GE,field = "createTime")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Past(message = "开始时间格式不正确。")
    private Date startTime;

    /** 结束创建时间 */
    @ApiModelProperty("结束创建时间（yyyy-MM-dd HH:mm:ss）")
    @CompareQuery(sign = CompareQueryEnum.LE,field = "createTime")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Past(message = "结束时间格式不正确。")
    private Date endTime;

}
