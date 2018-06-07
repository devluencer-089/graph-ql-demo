package com.senacor.university.graphql.domain.project;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Project {

    private final String id;
    private final String projectCode;
    private final String cstLeadId;
    private final List<String> staffIds;

    public Project(
            @JsonProperty("id") String id,
            @JsonProperty("projectCode") String projectCode,
            @JsonProperty("cstLeadId") String cstLeadId,
            @JsonProperty("staffIds") List<String> staffIds) {
        this.id = id;
        this.projectCode = projectCode;
        this.cstLeadId = cstLeadId;
        this.staffIds = staffIds;
    }

    private Project(Builder builder) {
        id = builder.id;
        projectCode = builder.projectCode;
        cstLeadId = builder.cstLeadId;
        staffIds = builder.staffIds;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Project copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.projectCode = copy.getProjectCode();
        builder.cstLeadId = copy.getCstLeadId();
        builder.staffIds = copy.getStaffIds();
        return builder;
    }

    public String getId() {
        return id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getCstLeadId() {
        return cstLeadId;
    }

    public List<String> getStaffIds() {
        return staffIds;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", cstLeadId='" + cstLeadId + '\'' +
                ", staffIds=" + staffIds +
                '}';
    }


    public static final class Builder {
        private String id;
        private String projectCode;
        private String cstLeadId;
        private List<String> staffIds;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder projectCode(String projectCode) {
            this.projectCode = projectCode;
            return this;
        }

        public Builder cstLeadId(String cstLeadId) {
            this.cstLeadId = cstLeadId;
            return this;
        }

        public Builder staffIds(List<String> staffIds) {
            this.staffIds = staffIds;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }
}
