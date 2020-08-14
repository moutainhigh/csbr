package com.csbr.qingcloud.common.admin.cloud.domain.dto.platform;


import com.csbr.cloud.mybatis.annotations.CompareQuery;
import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.enums.CompareQueryEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @program: auth
 * @description: 平台信息查询参数
 * @author: yio
 * @create: 2020-07-09 17:25
 **/
@Data
@ApiModel(value = "平台信息查询参数")
public class PlatformQueryDTO extends BasePageDTO {
    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 名称 */
    @ApiModelProperty("名称")
    @LikeQuery
    private String platformName;

    /** 开始创建时间 */
    @ApiModelProperty("开始创建时间（yyyy-MM-dd HH:mm:ss）")
    @CompareQuery(sign = CompareQueryEnum.GE,field = "createTime")
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @Past(message = "开始时间格式不正确。")
    private Date startTime;

    /** 结束创建时间 */
    @ApiModelProperty("结束创建时间（yyyy-MM-dd HH:mm:ss）")
    @CompareQuery(sign = CompareQueryEnum.LE,field = "createTime")
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @Past(message = "结束时间格式不正确。")
    private Date endTime;
}
