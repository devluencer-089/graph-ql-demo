package com.senacor.university.graphql.employee;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesQueryIntegrationTest {

    @Autowired
    @LocalServerPort
    private int port;

    @Test
    public void itLoadsAllEmployees() throws IOException {

        URL url = new ClassPathResource("queries/all_employees.txt").getURL();
        String graphQLQuery = Resources.toString(url, Charsets.UTF_8);

        JsonPath jsonPath = given()
                .body(Collections.singletonMap("query", graphQLQuery))
                .port(port)
                .when()
                .post("/graphql")
                .then()
                .log().body()
                .extract().body().jsonPath();


        Employee[] employees = jsonPath.getObject("data.employees", Employee[].class);

        assertThat(employees).hasSize(5);
    }

    @Test
    public void itFindsASingleEmployeeById() throws IOException {

        URL url = new ClassPathResource("queries/find_employee_by_id.txt").getURL();
        String graphQLQuery = Resources.toString(url, Charsets.UTF_8);

        JsonPath jsonPath = given()
                .body(Collections.singletonMap("query", graphQLQuery))
                .port(port)
                .when()
                .post("/graphql")
                .then()
                .log().body()
                .extract().body().jsonPath();


        Employee employee = jsonPath.getObject("data.employee", Employee.class);

        EmployeeAssert.assertThat(employee)
                .hasId("001")
                .hasFirstName("Si")
                .hasLastName("Tran")
                .hasGender(Gender.MALE)
                .hasAge(31);
    }
}
