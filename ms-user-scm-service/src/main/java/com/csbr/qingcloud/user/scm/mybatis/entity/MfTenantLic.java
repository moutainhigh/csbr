
package com.csbr.qingcloud.user.scm.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 平台会员企业证照实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfTenantLic extends BaseDO {
    
    /** 开始日期 */
    private Timestamp beginDate;

    /** 创建时间 */
    private Timestamp createTime;

    /** 结束日期 */
    private Timestamp endDate;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 是否无限期(Y 是；N 否) */
    private String isUnlimited;

    /** 证照编号 */
    private String licenseNum;
    
    /** 证照类型(1 营业执照； 2 税务登记证； 3 组织机构代码证； 4 生产许可证 5 经营许可证) */
    private String licenseType;

    /** 证照照片({Original：{name：不含路径有文件后缀, path：纯地址,不含文件名}Thumbnail：{name：不含路径有文件后缀,path：纯地址,不含文件名}mobile：{name：不含路径有文件后缀,path：纯地址,不含文件名} }) */
    private String pictContent;

    /** 会员GUID */
    private String tenantGuid;
    
    /** 修改时间 */
    private Timestamp updateTime;

}