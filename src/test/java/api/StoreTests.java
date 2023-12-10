package api;

import interfaces.IStore;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jsonBody.OrderBody;
import model.Order;
import net.jqwik.api.Example;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import spec.BaseTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreTests extends BaseTest implements IStore {
    @Example
    @Description("Добавить рандомный заказ")
    public void placeARandomOrder() {
        JSONObject body = randomOrder();
        Response response = RestAssured.given(requestSpec).
                body(body.toString()).
                post("/store/order");
        response.then().spec(responseSpec(200));
    }
    @ParameterizedTest
    @MethodSource("data.DataForOrders#createOrder")
    @Description("Добавить заказ")
    @DisplayName("POST запрос для добавления заказа")
    public void placeAnOrderForAPet(Order order) {
        OrderBody orderBody = new OrderBody(order.getId(), order.getPetId(), order.getQuantity(),
                order.getShipDate(), getOrderStatus(), order.getComplete());
        Response response = RestAssured.given(requestSpec).
                body(orderBody).
                post("/store/order");
        response.then().spec(responseSpec(200));
        OrderBody responseBody = response.as(OrderBody.class);
        assertEquals(orderBody.toString(), responseBody.toString());
    }
    @Test
    @Description("Проверка статус-кода 400 Bad Request в POST запросе на добавление заказа без RequestBody")
    @DisplayName("POST запрос для добавления заказа: 400 Bad Request")
    public void badPlaceAnOrderForAPet() {
        JSONObject body = new JSONObject();
        body.put("id", 494940399);
        Response response = RestAssured.given(requestSpec).
                post("/store/order");
        response.then().spec(responseSpec(400));
    }
    @Test
    @Description("Получить заказ по id")
    @DisplayName("GET запрос на получение заказа по id")
    public void findPurchaseOrderByID() {
        int orderId = 938;
        JSONObject body = new JSONObject();
        body.put("id", orderId);
        Response resp = RestAssured.given(requestSpec).
                body(body.toString()).
                post("/store/order");
        Response response = RestAssured.given(requestSpec).
                get("/store/order/" + orderId);
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Проверка ошибки 404 в запросе на получение заказа по id с отрицательным значением")
    @DisplayName("GET запрос на получение заказа по id: 404 Not Found")
    public void notFoundPurchaseOrderByID() {
        int orderId = -12;
        Response response = RestAssured.given(requestSpec).
                get("/store/order/" + orderId);
        response.then().spec(responseSpec(404));
    }
    @Test
    @Description("Удалить заказ по id")
    @DisplayName("DELETE запрос на удаление заказа по id")
    public void deletePurchaseOrderByID() {
        int orderId = 79;
        JSONObject body = new JSONObject();
        body.put("id", orderId);
        Response resp = RestAssured.given(requestSpec).
                body(body.toString()).
                post("/store/order");
        //delete this order
        Response response = RestAssured.given(requestSpec).
                delete("/store/order/" + orderId);
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Проверка ошибки 404 в запросе на удаление заказа по несуществующему id")
    @DisplayName("DELETE запрос на удаление заказа по id: 404 Not Found")
    public void notFoundDeletePurchaseOrderByID() {
        int orderId = 976543;
        Response response = RestAssured.given(requestSpec).
                delete("/store/order/" + orderId);
        response.then().spec(responseSpec(404));
    }
    @Test
    @Description("Получить количество питомцев по статусу")
    @DisplayName("GET запрос на получение количества питомцев по статусу")
    public void returnsPetInventoriesByStatus() {
        Response response = RestAssured.given(requestSpec).
                get("/store/inventory");
        response.then().spec(responseSpec(200));
    }
}
