package com.ljava.excel.poitoword;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WordGenerator {
    public static XWPFDocument createWordWithMergedCells(XWPFDocument document, List<List<LinkedHashMap<Integer, String>>> sumData, List<List<CellRangeAddress>> mergedRegionsList) throws IOException {
        //XWPFDocument document = new XWPFDocument();
        // 创建自定义标题样式
        addCustomHeadingStyle(document, "CustomHeading1", 1);
        addCustomHeadingStyle(document, "CustomHeading2", 2);
        AtomicInteger x = new AtomicInteger(1);
        for (int tableIndex = 0; tableIndex < sumData.size(); tableIndex++) {
            List<LinkedHashMap<Integer, String>> data = sumData.get(tableIndex);
            List<CellRangeAddress> mergedRegions = mergedRegionsList.get(tableIndex);

            // Add a paragraph between tables for spacing
            if (tableIndex > 0) {
                XWPFParagraph paragraph = document.createParagraph();
                paragraph.setSpacingAfter(200); // Adjust the spacing as needed
            }
            //sumData.forEach(data -> {
            // 插入第一个表格后的标题
            XWPFParagraph title1 = document.createParagraph();
            title1.setAlignment(ParagraphAlignment.CENTER);// 设置标题居中
            //title1.setStyle("CustomHeading1");
            XWPFRun run = title1.createRun();
            run.setText("这是第" + x.get() + "个表格的标题");
            run.setBold(true); // 设置加粗
            run.setFontSize(18); // 设置字体大小
            XWPFTable table = document.createTable(data.size(), data.get(0).size());

            // 遍历每行数据
            for (int i = 0; i < data.size(); i++) {
                XWPFTableRow row = table.getRow(i);

                LinkedHashMap<Integer, String> rowData = data.get(i);
                        /*if (row == null) {
                            row = table.createRow();
                        }*/
                // 遍历每列数据
                for (int j = 0; j < rowData.size(); j++) {
                    XWPFTableCell cell = row.getCell(j);
                    if (cell == null) {
                        cell = row.createCell();
                    }
                    cell.setText(rowData.get(j));

                    // 设置表头居中
                    /*if (i == 0) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            paragraph.setAlignment(ParagraphAlignment.CENTER);
                        }
                    } else {
                        // 设置第一列的对齐方式
                        if (j == 0) {
                            if (i == data.size() - 1) {
                                for (XWPFParagraph paragraph : cell.getParagraphs()) {
                                    paragraph.setAlignment(ParagraphAlignment.CENTER);
                                }
                            } else if (i > 0 && i < data.size() - 1) {
                                // 不设置样式
                            }
                        } else {
                            // 设置数据对齐方式
                            if (isAllChinese(rowData.get(j))) {
                                for (XWPFParagraph paragraph : cell.getParagraphs()) {
                                    paragraph.setAlignment(ParagraphAlignment.CENTER);
                                }
                            } else {
                                for (XWPFParagraph paragraph : cell.getParagraphs()) {
                                    paragraph.setAlignment(ParagraphAlignment.RIGHT);
                                }
                            }
                        }
                    }*/
                    /*if (i == 0) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            paragraph.setAlignment(ParagraphAlignment.CENTER);
                        }
                    } else {
                        // 设置数据对齐方式
                        if (j > 0 && isAllChinese(rowData.get(j))) {
                            for (XWPFParagraph paragraph : cell.getParagraphs()) {
                                paragraph.setAlignment(ParagraphAlignment.CENTER);
                            }
                        } else {
                            for (XWPFParagraph paragraph : cell.getParagraphs()) {
                                paragraph.setAlignment(ParagraphAlignment.RIGHT);
                            }
                        }
                    }*/
                }
            }
            /*for (CellRangeAddress region : mergedRegions) {
                mergeCellsHorizontal(table, region.getFirstRow(), region.getFirstColumn(), region.getLastColumn());
                mergeCellsVertically(table, region.getFirstColumn(), region.getFirstRow(), region.getLastRow());
            }*/
            // 遍历合并区域并应用
            for (CellRangeAddress region : mergedRegions) {
                applyMergeRegion(table, region);
            }
            x.getAndIncrement();
        }
        //});
        // 保存 Word 文件
        //try (FileOutputStream out = new FileOutputStream(outputPath)) {
           // document.write(out);
        //}
        //document.close();
        return document;
    }

    private static void applyMergeRegion(XWPFTable table, CellRangeAddress region) {
        int firstRow = region.getFirstRow();
        int lastRow = region.getLastRow();
        int firstCol = region.getFirstColumn();
        int lastCol = region.getLastColumn();

        //mergeCellsHorizontal(table, firstRow, firstCol, lastCol);
        //mergeCellsVertically(table, firstCol, firstRow, lastRow);
        // 水平合并单元格
        for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++) {
            mergeCellsHorizontal(table, rowIndex, firstCol, lastCol);
        }

        // 垂直合并单元格
        for (int colIndex = firstCol; colIndex <= lastCol; colIndex++) {
            mergeCellsVertically(table, colIndex, firstRow, lastRow);
        }

        System.out.println("Merging cells from (" + firstRow + ", " + firstCol + ") to (" + lastRow + ", " + lastCol + ")");

        /*for (int rowIndex = firstRow; rowIndex <= lastRow; rowIndex++) {
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
        }*/
    }
            /**
             *   word跨列合并单元格
             *   table 表单对象
             *   row  合并行
             *   fromCell 起始列
             *   toCell  结束列
             */
    private static void mergeCellsHorizontal(XWPFTable table, Integer row, Integer fromCell, Integer toCell) {
        if(fromCell == toCell){
            return;
        }
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            if(table != null && table.getRow(row) != null) {
                XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
                if(cell != null){
                    if (cellIndex == fromCell) {
                        // The first merged cell is set with RESTART merge value
                        cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                    } else {
                        // Cells which join (merge) the first one, are set with CONTINUE
                        cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                    }

                }
            }
        }
    }

    /**
     *   word跨行合并单元格
     *   table 表单对象
     *   row  合并列
     *   fromCell 起始行
     *   toCell  结束行
     */
    private static void mergeCellsVertically(XWPFTable table, Integer col, Integer fromRow, Integer toRow) {
        if(fromRow == toRow){
            return;
        }
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            if(table != null && table.getRow(rowIndex) != null){
                XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
                if(cell != null){
                    if ( rowIndex == fromRow ) {
                        // The first merged cell is set with RESTART merge value
                        cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
                    } else {
                        // Cells which join (merge) the first one, are set with CONTINUE
                        cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
                    }

                }
            }
        }
    }


    // 添加自定义标题样式
    private static void addCustomHeadingStyle(XWPFDocument document, String styleId, int level) {
        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId(styleId);

        CTString name = CTString.Factory.newInstance();
        name.setVal(styleId);
        ctStyle.setName(name);

        CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
        indentNumber.setVal(BigInteger.valueOf(level));

        // 设置标题级别
        ctStyle.setUiPriority(indentNumber);
        ctStyle.setQFormat(CTOnOff.Factory.newInstance());
        ctStyle.setPPr(CTPPrGeneral.Factory.newInstance());
        ctStyle.getPPr().setOutlineLvl(indentNumber);

        XWPFStyles styles = document.createStyles();
        styles.addStyle(new XWPFStyle(ctStyle));
    }

    private static boolean isChinese(char c) {
        return c >= '\u4e00' && c <= '\u9fa5';
    }
    private static boolean isAllChinese(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c) || !isChinese(c)) {
                return false;
            }
        }
        return true;
    }
}

