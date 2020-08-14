package com.csbr.qingcloud.common.admin.cloud.data.importexport.util;

import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.util.excel.ExcelUtil;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.util.excel.ExcelXUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @program: common-admin-cloud-service
 * @description: 导入导出工具工厂
 * @author: Huanglh
 * @create: 2020-08-01 18:07
 **/
@Component
public class ImportExportUtilFactory {
    // Excel老版本后缀
    public static final String EXCEL_OLD_SUFFIX = "xls";
    // Excel新版本后缀
    public static final String EXCEL_NEW_SUFFIX = "xlsx";

    private ExcelUtil excelUtil;
    private ExcelXUtil excelXUtil;

    public ImportExportUtil getExcelUtil(String suffix) {
        // 删除开头的 .
        suffix = StringUtils.removeStart(suffix, ".");
        // 新版本Excel
        if (suffix.equals(EXCEL_NEW_SUFFIX)) {
            this.excelXUtil = ObjectUtils.isEmpty(this.excelXUtil) ? new ExcelXUtil() : this.excelXUtil;
            return this.excelXUtil;
        }
        // 老版本Excel
        if (suffix.equals(EXCEL_OLD_SUFFIX)) {
            this.excelUtil = ObjectUtils.isEmpty(this.excelUtil) ? new ExcelUtil() : this.excelUtil;
            return this.excelUtil;
        }

        throw new CsbrSystemException(SystemError.ERROR, "文件后缀名必须为 " + EXCEL_NEW_SUFFIX + " 或 " + EXCEL_OLD_SUFFIX);
    }
}
