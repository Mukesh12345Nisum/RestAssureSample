package restassuredapi.SerializeAndDeserialize;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class RequestChaining
{
    @Test
    public void post()
    {
       baseURI= "https://reqres.in/api/users";
        String data= """
                "name": "morpheus",
                    "job": "leader"
                """;

      Response resp=  given().body(data).when().post();
      resp.then().assertThat().statusCode(201).log()
                .body();

      /*-------------------Getting the data--------------------*/
       String newdata=resp.jsonPath().get("id");

        given().contentType(ContentType.JSON).pathParam("newid",newdata).when().get("/{newid}").then()
                .assertThat().log().body();

      /*-------------------Deleting the data---------------------*/
        given().contentType(ContentType.JSON).pathParam("newid",newdata).when().delete("/{newid}").then()
                .assertThat().log().body();


    }

   @Test
    public void get()
   {
       baseURI="https://reqres.in/api/users";

   }






}
