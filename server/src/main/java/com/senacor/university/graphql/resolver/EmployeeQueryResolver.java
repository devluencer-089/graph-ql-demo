package com.senacor.university.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.senacor.university.graphql.domain.Employee;
import com.senacor.university.graphql.domain.Level;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class EmployeeQueryResolver implements GraphQLQueryResolver {

    private List<Employee> testdata;

    public EmployeeQueryResolver() {
        testdata = staticData();
    }

    public Employee getEmployee(Long id) {
        return testdata.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> allEmployees() {
        return testdata;
    }

    private List<Employee> staticData() {
        return Arrays.asList(
                Employee.builder()
                        .id(3)
                        .firstname("Si")
                        .lastname("Tran")
                        .level(Level.SENIOR_DEVELOPER)
                        .phoneNumber("+49 (160) xxxx xxxx")
                        .build(),
                Employee.builder()
                        .id(1)
                        .firstname("Michael")
                        .lastname("Omann")
                        .level(Level.ARCHITECT)
                        .phoneNumber("+49 (162) xxxx xxx")
                        .build(),
                Employee.builder()
                        .id(2)
                        .firstname("Michael")
                        .lastname("Sewell")
                        .level(Level.ARCHITECT)
                        .phoneNumber("+49 (151) xxxx xxxx")
                        .build(),
                Employee.builder()
                        .id(4)
                        .firstname("Michael")
                        .lastname("Omann")
                        .level(Level.ARCHITECT)
                        .phoneNumber("+49 (162) xxxx xxx")
                        .build(),
                Employee.builder()
                        .id(5)
                        .firstname("Michael")
                        .lastname("Sewell")
                        .level(Level.ARCHITECT)
                        .phoneNumber("+49 (151) xxxx xxxx")
                        .build()
        );
    }
}
