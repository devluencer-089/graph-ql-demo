package com.senacor.university.graphql.domain.project;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.senacor.university.graphql.domain.employee.Employee;
import com.senacor.university.graphql.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class ProjectResolver implements GraphQLResolver<Project> {

    private EmployeeRepository repository;

    public ProjectResolver(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Optional<Employee> cstLead(Project project) {
        return repository.findById(project.getCstLeadId());
    }

    public List<Employee> staff(Project project) {
        return repository.findByProjectId(project.getId()).stream()
                .collect(Collectors.toList());
    }
}