package com.csbr.cloud.hy.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangheng
 * @date 2020/6/28 16:12
 */
@Data
public class LogisticsOpenEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //时间
    private String time;

    //物流信息
    private String processInfo;
}
