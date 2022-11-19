package com.RDCD_Coop.DataProvider;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;

public class Data {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static DataFormatter formatter = new DataFormatter();

    public Data(String path, String sheetName) throws IOException{
        try{
            workbook = new XSSFWorkbook(path);
            sheet = workbook.getSheet(sheetName);
        }
        catch(NullPointerException NPE){
            System.out.println("Exception Occurred: " +NPE.getMessage());
        }
    }

    public static int getRowCount(){
        int rowCount = 0;
        try{
            rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("No of Rows: "+rowCount);
        }
        catch(NullPointerException NPE){
            System.out.println("Exception Occurred: " +NPE.getMessage());
        }
        return rowCount;
    }

    public static int getColumnCount(){
        int colCount = 0;
        try{
            colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println("No of Columns: "+colCount);
        }
        catch(NullPointerException NPE){
            System.out.println("Exception Occurred: " +NPE.getMessage());
        }
        return colCount;
    }

    public static String getCellData(int rowNo, int colNo){
        String cellData = null;
        try{
            cellData = formatter.formatCellValue(sheet.getRow(rowNo).getCell(colNo));
        }
        catch(NullPointerException NPE){
            System.out.println("Exception Occurred: " +NPE.getMessage());
        }
        return cellData;
    }
}