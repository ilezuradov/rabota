package com.school21.okhttp;
// manual-solution
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school21.models.User;
import com.school21.models.UserListResponse;
import com.school21.utils.TestUtils;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OkHttp API Tests - manual-solution")
public class OkHttpTests extends OkHttpTestBase {

    private final ObjectMapper mapper = new ObjectMapper();

    @AfterEach
    public void tearDown() {
        TestUtils.sleep(1);
    }

    @Test
    @DisplayName("GET /users?page=2 — Успешное получение списка пользователей")
    public void getUsersListSuccessTest() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + USERS_ENDPOINT + "?page=2")
                .get()
                .build();

        try (Response response = executeRequest(request)) {
            validateResponse(response, 200);
            UserListResponse responseDto = parseUserListResponse(response);

            assertEquals(2, responseDto.getPage(), "Страница ответа должна быть равна 2");
            assertNotNull(responseDto.getData(), "Список пользователей не должен быть null");
        }
    }

    @Test
    @DisplayName("GET /users/23 — Негативный тест: пользователь не найден (404)")
    public void getUserNotFoundTest() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + USERS_ENDPOINT + "/23")
                .get()
                .build();

        try (Response response = executeRequest(request)) {
            validateResponse(response, 404);
        }
    }

    @Test
    @DisplayName("POST /users — Успешное создание пользователя (построчно + mapper.readValue)")
    public void createUserSuccessTest() throws IOException {
        String jsonString = "{" +
                "\"name\": \"morpheus\"," +
                "\"job\": \"leader\"" +
                "}";

        User user = mapper.readValue(jsonString, User.class);

        String finalJsonBody = mapper.writeValueAsString(user);
        RequestBody body = RequestBody.create(finalJsonBody, jsonMediaType);

        Request request = new Request.Builder()
                .url(BASE_URL + USERS_ENDPOINT)
                .post(body)
                .build();

        try (Response response = executeRequest(request)) {
            validateResponse(response, 201);
            User createdUser = parseUser(response);

            assertEquals("morpheus", createdUser.getName(), "Имя пользователя не совпадает");
            assertEquals("leader", createdUser.getJob(), "Профессия пользователя не совпадает");
        }
    }

    @Test
    @DisplayName("POST /register — Негативный тест: ошибка регистрации без пароля (400)")
    public void registerUserMissingPasswordTest() throws IOException {
        String rawJson = "{\"email\": \"sydney@fife\"}";
        RequestBody body = RequestBody.create(rawJson, jsonMediaType);

        Request request = new Request.Builder()
                .url(BASE_URL + "/register")
                .post(body)
                .build();

        try (Response response = executeRequest(request)) {
            assertFalse(response.isSuccessful(), "Регистрация без пароля должна завершиться ошибкой!");
            assertEquals(400, response.code(), "Код ответа должен быть 400 Bad Request!");
        }
    }

    @Test
    @DisplayName("PUT /users/2 — Успешное обновление пользователя")
    public void updateUserSuccessTest() throws IOException {
        String jsonString = "{" +
                "\"name\": \"morpheus\"," +
                "\"job\": \"zion resident\"" +
                "}";

        User user = mapper.readValue(jsonString, User.class);
        String finalJsonBody = mapper.writeValueAsString(user);
        RequestBody body = RequestBody.create(finalJsonBody, jsonMediaType);

        Request request = new Request.Builder()
                .url(BASE_URL + USERS_ENDPOINT + "/2")
                .put(body)
                .build();

        try (Response response = executeRequest(request)) {
            validateResponse(response, 200);
            User updatedUser = parseUser(response);

            assertEquals("zion resident", updatedUser.getJob(), "Профессия не обновилась!");
        }
    }

    @Test
    @DisplayName("PUT /unknown_endpoint/999 — Негативный тест: обновление по несуществующему пути")
    public void updateInvalidEndpointTest() throws IOException {
        RequestBody body = RequestBody.create("{}", jsonMediaType);

        Request request = new Request.Builder()
                .url(BASE_URL + "/unknown_endpoint/999")
                .put(body)
                .build();

        try (Response response = executeRequest(request)) {
            // ReqRes возвращает код 200 для этого специфичного тест-кейса с неизвестным эндпоинтом
            assertEquals(200, response.code(), "Код ответа должен соответствовать поведению сервера ReqRes");
        }
    }

    @Test
    @DisplayName("DELETE /users/2 — Успешное удаление пользователя")
    public void deleteUserSuccessTest() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + USERS_ENDPOINT + "/2")
                .delete()
                .build();

        try (Response response = executeRequest(request)) {
            validateResponse(response, 204);
        }
    }

    @Test
    @DisplayName("DELETE /invalid_endpoint — Негативный тест: удаление по неверному эндпоинту (401)")
    public void deleteInvalidEndpointTest() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/invalid_delete_endpoint")
                .delete()
                .build();

        try (Response response = executeRequest(request)) {
            assertFalse(response.isSuccessful(), "Удаление несуществующего ресурса должно вернуть ошибку!");
            assertEquals(401, response.code(), "Код ответа должен быть 401!");
        }
    }
}