package com.csbr.qingcloud.common.admin.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: common-admin-cloud-service
 * @description: OBS配置文件
 * @author: Huanglh
 * @create: 2020-07-27 09:36
 **/
@Component
@ConfigurationProperties(prefix = "csbr.obs")
@Data
public class ObsConfig {
    /** 终端节点 */
    private String endPoint;

    /** 访问密钥 */
    private String accessKey;

    /** 访问私钥 */
    private String secretKey;

    /** 使用的通 */
    private String bucketName;
}
