package com.csbr.qingcloud.common.admin.cloud.data.importexport.util;

import java.io.InputStream;
import java.util.List;

/**
 * @program: common-admin-cloud-service
 * @description: 输出输入工具接口
 * @author: Huanglh
 * @create: 2020-08-01 17:00
 **/
public interface ImportExportUtil {
    /**
     * 导入数据到DTO
     *
     * @param sourceStream 导入数据流
     * @param dtoClazz     DTO的类型
     * @param startRow     数据读取起始行，从0开始
     * @param <T>
     * @return
     */
    <T> List<T> importDataToDto(InputStream sourceStream, Class<T> dtoClazz, Integer startRow);
}
