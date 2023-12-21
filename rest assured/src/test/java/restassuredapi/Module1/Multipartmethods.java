package restassuredapi.Module1;

import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.config.RestAssuredConfig.config;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Multipartmethods
{
    //using multipart post method
    //can send multipart multiple times
    @Test
    public void multipart_post()
    {
        baseURI="https://postman-echo.com";

        given().multiPart("foo1","bar1").multiPart("foo2","bar2").multiPart("foo3","bar3").log().body()
                .when().post("/post").then().log().all();

    }

    @Test
    public void multipart_uploadfile()
    {
        baseURI="https://postman-echo.com";

        given().multiPart("file",new File("C:\\Users\\smukeshali\\Desktop\\rest assured\\rest assured\\src\\test\\resources\\multipart.json")).log().body()
                .when().post("/post").then().log().all();

    }

    //currently unable to load the website so couldnt able to download
    @Test
    public void downloadfile() throws IOException {
        InputStream is=
        given().baseUri("https://raw.githubusercontent.com").log().all()
                .when().get("appium/appium/master/sample-cpde/apps/ApiDemos-debug.apk").then().log().all()
                .extract().response().asInputStream();

        OutputStream os= new FileOutputStream(new File("ApiDemos-debug.apk"));
        byte[] bytes= new byte[is.available()];
        is.read(bytes);
        os.write(bytes);
        os.close();
    }

    @Test
    public void Formurlencoding()
    {
        //passing url encoded in form parameters
        //status code will be 200
        baseURI="https://postman-echo.com";
        given().formParam("key1","value1").
                formParam("key 2","value 2")
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .log().all().
        when().post("/post").then().assertThat().statusCode(200)
                .log().all();
    }

    @Test
    public void jsonschemavalidation()
    {
        baseURI="https://postman-echo.com";
        given().when().get("/get").then().log().all().assertThat().
        statusCode(200).body(matchesJsonSchemaInClasspath("C:\\Users\\smukeshali\\Desktop\\rest assured\\rest assured\\src\\test\\resources\\Echo.json"));

    }










}
