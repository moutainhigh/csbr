package com.csbr.qingcloud.user.scm.util;

/**
 * @program: ms-user-scm-service
 * @description: 企业分类枚举
 * @author: yio
 * @create: 2020-07-29 11:04
 **/
public enum  EnterpriseCateEnum {
    PLATFORM("00"),
    PHARMACEUTICAL_MANUFACTURERS("0101"),
    COS_ENTERPRISE("010203"),
    INDIVIDUAL_MERCHANTS("0901");
    private  EnterpriseCateEnum(String value){
        this.value=value;
    }
    private final String value;

    public String getValue(){
        return value;
    }
}
