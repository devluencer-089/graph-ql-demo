package com.senacor.university.graphql.employee;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeRootResolver implements GraphQLQueryResolver {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeRootResolver(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> employees() {
        return repository.findAll().stream()
                .map(entity -> mapEmployee(entity))
                .collect(Collectors.toList());
    }

    public Optional<Employee> employee(String id) {
        return repository.findById(id).map(entity -> mapEmployee(entity));
    }

    private static Employee mapEmployee(EmployeeEntity entity) {
        return Employee.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .age(entity.getAge())
                .gender(entity.getGender())
                .projectId(entity.getProjectId())
                .build();
    }
}
