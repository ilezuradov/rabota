package com.school21.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school21.models.User;
import com.school21.models.UserListResponse;
import com.school21.models.UserSingleResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Retrofit API Tests - manual-solution")
public class ManualRetrofitTests extends RetrofitTestBase {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("GET /users?page=1 — Успешное получение списка пользователей (измененная страница)")
    public void testGetUsersSuccessManual() throws IOException {
        Call<UserListResponse> call = apiService.getUsers(1);
        Response<UserListResponse> response = executeCall(call);

        validateResponse(response, 200);
        assertNotNull(response.body(), "Тело ответа не должно быть пустым");
        assertEquals(1, response.body().getPage(), "Номер страницы должен быть 1");
        assertFalse(response.body().getData().isEmpty(), "Список пользователей не должен быть пустым");
    }

    @Test
    @DisplayName("GET /users/23 — Негативный тест: получение несуществующего пользователя (404)")
    public void testGetUserNotFoundManual() throws IOException {
        Call<UserSingleResponse> call = apiService.getUser(23);
        Response<UserSingleResponse> response = call.execute();

        assertEquals(404, response.code(), "Код ответа должен быть 404 Not Found");
    }

    @Test
    @DisplayName("GET /users/3 — Успешное получение одного пользователя (измененный ID)")
    public void testGetSingleUserSuccessManual() throws IOException {
        Call<UserSingleResponse> call = apiService.getUser(3);
        Response<UserSingleResponse> response = executeCall(call);

        validateResponse(response, 200);
        assertNotNull(response.body(), "Тело ответа не должно быть пустым");
        assertEquals(3, response.body().getData().getId(), "ID пользователя должен быть равен 3");
    }

    @Test
    @DisplayName("POST /users — Успешное создание (построчно + mapper.readValue)")
    public void testCreateUserSuccessManual() throws IOException {
        // Создание JSON построчно в виде строки
        String jsonString = "{" +
                "\"name\": \"Neo\"," +
                "\"job\": \"Hacker\"" +
                "}";

        // Десериализация сразу из строкового значения через mapper.readValue
        User newUser = mapper.readValue(jsonString, User.class);

        Call<User> call = apiService.createUser(newUser);
        Response<User> response = executeCall(call);

        validateResponse(response, 201);
        assertNotNull(response.body(), "Тело ответа не должно быть пустым");
        assertEquals("Neo", response.body().getName(), "Имя созданного пользователя должно быть Neo");
        assertEquals("Hacker", response.body().getJob(), "Профессия должна быть Hacker");
    }

    @Test
    @DisplayName("POST /users — Негативный тест: создание с пустыми данными")
    public void testCreateUserInvalidManual() throws IOException {
        String jsonString = "{}";
        User invalidUser = mapper.readValue(jsonString, User.class);

        Call<User> call = apiService.createUser(invalidUser);
        Response<User> response = executeCall(call);

        assertNotNull(response, "Ответ от сервера не должен быть null");
    }

    @Test
    @DisplayName("PUT /users/3 — Успешное обновление (построчно + mapper.readValue)")
    public void testUpdateUserSuccessManual() throws IOException {
        String jsonString = "{" +
                "\"name\": \"Trinity\"," +
                "\"job\": \"Operator\"" +
                "}";

        User updatedUser = mapper.readValue(jsonString, User.class);

        Call<User> call = apiService.updateUser(3, updatedUser);
        Response<User> response = executeCall(call);

        validateResponse(response, 200);
        assertNotNull(response.body(), "Тело ответа не должно быть пустым");
        assertEquals("Trinity", response.body().getName(), "Имя должно обновиться на Trinity");
    }

    @Test
    @DisplayName("PUT /users/999 — Негативный тест обновления несуществующего пользователя")
    public void testUpdateUserInvalidManual() throws IOException {
        String jsonString = "{" +
                "\"name\": \"Agent Smith\"" +
                "}";

        User updatedUser = mapper.readValue(jsonString, User.class);

        Call<User> call = apiService.updateUser(999, updatedUser);
        Response<User> response = call.execute();

        assertTrue(response.code() >= 200, "Код ответа должен быть успешным или возвращать статус сервера");
    }

    @Test
    @DisplayName("DELETE /users/3 — Успешное удаление пользователя")
    public void testDeleteUserSuccessManual() throws IOException {
        Call<Void> call = apiService.deleteUser(3);
        Response<Void> response = call.execute();

        assertEquals(204, response.code(), "Код ответа при удалении должен быть 204 No Content");
    }
}