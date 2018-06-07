package com.senacor.university.graphql.domain.employee;

import com.google.common.collect.ImmutableSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static com.google.common.collect.ImmutableSet.toImmutableSet;
import static com.senacor.university.graphql.domain.employee.Email.from;
import static java.time.Month.APRIL;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;

@Component
public class EmployeeRepository {

    private final Set<Employee> employees;

    {
        employees = ImmutableSet.of(
                Employee.builder()
                        .id("001")  .projectId("001")   .firstName("Si")        .lastName("Tran")   .email(from("si.tran@senacor.com"))         .gender(Gender.MALE)    .age(31)    .employedSince(LocalDate.of(2010, JANUARY, 1)),
                Employee.builder()
                        .id("002")  .projectId("001")   .firstName("Michael")   .lastName("Omann")  .email(from("michael.omann@senacor.com"))   .gender(Gender.MALE)    .age(34)    .employedSince(LocalDate.of(2011, FEBRUARY, 2)),
                Employee.builder()
                        .id("003")  .projectId("001")   .firstName("Michael")   .lastName("Sewell") .email(from("michael.sewell@senacor.com"))  .gender(Gender.MALE)    .age(31)    .employedSince(LocalDate.of(2012, MARCH, 3)),
                Employee.builder()
                        .id("004")  .projectId("001")   .firstName("Hannes")    .lastName("Leitl")  .email(from("hannes.leitl@senacor.com"))    .gender(Gender.MALE)    .age(63)    .employedSince(LocalDate.of(2013, APRIL, 4)),
                Employee.builder()
                        .id("005")  .projectId("001")   .firstName("Hanna")     .lastName("Häusel") .email(from("hanna.haeusel@senacor.com"))   .gender(Gender.FEMALE)  .age(29)    .employedSince(LocalDate.of(2014, MAY, 5))

        ).stream().map(Employee.EmployeeBuilder::build).collect(toImmutableSet());
    }

    public Optional<Employee> findById(String id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findAny();
    }

    public List<Employee> findByProjectId(String projectId) {
        return employees.stream()
                .filter(employee -> employee.getProjectId().equals(projectId))
                .collect(toImmutableList());
    }


    public List<Employee> findByGender(Gender gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(toImmutableList());
    }

    public List<Employee> findOlderThan(int age) {
        return employees.stream()
                .filter(employee -> employee.getAge() > age)
                .collect(toImmutableList());
    }

    public List<Employee> findAll() {
        return employees.stream().collect(toImmutableList());
    }
}
