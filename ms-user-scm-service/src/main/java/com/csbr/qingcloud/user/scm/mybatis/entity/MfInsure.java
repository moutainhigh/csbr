
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
 * 保险资料实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfInsure extends BaseDO {

    /** 城市(编号) */
    private String city;

    /** 代码 */
    private String code;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 区/县/乡(编号) */
    private String district;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 保险类型(1 设备保险；2 公积金) */
    private String insureType;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 商户GUID */
    private String merchantGuid;

    /** 省份(编号) */
    private String province;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

    /** 地点(省/市/区 文本) */
    private String venue;

}