
package com.csbr.qingcloud.user.scm.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 商户资料实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfMerchant extends BaseDO {
    
    /** 简称 */
    private String abbreviation;

    /** 地址 */
    private String address;

    /** 审批状态(W 待提交 A 审批中 Y 已通过 R 驳回) */
    private String approvalState;

    /** 证照开始日期 */
    private Timestamp beginDate;

    /** 业务状态(Y 有效；S：停用) */
    private String bizState;

    /** 城市(编号) */
    private String city;

    /** 统一社会信用代码 */
    private String companySocialId;

    /** 联系电话 */
    private String contactTel;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 备用日期1 */
    private Timestamp date1;

    /** 备用日期2 */
    private Timestamp date2;

    /** 备用日期3 */
    private Timestamp date3;

    /** 区/县/乡(编号) */
    private String district;

    /** 证照结束日期 */
    private Timestamp endDate;

    /** 企业分类(00 平台；0101 医药生产；010203 COS企业；0901 个体商户 对应mf_enterprise_cate表中的值) */
    private String enterpriseCate;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 身份证号 */
    private String idCode;

    /** 身份证照片({front:正面,back:背面}) */
    private String idPictContent;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 最后审批时间 */
    private Timestamp lastApprovalTime;

    /** 最后审批人 */
    private String lastApprover;

    /** 最后审批人GUID */
    private String lastApproverGuid;
    
    /** 证照编号 */
    private String licenseNum;
    
    /** 经营者 */
    private String manager;

    /** 名称 */
    private String merchantName;

    /** 备用数字1 */
    private Double num1;

    /** 备用数字2 */
    private Double num2;

    /** 备用数字3 */
    private Double num3;

    /** 证照照片({Original：{name：不含路径有文件后缀, path：纯地址,不含文件名}Thumbnail：{name：不含路径有文件后缀,path：纯地址,不含文件名}mobile：{name：不含路径有文件后缀,path：纯地址,不含文件名} }) */
    private String pictContent;

    /** 流程ID */
    private String procDefId;

    /** 省份(编号) */
    private String province;

    /** 驳回原因 */
    private String rejectReason;

    /** 性别(M 男；F 女) */
    private String sex;

    /** 修改时间 */
    @Version
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

    /** 备用字符1 */
    private String varchar1;

    /** 备用字符2 */
    private String varchar2;

    /** 备用字符3 */
    private String varchar3;

    /** 备用字符4 */
    private String varchar4;

    /** 地点(省/市/区 文本) */
    private String venue;

    /**
     * 是否自由人(Y 是；N 否 默认 Y)
     */
    private String isFreeMan;

    /**
     * 合作企业GUID
     */
    private String coOperativesGuid;

    /**
     * 合作企业名称
     */
    private String coOperativesName;

    /**
     * 任务ID
     */
    private String taskId;


}