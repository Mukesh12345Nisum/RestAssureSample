package restassuredapi.Module4;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RequestandResponseLogging
{
 @Test
    public void differentlogs()
 {
     RequestSpecBuilder rreq= new RequestSpecBuilder();
     rreq.setBaseUri("https://restful-booker.herokuapp.com").setBasePath("/booking");

     RequestSpecification req=rreq.build();

     Response resp=given(req).log().uri().log().body().log().method().log().headers().log().cookies()
                    .when().get();
     resp.then().log().body().log().cookies().log().headers().log().status().log().toString();
     System.out.println(resp.asString());
 }

 @Test
  public void ifvalidationfails()
 {
     baseURI="https://api.getpostman.com";

     /*
     Response resp= given().log().ifValidationFails().when().get();
     resp.then().log().ifValidationFails().log().body();
   System.out.println("log if validation fails : "+ resp.asString());
     */

   //Another method
   Response resp1=  given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).when().get();
   resp1.then().log().body();

   System.out.println("log if validation in header only" + resp1);

 }

 @Test
    public void blacklistheader()
 {
     //blacklisting the header details in the log
     baseURI="https://restful-booker.herokuapp.com";
    Response resp= given().config(RestAssured.config().logConfig(LogConfig.logConfig().
                    blacklistHeader("Accept","set-cookie").blacklistHeader("Multiparts","Body")))
             .log().headers().when().get("/booking");
    resp.then().log().headers();
 }







}
