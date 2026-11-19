package com.school21.restassured;
// ai-assisted-solution
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school21.models.UserRecord;
import com.school21.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("REST-Assured API Tests - ai-assisted-solution")
public class RestAssuredTests extends RestAssuredTestBase {

    private final ObjectMapper mapper = new ObjectMapper();

    @AfterEach
    public void tearDown() {
        TestUtils.sleep(2);
    }

    @Test
    @DisplayName("GET /users - Positive: Получить список пользователей")
    public void testGetUsersPositive() {
        Response response = getRequest(USERS_ENDPOINT + "?page=2");
        validateStatusCode(response, 200);

        int page = response.jsonPath().getInt("page");
        assertThat(page)
                .as("Проверка номера страницы в ответе")
                .isEqualTo(2);

        String email = response.jsonPath().getString("data[0].email");
        assertThat(email)
                .as("Проверка, что email первого пользователя в списке не пустой")
                .isNotNull();
    }

    @Test
    @DisplayName("GET /users - Negative: Запрос страницы с неверными параметрами")
    public void testGetusersNegative() {
        Response response = getRequestWithParams(USERS_ENDPOINT, "page", 9999);
        validateStatusCode(response, 200);

        int total = response.jsonPath().getInt("total");
        assertThat(total)
                .as("Проверка общего количества элементов для несуществующей страницы")
                .isEqualTo(12);
    }

    @Test
    @DisplayName("GET /users/{id} - Positive: Получить пользователя по ID")
    public void testGetUserByIdPositive() {
        Response response = getRequest(USERS_ENDPOINT + "/2");
        validateStatusCode(response, 200);

        UserRecord user = response.jsonPath().getObject("data", UserRecord.class);

        assertThat(user.id())
                .as("Проверка ID полученного пользователя")
                .isEqualTo(2);
        assertThat(user.email())
                .as("Проверка email пользователя")
                .isEqualTo("janet.weaver@reqres.in");
    }

    @Test
    @DisplayName("GET /users/{id} - Negative: Несуществующий пользователь")
    public void testGetUserByIdNegative() {
        Response response = getRequest(USERS_ENDPOINT + "/99999");
        validateStatusCode(response, 404);
    }

    @Test
    @DisplayName("POST /users - Positive: Создать пользователя (построчно + mapper.readValue)")
    public void testCreateUserPositive() throws Exception {
        // Создание JSON построчно в виде строки
        String jsonString = "{" +
                "\"name\": \"Alice Johnson\"," +
                "\"job\": \"DevOps Engineer\"" +
                "}";

        // Сразу из строкового значения через mapper.readValue в Record
        UserRecord newUser = mapper.readValue(jsonString, UserRecord.class);

        Response response = postRequest(USERS_ENDPOINT, newUser);
        validateStatusCode(response, 201);

        UserRecord createdUser = response.as(UserRecord.class);
        assertThat(createdUser.name())
                .as("Проверка имени созданного пользователя")
                .isEqualTo("Alice Johnson");
        assertThat(createdUser.job())
                .as("Проверка профессии созданного пользователя")
                .isEqualTo("DevOps Engineer");
        assertThat(createdUser.id())
                .as("Проверка, что ID созданного пользователя сгенерирован")
                .isNotNull();
    }

    @Test
    @DisplayName("POST /users - Negative: Создать пользователя с пустым телом")
    public void testCreateUserNegative() {
        String emptyJson = "{}";
        Response response = postRequest(USERS_ENDPOINT, emptyJson);
        validateStatusCode(response, 201);

        UserRecord createdUser = response.as(UserRecord.class);
        assertThat(createdUser.createdAt())
                .as("Проверка наличия даты создания при пустом запросе")
                .isNotNull();
    }

    @Test
    @DisplayName("PUT /users/{id} - Positive: Обновить пользователя (построчно + mapper.readValue)")
    public void testUpdateUserPositive() throws Exception {
        String jsonString = "{" +
                "\"name\": \"Bob Wilson\"," +
                "\"job\": \"Tech Lead\"" +
                "}";

        UserRecord updateUser = mapper.readValue(jsonString, UserRecord.class);

        Response response = putRequest(USERS_ENDPOINT + "/2", updateUser);
        validateStatusCode(response, 200);

        UserRecord updatedUser = response.as(UserRecord.class);
        assertThat(updatedUser.name())
                .as("Проверка обновленного имени")
                .isEqualTo("Bob Wilson");
        assertThat(updatedUser.job())
                .as("Проверка обновленной должности")
                .isEqualTo("Tech Lead");
    }

    @Test
    @DisplayName("PUT /users/{id} - Negative: Обновить несуществующего пользователя")
    public void testUpdateUserNegative() throws Exception {
        String jsonString = "{" +
                "\"name\": \"Test\"," +
                "\"job\": \"Test Job\"" +
                "}";

        UserRecord updateUser = mapper.readValue(jsonString, UserRecord.class);

        Response response = putRequest(USERS_ENDPOINT + "/99999", updateUser);
        validateStatusCode(response, 200);

        UserRecord updatedUser = response.as(UserRecord.class);
        assertThat(updatedUser.updatedAt())
                .as("Проверка наличия времени обновления")
                .isNotNull();
    }

    @Test
    @DisplayName("DELETE /users/{id} - Positive: Удалить пользователя")
    public void testDeleteUserPositive() {
        Response response = deleteRequest(USERS_ENDPOINT + "/2");
        validateStatusCode(response, 204);
    }

    @Test
    @DisplayName("DELETE /users/{id} - Negative: Удалить несуществующего пользователя")
    public void testDeleteUserNegative() {
        Response response = deleteRequest(USERS_ENDPOINT + "/99999");
        validateStatusCode(response, 204);
        assertThat(response.statusCode())
                .as("Проверка статус-кода удаления")
                .isEqualTo(204);
    }
}