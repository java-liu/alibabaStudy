package com.ljava.excel.exceltoword2;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableMerger {

    public static void main(String[] args) {
        String excelPath = "/Users/liuys/Downloads/demo1.xlsx";
        String outputPath = "/Users/liuys/Downloads/result4.docx";
        try {
            // 读取 Excel 表格
            List<TableInfo> tables = ExcelReader.readTables(excelPath);

            // 创建 Word 文档
            XWPFDocument document = new XWPFDocument();

            // 处理每个表格
            for (int i = 0; i < tables.size(); i++) {
                TableInfo tableInfo = tables.get(i);

                // 插入空行
                int emptyRows = tableInfo.getStartRow();
                for (int j = 0; j < emptyRows; j++) {
                    XWPFParagraph paragraph = document.createParagraph();
                    XWPFRun run = paragraph.createRun();
                    run.setText("\n");
                }

                // 创建表格
                XWPFTable table = document.createTable(tableInfo.getData().size(), tableInfo.getData().get(0).size());

                // 填充表格数据
                for (int j = 0; j < tableInfo.getData().size(); j++) {
                    XWPFTableRow row = table.getRow(j);
                    for (int k = 0; k < tableInfo.getData().get(j).size(); k++) {
                        XWPFTableCell cell = row.getCell(k);
                        cell.setText(tableInfo.getData().get(j).get(k));
                    }
                }

                // 应用合并单元格
                for (CellRangeAddress region : tableInfo.getMergeRegions()) {
                    applyMergeRegion(table, region, emptyRows);
                }

                // 插入分隔行
                if (i < tables.size() - 1) {
                    XWPFParagraph separator = document.createParagraph();
                    XWPFRun run = separator.createRun();
                    run.setText("这是分隔");
                }
            }

            // 保存文档
            FileOutputStream out = new FileOutputStream(outputPath);
            document.write(out);
            out.close();
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void applyMergeRegion(XWPFTable table, CellRangeAddress region, int offset) {
        int firstRow = region.getFirstRow() - offset;
        int lastRow = region.getLastRow() - offset;
        int firstCol = region.getFirstColumn();
        int lastCol = region.getLastColumn();

        System.out.println("Merging cells from (" + firstRow + ", " + firstCol + ") to (" + lastRow + ", " + lastCol + ")");

        for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            if (row == null) {
                System.out.println("Row " + rowIndex + " does not exist");
                continue; // 如果行不存在，跳过
            }
            for (int colIndex = firstCol; colIndex <= lastCol; colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                if (cell == null) {
                    System.out.println("Cell (" + rowIndex + ", " + colIndex + ") does not exist");
                    continue; // 如果单元格不存在，跳过
                }
                CTTcPr tcPr = cell.getCTTc().getTcPr();
                if (tcPr == null) {
                    tcPr = cell.getCTTc().addNewTcPr();
                }
                if (rowIndex == firstRow && colIndex == firstCol) {
                    // The first merged cell is set with RESTART merge value
                    CTVMerge vMerge = tcPr.addNewVMerge();
                    vMerge.setVal(STMerge.RESTART);
                    CTHMerge hMerge = tcPr.addNewHMerge();
                    hMerge.setVal(STMerge.RESTART);
                } else if (rowIndex == firstRow) {
                    // Cells in the first row which join (merge) the first one, are set with CONTINUE horizontally
                    CTHMerge hMerge = tcPr.addNewHMerge();
                    hMerge.setVal(STMerge.CONTINUE);
                } else if (colIndex == firstCol) {
                    // Cells in the first column which join (merge) the first one, are set with CONTINUE vertically
                    CTVMerge vMerge = tcPr.addNewVMerge();
                    vMerge.setVal(STMerge.CONTINUE);
                } else {
                    // Cells which join (merge) the first one, are set with CONTINUE both horizontally and vertically
                    CTVMerge vMerge = tcPr.addNewVMerge();
                    vMerge.setVal(STMerge.CONTINUE);
                    CTHMerge hMerge = tcPr.addNewHMerge();
                    hMerge.setVal(STMerge.CONTINUE);
                }
            }
        }
    }
}

class ExcelReader {

    public static List<TableInfo> readTables(String filePath) throws IOException {
    FileInputStream fis = new FileInputStream(filePath);
    Workbook workbook = new XSSFWorkbook(fis);
    Sheet sheet = workbook.getSheetAt(0);

    List<TableInfo> tables = new ArrayList<>();

    int currentStartRow = 0;
    while (currentStartRow < sheet.getLastRowNum()) {
        int currentEndRow = currentStartRow;
        while (currentEndRow < sheet.getLastRowNum() && !isRowEmpty(sheet.getRow(currentEndRow)) && !isSeparatorRow(sheet.getRow(currentEndRow))) {
            currentEndRow++;
        }

        if (currentEndRow > currentStartRow) {
            List<List<String>> data = new ArrayList<>();
            List<CellRangeAddress> mergeRegions = new ArrayList<>();

            for (int i = currentStartRow; i < currentEndRow; i++) {
                Row row = sheet.getRow(i);
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowData.add("");
                            break;
                    }
                }
                data.add(rowData);
            }

            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                CellRangeAddress region = sheet.getMergedRegion(i);
                if (region.getFirstRow() >= currentStartRow && region.getLastRow() < currentEndRow) {
                    mergeRegions.add(region);
                }
            }

            tables.add(new TableInfo(currentStartRow, data, mergeRegions));
        }

        // 跳过分隔行
        if (currentEndRow < sheet.getLastRowNum() && isSeparatorRow(sheet.getRow(currentEndRow))) {
            currentEndRow++;
        }

        currentStartRow = currentEndRow + 1;
    }

    workbook.close();
    fis.close();

    return tables;
}

    private static boolean isSeparatorRow(Row row) {
        if (row == null) {
            return false;
        }
        for (Cell cell : row) {
            if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().equals("这是分隔")) {
                return true;
            }
        }
        return false;
    }


    private static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (int c = 0; c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}

class TableInfo {
    private int startRow;
    private List<List<String>> data;
    private List<CellRangeAddress> mergeRegions;

    public TableInfo(int startRow, List<List<String>> data, List<CellRangeAddress> mergeRegions) {
        this.startRow = startRow;
        this.data = data;
        this.mergeRegions = mergeRegions;
    }

    public int getStartRow() {
        return startRow;
    }

    public List<List<String>> getData() {
        return data;
    }

    public List<CellRangeAddress> getMergeRegions() {
        return mergeRegions;
    }
}
