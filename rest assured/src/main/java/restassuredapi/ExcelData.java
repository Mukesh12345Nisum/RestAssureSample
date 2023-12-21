package restassuredapi;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelData
{
   Workbook wb;
    FileInputStream fis;
  public String ReadDataFromExcel(String Sheetname,int row, int cell) throws IOException
  {
      fis= new FileInputStream("C:\\Users\\smukeshali\\Desktop\\rest assured\\rest assured\\src\\test\\resources\\Book - Copy.xlsx");
      Workbook wb = WorkbookFactory.create(fis);
      String data= wb.getSheet(Sheetname).getRow(row).getCell(cell).toString();
      return data;

  }

  public int totalrows(String Sheetname) throws IOException
  {
      fis= new FileInputStream("C:\\Users\\smukeshali\\Desktop\\rest assured\\rest assured\\src\\test\\resources\\Book - Copy.xlsx");
      wb = WorkbookFactory.create(fis);
      int datavalue= wb.getSheet(Sheetname).getLastRowNum();
      return datavalue;

  }

    public int totalcolumn(String Sheetname,int row) throws IOException {
        fis = new FileInputStream("C:\\Users\\smukeshali\\Desktop\\rest assured\\rest assured\\src\\test\\resources\\Book - Copy.xlsx");
        wb = WorkbookFactory.create(fis);
        int datavalue = wb.getSheet(Sheetname).getRow(row).getLastCellNum();
        return datavalue;

    }



    }
