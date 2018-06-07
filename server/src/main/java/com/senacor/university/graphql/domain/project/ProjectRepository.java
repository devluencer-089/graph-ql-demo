package com.senacor.university.graphql.domain.project;

import com.google.common.collect.ImmutableSet;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static com.google.common.collect.ImmutableSet.toImmutableSet;

@Component
public class ProjectRepository {

    private final Set<Project> projects;

    {
        projects = ImmutableSet.of(
                Project.newBuilder()
                        .id("001").projectCode("HRE").cstLeadId("005").staffIds(Arrays.asList("001", "002", "003", "004", "005")),
                Project.newBuilder()
                        .id("002").projectCode("BAW").cstLeadId("004").staffIds(Arrays.asList("001", "002", "003", "004", "005")),
                Project.newBuilder()
                        .id("003").projectCode("BDB").cstLeadId("003").staffIds(Arrays.asList("001", "002", "003", "004", "005")),
                Project.newBuilder()
                        .id("004").projectCode("AOK").cstLeadId("002").staffIds(Arrays.asList("001", "002", "003", "004", "005")),
                Project.newBuilder()
                        .id("005").projectCode("TEB").cstLeadId("001").staffIds(Arrays.asList("001", "002", "003", "004", "005"))

                ).stream().map(Project.Builder::build).collect(toImmutableSet());
    }

    public Optional<Project> findById(String id) {
        return projects.stream()
                .filter(project -> project.getId().equals(id))
                .findAny();
    }

    public List<Project> findAll() {
        return projects.stream().collect(toImmutableList());
    }
}
