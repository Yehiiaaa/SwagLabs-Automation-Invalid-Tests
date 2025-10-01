package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelFileManager {
    XSSFWorkbook workbook;
    Sheet sheet;

    public ExcelFileManager(String filePath, String sheetName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getColumnCount() {
        try {
            return sheet.getRow(0).getPhysicalNumberOfCells();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getRowCount() {
        try {
            return sheet.getPhysicalNumberOfRows();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //    public String getSpecificCellValue (int rowIndex, int colIndex){
//        return sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
//    }
    public String getSpecificCellValue(int rowIndex, int colIndex) {
        try {
            Row row = sheet.getRow(rowIndex);
            Cell cell = row.getCell(colIndex);

            return switch (cell.getCellType()) {
                case STRING -> cell.getStringCellValue();
                case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
                case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
                case FORMULA -> cell.getCellFormula();
                default -> "";
            };
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
