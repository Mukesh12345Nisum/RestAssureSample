package restassuredapi.NestedObjectsCreation;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class MultipleParameters
{
   @Test
   public void pathparam()
   {
       baseURI="https://reqres.in";
       given().pathParam("userId","2").when().get("/api/users/{userId}")
               .then().log().body();

   }

   @Test
   public void newpathparam()
   {
       RequestSpecBuilder res= new RequestSpecBuilder();

       RequestSpecification req= RestAssured.given();
       req.basePath("https://reqres.in").pathParam("userId","2");
       req=res.build();
       given(req).when().get("/api/users/{userId}")
               .then().log().body();
   }


  /*---------------------------query parameter--------------------------------------------*/
 @Test
    public void queryparam()
 {
     baseURI="https://postman-echo.com/get";
     given().queryParam("foo1","bar1").queryParam("foo2","bar2")
             .when().get().then().log().body();
 }

    @Test
    public void multiple_query()
    {
        baseURI="https://postman-echo.com/get";
        given().queryParam("foo1","bar1,bar2,bar3")
                .when().get().then().log().body();
    }

 @Test
    public void newqueryparam()
 {
     RequestSpecBuilder res= new RequestSpecBuilder();

     RequestSpecification req= RestAssured.given();
     req.basePath("https://postman-echo.com/get").queryParam("foo1","bar1").queryParam("foo2","bar2");
     req=res.build();
     given(req).when().get().then().log().body();

 }










}
