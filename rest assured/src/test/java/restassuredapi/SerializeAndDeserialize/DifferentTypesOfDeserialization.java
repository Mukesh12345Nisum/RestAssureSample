package restassuredapi.SerializeAndDeserialize;

import DeserializationPOJOClasses.Datapojo;
import DeserializationPOJOClasses.ListUserPojo;
import SerializeAndDeserializePojoClass.Address;
import SerializeAndDeserializePojoClass.Geo;
import SerializeAndDeserializePojoClass.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import restassuredapi.PostmanEchoPojo;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DifferentTypesOfDeserialization
{
   ResponseSpecification res;
    @BeforeClass
    public void responsespecbuilder()
    {
        ResponseSpecBuilder rres=  new ResponseSpecBuilder();
        res=rres.build();
        res.time(Matchers.lessThanOrEqualTo(5000L)).contentType("text/html;charset=iso-8859-1");

    }
 @Test
    public void deserialize() throws JsonProcessingException
 {
     baseURI="https://1e03b6b5-34ec-4113-a2d9-43c4bdea138b.mock.pstmn.io";
     PostmanEchoPojo pe= new PostmanEchoPojo("bar1","bar2");
     /*
     HashMap <String, String> pe= new HashMap<>();
     pe.put("foo1","bar1");
     pe.put("foo2","bar2");
*/
         PostmanEchoPojo deserialize=  given().body(pe).when().post("/simplepost").then().extract().as(PostmanEchoPojo.class);


     ObjectMapper ob= new ObjectMapper();
     String deser=ob.writeValueAsString(deserialize);
     String Prod=ob.writeValueAsString(pe);
     System.out.println(deser);
     System.out.println(Prod);
     Assert.assertEquals(deser,Prod);

 }

 //For reqres multiple pojo classes deserializing
   @Test
    public void reqres()
   {
       /*
       {
    "page": 2,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 7,
            "email": "michael.lawson@reqres.in",
            "first_name": "Michael",
            "last_name": "Lawson",
            "avatar": "https://reqres.in/img/faces/7-image.jpg"
        },
        {
            "id": 8,
            "email": "lindsay.ferguson@reqres.in",
            "first_name": "Lindsay",
            "last_name": "Ferguson",
            "avatar": "https://reqres.in/img/faces/8-image.jpg"
        },
        {
            "id": 9,
            "email": "tobias.funke@reqres.in",
            "first_name": "Tobias",
            "last_name": "Funke",
            "avatar": "https://reqres.in/img/faces/9-image.jpg"
        },
        {
            "id": 10,
            "email": "byron.fields@reqres.in",
            "first_name": "Byron",
            "last_name": "Fields",
            "avatar": "https://reqres.in/img/faces/10-image.jpg"
        },
        {
            "id": 11,
            "email": "george.edwards@reqres.in",
            "first_name": "George",
            "last_name": "Edwards",
            "avatar": "https://reqres.in/img/faces/11-image.jpg"
        },
        {
            "id": 12,
            "email": "rachel.howell@reqres.in",
            "first_name": "Rachel",
            "last_name": "Howell",
            "avatar": "https://reqres.in/img/faces/12-image.jpg"
        }
    ],
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
        */



       baseURI="http://reqres.in/api/users?page=2";

    //we are linking class to the rest api methods
      //Another thing is that for post operation we can use parameterised constructor
      //But for get need to implement  method string tostring
       ListUserPojo pojo=  given().when().get().as(ListUserPojo.class);

       System.out.println(pojo.toString());

       List<Datapojo>list= pojo.getData();
       for(int i=0;i<list.size();i++)
       {
          System.out.println(list.get(i));
       }


   }

@Test
    public void assessment()
{
    Geo geo= new Geo(136.57888,165.89766);
    Address add= new Address("oxford street","Apt. 556","gwenbourg","517289",geo);
    UserDetails ud= new UserDetails("sample","1-158, Hyderabad","abc@gmail.com",add);

    baseURI="https://jsonplaceholder.typicode.com/users";

    Response res= given().body(ud).when().post();
            res.then().statusCode(201).log().body();
    System.out.println(res.toString());
    //This deserialization is not working
         System.out.println(res.as(UserDetails.class).getAddress());


/*
    UserDetails resp=given().body(ud).when().post().as(UserDetails.class);

    System.out.println(resp.toString());
*/



}






}
