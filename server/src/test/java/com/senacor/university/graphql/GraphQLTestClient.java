package com.senacor.university.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static java.util.Collections.emptyMap;

@Component
public class GraphQLTestClient {

    private final Environment environment;
    private final ObjectMapper objectMapper;

    @Autowired
    public GraphQLTestClient(Environment environment, ObjectMapper objectMapper) {
        this.environment = environment;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void configureJsonPath() {
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                objectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> objectMapper)
        );
    }

    public GraphQLResult executeQuery(String queryFileName) throws IOException {
        return executeQuery(queryFileName, emptyMap());
    }

    public GraphQLResult executeQuery(String queryFileName, Map<String, Object> variables) throws IOException {
        return executeQuery(queryFileName, null, variables);
    }

    public GraphQLResult executeQuery(String queryFileName, String operationName, Map<String, Object> variables) throws IOException {

        String queryFileContents = readQueryFileContents(queryFileName.trim());

        return execute(queryFileContents, operationName, variables);
    }

    private GraphQLResult execute(String queryFileContents, String operationName, Map<String, Object> variables) throws JsonProcessingException {
        ImmutableMap.Builder<Object, Object> requestBody = ImmutableMap.builder()
                .put("query", queryFileContents);

        if (operationName != null) {
            requestBody.put("operationName", operationName);
        }

        if (!variables.isEmpty()) {
            requestBody.put("variables", objectMapper.writeValueAsString(variables));
        }

        int port = Integer.parseInt(environment.getProperty("local.server.port"));

        ExtractableResponse<Response> response = given()
                .body(requestBody.build())
                .port(port)
                .when()
                .post("/graphql")
                .then()
                .log().everything()
                .extract();

        int statusCode = response.statusCode();
        JsonPath bodyJsonPath = response.body().jsonPath();

        return new GraphQLResult(bodyJsonPath, statusCode);
    }

    private static String readQueryFileContents(String queryFileName) throws IOException {
        URL url = new ClassPathResource("queries/" + queryFileName).getURL();
        String graphQLQuery = Resources.toString(url, Charsets.UTF_8);
        return graphQLQuery;
    }
}
