package com.school21.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRecord(
    @JsonProperty("id") Integer id,
    @JsonProperty("email") String email,
    @JsonProperty("first_name") String firstName,
    @JsonProperty("last_name") String lastName,
    @JsonProperty("avatar") String avatar,
    String name,
    String job,
    @JsonProperty("createdAt") String createdAt,
    @JsonProperty("updatedAt") String updatedAt
) {}
