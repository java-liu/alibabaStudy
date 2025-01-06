package com.ljava.excel.easytoword;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader2 {
    public static class CellData {
        public String value;
        public boolean isMerged;
        public int rowSpan;
        public int colSpan;

        public CellData(String value) {
            this.value = value;
            this.isMerged = false;
            this.rowSpan = 1;
            this.colSpan = 1;
        }
    }

    public static List<List<CellData>> readExcelWithMergedCells(String excelPath) throws IOException {
        List<List<CellData>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelPath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            // 遍历 Excel 的每一行和每个单元格
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                List<CellData> rowData = new ArrayList<>();

                if (row != null) {
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        String cellValue = (cell != null) ? cell.toString() : "";

                        CellData cellData = new CellData(cellValue);

                        // 检查是否是合并单元格
                        for (CellRangeAddress mergedRegion : sheet.getMergedRegions()) {
                            if (mergedRegion.isInRange(i, j)) {
                                cellData.isMerged = true;
                                cellData.rowSpan = mergedRegion.getLastRow() - mergedRegion.getFirstRow() + 1;
                                cellData.colSpan = mergedRegion.getLastColumn() - mergedRegion.getFirstColumn() + 1;
                                break;
                            }
                        }
                        rowData.add(cellData);
                    }
                }
                data.add(rowData);
            }
        }
        return data;
    }
}
