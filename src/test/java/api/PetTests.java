package api;

import interfaces.IPet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import jsonBody.Category;
import jsonBody.PetBody;
import model.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import spec.BaseTest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PetTests extends BaseTest implements IPet {
    @Test
    @Description("Добавить нового питомца в магазин")
    public void createPet() {
        Category category = new Category(1, "parrot");
        ArrayList<String> pic = new ArrayList<>();
        pic.add("https://images.app.goo.gl/HS4Y8P9JA8dpKe7A8");
        PetBody petBody = new PetBody(111, category, "Sugar", pic, "available");
        Response response = RestAssured.given(requestSpec).
                body(petBody).
                post("/pet");
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Загрузить изображение питомца")
    public void uploadAnImage() {
        int petId = 1;
        File file = new File("C:\\IdeaProjects\\pet\\src\\test\\java\\resources\\pet-planning_pet-trust-primer_main-image.jpg");
        Response response = RestAssured.given(requestSpec).
                contentType(ContentType.MULTIPART).
                multiPart(file).
                post("/pet/" + petId + "/uploadImage");
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Проверка статус кода 400 Bad Request с ошибкой в запросе(отсутствие файла)")
    public void badUploadAnImage() {
        int petId = 1;
        Response response = RestAssured.given(requestSpec).
                contentType(ContentType.MULTIPART).
                post("/pet/" + petId + "/uploadImage");
        response.then().spec(responseSpec(400));
    }
    @Test
    @Description("Проверка статус кода 404 Not Found при загрузке изображения к питомцу с невалидным id")
    public void notFoundUploadAnImage() {
        String petId = "error";
        File file = new File("C:\\IdeaProjects\\pet\\src\\test\\java\\resources\\pet-planning_pet-trust-primer_main-image.jpg");
        Response response = RestAssured.given(requestSpec).
                contentType(ContentType.MULTIPART).
                multiPart(file).
                post("/pet/" + petId + "/uploadImage");
        response.then().spec(responseSpec(404));
    }
    @Test
    @Description("Обновить информацию о питомце")
    public void updateAnExistingPet() {
        Category category = new Category(1, "updated parrot");
        ArrayList<String> pic = new ArrayList<>();
        pic.add("https://images.app.goo.gl/pFzg9GJ3F1rdZBet6");
        PetBody petBody = new PetBody(1, category, "Updated-Sugar", pic, "sold");
        Response response = RestAssured.given(requestSpec).
                body(petBody).
                put("/pet");
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Получить информацию о питомцах по статусу")
    public void findPetsByStatus() {
        String status = getPetStatus();
        Response response = RestAssured.given(requestSpec).
                queryParam("status", status).
                get("/pet/findByStatus");
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Получить информацию о питомце по id")
    public void findPetByID() {
        int petId = 111;
        Response response = RestAssured.given(requestSpec).
                get("/pet/" + petId);
        response.then().spec(responseSpec(200));
    }
    @Test
    @Description("Проверка статус-кода 404 Not Found при получении информации о питомце по несуществующему id")
    public void notFoundPetByNonExistentID() {
        int petId = 959039484;
        Response response = RestAssured.given(requestSpec).
                get("/pet/" + petId);
        response.then().spec(responseSpec(404));
    }
    @Test
    @Description("Обновить информацию о питомце в магазине")
    public void updatesAPetInTheStoreWithFormData() {
        int petId = 111;
        String name = "updated parrot";
        String status = getPetStatus();
        Map<String, String> formParams = new HashMap<>();
        formParams.put("name", name);
        formParams.put("status", status);
        Response response = RestAssured.given(requestSpec).
                contentType(ContentType.URLENC).
                formParams(formParams).
                post("/pet/" + petId);
        response.then().spec(responseSpec(200));
    }
    @ParameterizedTest
    @MethodSource("data.DataForPets#createPet")
    @Description("Удалить питомца по id")
    public void deletesAPet(Pet pet) {
        PetBody petBody = createPetWithPOJO(pet, getPetStatus());
        Response postResp = RestAssured.given(requestSpec).
                body(petBody).
                post("/pet");
        Response response = RestAssured.given(requestSpec).
                delete("/pet/" + pet.getId());
        response.then().spec(responseSpec(200));
    }
}
