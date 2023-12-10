package spec;

import io.qameta.allure.Allure;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import static io.restassured.RestAssured.oauth2;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static RequestSpecification unauthorizedRequestSpec;
    public static String username = "userForTests";
    public static String password = "1234";
    public static String baseUri = "https://petstore.swagger.io/v2";
    public static String token = "special-key";

    @BeforeEach
    public void requestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri(baseUri).
                setAuth(oauth2(token)).
                setContentType(ContentType.JSON).
                build();
        unauthorizedRequestSpec = new RequestSpecBuilder().
                setBaseUri(baseUri).
                setContentType(ContentType.JSON).
                build();
    }

    public ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder().
                expectStatusCode(statusCode).
                expectResponseTime(lessThanOrEqualTo(3000L)).
                log(LogDetail.ALL).
                build();
    }
    @BeforeAll
    public void startUp(){
        BeforeAllTests beforeAllTests = new BeforeAllTests();
        this.requestSpecification();
        beforeAllTests.createUserForTests(requestSpec, username, password);
    }

}
