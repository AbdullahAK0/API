package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C13_Get_SoftAssertIleExpectedDataTesti {

    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
        Response Body
        {
        "status":"success",
        "data":{
                "id":3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
                },
        "message":"Successfully! Record has been fetched."
        }
     */


    @Test
    public void get01(){

        // 1 - Url olustur.

        String url = "http://dummy.restapiexample.com/api/v1/employee/3";

        // 2- Expected Data hazirla

        /*
        {
        "status":"success",
        "data":{
                "id":3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
                },
        "message":"Successfully! Record has been fetched."
        }
         */

        JSONObject data = new JSONObject();

        data.put("id",3);
        data.put("employee_name","Ashton Cox");
        data.put("employee_salary",86000);
        data.put("employee_age",66);
        data.put("profile_image","");

        JSONObject exbody = new JSONObject();

        exbody.put("status","success");
        exbody.put("message","Successfully! Record has been fetched.");
        exbody.put("data",data);

        Response response = given().when().get(url);

        response.prettyPrint();

        // 4 - Assertion

        JsonPath respJS = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(respJS.get("status"),exbody.get("status"));
        softAssert.assertEquals(respJS.get("message"),exbody.get("message"));
        softAssert.assertEquals(respJS.get("data.id"),exbody.getJSONObject("data").get("id"));
        softAssert.assertEquals(respJS.get("employee_name"),exbody.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(respJS.get("employee_salary"),exbody.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(respJS.get("employee_age"),exbody.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(respJS.get("profile_image"),exbody.getJSONObject("data").get("profile_image"));

        softAssert.assertAll();


    }
}
