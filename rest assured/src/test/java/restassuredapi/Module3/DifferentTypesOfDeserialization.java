package restassuredapi.Module3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.config.EncoderConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import restassuredapi.Product;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RestAssuredConfig.config;

public class DifferentTypesOfDeserialization
{
 @Test
    public void deserialize() throws JsonProcessingException
 {

     Product p= new Product(12,"Samsung QLed","Ultra model related tv",25.37,35,49.3);

     ObjectMapper ob= new ObjectMapper();

     Product deserialze= given().config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
             .body(p).when().post("/add").then().
             extract().response().as(Product.class);

     String deser=ob.writeValueAsString(deserialze);
     String Prod=ob.writeValueAsString(p);
     System.out.println(deser);
     System.out.println(Prod);
     Assert.assertEquals(deser,Prod);




 }


}
