package com.ljava.excel.poitoword;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * row:行
 * column: 列
 */
public class ExcelReader {
    // 读取 Excel 数据内容
    public static List<LinkedHashMap<Integer, String>> readExcelData(String excelPath) throws IOException {
        List<LinkedHashMap<Integer, String>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelPath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                int rowNum = row.getRowNum();
                LinkedHashMap<Integer, String> rowData = new LinkedHashMap<>();
                for (Cell cell : row) {
                    cell.setCellType(CellType.STRING);
                    int colIndex = cell.getColumnIndex();
                    rowData.put(colIndex, cell.getStringCellValue());
                    //rowData.put("Row" + rowNum + ",Col" + colIndex, cell.getStringCellValue());
                }
                data.add(rowData);
            }
        }
        data.forEach(rowData -> System.out.println(rowData));
        return data;
    }

    // 获取 Excel 的合并单元格信息
    public static List<List<CellRangeAddress>> getMergedRegions(String excelPath) throws IOException {
        List<List<CellRangeAddress>> allMergedRegions = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelPath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<CellRangeAddress> sheetMergedRegions = sheet.getMergedRegions();

            int startRow = 0;
            int endRow = 0;
            boolean inTable = false;

            for (Row row : sheet) {
                boolean isSeparator = false;
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING && "这是分隔".equals(cell.getStringCellValue())) {
                        isSeparator = true;
                        break;
                    }
                }
                if(isSeparator){
                    if (inTable) {
                        // 结束当前表格
                        addMergedRegions(allMergedRegions, sheetMergedRegions, startRow, endRow);
                        inTable = false;
                    }
                }else{
                    if (!inTable) {
                        // 开始新的表格
                        startRow = row.getRowNum();
                        inTable = true;
                    }
                    endRow = row.getRowNum();
                }
            }

            // 处理最后一个表格
            if (inTable) {
                addMergedRegions(allMergedRegions, sheetMergedRegions, startRow, endRow);
            }
        }
        return allMergedRegions;
    }

    public static List<CellRangeAddress> readExcel(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        List<CellRangeAddress> mergeRegions = new ArrayList<>();
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress region = sheet.getMergedRegion(i);
            mergeRegions.add(region);
        }

        workbook.close();
        fis.close();

        return mergeRegions;
    }

    private static void addMergedRegions(List<List<CellRangeAddress>> allMergedRegions, List<CellRangeAddress> sheetMergedRegions, int startRow, int endRow) {
        List<CellRangeAddress> currentTableMergedRegions = new ArrayList<>();
        for (CellRangeAddress mergedRegion : sheetMergedRegions) {
            if (mergedRegion.getFirstRow() >= startRow && mergedRegion.getLastRow() <= endRow) {
                currentTableMergedRegions.add(mergedRegion);
            }
        }
        allMergedRegions.add(currentTableMergedRegions);
    }
}
