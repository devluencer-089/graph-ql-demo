package com.senacor.university.graphql.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Employee {

    private int id;

    private String lastname;

    private String firstname;

    private Level level;

    private List<Employee> coworkers;

    private String phoneNumber;
}
