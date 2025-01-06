package com.ljava.excel.toword;

import com.alibaba.excel.EasyExcel;

import java.util.LinkedHashMap;
import java.util.List;

public class ExcelReader {
    public static List<LinkedHashMap<Integer,String>> readExcel(String excelPath, String sheetName) {
        List<LinkedHashMap<Integer,String>> data = EasyExcel.read(excelPath).sheet(sheetName).doReadSync();
        return data;
    }
}
