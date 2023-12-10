package api;

import interfaces.IUser;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jsonBody.UserBody;
import model.User;
import net.jqwik.api.Example;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import spec.BaseTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests extends BaseTest implements IUser {
    @Example
    @Description("Создать рандомного пользователя")
    public void createRandomUser() {
        Response response = RestAssured.given(requestSpec).
                body(randomUser().toString()).
                post("/user");
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Проверка статус кода 404 Not Found с ошибкой в uri при создании пользователя")
    @DisplayName("POST запрос для создания пользователя: 404 Not Found")
    public void notFoundCreateUser() {
        UserBody userBody = new UserBody("Beverniz", "Agelaus", "Russel",
                "frifabreximmu-8614@yopmail.com", "lqG1P2", "+1-436-682-9229", 1);
        Response response = RestAssured.given(requestSpec).
                body(randomUser().toString()).
                post("/user-error");
        response.then().spec(responseSpec(404));
    }
    @ParameterizedTest
    @MethodSource("data.DataForUsers#user2")
    @Description("Получить информацию о пользователе по username")
    @DisplayName("GET запрос на получение информации о пользователе по username")
    public void getUserByUsername(User user) throws InterruptedException {
        UserBody userBody = new UserBody(user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getUserStatus());
        Response resp = RestAssured.given(requestSpec).
                body(userBody).
                post("/user");
        TimeUnit.SECONDS.sleep(1);
        Response response = RestAssured.given(unauthorizedRequestSpec).
                get("/user/" + user.getUsername());
        response.then().spec(responseSpec(200));
        UserBody responseBody = response.as(UserBody.class);
        assertEquals(userBody.toString(), responseBody.toString());
    }
    @Test
    @Description("Проверка ошибки 404 с несуществующим username в запросе на получение информации о пользователе")
    @DisplayName("GET запрос на получение информации о пользователе: 404 Not Found")
    public void notFoundGetUserByUsername() {
        String username = "error";
        Response response = RestAssured.given(unauthorizedRequestSpec).
                get("/user/" + username);
        response.then().spec(responseSpec(404));
    }
    @Test
    @Description("Проверка ошибки 405 с использованием " +
            "запрещенного метода в запросе на получение информации о пользователе")
    @DisplayName("POST запрос на получение информации о пользователе: 405 Method Not Allowed")
    public void notAllowedGetUserByUsername() {
        Response response = RestAssured.given(unauthorizedRequestSpec).
                post("/user/" + username);
        response.then().spec(responseSpec(405));
    }
    @Test
    @Description("Обновить информацию о пользователе по username")
    @DisplayName("PUT запрос на обновление информации о пользователе по username")
    public void updateUser() {
        JSONObject body = new JSONObject();
        body.put("phone", "76(5918)49963 ");
        Response response = RestAssured.given(requestSpec).
                body(body.toString()).
                put("/user/" + username);
        response.then().spec(responseSpec(200));
    }

    @ParameterizedTest
    @MethodSource("data.DataForUsers#user1")
    @Description("Удаление пользователя по username")
    @DisplayName("DELETE запрос на удаление пользователя по username")
    public void deleteUser(User user) {
        UserBody userBody = new UserBody(user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getUserStatus());
        Response resp = RestAssured.given(requestSpec).
                body(userBody).
                post("/user");
        Response response = RestAssured.given(requestSpec).
                delete("/user/" + user.getUsername());
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Проверка ошибк 404 Not Found при удалении пользователя по несуществующему username")
    @DisplayName("DELETE запрос на удаление пользователя: 404 Not Found")
    public void notFoundDeleteUser() {
        String username = "error";
        Response response = RestAssured.given(requestSpec).
                delete("/user/" + username);
        response.then().spec(responseSpec(404));
    }
    @Test
    @Description("Войти в систему")
    @DisplayName("GET запрос для входа в систему")
    public void logsUserIntoTheSystem(){
        Response response = RestAssured.given(requestSpec).
                queryParam("username", username).
                queryParam("password", password).
                get("/user/login");
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Проверка 405 Method Not Allowed при выборе некорректного медода в запросе на вход в систему")
    @DisplayName("POST запрос для входа в систему: 405 Method Not Allowed")
    public void notAllowedLogsUserIntoTheSystem(){
        Response response = RestAssured.given(requestSpec).
                queryParam("username", username).
                queryParam("password", password).
                post("/user/login");
        response.then().spec(responseSpec(405));
    }
    @Test
    @Description("Выйти из системы")
    @DisplayName("GET запрос для выхода из системы")
    public void logsOutCurrentLoggedInUserSession() {
        Response response = RestAssured.given(requestSpec).
                get("/user/logout");
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Создать список пользователей")
    @DisplayName("POST запрос на создание списка пользователей")
    public void createsListOfUsersWithGivenInputArray(){
        ArrayList<JSONObject> list = new ArrayList<>();
        int rand = getRandomQuantity(7, 2);
        for(int i = 0; i < rand; i++) {
            list.add(randomUser());
        }
        Response response = RestAssured.given(requestSpec).
                body(list).
                post("/user/createWithArray");
        response.then().spec(responseSpec(200));
    }
}
