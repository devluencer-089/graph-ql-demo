package com.senacor.university.graphql.employee;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeQueryResolver implements GraphQLQueryResolver {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeQueryResolver(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> employees() {
        return repository.findAll();
    }

    public Optional<Employee> employee(String id) {
        return repository.findById(id);
    }
}
