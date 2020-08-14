package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfMenu;
import lombok.Data;

/**
 * @program: common-admin-platform-auth-service
 * @description: 角色对应的菜单功能
 * @author: yio
 * @create: 2020-07-23 12:08
 **/
@Data
public class MfRoleMenuPOJO extends MfMenu {

    /** 产品名称 */
    private String productName;
}
