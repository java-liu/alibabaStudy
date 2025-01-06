package com.ljava.excel.easytoword;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;

public class WordGenerator {
    public static void createWordWithMergedCells(String outputPath, List<LinkedHashMap<Integer, String>> data, List<CellRangeAddress> mergedRegions) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFTable table = document.createTable(data.size(), data.get(0).size());
        // 遍历每行数据
        for (int i = 0; i < data.size(); i++) {
            XWPFTableRow row = table.getRow(i);
            LinkedHashMap<Integer, String> rowData = data.get(i);

            // 遍历每个单元格
            for (int j = 0; j < rowData.size(); j++) {
                XWPFTableCell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell();
                }
                String cellValue = rowData.get(j);
                if (cellValue != null) {
                    cell.setText(cellValue);  // 设置单元格文本
                }
            }
        }

        // 处理合并区域
        for (CellRangeAddress region : mergedRegions) {
            int firstRow = region.getFirstRow();
            int lastRow = region.getLastRow();
            int firstCol = region.getFirstColumn();
            int lastCol = region.getLastColumn();

            // 设置跨行合并
            if (firstRow != lastRow) {
                XWPFTableCell startCell = table.getRow(firstRow).getCell(firstCol);
                if (startCell != null) {
                    startCell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
                    for (int i = firstRow + 1; i <= lastRow; i++) {
                        XWPFTableCell mergeCell = table.getRow(i).getCell(firstCol);
                        if (mergeCell != null) {
                            mergeCell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
                        }
                    }
                }
            }

            // 设置跨列合并
            if (firstCol != lastCol) {
                XWPFTableCell startCell = table.getRow(firstRow).getCell(firstCol);
                if (startCell != null) {
                    startCell.getCTTc().addNewTcPr().addNewGridSpan().setVal(BigInteger.valueOf(lastCol - firstCol + 1));
                }
            }
        }

        // 保存 Word 文件
        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            document.write(out);
        }
        document.close();
    }
}
