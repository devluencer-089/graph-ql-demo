package com.senacor.university.graphql.domain.project;


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
    private String cstLeadId;
    private List<String> staffIds;

}
