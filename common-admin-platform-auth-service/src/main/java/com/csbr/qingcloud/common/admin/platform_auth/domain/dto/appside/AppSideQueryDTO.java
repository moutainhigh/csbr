package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside;

import com.baomidou.mybatisplus.annotation.TableId;
import com.csbr.cloud.mybatis.annotations.CompareQuery;
import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.enums.CompareQueryEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 应用端查询的参数
 * @author: yio
 * @create: 2020-07-21 09:44
 **/
@Data
@ApiModel("应用端查询参数")
public class AppSideQueryDTO extends BasePageDTO {
    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 应用端名称 */
    @ApiModelProperty("应用端名称")
    @LikeQuery
    private String appSideName;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

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
