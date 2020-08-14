package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 产品新增参数
 * @author: yio
 * @create: 2020-07-21 09:27
 **/
@Data
@ApiModel("产品新增参数")
public class ProductAddDTO {



    /** 产品名 */
    @ApiModelProperty("产品名")
    @NotBlank(message = "产品名不能为空。")
    private String productName;

    /** 负责人 */
    @ApiModelProperty("负责人")
    private String mananger;

    /** 负责人电话 */
    @ApiModelProperty("负责人电话")
    private String manangerContactTel;

    /** 运营方 */
    @ApiModelProperty("运营方")
    private String operationParty;

    /** 运营联系人 */
    @ApiModelProperty("运营联系人")
    private String operationContacts;

    /** 运营方联系电话 */
    @ApiModelProperty("运营方联系电话")
    private String operationContactTel;

    /** 上线时间 */
    @ApiModelProperty("上线时间")
    private Timestamp upTime;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;



}
