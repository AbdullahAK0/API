package practice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Q03_Post {

    /*
            https://restful-booker.herokuapp.com/booking
             url’ine asagidaki body'ye sahip
            bir POST request gonderdigimizde
                       {
                            "firstname" : "Ali",
                            "lastname" : "Bak",
                            "totalprice" : 500,
                            "depositpaid" : false,
                            "bookingdates" : {
                                "checkin" : "2021-06-01",
                                "checkout" : "2021-06-10"
                            },
                            "additionalneeds" : "wi-fi"
                        }
            donen Response’un,
            status code’unun 200,
            ve content type’inin application-json,
            ve response body’sindeki
                "firstname“in,"Ali",
                ve "lastname“in, "Bak",
                ve "totalprice“in,500,
                ve "depositpaid“in,false,
                ve "checkin" tarihinin 2021-06-01
                ve "checkout" tarihinin 2021-06-10
                ve "additionalneeds“in,"wi-fi"
            oldugunu test edin
     */

    @Test
    public void post01(){

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject jsonObject = new JSONObject();

        JSONObject bookinDates = new JSONObject();
        bookinDates.put("checkin","2021-06-01");
        bookinDates.put("checkout","2021-06-10");

        jsonObject.put("firstname" , "Ali");
        jsonObject.put("lastname" , "Bak");
        jsonObject.put("totalprice" , 500);
        jsonObject.put("depositpaid" , false);
        jsonObject.put("bookingdates",bookinDates);
        jsonObject.put("additionalneeds" , "wi-fi");

        System.out.println(jsonObject);

        Response response = given().contentType(ContentType.JSON).when().body(jsonObject.toString()).post(url);
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .body("booking.firstname", equalTo("Ali"),"booking.lastname",equalTo("Bak"),"booking.totalprice",equalTo(500)
                ,"booking.depositpaid",equalTo(false),"booking.bookingDates.checkin",equalTo("2021-06-01"),"booking.bookingDates.checkout",equalTo("2021-06-10")
                ,"booking.additionalneeds",equalTo("wi-fi"));
    }
}
