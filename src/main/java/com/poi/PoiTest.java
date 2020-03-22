package com.poi;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class PoiTest {
    private static final Logger logger = LoggerFactory.getLogger(PoiTest.class);
    public static void readExcel() throws IOException{
        String filePath = "C:\\Users\\lys\\Desktop\\task.xlsx";
        XSSFWorkbook xssfWorkbook = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            //BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            //POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
            logger.info("xssfWorkbook对象:{}",xssfWorkbook);
            //读取第一个工作表
            Sheet sheet = xssfWorkbook.getSheetAt(0);
            int maxRow = sheet.getLastRowNum();
            logger.info("excel总行数:{}",maxRow);
            for(int row = 0; row <= maxRow; row++){
                //获取最后单元格nnum,即总单元格 ‘此处从1开始计数’
                int maxRol = sheet.getRow(row).getLastCellNum();
                System.out.println("第" + row + "行的数据如下-----");
                for(int rol = 0;rol < maxRol; rol++){
                    System.out.print(sheet.getRow(row).getCell(rol) + " ");
                }
                System.out.println();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            xssfWorkbook.close();
        }

    }

    public static void main(String[] args) throws IOException {
        readExcel();
    }
}
