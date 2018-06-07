package com.senacor.university.graphql;

import graphql.GraphQLError;
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.stream.Collectors;

public class GraphQLResult {

    private static final String DEFAULT_ROOT_PATH = "data";
    private static final String DEFAULT_ERROR_PATH = "errors";

    private final JsonPath jsonPath;
    private final String rootPath;
    private final int statusCode;

    GraphQLResult(JsonPath jsonPath, int statusCode) {
        this(jsonPath, statusCode, DEFAULT_ROOT_PATH);
    }


    private GraphQLResult(JsonPath jsonPath, int statusCode, String rootPath) {
        this.jsonPath = jsonPath;
        this.rootPath = rootPath;
        this.statusCode = statusCode;
    }

    public GraphQLResult descentTo(String path) {
        return new GraphQLResult(jsonPath, statusCode, String.join(".", rootPath, path));
    }

    public <T> List<T> asListOf(Class<T> clazz) {
        return jsonPath.getList(rootPath, clazz);
    }

    public <T> T as(Class<T> clazz) {
        return jsonPath.getObject(rootPath, clazz);
    }

    public String asString() {
        return jsonPath.getString(rootPath);
    }

    public List<GraphQLError> errors() {
        return jsonPath.getList(DEFAULT_ERROR_PATH, TestGraphQLError.class).stream()
                .map(GraphQLError.class::cast).collect(Collectors.toList());
    }

    public int asInt() {
        return jsonPath.getInt(rootPath);
    }

    public JsonPath getJsonPath() {
        return jsonPath;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
