package com.senacor.university.graphql.domain.employee;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeRootResolver implements GraphQLQueryResolver {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeRootResolver(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> employees() {
        return repository.findAll();
    }

    public Optional<Employee> employee(String id) {
        return repository.findById(id);
    }
}
