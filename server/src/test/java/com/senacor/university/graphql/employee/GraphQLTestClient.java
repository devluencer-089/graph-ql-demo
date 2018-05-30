package com.senacor.university.graphql.employee;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import io.restassured.path.json.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;

import static io.restassured.RestAssured.given;

@Component
public class GraphQLTestClient {

    private final Environment environment;

    @Autowired
    public GraphQLTestClient(Environment environment) {
        this.environment = environment;
    }


    public GraphQLResult executeQuery(String queryFileName) throws IOException {

        String queryFileContents = readQueryFileContents(queryFileName);
        int serverPort = Integer.parseInt(environment.getProperty("local.server.port"));

        JsonPath jsonPath = execute(queryFileContents, serverPort);

        return new GraphQLResult(jsonPath);
    }

    private JsonPath execute(String queryFileContents, int serverPort) {
        return given()
                    .body(Collections.singletonMap("query", queryFileContents))
                    .port(serverPort)
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
