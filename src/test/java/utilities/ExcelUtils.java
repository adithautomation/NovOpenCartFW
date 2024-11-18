package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public String filePath;
    public String sheetName;
    
    public ExcelUtils(String filePath, String sheetName) {
        this.filePath = filePath;
        this.sheetName = sheetName;
    }
    
    public int getRowCount() throws IOException {
        try (FileInputStream fi = new FileInputStream(filePath); 
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            return sheet.getLastRowNum();
        }
    }
    
    public int getCellCount(int rownum) throws IOException {
        try (FileInputStream fi = new FileInputStream(filePath); 
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            return row.getLastCellNum();
        }
    }
    
   
    public String getCellData(int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(filePath); 
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            XSSFCell cell = row.getCell(colnum);
            
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            return "";
        }
    }
    
  
    public void setCellData(int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(filePath);
        
        if (!xlfile.exists()) {
            try (XSSFWorkbook workbook = new XSSFWorkbook(); 
                 FileOutputStream fo = new FileOutputStream(filePath)) {
                workbook.write(fo);
            }
        }
        
        try (FileInputStream fi = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fi);
             FileOutputStream fo = new FileOutputStream(filePath)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
            
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) {
                row = sheet.createRow(rownum);
            }
            
            XSSFCell cell = row.createCell(colnum);
            cell.setCellValue(data);
            
            workbook.write(fo);
        }
    }
    
    public void fillGreenColor(int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fi);
             FileOutputStream fo = new FileOutputStream(filePath)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            XSSFCell cell = row.getCell(colnum);
            
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            cell.setCellStyle(style);
            workbook.write(fo);
        }
    }
    
    public void fillRedColor(int rownum, int colnum) throws IOException {
        try (FileInputStream fi = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fi);
             FileOutputStream fo = new FileOutputStream(filePath)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(rownum);
            XSSFCell cell = row.getCell(colnum);
            
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            cell.setCellStyle(style);
            workbook.write(fo);
        }
    }
}
