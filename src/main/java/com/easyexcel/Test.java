package com.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;

public class Test {
    public static void main(String[] args){
        String fileName ="C:\\Users\\lys\\Desktop\\task.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class,new MyListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
    }
}
