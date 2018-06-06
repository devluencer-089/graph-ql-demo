package com.senacor.university.graphql.error;

import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

import java.util.List;

public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors;

    }
}
