package restassuredapi.Module1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import restassuredapi.Product;

import java.io.File;
import java.util.HashMap;

public class api {

	public String emp="https://dummy.restapiexample.com/api/v1/employees";
	public String prod="https://dummyjson.com/products";


 /*----------------------------------Get---------------------------------------*/

	@Test
	public void get() {

		String ep = "https://api.genderize.io?name=luc";
		given().contentType(ContentType.JSON).when()
				.get(ep).then().assertThat()
				.statusCode(200).log().body();
	}



	@Test()
	public void newget()
	{

		var resp=given().contentType(ContentType.JSON).when().get(prod);
				resp.then().assertThat().statusCode(200).
		log().body();
	}


	@Test
	public void herobookupget()
	{
		baseURI="https://restful-booker.herokuapp.com/booking";
		String data= """
				"firstname" : "Jim",
				    "lastname" : "Brown",
				    "totalprice" : 111,
				    "depositpaid" : true,
				    "bookingdates" : {
				        "checkin" : "2018-01-01",
				        "checkout" : "2019-01-01"
				    },
				    "additionalneeds" : "Breakfast"
				""";
		given().contentType(ContentType.JSON).body(data).when().post().then().assertThat()
				.log().body();
	}

/*----------------------------------Post-----------------------------------*/

	@Test
	public void post()
	{
		HashMap jo= new HashMap();
		jo.put("name","morpheous");
		jo.put("job","leader");

		given().contentType(ContentType.JSON).body(jo).when().post(emp)
				.then().assertThat().log().body();


	}



/*------------------------------put--------------------------*/

@Test
public void newput()
{

		baseURI="https://dummyjson.com/products/1";
		String jo= """
				              "id": 153,
				               "title": "iPhone 90",
				               "description": "An apple mobile which is nothing like apple",
				               "price": 5497,
				""";
	Response resp=given().contentType(ContentType.JSON).body(jo).when().put();
			resp.then().assertThat().statusCode(200).log().body();
			System.out.println(resp.asString());


}

/*-----------------------------Delete----------------------------------*/
@Test
public void delete()
{
	baseURI="https://dummyjson.com/products";
	Response resp=given().contentType(ContentType.JSON).when().delete("/1");
	resp.then().assertThat().statusCode(204).log().body();
	System.out.println(resp.asString());


}


		
 /*--------------------Assertion---------------------------*/


	@Test()
	public void valueassert()
	{
		/*
		 "id": 6,
            "title": "MacBook Pro",
            "description": "MacBook Pro 2021 with mini-LED display may launch between September, November",
            "price": 1749,
            "discountPercentage": 11.02,
            "rating": 4.57,
            "stock": 83,
            "brand": "Apple",
            "category": "laptops",
            "thumbnail": "https://i.dummyjson.com/data/products/6/thumbnail.png",
            "images": [
                "https://i.dummyjson.com/data/products/6/1.png",
                "https://i.dummyjson.com/data/products/6/2.jpg",
                "https://i.dummyjson.com/data/products/6/3.png",
                "https://i.dummyjson.com/data/products/6/4.jpg"
            ]
		 */

		var resp=given().contentType(ContentType.JSON).when().
				get(prod);
		resp.then().assertThat().statusCode(200).log().body().
				body("products.id",Matchers.everyItem(notNullValue())).
				body("products.title", Matchers.everyItem(notNullValue())).
				body("products.description", Matchers.everyItem(notNullValue())).
				body("products.price", Matchers.everyItem(notNullValue())).
				body("products.rating", Matchers.everyItem(notNullValue()));
	}

  @Test
  public void ArrayAssert()
  {
	  //new hamcrest version not able to assert so use 2.1
	  //numeric values not asserting correctly
	  var resp=given().contentType(ContentType.JSON).when().
			  get(prod);
	  resp.then().assertThat().statusCode(200).log().body().
			  body("products[2].title",equalTo("Samsung Universe 9") ).
	          body("products[10].description",equalTo("Mega Discount, Impression of Acqua Di Gio by GiorgioArmani concentrated attar perfume Oil")).
              body("products[3].discountPercentage",equalTo("<17.91F>")).
	          body("products[8].images[3]",equalTo("https://i.dummyjson.com/data/products/9/4.jpg")).
			  body("products[15].images[0]",equalTo("https://i.dummyjson.com/data/products/16/1.png"));
  }


	//path parameter

    @Test
	public void path()
	{
		baseURI="https://dummyjson.com/products";
		var resp=given().pathParam("Id",1).contentType(ContentType.JSON).when().
				get("/{Id}");
		resp.then().assertThat().statusCode(200).log().body();
	}

  //Query parameter











	}

