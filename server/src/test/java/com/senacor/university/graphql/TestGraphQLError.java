package com.senacor.university.graphql;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class TestGraphQLError implements GraphQLError {

    private final String message;
    private final List<Object> path;
    private final Throwable exception;
    private final List<SourceLocation> locations;
    private final Map<String, Object> extensions;
    private final ErrorType errorType;

    @JsonCreator
    public TestGraphQLError(
            @JsonProperty("message") String message,
            @JsonProperty("path") List<Object> path,
            @JsonProperty("exception") Throwable exception,
            @JsonProperty("locations") List<SourceLocation> locations,
            @JsonProperty("extensions") Map<String, Object> extensions,
            @JsonProperty("errorType") ErrorType errorType) {
        this.message = message;
        this.path = path;
        this.exception = exception;
        this.locations = locations;
        this.extensions = extensions;
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<Object> getPath() {
        return path;
    }

    @Override
    public Map<String, Object> toSpecification() {
        return null;
    }

    public Throwable getException() {
        return exception;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return locations;
    }

    @Override
    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }
}
