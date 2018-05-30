package com.senacor.university.graphql.employee;

import io.restassured.path.json.JsonPath;

import java.util.List;

public class GraphQLResult {
    private final JsonPath jsonPath;

    public GraphQLResult(JsonPath jsonPath) {
        this.jsonPath = jsonPath;
    }


    public <T> List<T> asListOf(String path, Class<T> clazz) {
        return jsonPath.getList("data." + path, clazz);
    }

    public <T> T as(String path, Class<T> clazz) {
        return jsonPath.getObject("data." + path, clazz);
    }
}
