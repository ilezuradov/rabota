package com.school21.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school21.models.User;
import com.school21.models.UserListResponse;
import com.school21.models.UserSingleResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public abstract class OkHttpTestBase {
    
    protected static final String BASE_URL = "https://reqres.in/api";
    protected static final String USERS_ENDPOINT = "/users";
    
    protected OkHttpClient client;
    protected ObjectMapper objectMapper;
    protected MediaType jsonMediaType;
    
    @BeforeEach
    public void setUp() {
        client = new OkHttpClient.Builder()
                .build();
        objectMapper = new ObjectMapper();
        jsonMediaType = MediaType.parse("application/json; charset=utf-8");
    }
    
    protected Response executeRequest(Request request) throws IOException {
        return client.newCall(request).execute();
    }
    
    protected void validateResponse(Response response, int expectedStatusCode) throws IOException {
        int actualStatusCode = response.code();
        if (actualStatusCode != expectedStatusCode) {
            String body = response.body() != null ? response.body().string() : "empty body";
            throw new AssertionError(
                String.format("Expected %d, got %d. Body: %s", 
                    expectedStatusCode, actualStatusCode, body)
            );
        }
    }
    
    protected UserListResponse parseUserListResponse(Response response) throws IOException {
        String json = response.body().string();
        return objectMapper.readValue(json, UserListResponse.class);
    }
    
    protected UserSingleResponse parseUserSingleResponse(Response response) throws IOException {
        String json = response.body().string();
        return objectMapper.readValue(json, UserSingleResponse.class);
    }
    
    protected User parseUser(Response response) throws IOException {
        String json = response.body().string();
        return objectMapper.readValue(json, User.class);
    }
    
    protected String createUserJsonString(User user) throws IOException {
        return objectMapper.writeValueAsString(user);
    }
    
    protected String createUserJsonFieldByField(String name, String job) {
        return String.format("{\"name\":\"%s\",\"job\":\"%s\"}", name, job);
    }

}
