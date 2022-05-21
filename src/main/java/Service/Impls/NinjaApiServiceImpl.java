package Service.Impls;

import Service.NinjaService;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class NinjaApiServiceImpl implements NinjaService {

    private Properties properties = new Properties();
    private static final String createOrderEndPoint = "https://v4.ninja.com/sg/order-create/orders";
    private static final String getTokenEndPoint = "https://v4.ninja.com/sg/oauth/access_token";
    private static String accessToken = "";

    @Override
    public boolean isRegisteredUser() {
        // Found no details to validate registered user , returning true
        return true;
    }

    @Override
    public boolean isAuthenticated() {
        loadProperties();
        Map<String, String> body = new HashMap<>();
        body.put("client_id", properties.getProperty("clientId"));
        body.put("client_secret", properties.getProperty("clientSecret"));
        Response responseCode = given().auth().none().log().all()
                .contentType(ContentType.JSON).body(body)
                .when().post(getTokenEndPoint)
                .then().log().all().statusCode(HttpStatus.SC_OK).extract()
                .response();
        //validating response code
        return (responseCode.getStatusCode() == 200);

    }

    @Override
    public String getAccessToken() {
        loadProperties();
        Map<String, String> body = new HashMap<>();
        body.put("client_id", properties.getProperty("clientId"));
        body.put("client_secret", properties.getProperty("clientSecret"));
        JsonPath jsonPath = given().auth().none()
                .log().all()
                .contentType(ContentType.JSON).body(body)
                .when().post(getTokenEndPoint)
                .then().log().all().statusCode(HttpStatus.SC_OK).extract()
                .jsonPath();
        accessToken = jsonPath.getString("access_token");
        return accessToken;
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
        return given().auth().none().header("authorization", accessToken).log().all()
                .contentType(ContentType.JSON).body(orderDetails).when()
                .post(createOrderEndPoint).then().log().all().statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getBoolean("status");

    }


    public void loadProperties() {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
