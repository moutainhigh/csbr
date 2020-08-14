package com.csbr.cloud.flinkserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangheng
 * @date 2020/1/7 11:38
 */
@Data
public class MFCustomerInfoEntity {

    private String guid;

    //名称
    private String chineseName;

    //企业类型
    private String enterpriseType;

    //企业类型
    private String enterpriseClass;

    //医疗机构等级
    private String medLevel;

    //省份
    private String province;

    //城市
    private String city;

    //区/县/乡
    private String district;

    //地址
    private String address;

    //联系人
    private String contacts;

    //联系电话
    private String contactTel;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
}
