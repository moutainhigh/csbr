package com.csbr.qingcloud.common.admin.cloud.data.importexport.util.excel;

import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: common-admin-cloud-service
 * @description: 老版本excel工具
 * @author: Huanglh
 * @create: 2020-07-31 17:02
 **/
public class ExcelUtil extends AbstractExcelUtil {
    /**
     * 导入数据到DTO
     *
     * @param sourceStream 导入数据流
     * @param dtoClazz     DTO的类型
     * @param startRow     数据读取起始行，从0开始
     * @return
     */
    @Override
    public <T> List<T> importDataToDto(InputStream sourceStream, Class<T> dtoClazz, Integer startRow) {
        HSSFWorkbook workbook;
        try {
            // 读取excel
            workbook = new HSSFWorkbook(sourceStream);
        } catch (Exception e) {
            throw new CsbrSystemException(SystemError.IMPORT_ERROR, e.getMessage());
        }

        // 只处理第一个sheet
        HSSFSheet sheet = workbook.getSheetAt(0);
        if (ObjectUtils.isEmpty(sheet)) {
            throw new CsbrSystemException(SystemError.IMPORT_ERROR, "未找到需导入的数据");
        }

        List<T> datas = new ArrayList<>();
        // dto属性注解信息
        Field[] dtoFields = dtoClazz.getDeclaredFields();
        // 逐行读取数据
        // 总行数，需要加上起始行数
        Integer rowCount = sheet.getLastRowNum() + startRow;
        for (int rowNum = startRow; rowNum < rowCount; rowNum++) {
            // 获取一行
            HSSFRow row = sheet.getRow(rowNum);
            if (ObjectUtils.isNotEmpty(row)) {
                T dto;
                try {
                    dto = dtoClazz.newInstance();
                } catch (Exception e) {
                    throw new CsbrSystemException(SystemError.IMPORT_ERROR, e.getMessage());
                }
                // 逐单元格读取数据
                for (short cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                    HSSFCell cell = row.getCell(cellNum);
                    // 取出单元格的值
                    Object cellVal = ObjectUtils.isEmpty(cell) ? "" : this.getCellValue(cell);
                    // 设置单元格的值
                    dto = setCellValue(dtoFields, dtoClazz, dto, cellNum, cellVal);
                }
                if (ObjectUtils.isNotEmpty(dto)) {
                    datas.add(dto);
                }
            }
        }

        // 关闭excel
        try {
            workbook.close();
        } catch (IOException e) {
            throw new CsbrSystemException(SystemError.IMPORT_ERROR, e.getMessage());
        }

        return datas;
    }

}
