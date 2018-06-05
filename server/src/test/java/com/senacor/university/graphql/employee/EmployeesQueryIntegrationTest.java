package com.senacor.university.graphql.employee;


import com.senacor.university.graphql.GraphQLResult;
import com.senacor.university.graphql.GraphQLTestClient;
import com.senacor.university.graphql.project.Project;
import com.senacor.university.graphql.project.ProjectAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@DisplayName("Employee Integration Tests")
public class EmployeesQueryIntegrationTest {

    @Autowired
    private GraphQLTestClient client;


    @Nested
    class RootQueries {

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
        public void explicitOperationNamesAreSupported() throws IOException {

            GraphQLResult result = client.executeQuery("query_with_operation_name.txt");

            assertThat(result.descentTo("brain").as(Employee.class)).isNotNull();
            assertThat(result.descentTo("face").as(Employee.class)).isNotNull();
            assertThat(result.descentTo("muscle").as(Employee.class)).isNotNull();
        }
    }


    @Nested
    class Aliases {

        @Test
        public void aliasesAreRequiredIfResultIsAmbiguous() throws IOException {

            GraphQLResult result = client.executeQuery("find_multiple_employees_by_id.txt");

            Employee brain = result.descentTo("brain").as(Employee.class);
            EmployeeAssert.assertThat(brain).hasFirstName("Si").hasLastName("Tran");

            Employee muscle = result.descentTo("muscle").as(Employee.class);
            EmployeeAssert.assertThat(muscle).hasFirstName("Michael").hasLastName("Omann");

            Employee face = result.descentTo("face").as(Employee.class);
            EmployeeAssert.assertThat(face).hasFirstName("Michael").hasLastName("Sewell");
        }

        @Test
        public void duplicateQueriesAreIgnored() throws IOException {

            GraphQLResult result = client.executeQuery("find_multiple_employees_with_same_id.txt");

            Employee employee = result.descentTo("employee").as(Employee.class);
            EmployeeAssert.assertThat(employee).hasId("001");
        }
    }

    @Nested
    class Fragments {

        @Test
        public void fragmentsAllowForMoreConciseQueries() throws IOException {

            GraphQLResult result = client.executeQuery("find_multiple_employees_by_id_using_fragments.txt");

            Employee brain = result.descentTo("brain").as(Employee.class);
            EmployeeAssert.assertThat(brain).hasFirstName("Si").hasLastName("Tran");

            Employee muscle = result.descentTo("muscle").as(Employee.class);
            EmployeeAssert.assertThat(muscle).hasFirstName("Michael").hasLastName("Omann");

            Employee face = result.descentTo("face").as(Employee.class);
            EmployeeAssert.assertThat(face).hasFirstName("Michael").hasLastName("Sewell");
        }
    }


    @Nested
    class ComplexTypes {

        @Test
        public void complexTypesAreResolvedOnlyIfRequested() throws IOException {

            GraphQLResult result = client.executeQuery("single_employee_with_project.txt");

            Employee employee = result.descentTo("employee").as(Employee.class);
            Project project = employee.getProject();
            ProjectAssert.assertThat(project).hasId("001");

            Employee cstLead = project.getCstLead();
            EmployeeAssert.assertThat(cstLead)
                    .hasId("005")
                    .hasFirstName("Hanna")
                    .hasLastName("Häusel");
        }

        @Test
        public void complexTypesCanBeLists() throws IOException {

            GraphQLResult result = client.executeQuery("single_employee_with_project_and_staff.txt");

            Employee employee = result.descentTo("employee").as(Employee.class);
            Project project = employee.getProject();
            ProjectAssert.assertThat(project).hasId("001");

            List<Employee> staff = project.getStaff();
            assertThat(staff).hasSize(5)
                    .extracting("id")
                    .containsExactly("001", "002", "003", "004", "005");
        }

    }


}
