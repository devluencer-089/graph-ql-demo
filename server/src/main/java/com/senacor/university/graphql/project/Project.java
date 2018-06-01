package com.senacor.university.graphql.project;


import com.senacor.university.graphql.employee.Employee;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import java.util.List;

@Value
@Builder
@Wither
public class Project {

    private String id;
    private String projectCode;
    private Employee cstLead;
    private String cstLeadId;
    private List<Employee> staff;

}
