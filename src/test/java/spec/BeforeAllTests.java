package spec;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;

public class BeforeAllTests{
    public void createUserForTests(RequestSpecification requestSpecification, String username, String password) {
        JSONObject body  = new JSONObject();
        body.put("username", username);
        body.put("firstName", "Tester");
        body.put("lastName", "Testerovich");
        body.put("password", password);
        Response response = RestAssured.given(requestSpecification).
                body(body.toString()).
                post("/user");
    }
}
