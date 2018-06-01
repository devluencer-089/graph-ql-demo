package com.senacor.university.graphql.employee;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.senacor.university.graphql.project.Project;
import com.senacor.university.graphql.project.ProjectEntity;
import com.senacor.university.graphql.project.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmployeeResolver implements GraphQLResolver<Employee> {

    private ProjectRepository repository;

    public EmployeeResolver(ProjectRepository repository) {
        this.repository = repository;
    }

    public Optional<Project> project(Employee employee) {
        return repository.findById(employee.getProjectId()).map(entity -> mapProject(entity));
    }

    private static Project mapProject(ProjectEntity entity) {
        return Project.builder()
                .id(entity.getId())
                .staff(null)
                .cstLeadId(entity.getCstLeadId())
                .cstLead(null)
                .projectCode(entity.getProjectCode())
                .build();
    }
}