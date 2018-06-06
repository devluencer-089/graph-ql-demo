package com.senacor.university.graphql.error;

import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class DataFetchingError implements GraphQLError {


    private final ExceptionWhileDataFetching delegate;

    public DataFetchingError(ExceptionWhileDataFetching delegate) {
        this.delegate = delegate;
    }

    public DataFetchingError(ExecutionPath path, Throwable exception, SourceLocation sourceLocation) {
        this(new ExceptionWhileDataFetching(path, exception, sourceLocation));
    }

    @Override
    public String getMessage() {
        return delegate.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return delegate.getLocations();
    }

    @Override
    public List<Object> getPath() {
        return delegate.getPath();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return delegate.getExtensions();
    }

    @Override
    public ErrorType getErrorType() {
        return delegate.getErrorType();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }
}
