package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 岗位查询结果对象
 * @author: yio
 * @create: 2020-07-31 11:44
 **/
@Data
@ApiModel("获取岗位的返回值对象")
public class PositionVO {

    /** 企业GUID */
    private String tenantGuid;

    /** 系统唯一标识 */
    private String guid;

    /** 岗位 */
    private String positionName;

    /** 顺序 */
    private Integer orderNum;

    /** 岗位人数 */
    private Integer positionPeopleNum;
    /** 业务状态(Y 有效；S：停用；默认 Y) */
    private String bizState;

    /** 是否删除(Y 是；N 否 默认 N) */
    private String isDeleted;

      /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;
}
