package com.csbr.qingcloud.user.scm.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 商户银行账号查询实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@ApiModel(value = "商户银行账号查询实体")
public class MfMerchantBankAccountSO extends BasePageDTO {

    /** 商户GUID */
    @ApiModelProperty("商户GUID")
    private String merchantGuid;

}
