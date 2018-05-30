package com.senacor.university.graphql.employee;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
@Wither
public class Employee {
    String id;
    String firstName;
    String lastName;
    int age;
    Gender gender;
}
