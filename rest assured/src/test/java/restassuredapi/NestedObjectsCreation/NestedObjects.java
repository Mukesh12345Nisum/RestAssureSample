package restassuredapi.NestedObjectsCreation;

import static io.restassured.RestAssured.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

public class NestedObjects
{
    RequestSpecification req;
  @Test
    public void directdata()
  {/*
      "products": [
      {
          "id": 1,
              "title": "iPhone 9",
              "description": "An apple mobile which is nothing like apple",
              "price": 549,
              "discountPercentage": 12.96,
              "rating": 4.69,
              "stock": 94,
              "brand": "Apple",
              "category": "smartphones",
              "thumbnail": "https://i.dummyjson.com/data/products/1/thumbnail.jpg",
              "images": [
          "https://i.dummyjson.com/data/products/1/1.jpg",
                  "https://i.dummyjson.com/data/products/1/2.jpg",
                  "https://i.dummyjson.com/data/products/1/3.jpg",
                  "https://i.dummyjson.com/data/products/1/4.jpg",
                  "https://i.dummyjson.com/data/products/1/thumbnail.jpg"
            ]
      },
      */
      HashMap<Object,Object> all= new HashMap<>();
      HashMap<String, Object> prod= new HashMap<String,Object>();

      HashMap<String,String> hm= new HashMap<String, String>();
      hm.put("id","55");
      hm.put("title","iphone 15");
      hm.put("description", "Launching new apple product");
      hm.put("price","578");
      hm.put("discountpercentage","5.3");
      hm.put("rating","3.9");
      hm.put("stock","98");
      hm.put("brand","Apple");
      hm.put("Category","smartphones");
      hm.put("thumbnail","https://i.dummyjson.com/data/products/1/thumbnail.jpg");

      HashMap<String,Object>images= new HashMap<>();
      HashSet<String> data= new HashSet<>();
      data.add("https://i.dummyjson.com/data/products/1/1.jpg");
      data.add("https://i.dummyjson.com/data/products/1/2.jpg");
      data.add("https://i.dummyjson.com/data/products/1/3.jpg");
      data.add("https://i.dummyjson.com/data/products/1/4.jpg");
      data.add("https://i.dummyjson.com/data/products/1/5.jpg");


      images.put("images",data);

      prod.put("products",hm);
     all.put(prod,images);

     baseURI="https://dummyjson.com/products/add";
     Response  response=given().body(all).when().get();
     response.then().statusCode(201).log().body();

     System.out.println(response.asString());

  }

  @Test
  public void imagefile()
  {
      RequestSpecBuilder rreq= new RequestSpecBuilder();
      rreq.setBaseUri("https://api.thedogapi.com").setBasePath("/v1/images/upload");
      req=rreq.build();

      given(req).contentType(ContentType.JSON).header("x-api-key","live_m0hWDnXwjqRuVeqoZeUs83PoMOwxKdWwYqKaf6GtWTI3iD5wDF3L9UftIh4e9GIv").when().post().then().log().body();
  }


  @Test
    public void systemfile()
  {
      baseURI="https://dummyjson.com/products/add";
      File file= new File("C:\\Users\\smukeshali\\Desktop\\rest assured\\rest assured\\src\\test\\resources\\NestedArray.json");

      Response resp=given().body(file).when().post();
              resp.then().log().body();
              int data=resp.jsonPath().get("id");
              System.out.println(data);
              int expid=101;
      Assert.assertEquals(expid,data);
  }

  @Test
    public void externalfile()
  {

      baseURI="https://dummyjson.com/products/add";
      File file= new File("D:\\NestedArray.json");

      Response resp=given().body(file).when().post();
      resp.then().log().body();
      int data=resp.jsonPath().get("id");
      System.out.println(data);
      int expid=101;
      Assert.assertEquals(expid,data);
  }


  @Test
    public void differentsetvalues()
  {
       String emp="https://fakerestapi.azurewebsites.net/api/v1/Activities";

      HashMap jo= new HashMap();


      jo.put("title","automation");
      jo.put("duedate","2023-12-19T10:34:23.7631729+00:00");

      HashMap jso= new HashMap();
      jso.put("title","manual");
      jso.put("duedate","2023-12-19T10:34:23.7631729+00:00");

      List<HashMap <String,String>> h= new ArrayList<>();
      h.add(jo);
      h.add(jso);

      given().contentType(ContentType.JSON).body(h).when().post(emp)
              .then().assertThat().log().body();

  }

  @Test()
  public void diffsetvalues()
  {
      /*
{
  "colors": [
    {
      "color": "black",
      "category": "hue",
      "type": "primary",
      "code": {
        "rgba": [
          255,
          255,
          255,
          1
        ],
        "hex": "#000"
      }
    },
    {
      "color": "white",
      "category": "value",
      "code": {
        "rgba": [
          0,
          0,
          0,
          1
        ],
        "hex": "#FFF"
      }
    }
  ]
}

       */

      //Main two objects
      HashMap<String,Object> colors= new HashMap<>();
      /*--------------------------------*/
      //First object data
      HashMap<Object,Object> firstcolor= new HashMap<>();

      //Creating data for colors first
      HashMap<String,String> firstcolordetails= new HashMap<>();
      firstcolordetails.put("color","black");
      firstcolordetails.put("category","hue");
      firstcolordetails.put("type","hue");


      //Nested object for code
      HashMap<String,Object> code= new HashMap<>();

      HashMap<String,Object> allcode= new HashMap<>();

      //RGB colors
       List<Object> rgbcode= new ArrayList();
       rgbcode.add(255);
       rgbcode.add(255);
       rgbcode.add(255);
       rgbcode.add(2);

       List<Object> hex= new ArrayList<>();
       hex.add("#000");

       code.put("rgbcode",rgbcode);
       code.put("hex",hex);


       //Now adding data in firstcolor
      allcode.put("code",code);
      firstcolor.put(firstcolordetails,allcode);

      colors.put("firstcolor",firstcolor);
      System.out.println(colors);

      Gson gson = new Gson();
      String json = gson.toJson(colors);
      System.out.println(json);

























  }














}
