package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 企业证照显示对象
 * @author: yio
 * @create: 2020-07-29 17:44
 **/
@Data
@ApiModel("获取企业证照的返回值对象")
public class TenantLicVO {

    /**
     * 系统唯一标识
     */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /**
     * 是否无限期(Y 是；N 否)
     */
    @ApiModelProperty("是否无限期(Y 是；N 否)")
    private String isUnlimited;

    /**
     * 证照编号
     */
    @ApiModelProperty("证照编号")
    private String licenseNum;

    /**
     * 证照类型(1 营业执照； 2 税务登记证； 3 组织机构代码证； 4 生产许可证 5 经营许可证)
     */
    @ApiModelProperty("证照类型(1 营业执照； 2 税务登记证； 3 组织机构代码证； 4 生产许可证 5 经营许可证)")
    private String licenseType;

    /**
     * 证照照片({Original：{name：不含路径有文件后缀, path：纯地址,不含文件名}Thumbnail：{name：不含路径有文件后缀,path：纯地址,不含文件名}mobile：{name：不含路径有文件后缀,path：纯地址,不含文件名} })
     */
    @ApiModelProperty("证照照片({Original：{name：不含路径有文件后缀, path：纯地址,不含文件名}Thumbnail：{name：不含路径有文件后缀,path：纯地址,不含文件名}mobile：{name：不含路径有文件后缀,path：纯地址,不含文件名} })")
    private String pictContent;

    /**
     * 会员GUID
     */
    @ApiModelProperty("会员GUID")
    private String tenantGuid;

    /**
     * 开始日期
     */
    @ApiModelProperty("开始日期")
    private Timestamp beginDate;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /**
     * 结束日期
     */
    @ApiModelProperty("结束日期")
    private Timestamp endDate;


    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;
}

