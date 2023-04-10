package practice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Q01_Get {

    // API URL: https://automationexercise.com/api/productsList
    // Request Method: GET
    // Response Code: 200
    // Response JSON: All brands list

    @Test
    public void get01(){

        String url = "https://automationexercise.com/api/brandsList";

        Response response = given().when().get(url);

        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.HTML);
    }
}
