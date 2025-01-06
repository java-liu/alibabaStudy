package com.ljava.excel.toword;

import org.apache.commons.collections4.map.LinkedMap;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelToWordConverter {
    public static void main(String[] args) {
        String excelPath = "/Users/liuys/Downloads/附注模板--单体-ok.xlsx";
        String outputPath = "/Users/liuys/Downloads/result.docx";
        String sheetName = "附注";  // 需要的工作表名

        try {
            // 从 Excel 读取数据
            List<LinkedHashMap<Integer,String>> data = ExcelReader.readExcel(excelPath, sheetName);
            //List<LinkedHashMap<Integer, String>> data = ExcelReaderWithMergedCells.readExcelDataWithMergedCells(excelPath);

            // 生成 Word 文件
            WordGenerator.createWordWithTable(outputPath, data, "附注");

            System.out.println("生成的 Word 文件已保存到：" + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
