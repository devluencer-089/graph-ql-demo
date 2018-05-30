package com.senacor.university.graphql.employee;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesQueryIntegrationTest {

    @Autowired
    private GraphQLTestClient client;

    @Test
    public void itLoadsAllEmployees() throws IOException {

        GraphQLResult result = client.executeQuery("all_employees.txt");

        List<Employee> employees = result.descentTo("employees").asListOf(Employee.class);
        assertThat(employees).hasSize(5);
    }

    @Test
    public void itFindsASingleEmployeeById() throws IOException {

        GraphQLResult result = client.executeQuery("find_employee_by_id.txt");

        Employee employee = result.descentTo("employee").as(Employee.class);
        EmployeeAssert.assertThat(employee)
                .hasId("001")
                .hasFirstName("Si")
                .hasLastName("Tran")
                .hasGender(Gender.MALE)
                .hasAge(31);
    }
}
