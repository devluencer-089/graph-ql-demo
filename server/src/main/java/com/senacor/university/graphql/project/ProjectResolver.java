package com.senacor.university.graphql.project;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.senacor.university.graphql.employee.Employee;
import com.senacor.university.graphql.employee.EmployeeEntity;
import com.senacor.university.graphql.employee.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ProjectResolver implements GraphQLResolver<Project> {

    private EmployeeRepository repository;

    public ProjectResolver(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Optional<Employee> cstLead(Project project) {
        return repository.findById(project.getCstLeadId()).map(entity -> mapEmployee(entity));
    }

    private static Employee mapEmployee(EmployeeEntity entity) {
        return Employee.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .age(entity.getAge())
                .projectId(entity.getProjectId())
                .project(null)
                .build();
    }

}