package practice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Q02_Post {

    //API URL: https://automationexercise.com/api/searchProduct
    //Request Method: POST
    //Request Parameter: search_product (For example: top, tshirt, jean)
    //Response Code: 200
    //Response JSON: Searched products list

    @Test
    public void post01(){

        String url = "https://automationexercise.com/api/searchProduct";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("search_product","tshirt");

        Response response = given().contentType(ContentType.HTML).when().body(jsonObject.toString()).post(url);

        response.prettyPrint();
    }
}
