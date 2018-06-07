package com.senacor.university.graphql.error;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.servlet.GraphQLErrorHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
       return errors.stream()
                .map(error -> {
                    if (error instanceof Exception) {
                        return new AnemicError(error);
                    }
                    return error;
                })
               .collect(Collectors.toList());
    }

    private static final class AnemicError implements GraphQLError {
        private final GraphQLError delegate;

        private AnemicError(GraphQLError delegate) {
            this.delegate = delegate;
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
        public ErrorType getErrorType() {
            return delegate.getErrorType();
        }

        @Override
        public List<Object> getPath() {
            return delegate.getPath();
        }

        @Override
        public Map<String, Object> getExtensions() {
            return delegate.getExtensions();
        }
    }
}
