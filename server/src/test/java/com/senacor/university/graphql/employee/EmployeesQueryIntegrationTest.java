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

    @Test
    public void itIgnoresRedundantQueries() throws IOException {

        GraphQLResult result = client.executeQuery("find_multiple_employees_with_same_id.txt");

        Employee employee = result.descentTo("employee").as(Employee.class);
        EmployeeAssert.assertThat(employee).hasId("001");
    }

    @Test
    public void itFindsMultipleEmployeesWhenUsingAliases() throws IOException {

        GraphQLResult result = client.executeQuery("find_multiple_employees_by_id.txt");

        Employee brain = result.descentTo("brain").as(Employee.class);
        EmployeeAssert.assertThat(brain).hasFirstName("Si").hasLastName("Tran");

        Employee muscle = result.descentTo("muscle").as(Employee.class);
        EmployeeAssert.assertThat(muscle).hasFirstName("Michael").hasLastName("Omann");

        Employee face = result.descentTo("face").as(Employee.class);
        EmployeeAssert.assertThat(face).hasFirstName("Michael").hasLastName("Sewell");
    }

    @Test
    public void itFindsMultipleEmployeesWhenUsingAliasesAndFragments() throws IOException {

        GraphQLResult result = client.executeQuery("find_multiple_employees_by_id_using_fragments.txt");

        Employee brain = result.descentTo("brain").as(Employee.class);
        EmployeeAssert.assertThat(brain).hasFirstName("Si").hasLastName("Tran");

        Employee muscle = result.descentTo("muscle").as(Employee.class);
        EmployeeAssert.assertThat(muscle).hasFirstName("Michael").hasLastName("Omann");

        Employee face = result.descentTo("face").as(Employee.class);
        EmployeeAssert.assertThat(face).hasFirstName("Michael").hasLastName("Sewell");
    }
}
