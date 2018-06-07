package com.senacor.university.graphql.domain.employee;

import com.senacor.university.graphql.scalars.Email;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import java.time.LocalDate;

@Value
@Builder
@Wither
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private Email email;
    private int age;
    private Gender gender;
    private String projectId;
    private LocalDate employedSince;
}
