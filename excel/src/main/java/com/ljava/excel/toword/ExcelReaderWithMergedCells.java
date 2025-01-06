package com.ljava.excel.toword;

import org.apache.commons.collections4.map.LinkedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReaderWithMergedCells {
    public static List<LinkedHashMap<Integer, String>> readExcelDataWithMergedCells(String excelPath) throws IOException {
        List<LinkedHashMap<Integer, String>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelPath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            // 获取合并单元格信息
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    LinkedHashMap<Integer, String> rowData = new LinkedHashMap<>();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            String cellValue = cell.toString();
                            rowData.put(j, cellValue);
                        }
                    }
                    data.add(rowData);
                }
            }
        }
        return data;
    }
}
