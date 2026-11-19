package com.school21.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // 1. Импортируем библиотеку
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // 2. Говорим игнорировать неизвестные поля
public class UserListResponse {


    @JsonProperty("page")
    private int page;

    @JsonProperty("per_page")
    private int perPage;

    @JsonProperty("total")
    private int total;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("data")
    private List<User> data;

    private Support support;

}