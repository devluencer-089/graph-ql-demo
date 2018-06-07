package com.senacor.university.graphql.domain.employee;


import com.senacor.university.graphql.GraphQLResult;
import com.senacor.university.graphql.GraphQLTestClient;
import com.senacor.university.graphql.domain.project.Project;
import com.senacor.university.graphql.domain.project.ProjectAssert;
import graphql.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static graphql.ErrorType.DataFetchingException;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@DisplayName("Employee Integration Tests")
public class EmployeesQueryIntegrationTest {

    @Autowired
    private GraphQLTestClient client;


    @Nested
    class Fields {

        @Test
        public void fieldsCanBeQueriedAndTraversed() throws IOException {

            GraphQLResult result = client.executeQuery("all_employees.txt");

            List<Employee> employees = result.descentTo("employees").asListOf(Employee.class);
            assertThat(employees).hasSize(5);
        }
    }

    @Nested
    class Arguments {

        @Test
        public void everyFieldCanGetItsOwnSetOfArguments() throws IOException {

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
        public void argumentsAreBeOptionalIfDefinedAsOptionalInTheSchemaAndCanHaveDefaultValues() throws IOException {

            GraphQLResult result = client.executeQuery("all_employees_filtered_by_gender.txt");

            List<Employee> employees = result.descentTo("employees").asListOf(Employee.class);
            assertThat(employees).hasSize(1)
                    .first().satisfies(femaleEmployee -> {
                EmployeeAssert.assertThat(femaleEmployee)
                        .hasId("005")
                        .hasFirstName("Hanna")
                        .hasLastName("Häusel")
                        .hasGender(Gender.FEMALE);
            });
        }
    }


    @Nested
    class Aliases {

        @Test
        public void aliasesLetYouRenameFields() throws IOException {

            GraphQLResult result = client.executeQuery("find_employee_by_id_with_alias.txt");

            assertThat(result.descentTo("code_monkey.name").asString()).isEqualTo("Tran");

        }

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
    class Variables {

        @Test
        public void namedOperationsCanAlsoAcceptVariables() throws IOException {

            GraphQLResult result = client.executeQuery(
                    "find_employee_by_id_with_variable.txt",
                    "FindEmployeeById",
                    Collections.singletonMap("employeeId", "001"));

            assertThat(result.descentTo("employee").as(Employee.class)).isNotNull();
        }
    }


    @Nested
    class ComplexTypes {

        @Test
        public void complexTypesAreResolvedOnlyIfRequested() throws IOException {

            GraphQLResult result = client.executeQuery("single_employee_with_project.txt");

            Project project  = result.descentTo("employee.project").as(Project.class);
            ProjectAssert.assertThat(project).hasId("001");

            Employee cstLead = result.descentTo("employee.project.cstLead").as(Employee.class);
            EmployeeAssert.assertThat(cstLead)
                    .hasId("005")
                    .hasFirstName("Hanna")
                    .hasLastName("Häusel");
        }

        @Test
        public void complexTypesCanBeLists() throws IOException {

            GraphQLResult result = client.executeQuery("single_employee_with_project_and_staff.txt");

            Project project  = result.descentTo("employee.project").as(Project.class);
            ProjectAssert.assertThat(project).hasId("001");

            List<Employee> staff = result.descentTo("employee.project.staff").asListOf(Employee.class);
            assertThat(staff).hasSize(5)
                    .extracting("id")
                    .containsExactly("001", "002", "003", "004", "005");
        }

    }

    @Nested
    class Errors {

        @Test
        public void errorsProduceStatusCode200JustLikeSuccessfulQueries() throws IOException {
            GraphQLResult result = client.executeQuery(
                    "find_employee_that_does_not_exist.txt",
                    "NonExistentEmployee",
                    Collections.emptyMap());

            assertThat(result.getStatusCode()).isEqualTo(200);

        }

        @Test
        public void errorsResideInTheErrorArrayRightUnderTheRootObject() throws IOException {
            GraphQLResult result = client.executeQuery("find_employee_that_does_not_exist.txt");

            assertThat(result.errors())
                    .hasSize(1)
                    .first()
                    .hasFieldOrPropertyWithValue("message", "Exception while fetching data (/employee) : customer with id 006 not found")
                    .hasFieldOrProperty("path")
                    .hasFieldOrPropertyWithValue("extensions", null)
                    .hasFieldOrProperty("locations")
                    .hasFieldOrPropertyWithValue("errorType", DataFetchingException);
        }


        @Test
        public void extensionsHoldOptionalInformationRelatedToTheError() {
            //TODO
        }

        @Test
        public void partialResultsWithErrorsAreAllowed() throws IOException {
            GraphQLResult result = client.executeQuery("partial_errors.txt");

            Employee first = result.descentTo("first").as(Employee.class);
            assertThat(first).isNotNull();

            Employee second = result.descentTo("second").as(Employee.class);
            assertThat(second).isNull();

            assertThat(result.errors())
                    .hasSize(1)
                    .first()
                    .hasFieldOrPropertyWithValue("message", "Exception while fetching data (/second) : customer with id 009 not found");

        }

        @Test
        public void constraintValidationAnnotationsAreNotSupported() throws IOException {
            GraphQLResult result = client.executeQuery("find_employees_with_constraint_validation.txt");

            assertThat(result.errors()).isEmpty();
        }
    }


    @Nested
    class Instrumentation {

        @Test
        public void queryComplexityCanBeLimited() throws IOException {
            GraphQLResult result = client.executeQuery("query_exceeding_configured_complexity.txt");

            assertThat(result.errors()).hasSize(1)
                    .first()
                    .hasFieldOrPropertyWithValue("message", "maximum query complexity exceeded 22 > 20")
                    .hasFieldOrPropertyWithValue("errorType", ErrorType.ExecutionAborted);

        }


        @Test
        public void queryDepthCanBeLimited() throws IOException {
            GraphQLResult result = client.executeQuery("query_exceeding_configured_depth.txt");

            assertThat(result.errors()).hasSize(1)
                    .first()
                    .hasFieldOrPropertyWithValue("message", "maximum query depth exceeded 6 > 4")
                    .hasFieldOrPropertyWithValue("errorType", ErrorType.ExecutionAborted);


        }
    }

}
