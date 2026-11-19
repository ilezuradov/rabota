package com.school21.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.BeforeEach;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class RetrofitTestBase {
    
    protected static final String BASE_URL = "https://reqres.in/api/";
    
    protected ApiService apiService;
    protected Retrofit retrofit;
    
    @BeforeEach
    public void setUp() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        apiService = retrofit.create(ApiService.class);
    }
    
    protected <T> Response<T> executeCall(Call<T> call) throws IOException {
        return call.execute();
    }
    
    protected void validateResponse(Response<?> response, int expectedStatusCode) {
        if (response.code() != expectedStatusCode) {
            throw new AssertionError(
                String.format("Expected %d, got %d", expectedStatusCode, response.code())
            );
        }
    }
}
