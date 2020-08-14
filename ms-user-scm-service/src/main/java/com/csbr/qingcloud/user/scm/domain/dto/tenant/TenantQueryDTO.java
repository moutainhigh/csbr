package com.csbr.qingcloud.user.scm.domain.dto.tenant;

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
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @program: ms-user-scm-service
 * @description: 企业查询参数
 * @author: yio
 * @create: 2020-07-29 12:06
 **/
@Data
@ApiModel("企业查询参数")
public class TenantQueryDTO extends BasePageDTO {
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    @LikeQuery
    private String tenantName;

    /** 业务状态(Y 有效；S 停用) */
    @ApiModelProperty("业务状态(Y 有效；S 停用)")
    @Pattern(regexp = "^Y|S$", message = "业务状态应该为Y、S中的值。")
    private String bizState;

    /**
     * 企业分类(00 平台；0101 医药生产；010203 COS企业； 对应mf_enterprise_cate表中的值)
     */
    @ApiModelProperty("企业分类(00 平台；0101 医药生产；010203 COS企业；对应mf_enterprise_cate表中的值)")
    @Pattern(regexp = "^00|0101|010203$", message = "企业类型应该为00、0101、010203中的值。")
    private String enterpriseCate;

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
