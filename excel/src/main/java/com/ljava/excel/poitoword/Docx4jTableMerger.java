package com.ljava.excel.poitoword;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.*;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Docx4jTableMerger {

    public static void convertExcelToWord(XSSFSheet sheet, String outputPath) throws Docx4JException {

        List<CellRangeAddress> mergedRegions = new ArrayList<>();
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            mergedRegions.add(sheet.getMergedRegion(i));
        }
        // 创建 Word 文档
        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        Tbl table = createTableFromSheet(sheet, mergedRegions);

        // 添加表格到文档
        wordPackage.getMainDocumentPart().addObject(table);

        // 保存 Word 文档
        wordPackage.save(new File(outputPath));
    }

    private static Tbl createTableFromSheet(XSSFSheet sheet, List<CellRangeAddress> mergedRegions) {
        ObjectFactory factory = new ObjectFactory();
        Tbl table = factory.createTbl();

        // 遍历 Excel 表格行
        for (int rowIdx = 0; rowIdx <= sheet.getLastRowNum(); rowIdx++) {
            XSSFRow row = sheet.getRow(rowIdx);
            Tr tableRow = factory.createTr();

            if (row != null) {
                // 遍历每一列
                for (int colIdx = 0; colIdx < row.getLastCellNum(); colIdx++) {
                    Tc tableCell = factory.createTc();
                    XSSFCell excelCell = row.getCell(colIdx);

                    // 设置单元格文本
                    if (excelCell != null) {
                        String cellValue = excelCell.toString();
                        P paragraph = factory.createP();
                        R run = factory.createR();
                        Text text = factory.createText();

                        // 修复潜在空值或特殊字符问题
                        text.setValue(cellValue != null ? cellValue : "");
                        text.setSpace("preserve"); // 确保空格不被忽略

                        run.getContent().add(text);
                        paragraph.getContent().add(run);
                        tableCell.getContent().add(paragraph);
                    }

                    // 检查合并区域
                    for (CellRangeAddress region : mergedRegions) {
                        if (region.isInRange(rowIdx, colIdx)) {
                            applyCellMerge(tableCell, region, rowIdx, colIdx);
                        }
                    }

                    tableRow.getContent().add(tableCell);
                }
            }
            table.getContent().add(tableRow);
        }

        return table;
    }

    private static void applyCellMerge(Tc tableCell, CellRangeAddress region, int currentRow, int currentColumn) {
        // 确保 TcPr 不为空
        TcPr tcPr = tableCell.getTcPr();
        if (tcPr == null) {
            tcPr = new TcPr();
            tableCell.setTcPr(tcPr);
        }

        ObjectFactory factory = new ObjectFactory();

        // 跨列合并
        if (region.getFirstColumn() != region.getLastColumn()) {
            TcPrInner.GridSpan gridSpan = factory.createTcPrInnerGridSpan();
            gridSpan.setVal(BigInteger.valueOf(region.getLastColumn() - region.getFirstColumn() + 1));
            tcPr.setGridSpan(gridSpan);
        }

        // 跨行合并
        if (region.getFirstRow() != region.getLastRow()) {
            TcPrInner.VMerge vMerge = factory.createTcPrInnerVMerge();
            if (currentRow == region.getFirstRow()) {
                vMerge.setVal("restart");
            } else {
                vMerge.setVal("continue");
            }
            tcPr.setVMerge(vMerge);
        }
    }
}
