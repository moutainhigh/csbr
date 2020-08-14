package com.csbr.qingcloud.common.admin.cloud.data.importexport.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel 文件中单元格的序号
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCellNum {
    // 序号，从 0 开始
    short number();
}
