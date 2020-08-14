package com.csbr.qingcloud.user.scm.domain.dto.position;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 岗位修改参数
 * @author: yio
 * @create: 2020-07-31 10:48
 **/
@Data
@ApiModel("岗位修改参数")
public class PositionUpdateDTO extends PositionAddDTO{


    /** 系统唯一标识 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 岗位人数 */
    @ApiModelProperty("岗位人数")
    private Integer positionPeopleNum;




}
