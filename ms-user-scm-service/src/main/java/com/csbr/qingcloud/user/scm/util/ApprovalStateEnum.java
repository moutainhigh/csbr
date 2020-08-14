package com.csbr.qingcloud.user.scm.util;

/**
 * @program: ms-user-scm-service
 * @description: 审批枚举
 * @author: yio
 * @create: 2020-07-30 14:06
 **/
public enum ApprovalStateEnum {
    WAIT_SUBMIT("W"),
    CHECKING("A"),
    PASSED("Y"),
    REJECT("R");
    private  ApprovalStateEnum(String value){
        this.value=value;
    }
    private final String value;

    public String getValue(){
        return value;
    }
}
