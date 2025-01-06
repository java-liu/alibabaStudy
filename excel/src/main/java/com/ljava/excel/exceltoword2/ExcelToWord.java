package com.ljava.excel.exceltoword2;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class ExcelToWord {

    public static void main(String[] args) throws Exception {
        String excelPath = "/Users/liuys/Downloads/demo2.xlsx";
        String outputPath = "/Users/liuys/Downloads/result3.docx";

        // 读取 Excel 文件并获取表格信息
        Workbook workbook = new XSSFWorkbook(new FileInputStream(excelPath));
        Sheet sheet = workbook.getSheetAt(0);

        // 获取所有表格区域及内容
        List<Map<String, Object>> tableDataList = extractTablesFromSheet(sheet);

        // 生成 Word 文件
        createWordWithTables(outputPath, tableDataList, sheet);

        workbook.close();
        System.out.println("Word 文件已成功生成！");
    }

    // 提取多个表格信息，包括单元格内容和合并单元格信息
    private static List<Map<String, Object>> extractTablesFromSheet(Sheet sheet) {
        List<Map<String, Object>> tables = new ArrayList<>();

        boolean isNewTable = true;
        int startRow = -1;

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || isRowEmpty(row)) {
                // 空行结束当前表格
                if (!isNewTable) {
                    Map<String, Object> tableInfo = new HashMap<>();
                    tableInfo.put("startRow", startRow);
                    tableInfo.put("endRow", i - 1);
                    tableInfo.put("mergedRegions", getMergedRegions(sheet, startRow, i - 1));
                    tables.add(tableInfo);
                }
                isNewTable = true;
                continue;
            }

            if (isNewTable) {
                startRow = i;
                isNewTable = false;
            }
        }

        // 处理最后一个表格
        if (!isNewTable) {
            Map<String, Object> tableInfo = new HashMap<>();
            tableInfo.put("startRow", startRow);
            tableInfo.put("endRow", sheet.getLastRowNum());
            tableInfo.put("mergedRegions", getMergedRegions(sheet, startRow, sheet.getLastRowNum()));
            tables.add(tableInfo);
        }

        return tables;
    }

    // 判断行是否为空
    private static boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    // 获取指定范围内的合并单元格信息
    private static List<CellRangeAddress> getMergedRegions(Sheet sheet, int startRow, int endRow) {
        List<CellRangeAddress> mergedRegions = new ArrayList<>();
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress region = sheet.getMergedRegion(i);
            if (region.getFirstRow() >= startRow && region.getLastRow() <= endRow) {
                mergedRegions.add(region);
            }
        }
        return mergedRegions;
    }

    // 创建 Word 文档并插入表格
    private static void createWordWithTables(String wordPath, List<Map<String, Object>> tableDataList, Sheet sheet) throws IOException {
        XWPFDocument document = new XWPFDocument();

        for (Map<String, Object> tableData : tableDataList) {
            int startRow = (int) tableData.get("startRow");
            int endRow = (int) tableData.get("endRow");
            @SuppressWarnings("unchecked")
            List<CellRangeAddress> mergedRegions = (List<CellRangeAddress>) tableData.get("mergedRegions");


            // 创建表格
            int columnCount = sheet.getRow(startRow).getLastCellNum(); // 动态获取列数
            XWPFTable table = document.createTable(endRow - startRow + 1, columnCount);

            // 填充表格内容
            for (int i = startRow; i <= endRow; i++) {
                XWPFTableRow tableRow = table.getRow(i - startRow);
                Row excelRow = sheet.getRow(i);
                for (int j = 0; j < columnCount; j++) {
                    XWPFTableCell tableCell = tableRow.getCell(j);
                    if (tableCell == null) {
                        tableCell = tableRow.addNewTableCell();
                    }

                    Cell excelCell = excelRow.getCell(j);
                    String cellValue = (excelCell == null) ? "" : excelCell.toString();
                    tableCell.setText(cellValue);

                    // 处理合并单元格
                    applyMergeRegions(table, mergedRegions, i, j);
                }
            }
        }
        // 保存 Word 文件
        try (FileOutputStream fos = new FileOutputStream(wordPath)) {
            document.write(fos);
        }
    }

    // 应用合并单元格
    private static void applyMergeRegions(XWPFTable table, List<CellRangeAddress> mergedRegions, int row, int col) {
        for (CellRangeAddress region : mergedRegions) {
            if (region.isInRange(row, col)) {
                XWPFTableCell cell = table.getRow(row - region.getFirstRow()).getCell(col);
                if (region.getFirstRow() == row && region.getFirstColumn() == col) {
                    // 起始单元格
                    cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
                    cell.getCTTc().addNewTcPr().addNewGridSpan().setVal(BigInteger.valueOf(region.getLastColumn() - region.getFirstColumn() + 1));
                } else {
                    // 被合并的单元格
                    cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
                }
            }
        }
    }
}
