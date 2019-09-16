package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;


public class ExcelFileReader {

    private static XSSFWorkbook excelWBook;
    private static XSSFSheet excelWSheet;
    private static final String excelPath = "src\\main\\resources\\search_categories.xlsx";
    private static final String excelSheetName = "AllegroTestData";

    private static void setExcelFile(){
        try {
            FileInputStream ExcelFile = new FileInputStream(excelPath);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(excelSheetName);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static String[][] getTestData(String tableName) {
        String[][] testData = null;
        setExcelFile();
        try {
            XSSFCell[] boundaryCells = findCells(tableName);
            XSSFCell startCell = boundaryCells[0];
            XSSFCell endCell = boundaryCells[1];

            int startRow = startCell.getRowIndex() ;
            int startCol = startCell.getColumnIndex();
            int endRow = endCell.getRowIndex();
            int endCol = endCell.getColumnIndex();

            testData = new String[endRow - 1][endCol - 1];

            for (int i = 0; i < endRow-startRow-1; i++) {
                for (int j = 0; j < endCol-startCol-1; j++) {
                     testData[i][j] = excelWSheet.getRow(startRow + i + 1).getCell(startCol + j + 1).getStringCellValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData;
    }

    private static XSSFCell[] findCells(String tableName) {
        String pos = "begin";
        XSSFCell[] cells = new XSSFCell[2];

        for (Row row : excelWSheet) {
            for (Cell cell : row) {
                 if (tableName.equals(cell.getStringCellValue())) {
                    if (pos.equalsIgnoreCase("begin")) {
                        cells[0] = (XSSFCell) cell;
                        pos = "end";
                    } else {
                        cells[1] = (XSSFCell) cell;
                    }
                }
            }
        }
        return cells;
    }

}
