package Service.Impls;

import Beans.Orders;
import Service.NinjaService;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class NinjaApiServiceImpl implements NinjaService {


    @Override
    public boolean isRegisteredUser() {
        // Found no details to validate registered user , returning true
        return true;
    }

    @Override
    public String isAuthenticated() {
       /* ValidatableResponseOptions<ValidatableResponse, Response> responseCode = given().auth().none().log().all()
                .contentType(ContentType.JSON).body().when().post()
                .then().log().all();
        //validating response code
        Assert.assertEquals(responseCode.log().ifStatusCodeIsEqualTo(200);
        String accessToken= responseCode.log().all().extract().path("access_token");*/


        return "accessToken";
    }

    @Override
    public boolean login() {
        throw new UnsupportedOperationException("Only UI case");
    }

    @Override
    public String navigatesToOrderDetails() {
        throw new UnsupportedOperationException("Only UI case");
    }

    @Override
    public String printAirwayBill() {
        throw new UnsupportedOperationException("Only UI case");
    }

    @Override
    public boolean createOrder(String orderDetails) {
        return given().auth().none().log().all()
                .contentType(ContentType.JSON).body(orderDetails).when()
                .post().then().log().all().statusCode(HttpStatus.SC_OK).extract().jsonPath().getBoolean("status");

    }
}
