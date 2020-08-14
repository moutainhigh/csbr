package com.csbr.qingcloud.user.scm.domain.dto.tenant;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;

/**
 * @program: ms-user-scm-service
 * @description: 企业证照新增参数
 * @author: yio
 * @create: 2020-07-29 11:29
 **/
@Data
@ApiModel("企业证照新增参数")
public class TenantLicAddDTO {

    /**
     * 证照编号
     */
    @ApiModelProperty("证照编号")
    @NotBlank(message = "证照编号不能为空。")
    private String licenseNum;

    /**
     * 证照类型(1 营业执照； 2 税务登记证； 3 组织机构代码证； 4 生产许可证 5 经营许可证)
     */
    @ApiModelProperty("证照类型(1 营业执照； 2 税务登记证； 3 组织机构代码证； 4 生产许可证 5 经营许可证)")
    @NotBlank(message = "证照类型不能为空。")
    @Range(min = 1, max = 5, message = "证照类型应该为1至5中的值。")
    private String licenseType;

    /**
     * 证照照片({Original：{name：不含路径有文件后缀, path：纯地址,不含文件名}Thumbnail：{name：不含路径有文件后缀,path：纯地址,不含文件名}mobile：{name：不含路径有文件后缀,path：纯地址,不含文件名} })
     */
    @ApiModelProperty("证照照片({Original：{name：不含路径有文件后缀, path：纯地址,不含文件名}Thumbnail：{name：不含路径有文件后缀,path：纯地址,不含文件名}mobile：{name：不含路径有文件后缀,path：纯地址,不含文件名} })")
    private String pictContent;

    /**
     * 是否无限期(Y 是；N 否)
     */
    @ApiModelProperty("是否无限期(Y 是；N 否)")
    @Pattern(regexp = "^Y|N$", message = "否无限期应该为Y、N中的值。")
    private String isUnlimited = "N";

    /**
     * 开始日期
     */
    @ApiModelProperty("开始日期（yyyy-MM-dd）")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    /**
     * 结束日期
     */
    @ApiModelProperty("结束日期（yyyy-MM-dd）")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


}
