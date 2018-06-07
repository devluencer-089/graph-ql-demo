package com.senacor.university.graphql.domain.employee;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeRootResolver implements GraphQLQueryResolver {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeRootResolver(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> employees(Gender gender, @Min(0) Integer olderThan, int limit) {
        List<Employee> genderMatch = gender == null ? repository.findAll() : repository.findByGender(gender);
        List<Employee> ageMatch = olderThan == null ? repository.findAll() : repository.findOlderThan(olderThan);

        return ageMatch.stream()
                .filter(entity -> genderMatch.contains(entity))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Employee employee(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("customer with id %s not found", id)));
    }
}
