package com.senacor.university.graphql;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.collect.Sets;
import com.oembedler.moon.graphql.boot.GraphQLWebAutoConfiguration;
import com.senacor.university.graphql.error.CustomDataFetcherExceptionHandler;
import com.senacor.university.graphql.error.CustomGraphQLErrorHandler;
import com.senacor.university.graphql.json.SourceLocationDeserializer;
import com.senacor.university.graphql.domain.employee.Email;
import com.senacor.university.graphql.scalars.EmailScalarType;
import graphql.analysis.MaxQueryComplexityInstrumentation;
import graphql.analysis.MaxQueryDepthInstrumentation;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionStrategy;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.language.SourceLocation;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;
import graphql.servlet.GraphQLServletListener;
import graphql.servlet.ObjectMapperConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Map;

import static java.util.Arrays.asList;

@Configuration
public class CustomGraphQLConfiguration {

    private static final Logger operationLogger = LoggerFactory.getLogger("operations");

    @Value("${graphql.complexity.max}")
    private int maxComplexity;

    @Value("${graphql.depth.max}")
    private int maxDepth;

    @Bean
    ObjectMapperConfigurer customObjectMapperConfigurer() {
        return mapper -> {
            mapper.registerModule(new Jdk8Module());
            mapper.registerModule(new JavaTimeModule());
            mapper.registerModule(new ParameterNamesModule());

            SimpleModule emailModule = new SimpleModule(Version.unknownVersion());
            emailModule.addSerializer(Email.class, new Email.Serializer());
            emailModule.addDeserializer(Email.class, new Email.Deserializer());

            mapper.registerModule(emailModule);
        };
    }

    @Bean
    Jackson2ObjectMapperBuilderCustomizer customJackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder
                    .deserializerByType(SourceLocation.class, new SourceLocationDeserializer())
                    .serializerByType(Email.class, new Email.Serializer())
                    .deserializerByType(Email.class, new Email.Deserializer());
        };
    }

    @Bean
    ChainedInstrumentation customInstrumentation() {
        return new ChainedInstrumentation(asList(
                new MaxQueryDepthInstrumentation(maxDepth),
                new MaxQueryComplexityInstrumentation(maxComplexity, (environment, childComplexity) -> {
                    GraphQLFieldDefinition fieldDefinition = environment.getFieldDefinition();
                    GraphQLOutputType type = fieldDefinition.getType();
                    if (type instanceof GraphQLObjectType && Sets.newHashSet("Employee", "Project").contains(type.getName())) {
                        return 15 + childComplexity;
                    } else if (type instanceof GraphQLList && fieldDefinition.getName().equals("staff")) {
                        return 100 + childComplexity;
                    }
                    return 1 + childComplexity;
                })));
    }

    @Bean
    GraphQLServletListener operationLoggingServletListener(ObjectMapper objectMapper) {
        return new OperationLoggingServletListener(objectMapper);
    }

    @Bean
    CustomDataFetcherExceptionHandler customDataFetcherExceptionHandler() {
        return new CustomDataFetcherExceptionHandler();
    }

    @Bean
    ExecutionStrategy customQueryExecutionStrategy(CustomDataFetcherExceptionHandler exceptionHandler) {
        return new AsyncExecutionStrategy(exceptionHandler);
    }

    @Bean
    Map<String, ExecutionStrategy> executionStrategies(ExecutionStrategy customQueryExecutionStrategy) {
        return Collections.singletonMap(GraphQLWebAutoConfiguration.QUERY_EXECUTION_STRATEGY, customQueryExecutionStrategy);
    }

    @Bean
    CustomGraphQLErrorHandler customGraphQLErrorHandler() {
        return new CustomGraphQLErrorHandler();
    }

    @Bean
    EmailScalarType emailScalarType(ObjectMapper objectMapper) {
        return new EmailScalarType(objectMapper);
    }

}
