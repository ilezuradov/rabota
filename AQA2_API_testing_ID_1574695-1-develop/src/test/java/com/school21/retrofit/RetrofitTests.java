package com.school21.retrofit;
// ai-assisted-solution
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school21.models.User;
import com.school21.models.UserListResponse;
import com.school21.models.UserSingleResponse;
import com.school21.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Retrofit API Tests - ai-assisted-solution")
public class RetrofitTests extends RetrofitTestBase {

    private final ObjectMapper mapper = new ObjectMapper();

    @AfterEach
    public void tearDown() {
        TestUtils.sleep(2);
    }

    @Test
    @DisplayName("GET /users - Positive: Получить список пользователей")
    public void testGetUsersPositive() throws IOException {
        Response<UserListResponse> response = executeCall(apiService.getUsers(2));
        validateResponse(response, 200);

        UserListResponse userList = response.body();
        assertThat(userList).isNotNull();
        assertThat(userList.getPage()).isEqualTo(2);
        assertThat(userList.getData()).isNotEmpty();
    }

    @Test
    @DisplayName("GET /users - Negative: Страница с несуществующими данными")
    public void testGetUsersNegative() throws IOException {
        Response<UserListResponse> response = executeCall(apiService.getUsers(9999));
        validateResponse(response, 200);

        UserListResponse userList = response.body();
        assertThat(userList).isNotNull();
        assertThat(userList.getData()).isEmpty();
    }

    @Test
    @DisplayName("GET /users/{id} - Positive: Получить пользователя по ID")
    public void testGetUserByIdPositive() throws IOException {
        Response<UserSingleResponse> response = executeCall(apiService.getUser(2));
        validateResponse(response, 200);

        UserSingleResponse userResponse = response.body();
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getData().getId()).isEqualTo(2);
        assertThat(userResponse.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
    }

    @Test
    @DisplayName("GET /users/{id} - Negative: Несуществующий пользователь")
    public void testGetUserByIdNegative() throws IOException {
        Response<UserSingleResponse> response = executeCall(apiService.getUser(99999));
        validateResponse(response, 404);
    }

    @Test
    @DisplayName("POST /users - Positive: Создать пользователя (построчно + mapper.readValue)")
    public void testCreateUserPositive() throws IOException {
        // Создание JSON построчно в виде строки
        String jsonString = "{" +
                "\"name\": \"Charlie Brown\"," +
                "\"job\": \"Developer\"" +
                "}";

        // Десериализация сразу из строкового значения через mapper.readValue
        User newUser = mapper.readValue(jsonString, User.class);

        Response<User> response = executeCall(apiService.createUser(newUser));
        validateResponse(response, 201);

        User createdUser = response.body();
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getName()).isEqualTo("Charlie Brown");
        assertThat(createdUser.getJob()).isEqualTo("Developer");
        assertThat(createdUser.getId()).isNotNull();
    }

    @Test
    @DisplayName("POST /users - Negative: Создать пользователя с пустыми данными")
    public void testCreateUserNegative() throws IOException {
        String jsonString = "{}";
        User newUser = mapper.readValue(jsonString, User.class);

        Response<User> response = executeCall(apiService.createUser(newUser));
        validateResponse(response, 201);
    }

    @Test
    @DisplayName("PUT /users/{id} - Positive: Обновить пользователя (построчно + mapper.readValue)")
    public void testUpdateUserPositive() throws IOException {
        String jsonString = "{" +
                "\"name\": \"Diana Prince\"," +
                "\"job\": \"Product Manager\"" +
                "}";

        User updateUser = mapper.readValue(jsonString, User.class);

        Response<User> response = executeCall(apiService.updateUser(2, updateUser));
        validateResponse(response, 200);

        User updatedUser = response.body();
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getName()).isEqualTo("Diana Prince");
        assertThat(updatedUser.getJob()).isEqualTo("Product Manager");
        assertThat(updatedUser.getUpdatedAt()).isNotNull();
    }

    @Test
    @DisplayName("PUT /users/{id} - Negative: Обновить несуществующего пользователя")
    public void testUpdateUserNegative() throws IOException {
        String jsonString = "{" +
                "\"name\": \"Test\"," +
                "\"job\": \"Test Job\"" +
                "}";

        User updateUser = mapper.readValue(jsonString, User.class);

        Response<User> response = executeCall(apiService.updateUser(99999, updateUser));

        // Reqres.in возвращает 200 даже для несуществующего ID при PUT,
        // поэтому проверяем, что сервер ответил успехом и вернул дату обновления (или проверяем код 200)
        validateResponse(response, 200);
        assertThat(response.body().getUpdatedAt()).isNotNull();
    }

    @Test
    @DisplayName("DELETE /users/{id} - Positive: Удалить пользователя")
    public void testDeleteUserPositive() throws IOException {
        Response<Void> response = executeCall(apiService.deleteUser(2));
        validateResponse(response, 204);
    }

    @Test
    @DisplayName("DELETE /users/{id} - Negative: Удалить несуществующего пользователя")
    public void testDeleteUserNegative() throws IOException {
        Response<Void> response = executeCall(apiService.deleteUser(99999));
        validateResponse(response, 204);
    }
}