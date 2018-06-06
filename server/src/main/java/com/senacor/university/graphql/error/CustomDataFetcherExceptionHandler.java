package com.senacor.university.graphql.error;

import graphql.ExceptionWhileDataFetching;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomDataFetcherExceptionHandler implements DataFetcherExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomDataFetcherExceptionHandler.class);

    @Override
    public void accept(DataFetcherExceptionHandlerParameters handlerParameters) {
        Throwable exception = handlerParameters.getException();
        ExecutionPath path = handlerParameters.getPath();
        SourceLocation sourceLocation = handlerParameters.getField().getSourceLocation();

        ExceptionWhileDataFetching error = new ExceptionWhileDataFetching(path, exception, sourceLocation);
        handlerParameters.getExecutionContext().addError(new DataFetchingError(error));
    }
}
