package restassuredapi.Module4;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RequestSpecificationBuilderandRequestSpec
{
  RequestSpecification wres;
  @Test
    public void Requestspec()
  {
    //RequestSpecification calls  baseurl, basepath, headers.
    /*------RequestSpecification is Interface so directly calling  with given--------*/
    //It will not get set at starting of methods because of interface
    RequestSpecification gres= RestAssured.given();
    gres.baseUri("https://restful-booker.herokuapp.com").basePath("/booking");

    /*-------------RequestSpecification is Interface so directly calling with with-----------------*/
     wres=RestAssured.with();
    wres.baseUri("https://restful-booker.herokuapp.com").basePath("/ping");
     }

    @Test
  public void RequestspecBuilder()
    {
      //RequestSpecBuilder calls cookies,Headers,Body,Authentication, Form parameter, Query parameter, path parameter
      //But at the end need to link with RequestSpecification with build()
      /*-------------RequestSpecBuilder is class so creating object---------------*/
      //Every method calling will have set at beginning of method
      RequestSpecBuilder rreq= new RequestSpecBuilder();
      rreq.setBaseUri("https://restful-booker.herokuapp.com").setBasePath("/booking").setContentType(ContentType.JSON)
              ;
      RequestSpecification req=rreq.build();

      //Method 1
      Response res1=RestAssured.given(req).get();
      System.out.println("Method 1 type:"+res1.asString());

      //Method 2
      Response res2=given(req).get();
      System.out.println("Method 2 type:"+ res2.asString());

      //Method 3
      Response res3=given().spec(req).get();
      System.out.println("Method 3 type:"+ res3.asString());

    }

    @Test
  public void reqandreqspec()
    {

      wres=  new RequestSpecBuilder().setBaseUri("https://reqres.in").setBasePath("/api/users/2")
      .setContentType(ContentType.JSON).build();

      Response res3=given().spec(wres).get();
      res3.then().log().all();
      System.out.println("Method 3 type:"+ res3.asString());




    }


}
