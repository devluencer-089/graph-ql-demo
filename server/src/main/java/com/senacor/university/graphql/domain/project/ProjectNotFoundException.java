package com.senacor.university.graphql.domain.project;

import com.google.common.collect.ImmutableMap;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class ProjectNotFoundException extends RuntimeException implements GraphQLError {

    public ProjectNotFoundException(String message) {
        super(message);
    }


    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return ImmutableMap.of("additionalData", "...can be provided via 'extensions' properties");
    }
}
