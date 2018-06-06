package com.senacor.university.graphql.employee;

import com.google.common.collect.ImmutableSet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static com.google.common.collect.ImmutableSet.toImmutableSet;

@Component
public class EmployeeRepository {

    private final Set<EmployeeEntity> employees;

    {
        employees = ImmutableSet.of(
                EmployeeEntity.builder()
                        .id("001")  .projectId("001")   .firstName("Si")        .lastName("Tran")   .gender(Gender.MALE)    .age(31),
                EmployeeEntity.builder()
                        .id("002")  .projectId("001")   .firstName("Michael")   .lastName("Omann")  .gender(Gender.MALE)    .age(34),
                EmployeeEntity.builder()
                        .id("003")  .projectId("001")   .firstName("Michael")   .lastName("Sewell") .gender(Gender.MALE)    .age(31),
                EmployeeEntity.builder()
                        .id("004")  .projectId("001")   .firstName("Hannes")    .lastName("Leitl")  .gender(Gender.MALE)    .age(63),
                EmployeeEntity.builder()
                        .id("005")  .projectId("001")   .firstName("Hanna")     .lastName("Häusel") .gender(Gender.FEMALE)  .age(29)

        ).stream().map(EmployeeEntity.EmployeeEntityBuilder::build).collect(toImmutableSet());
    }

    public Optional<EmployeeEntity> findById(String id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findAny();
    }

    public List<EmployeeEntity> findByProjectId(String projectId) {
        return employees.stream()
                .filter(employee -> employee.getProjectId().equals(projectId))
                .collect(toImmutableList());
    }


    public List<EmployeeEntity> findByGender(Gender gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(toImmutableList());
    }

    public List<EmployeeEntity> findOlderThan(int age) {
        return employees.stream()
                .filter(employee -> employee.getAge() > age)
                .collect(toImmutableList());
    }

    public List<EmployeeEntity> findAll() {
        return employees.stream().collect(toImmutableList());
    }
}
