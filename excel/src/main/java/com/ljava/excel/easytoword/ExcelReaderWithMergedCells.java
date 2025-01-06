package com.ljava.excel.easytoword;

import com.alibaba.excel.EasyExcel;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExcelReaderWithMergedCells {
    // 使用 EasyExcel 读取普通数据内容
    public static List<LinkedHashMap<Integer,String>> readExcelData(String excelPath, String sheetName) {
        /*List<LinkedHashMap<Integer,String>> data = new ArrayList<>();
        EasyExcel.read(excelPath)
                .sheet()
                .doReadSync()
                .forEach(row -> data.add(new LinkedHashMap<>(row)));*/

        List<Object> objects = EasyExcel.read(excelPath).sheet(sheetName).doReadSync();
        List<LinkedHashMap<Integer,String>> data = EasyExcel.read(excelPath).sheet(sheetName).doReadSync();
        // 打印读取到的数据，检查第一列是否被读取
        for (Object row : objects) {
            System.out.println("Row: " + row);
        }
        //return data;
        return data;
    }

    // 使用 Apache POI 获取合并单元格信息
    public static List<CellRangeAddress> getMergedRegions(String excelPath) throws IOException {
        List<CellRangeAddress> mergedRegions = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(excelPath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            mergedRegions.addAll(sheet.getMergedRegions());
        }
        return mergedRegions;
    }
}
