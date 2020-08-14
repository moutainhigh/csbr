package com.csbr.qingcloud.common.admin.cloud.data.importexport.util.excel;

import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.util.CommonUtil;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.annotations.ExcelCellNum;
import com.csbr.qingcloud.common.admin.cloud.data.importexport.util.ImportExportUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * @program: common-admin-cloud-service
 * @description: excel 工具抽象类
 * @author: Huanglh
 * @create: 2020-08-01 17:12
 **/
public abstract class AbstractExcelUtil implements ImportExportUtil {

    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    protected Object getCellValue(Cell cell) {
        Object res;
        switch (cell.getCellType()) {
            case BOOLEAN:
                // 布尔值转换为字符串 Y N
                res = cell.getBooleanCellValue() ? "Y" : "N";
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 如果为日期，则转换为日期字符串
                    res = DateFormatUtils.format(DateUtil.getJavaDate(cell.getNumericCellValue()), "yyyy-MM-dd HH:mm:ss");
                } else {
                    res = Double.valueOf(cell.getNumericCellValue());
                }
                break;
            default:
                // 其他默认转字符串
                res = cell.getStringCellValue();
        }

        return res;
    }

    /**
     * 设置单元格的值
     *
     * @param dtoFields
     * @param dtoClazz
     * @param dto
     * @param cellNum
     * @param cellVal
     * @param <T>
     * @return
     */
    protected <T> T setCellValue(Field[] dtoFields, Class<T> dtoClazz, T dto, short cellNum, Object cellVal) {
        try {
            for (Field dtoField : dtoFields) {
                ExcelCellNum excelCellNum = dtoField.getAnnotation(ExcelCellNum.class);
                if (ObjectUtils.isEmpty(excelCellNum)) {
                    throw new CsbrSystemException(SystemError.IMPORT_ERROR, dtoField.getName() + " 属性没有 @ExcelCellNum注解");
                }
                // 注解的单元格序号和当前序号一致，则属性赋值
                if (cellNum == excelCellNum.number()) {
                    PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(dtoClazz, dtoField.getName());

                    // 检查属性类型，做相应转换(String Double Integer Timestamp)
                    Class<?> pClazz = pd.getPropertyType();
                    // 字符串
                    if (pClazz.isAssignableFrom(String.class)) {
                        if (cellVal instanceof Double) {
                            // 如果单元格值为 double 类型，需要做非科学计数法处理
                            pd.getWriteMethod().invoke(dto, CommonUtil.noScientificNotation((Double) cellVal));
                        } else {
                            pd.getWriteMethod().invoke(dto, String.valueOf(cellVal));
                        }
                        break;
                    }
                    // 时间戳
                    if (pClazz.isAssignableFrom(Timestamp.class)) {
                        pd.getWriteMethod().invoke(dto, Timestamp.valueOf(String.valueOf(cellVal)));
                        break;
                    }
                    // Double
                    if (pClazz.isAssignableFrom(Double.class)) {
                        if (cellVal instanceof String) {
                            pd.getWriteMethod().invoke(dto, Double.valueOf(String.valueOf(cellVal)));
                        } else {
                            pd.getWriteMethod().invoke(dto, cellVal);
                        }
                        break;
                    }
                    // Integer
                    if (pClazz.isAssignableFrom(Integer.class)) {
                        if (cellVal instanceof Double) {
                            // 如果单元格值为 double 类型，需要做非科学计数法处理
                            pd.getWriteMethod().invoke(dto, Integer.valueOf(CommonUtil.noScientificNotation((Double) cellVal)));
                        } else {
                            pd.getWriteMethod().invoke(dto, Integer.valueOf(String.valueOf(cellVal)));
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new CsbrSystemException(SystemError.IMPORT_ERROR, e.getMessage());
        }

        return dto;
    }
}
