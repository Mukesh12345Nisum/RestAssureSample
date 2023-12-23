package restassuredapi.SerializeAndDeserialize;


import io.restassured.response.Response;
import org.testng.IDataProviderListener;
import org.testng.IDataProviderMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restassuredapi.PostmanEchoPojo;
import restassuredapi.Product;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UsingDataProvider
{
  @Test(dataProvider = "workspace")
  public void usingobjectarray(int id, String title, String description, double price, double discount, double rating)
  {
    baseURI="https://dummyjson.com/products";
    Product p1= new Product(id,title,description,price,discount,rating);
     /*
     HashMap <String, String> pe= new HashMap<>();
     pe.put("foo1","bar1");
     pe.put("foo2","bar2");
*/
    Response deserialize=  given().body(p1).when().post("/add");
    deserialize.then().log().body();
       //   deserialize.extract().as(PostmanEchoPojo.class);
    Product dese=given().body(p1).when().post("/add").then().extract().as(Product.class);

    System.out.println(dese);


  }


  @DataProvider()
  public Object[][] workspace()
  {
    return new Object[][]
            {
                    {1,"Iphone 19x","will launch in future",25.96,35.78,12.50}


            };

  }


}