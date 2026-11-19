package com.school21.restassured;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school21.models.UserRecord;
import com.school21.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("REST-Assured API Tests - manual-solution")
public class RestAssuredManualTests extends RestAssuredTestBase {

    private final ObjectMapper mapper = new ObjectMapper();

    @AfterEach
    public void tearDown() {
        TestUtils.sleep(1);
    }

    @Test
    @DisplayName("GET /users?page=1 — Успешное получение первой страницы пользователей")
    public void getFirstPageUsersTest() {
        Response response = getRequestWithParams(USERS_ENDPOINT, "page", 1);
        validateStatusCode(response, 200);

        int page = response.jsonPath().getInt("page");
        assertEquals(1, page, "Номер страницы должен быть равен 1");
    }

    @Test
    @DisplayName("GET /users/2 — Успешное получение пользователя по ID (десериализация в Record)")
    public void getUserByIdRecordTest() {
        Response response = getRequest(USERS_ENDPOINT + "/2");
        validateStatusCode(response, 200);

        UserRecord user = response.jsonPath().getObject("data", UserRecord.class);

        assertEquals(2, user.id(), "ID пользователя должен быть равен 2");
        assertEquals("janet.weaver@reqres.in", user.email(), "Email пользователя не совпадает");
    }

    @Test
    @DisplayName("GET /users/999 — Негативный тест: пользователь не найден (404)")
    public void getUserNotFoundTest() {
        Response response = getRequest(USERS_ENDPOINT + "/999");
        validateStatusCode(response, 404);
    }

    @Test
    @DisplayName("POST /users — Успешное создание пользователя (построчно + mapper.readValue)")
    public void createUserRecordTest() throws Exception {
        // Конструкция создания полей построчно в виде строки
        String jsonString = "{" +
                "\"name\": \"John Doe\"," +
                "\"job\": \"Tester\"" +
                "}";

        // Десериализация сразу из строкового значения в Record через ObjectMapper
        UserRecord newUserData = mapper.readValue(jsonString, UserRecord.class);

        Response response = postRequest(USERS_ENDPOINT, newUserData);
        validateStatusCode(response, 201);

        UserRecord createdUser = response.as(UserRecord.class);
        assertEquals("John Doe", createdUser.name(), "Имя созданного пользователя должно быть John Doe");
        assertEquals("Tester", createdUser.job(), "Должность должна быть Tester");
        assertNotNull(createdUser.id(), "У созданного пользователя должен появиться ID");
    }

    @Test
    @DisplayName("POST /register — Негативный тест: ошибка регистрации без пароля (400)")
    public void registerUnsuccessfulTest() {
        String jsonBody = "{\"email\": \"sydney@fife\"}";
        Response response = postRequest("/register", jsonBody);
        validateStatusCode(response, 400);
    }

    @Test
    @DisplayName("PUT /users/2 — Успешное обновление данных пользователя")
    public void updateUserRecordTest() throws Exception {
        String jsonString = "{" +
                "\"name\": \"John Updated\"," +
                "\"job\": \"Senior Tester\"" +
                "}";

        UserRecord updateData = mapper.readValue(jsonString, UserRecord.class);

        Response response = putRequest(USERS_ENDPOINT + "/2", updateData);
        validateStatusCode(response, 200);

        UserRecord updatedUser = response.as(UserRecord.class);
        assertEquals("John Updated", updatedUser.name(), "Имя должно обновиться");
        assertEquals("Senior Tester", updatedUser.job(), "Должность должна обновиться");
    }

    @Test
    @DisplayName("PUT /unknown/2 — Негативный тест: обновление по неверному эндпоинту (404)")
    public void updateInvalidEndpointTest() throws Exception {
        String jsonString = "{" +
                "\"name\": \"Test\"," +
                "\"job\": \"Test\"" +
                "}";

        UserRecord updateData = mapper.readValue(jsonString, UserRecord.class);
        Response response = putRequest("/unknown/2", updateData);
        validateStatusCode(response, 404);
    }

    @Test
    @DisplayName("DELETE /users/2 — Успешное удаление пользователя (204)")
    public void deleteUserTest() {
        Response response = deleteRequest(USERS_ENDPOINT + "/2");
        validateStatusCode(response, 204);
    }
}