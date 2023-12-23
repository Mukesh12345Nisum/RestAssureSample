package restassuredapi.SerializeAndDeserialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restassuredapi.Product;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DifferntTypesOfSerialization
{
    ResponseSpecification res=null;
    @BeforeClass
    public void responsespecbuilder()
    {
        ResponseSpecBuilder rres=  new ResponseSpecBuilder();
        res=rres.build();
        res.statusCode(200).time(Matchers.lessThanOrEqualTo(5000L)).
                statusLine("HTTP/1.1 200 OK");

    }
    @Test
    public void constructortoobj() throws JsonProcessingException
    {
        Product prod= new Product(31,"New data","Describing new data program",12.0,25,37);
        Product prod2= new Product(32,"Samsung","Samsung related models",30.00, 1.25, 5);

        ObjectMapper ob= new ObjectMapper();
         String sob=ob.writeValueAsString(prod);
        String sob1=ob.writeValueAsString(prod2);


        RequestSpecBuilder rreq= new RequestSpecBuilder();
        rreq.setBaseUri("https://dummyjson.com/products");

        RequestSpecification req= rreq.build();

        Response resp= given(req).body(sob).when().post("/add");
        resp.then().spec(res).log().body();

        Response resp1= given(req).body(sob1).when().post("/add");
        resp1.then().spec(res).log().body();
    }

  @Test
    public void maptoobj() throws JsonProcessingException {
      HashMap jo= new HashMap();
      jo.put("id",31);
      jo.put("Onida","Old model tv sample");
      jo.put("price",13.50);
      jo.put("discount", 12);
      jo.put("rating",37);

      ObjectMapper ob= new ObjectMapper();
      String sob=ob.writeValueAsString(jo);

      RequestSpecBuilder rreq= new RequestSpecBuilder();
      rreq.setBaseUri("https://dummyjson.com/products");

      RequestSpecification req= rreq.build();

      Response resp= given(req).body(sob).when().post("/add");
      resp.then().spec(res).log().body();

  }

  @Test
    public void objnodetojson() throws JsonProcessingException {
      ObjectMapper ob= new ObjectMapper();
      ObjectNode on= ob.createObjectNode();
      on.put("id",35);
      on.put("LG","Old model tv sample");
      on.put("price",13.50);
      on.put("discount", 12);
      on.put("rating",37);


      String sob=ob.writeValueAsString(on);
      System.out.println(sob);

      given().body(sob).when().post("/add").then().log().body();

  }
    @Test
    public void arraynode() throws JsonProcessingException {
     ObjectMapper ob= new ObjectMapper();
     ArrayNode arraylist= ob.createArrayNode();

     ObjectNode on= ob.createObjectNode();
     on.put("id",35);
     on.put("LG","Old model tv sample");
     on.put("price",13.50);
     on.put("discount", 12);
     on.put("rating",37);

     ObjectNode on1= ob.createObjectNode();
     on1.put("id",38);
     on1.put("Samsung","New model tv");
     on1.put("price",25.50);
     on1.put("discount", 10);
     on1.put("rating",40);

    arraylist.add(on);
    arraylist.add(on1);

     String sob=ob.writeValueAsString(arraylist);
     System.out.println(sob);

     given().body(sob).when().post("/add").then().log();


 }



 @Test
    public void mockserver()
 {
     //equal to method and hamcrest matchers method not working
     baseURI="https://1e03b6b5-34ec-4113-a2d9-43c4bdea138b.mock.pstmn.io";
     String data= """
             {
                "foo1":"bar1",
                "foo2":"bar2"
             }
             """;
     given().contentType(ContentType.JSON).body(data).when().post("/simplepost").then().assertThat()
             .log().body();


 }






}
