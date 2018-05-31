package com.senacor.university.graphql.employee;

import io.restassured.path.json.JsonPath;

import java.util.List;

public class GraphQLResult {

    private static final String DEFAULT_ROOT_PATH = "data";

    private final JsonPath jsonPath;
    private final String rootPath;

    GraphQLResult(JsonPath jsonPath) {
        this(jsonPath, DEFAULT_ROOT_PATH);
    }


    private GraphQLResult(JsonPath jsonPath, String rootPath) {
        this.jsonPath = jsonPath;
        this.rootPath = rootPath;
    }

    public GraphQLResult descentTo(String path) {
        return new GraphQLResult(jsonPath, String.join(".", rootPath, path));
    }

    public <T> List<T> asListOf(Class<T> clazz) {
        return jsonPath.getList(rootPath, clazz);
    }

    public <T> T as(Class<T> clazz) {
        return jsonPath.getObject(rootPath, clazz);
    }

    public JsonPath jsonPath() {
        return jsonPath;
    }
}
