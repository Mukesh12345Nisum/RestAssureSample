package restassuredapi.Module4;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestandResponseSpecBuilder
{
    //Instead of calling RequestSpecification and ResponseSpecification we can declare RequestandResponseSpecificationBuilder
    //then can map to the interface with build and expect



    ResponseSpecification res=null;
  @BeforeClass
  public void responsespecbuilder()
  {
      ResponseSpecBuilder rres=  new ResponseSpecBuilder();
      res=rres.build();
      res.statusCode(201).time(Matchers.lessThanOrEqualTo(5000L)).
              statusLine("HTTP/1.1 201 Created");

  }

  @Test
    public void reqandresspecBuilder()
  {
      RequestSpecBuilder rreq= new RequestSpecBuilder();
      rreq.setBaseUri("https://restful-booker.herokuapp.com").setBasePath("/ping");

      RequestSpecification req= rreq.build();

      Response resp=given(req).when().get();
      resp.then().spec(res).log().body();

      System.out.println("Request and Response Specification:"+resp.asString());



  }

}
