package restassuredapi.NestedObjectsCreation;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restassuredapi.ExcelData;

import java.io.IOException;
import java.util.Arrays;

public class DatadrivenTesting
{
 @DataProvider()
 public Object[][] datasample() throws IOException
 {
  ExcelData el= new ExcelData();
   int rows=el.totalrows("Sheet1");
    int column=el.totalcolumn("Sheet1",0);

    Object[][] obj= new Object[rows][column];

    for(int i=1;i<=rows;i++)
    {
     for(int j=0;j<=column;j++)
     {
      obj[i-1][j]=el.ReadDataFromExcel("Sheet1",i,j);
     }
    }


    for(Object [] ob:obj)
    {
     System.out.println(Arrays.toString(ob));
    }

  return obj;



 }


 @Test(dataProvider="datasample")
 public void datareading() throws IOException
 {
   baseURI="https://restful-booker.herokuapp.com/booking";
    given().contentType(ContentType.JSON).body(datasample()).post().then().log().body();

 }
















}
