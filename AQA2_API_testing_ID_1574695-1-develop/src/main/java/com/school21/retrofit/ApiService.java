package com.school21.retrofit;

import com.school21.models.User;
import com.school21.models.UserListResponse;
import com.school21.models.UserSingleResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    
    @GET("users")
    Call<UserListResponse> getUsers(@Query("page") int page);
    
    @GET("users/{id}")
    Call<UserSingleResponse> getUser(@Path("id") int id);
    
    @POST("users")
    Call<User> createUser(@Body User user);
    
    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);
    
    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);
}
