package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: common-admin-platform-auth-service
 * @description: 用户修改参数
 * @author: yio
 * @create: 2020-07-28 13:46
 **/
@Data
@ApiModel("用户修改参数")
public class UserUpdateDTO extends UserAddDTO {

    /** 系统唯一标识 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 最后登录时间 */
    @ApiModelProperty("最后登录时间（yyyy-MM-dd HH:mm:ss）")
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @Past(message = "最后登录时间格式不正确。")
    private Date lastLoginTime;

    /** 登录失败次数 */
    @ApiModelProperty("登录失败次数")
    private Integer loginErrorCount;
}
