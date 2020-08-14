package com.csbr.qingcloud.common.admin.cloud.obs.service.impl;

import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CommonUtil;
import com.csbr.qingcloud.common.admin.cloud.config.ObsConfig;
import com.csbr.qingcloud.common.admin.cloud.obs.service.ObsService;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: common-admin-cloud-service
 * @description:
 * @author: Huanglh
 * @create: 2020-07-30 09:45
 **/
@Service
public class ObsServiceImpl implements ObsService {
    @Autowired
    private ObsConfig obsConfig;

    private ObsClient obsClient;

    /**
     * 上传图片
     *
     * @param image
     * @return
     */
    @Override
    public CommonRes uploadImage(MultipartFile image) {
        return this.uploadImage(image, "");
    }

    /**
     * 上传图片
     *
     * @param image
     * @param prefix
     * @return
     */
    @Override
    public CommonRes uploadImage(MultipartFile image, String prefix) {
        PutObjectResult objRes = this.uploadMultipartFile(image, "image", prefix);

        return CommonRes.success("upload image success", objRes.getObjectUrl());
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Override
    public CommonRes uploadFile(MultipartFile file) {
        return this.uploadFile(file, "");
    }

    /**
     * 上传文件
     *
     * @param file
     * @param prefix
     * @return
     */
    @Override
    public CommonRes uploadFile(MultipartFile file, String prefix) {
        PutObjectResult objRes = this.uploadMultipartFile(file, "file", prefix);

        return CommonRes.success("upload file success", objRes.getObjectUrl());
    }

    /**
     * 上传视频
     *
     * @param video
     * @return
     */
    @Override
    public CommonRes uploadVideo(MultipartFile video) {
        return this.uploadVideo(video, "");
    }

    /**
     * 上传视频
     *
     * @param video
     * @param prefix
     * @return
     */
    @Override
    public CommonRes uploadVideo(MultipartFile video, String prefix) {
        PutObjectResult objRes = this.uploadMultipartFile(video, "video", prefix);

        return CommonRes.success("upload video success", objRes.getObjectUrl());
    }

    // region 私有方法

    /**
     * 因需要使用注入的 obsConfig 所以无法使用构造方法
     * 用以下方法初始化 obsClient
     *
     * @return
     */
    private ObsClient getClient() {
        if (ObjectUtils.isEmpty(this.obsClient)) {
            this.obsClient = new ObsClient(this.obsConfig.getAccessKey(),
                    this.obsConfig.getSecretKey(), this.obsConfig.getEndPoint());
        }
        return this.obsClient;
    }

    /**
     * 上传 MultipartFile 类型文件的方法
     * Content-Type:multipart/form-data
     *
     * @param obj     MultipartFile 类型文件
     * @param objType 文件类型-第一层文件夹名称
     * @param prefix  前缀，可以用来做OBS的查询操作
     * @return
     */
    private PutObjectResult uploadMultipartFile(MultipartFile obj, String objType, String prefix) {
        StringBuilder objPath = new StringBuilder();
        // 第一层文件夹-类型
        objPath.append(objType + "/");
        // 第二层文件夹-当天日期
        objPath.append(DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd/"));
        // 前缀
        if (StringUtils.isNotEmpty(prefix)) {
            objPath.append(prefix + "_");
        }
        // 生成的唯一编号
        objPath.append(this.createUniqueCode());

        // 文件后缀名处理
        String fileName = obj.getOriginalFilename();
        objPath.append(CommonUtil.getSuffix(fileName));

        // 上传到 bucket
        PutObjectResult objRes;
        try {
            objRes = getClient().putObject(obsConfig.getBucketName(), objPath.toString(), obj.getInputStream());
        } catch (IOException e) {
            throw new CsbrSystemException(SystemError.ERROR, e.getMessage());
        }
        return objRes;
    }

    /**
     * 创建唯一编号
     * 格式： 时间戳（到毫秒）_随机码（6位，左边自动补0）
     * 随机码算法：
     * 1. 当前时间戳3位毫秒左位移计算（随机位移5到10位）
     * 2. 位移后的值+1后 除 3到9之间的随机数，得数保留整数位
     *
     * @return
     */
    private String createUniqueCode() {
        StringBuilder result = new StringBuilder();
        // 获取时间戳
        result.append(DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmssSSS"));
        result.append("_");
        // 毫秒 + 1 （范围 1 ~ 1000）
        Integer baseCode = Integer.parseInt(DateFormatUtils.format(System.currentTimeMillis(), "SSS")) + 1;
        // 位移运算，得数范围 （32 ~ 1024000）
        baseCode = baseCode << RandomUtils.nextInt(5, 11);
        baseCode = Math.floorDiv(baseCode, RandomUtils.nextInt(3, 10));
        // 6位长度字符串，位数不够左边自动补0
        result.append(String.format("%06d", baseCode));

        return result.toString();
    }

    // endregion 私有方法

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("file");

        String fileName = "asd.";
        Integer lastIndex = fileName.lastIndexOf('.');
        // 没有后缀时，lastIndex 为 -1
        if (lastIndex > -1 && lastIndex < (fileName.length() - 1)) {
            sb.append(fileName.substring(lastIndex));
        }

        System.out.println(sb.toString());
    }
}
