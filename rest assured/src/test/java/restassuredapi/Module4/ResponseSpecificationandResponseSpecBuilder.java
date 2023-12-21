package restassuredapi.Module4;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class ResponseSpecificationandResponseSpecBuilder
{

   //Because ResponseSpecification and ResponseSpecBuilder comes at then if declared at starting in class it is throwing
    //Error so declaring in Beforeclass for both of them , creating seperate variable outside



    ResponseSpecification res=null;


    @BeforeClass
    public void responsespecificationdeclaration()
    {
        //ResponseSpecification is an Interface having ready made methods

        res=RestAssured.expect();
        res.contentType(ContentType.JSON).statusCode(200).time(Matchers.lessThanOrEqualTo(5000L)).
        statusLine("HTTP/1.1 200 OK");


        //ResponseSpecBuilder is class so creating object and calling with expect before every method
        ResponseSpecBuilder rres= new ResponseSpecBuilder();
        rres.expectContentType(ContentType.JSON).expectStatusCode(200).expectResponseTime(Matchers.lessThanOrEqualTo(5000L)).
                expectStatusLine("HTTP/1.1 200 OK");
        //Because at the end need to link with interface
         res=rres.build();

         //Another method is that we can typecast from interface
        //res= (ResponseSpecification) new ResponseSpecBuilder();


    }



 @Test
    public void ResponseSpecification()
 {
     //Like RequestSpecification
     //Here we cannot declare directly in then need to keep seperate specification
    // Method 1:
     baseURI="https://restful-booker.herokuapp.com";
     Response resp=given().contentType(ContentType.JSON).when().get("/booking");
     resp.then().spec(res).log().body();


     //Method 2
     baseURI="https://restful-booker.herokuapp.com";
     Response rres=given().contentType(ContentType.JSON).when().get("/booking");
     rres.then().spec(res).log().body();

     System.out.println("Response Specification :" + resp.asString());
     System.out.println("Response SpecBuilder :" + rres.asString());


 }
















}
