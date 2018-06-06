package com.senacor.university.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import io.restassured.path.json.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static io.restassured.RestAssured.given;
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



    public GraphQLResult executeQuery(String queryFileName) throws IOException {
        return executeQuery(queryFileName, emptyMap());
    }

    public GraphQLResult executeQuery(String queryFileName, Map<String, Object> variables) throws IOException {
        return executeQuery(queryFileName, null, variables);
    }
    public GraphQLResult executeQuery(String queryFileName, String operationName, Map<String, Object> variables) throws IOException {

        String queryFileContents = readQueryFileContents(queryFileName);

        JsonPath jsonPath = execute(queryFileContents, operationName, variables);
        checkForErrors(jsonPath);

        return new GraphQLResult(jsonPath);
    }

    private void checkForErrors(JsonPath jsonPath) {
        Object errors = jsonPath.getJsonObject("errors");
        if (errors != null) {
            throw new AssertionError("Query returned errors: " + errors);
        }
    }

    private JsonPath execute(String queryFileContents, String operationName, Map<String, Object> variables) throws JsonProcessingException {
        ImmutableMap.Builder<Object, Object> requestBody = ImmutableMap.builder()
                .put("query", queryFileContents);

        if (operationName != null) {
            requestBody.put("operationName", operationName);
        }

        if (!variables.isEmpty()) {
            requestBody.put("variables", objectMapper.writeValueAsString(variables));
        }

        int port = Integer.parseInt(environment.getProperty("local.server.port"));

        return given()
                    .body(requestBody.build())
                    .port(port)
                    .when()
                    .post("/graphql")
                    .then()
                    .log().everything()
                    .statusCode(200)
                    .extract().body().jsonPath();
    }

    private static String readQueryFileContents(String queryFileName) throws IOException {
        URL url = new ClassPathResource("queries/" + queryFileName).getURL();
        String graphQLQuery = Resources.toString(url, Charsets.UTF_8);
        return graphQLQuery;
    }
}
