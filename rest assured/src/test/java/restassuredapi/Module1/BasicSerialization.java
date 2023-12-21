package restassuredapi.Module1;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restassuredapi.Product;
import restassuredapi.ProgramInfo;

public class BasicSerialization
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
    public void serialize()
 {
     Product prod= new Product(31,"New data","Describing new data program",12.0,25,37);
     Product prod2= new Product(32,"Samsung","Samsung related models",30.00, 1.25, 5);

     RequestSpecBuilder rreq= new RequestSpecBuilder();
     rreq.setBaseUri("https://dummyjson.com/products");

     RequestSpecification req= rreq.build();

     Response resp= given(req).body(prod).when().post("/add");
      resp.then().spec(res).log().body();

     Response resp1= given(req).body(prod2).when().post("/add");
     resp1.then().spec(res).log().body();

 }


    //Deserialization
    @Test
    public  void deserialize()
    {
        baseURI="https://dummyjson.com/products";
        Product product= new Product(1,"iphone 9","An apple mobile which is nothing like apple",549,12.96,4.69);

        Product resp=given().queryParam("id",1).contentType(ContentType.JSON).when().
                get().as(Product.class);

        assert(product).equals(resp);


    }

  @Test
    public void newserialize()
  {
      ProgramInfo pinfo = new ProgramInfo(0,"SQL220", "Intro to sql by shu220", "true");

      baseURI="https://lms-program-rest-service.herokuapp.com/programs";
      Response res = given().log().all().auth().basic("admin","password")
              .header("Content-Type", "application/json")
              .body(pinfo)
              .when().post()
              .then().assertThat().statusCode(201).extract().response();
      String response = res.asString();
      System.out.println(response);
  }











}
