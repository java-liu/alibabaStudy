package com.ljava.excel.easytoword;

import com.ljava.excel.poitoword.ExcelReader;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class ExcelToWordConverter {
    public static void main(String[] args) {
        String excelPath = "/Users/liuys/Downloads/附注模板--单体-ok.xlsx";
        String outputPath = "/Users/liuys/Downloads/result.docx";
        String sheetName = "资产负债表";  // 需要的工作表名

        // 使用 EasyExcel 读取 Excel 数据
        List<LinkedHashMap<Integer,String>> data = ExcelReaderWithMergedCells.readExcelData(excelPath, sheetName);
        // 从 Excel 读取数据和合并单元格信息
        //List<List<ExcelReader.CellData>> data = ExcelReader.readExcelWithMergedCells(excelPath);

        // 使用 Apache POI 获取合并单元格信息
        //List<CellRangeAddress> mergedRegions = ExcelReaderWithMergedCells.getMergedRegions(excelPath);

        // 根据数据生成带合并单元格的 Word 表格
        //WordGenerator2.createWordWithMergedCells(outputPath, data);

        System.out.println("生成的 Word 文件已保存到：" + outputPath);
    }
}
