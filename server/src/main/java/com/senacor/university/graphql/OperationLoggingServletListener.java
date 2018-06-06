package com.senacor.university.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.GraphQLError;
import graphql.servlet.GraphQLContext;
import graphql.servlet.GraphQLServletListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class OperationLoggingServletListener implements GraphQLServletListener {

    private static final Logger logger = LoggerFactory.getLogger("operations");
    private final ObjectMapper objectMapper;

    public OperationLoggingServletListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public OperationCallback onOperation(GraphQLContext context, String operationName, String query, Map<String, Object> variables) {
        return new OperationCallback() {

            @Override
            public void onSuccess(GraphQLContext context, String operationName, String query, Map<String, Object> variables, Object data, Object extensions) {
                if (operationName != null) {
                    logger.info("named operation '{}' executed successfully", operationName);
                }
            }

            @Override
            public void onError(GraphQLContext context, String operationName, String query, Map<String, Object> variables, Object data, List<GraphQLError> errors, Object extensions) {
                if (operationName != null) {
                    logger.error("named operation '{}' executed with {} errors", operationName, errors.size());
                }
            }
        };
    }
}
