package com.senacor.university.graphql.scalars;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.ScalarTypeDefinition;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

import java.io.IOException;
import java.time.LocalDate;

import static java.util.Collections.emptyList;

public class LocalDataScalarType extends GraphQLScalarType {

    public LocalDataScalarType(ObjectMapper objectMapper) {
        super("LocalDate", "LocalDate type", new Coercing<LocalDate, String>() {

            @Override
            public String serialize(Object input) throws CoercingSerializeException {
                if (!(input instanceof LocalDate)) {
                    throw new CoercingSerializeException("unsupported input type " + input.getClass());
                }
                try {
                    return objectMapper.writeValueAsString(input).replace("\"", "");
                } catch (IOException e) {
                    throw new CoercingSerializeException("unable to serialize email: " + e.getMessage());
                }
            }

            @Override
            public LocalDate parseValue(Object input) throws CoercingParseValueException {
                if (!(input instanceof String)) {
                    throw new CoercingParseValueException("unsupported input type " + input.getClass());
                }
                try {
                    return objectMapper.readValue((String) input, LocalDate.class);
                } catch (IOException e) {
                    throw new CoercingParseValueException("invalid date string " + " : " + e.getMessage());
                }
            }

            @Override
            public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
                if (!(input instanceof StringValue)) {
                    throw new CoercingParseLiteralException("unsupported input type " + input.getClass());
                }
                try {
                    return objectMapper.readValue((String) input, LocalDate.class);
                } catch (IOException e) {
                    throw new CoercingParseLiteralException("invalid date string " + input + " : " + e.getMessage());
                }
            }
        }, emptyList(), new ScalarTypeDefinition("local date"));
    }
}
