package com.ljava.excel.poitoword;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ExcelToWordConverter3 {
    public static void main(String[] args) {
        //String excelPath = "/Users/liuys/Downloads/附注模板--单体-ok.xlsx";
        String excelPath = "/Users/liuys/Downloads/demo1.xlsx";
        String outputPath = "/Users/liuys/Downloads/result4.docx";
        String sheetName = "资产负债表";  // 需要的工作表名

        try {

            // 使用 POI 读取 Excel 数据
            List<LinkedHashMap<Integer, String>> data = ExcelReader.readExcelData(excelPath);

            /*// 分割数据
            Map<String, List<LinkedHashMap<Integer, String>>> groupedData = groupByValue(data, "这是分隔");

            // 输出结果
            for (Map.Entry<String, List<LinkedHashMap<Integer, String>>> entry : groupedData.entrySet()) {
                System.out.println("Key: " + entry.getKey());
                for (LinkedHashMap<Integer, String> map : entry.getValue()) {
                    System.out.println(map);
                }
            }*/
            // 获取合并单元格信息
            //List<CellRangeAddress> mergedRegions = ExcelReader.getMergedRegions(excelPath);
            List<List<CellRangeAddress>> mergedRegions = ExcelReader.getMergedRegions(excelPath);
            final List<LinkedHashMap<Integer, String>>[] separateData = new List[]{new ArrayList<>()};
            List<List<LinkedHashMap<Integer, String>>> sumData = new ArrayList<>();
            List<List<CellRangeAddress>>  sumMergedRegions = new ArrayList<>();
            data.forEach(row ->
                    {
                        if(!row.containsValue("这是分隔") && row.size() > 0){
                            separateData[0].add(row);
                        }else{
                            // 生成带合并单元格的 Word 表格
                            if(separateData[0].size() > 0){
                                sumData.add(separateData[0]);
                                sumMergedRegions.add(mergedRegions.get(sumData.size()-1));
                                //WordGenerator.createWordWithMergedCells(outputPath, separateData[0], mergedRegions);
                                separateData[0] = new ArrayList<>();
                            }
                        }
                    }
            );

            // Create a single XWPFDocument
            XWPFDocument document = new XWPFDocument();

            for (int i = 0; i < sumData.size(); i++) {
                List<List<LinkedHashMap<Integer, String>>> singleTableData = new ArrayList<>();
                singleTableData.add(sumData.get(i));

                List<List<CellRangeAddress>> singleMergedRegions = new ArrayList<>();
                singleMergedRegions.add(sumMergedRegions.get(i));

                WordGenerator.createWordWithMergedCells(document, singleTableData, singleMergedRegions);
            }

            // Save the document once at the end
            try (FileOutputStream out = new FileOutputStream(outputPath)) {
                document.write(out);
            }
            document.close();
            //WordGenerator.createWordWithMergedCells(outputPath, sumData, mergedRegions);
            //Docx4jTableMerger.convertExcelToWord(sheet, outputPath);

            System.out.println("生成的 Word 文件已保存到：" + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, List<LinkedHashMap<Integer, String>>> groupByValue(List<LinkedHashMap<Integer, String>> data, String value) {
        return data.stream()
                .filter(map -> map.containsValue(value))
                .collect(Collectors.groupingBy(map -> map.values().stream()
                        .filter(v -> v.equals(value))
                        .findFirst()
                        .orElse("Other")));
    }
}
