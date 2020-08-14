package com.csbr.qingcloud.common.admin.cloud.obs.service;

import com.csbr.cloud.common.response.CommonRes;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: common-admin-cloud-service
 * @description: 华为云OBS服务
 * @author: Huanglh
 * @create: 2020-07-30 09:38
 **/
public interface ObsService {
    /**
     * 上传图片
     *
     * @param image
     * @return
     */
    CommonRes uploadImage(MultipartFile image);

    /**
     * 上传图片
     *
     * @param image
     * @param prefix
     * @return
     */
    CommonRes uploadImage(MultipartFile image, String prefix);

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    CommonRes uploadFile(MultipartFile file);

    /**
     * 上传文件
     *
     * @param file
     * @param prefix
     * @return
     */
    CommonRes uploadFile(MultipartFile file, String prefix);

    /**
     * 上传视频
     *
     * @param video
     * @return
     */
    CommonRes uploadVideo(MultipartFile video);

    /**
     * 上传视频
     *
     * @param video
     * @param prefix
     * @return
     */
    CommonRes uploadVideo(MultipartFile video, String prefix);
}
