package com.senacor.university.graphql.domain.project;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectRootResolver implements GraphQLQueryResolver {

    private final ProjectRepository repository;

    @Autowired
    public ProjectRootResolver(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> projects() {
        return repository.findAll();
    }

    public Project project(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(String.format("project with id %s not found", id)));
    }
}
