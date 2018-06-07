package com.senacor.university.graphql.domain.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Employee {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final Email email;
    private final int age;
    private final Gender gender;
    private final String projectId;
    private final LocalDate employedSince;

    public Employee(
            @JsonProperty("id") String id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") Email email,
            @JsonProperty("age") int age,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("projectId") String projectId,
            @JsonProperty("employedSince") LocalDate employedSince) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.projectId = projectId;
        this.employedSince = employedSince;
    }

    private Employee(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        age = builder.age;
        gender = builder.gender;
        projectId = builder.projectId;
        employedSince = builder.employedSince;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Email getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getProjectId() {
        return projectId;
    }

    public LocalDate getEmployedSince() {
        return employedSince;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Employee copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.firstName = copy.getFirstName();
        builder.lastName = copy.getLastName();
        builder.email = copy.getEmail();
        builder.age = copy.getAge();
        builder.gender = copy.getGender();
        builder.projectId = copy.getProjectId();
        builder.employedSince = copy.getEmployedSince();
        return builder;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email=" + email +
                ", age=" + age +
                ", gender=" + gender +
                ", projectId='" + projectId + '\'' +
                ", employedSince=" + employedSince +
                '}';
    }

    public static final class Builder {
        private String id;
        private String firstName;
        private String lastName;
        private Email email;
        private int age;
        private Gender gender;
        private String projectId;
        private LocalDate employedSince;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(Email email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder employedSince(LocalDate employedSince) {
            this.employedSince = employedSince;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
