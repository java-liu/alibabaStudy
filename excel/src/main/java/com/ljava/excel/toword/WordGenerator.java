package com.ljava.excel.toword;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class WordGenerator {
    public static void createWordWithTable(String outputPath, List<LinkedHashMap<Integer, String>> data, String title) throws IOException {
        // Create a new Word document
        XWPFDocument document = new XWPFDocument();

        // Add title
        XWPFParagraph titleParagraph = document.createParagraph();
        XWPFRun titleRun = titleParagraph.createRun();
        titleRun.setText(title);
        titleRun.setBold(true);
        titleRun.setFontSize(14);

        // Create a table in Word
        XWPFTable table = document.createTable();

        for (LinkedHashMap<Integer, String> rowData : data) {
            XWPFTableRow row = table.createRow();
            for (Object cellData : rowData.values()) {
                XWPFTableCell cell = row.createCell();
                cell.setText((String) cellData);
            }
        }

        // Save the Word document
        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            document.write(out);
        }
    }
}
