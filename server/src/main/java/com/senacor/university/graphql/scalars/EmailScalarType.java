package com.senacor.university.graphql.scalars;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senacor.university.graphql.domain.employee.Email;
import graphql.language.ScalarTypeDefinition;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

import static java.util.Collections.emptyList;

public class EmailScalarType extends GraphQLScalarType {

    public EmailScalarType(ObjectMapper objectMapper) {
        super("Email", "Email type", new Coercing<Email, String>() {

            @Override
            public String serialize(Object input) throws CoercingSerializeException {
                if (!(input instanceof Email)) {
                    throw new CoercingSerializeException("unsupported input type " + input.getClass());
                }
                try {
                    return objectMapper.writeValueAsString(input).replace("\"", "");
                } catch (JsonProcessingException e) {
                    throw new CoercingSerializeException("unable to serialize email: " + e.getMessage());
                }
            }

            @Override
            public Email parseValue(Object input) throws CoercingParseValueException {
                if (!(input instanceof String)) {
                    throw new CoercingParseValueException("unsupported input type " + input.getClass());
                }
                Email email = Email.from((String) input);
                if (!email.isValid()) {
                    throw new CoercingParseValueException("invalid email " + email.getValue());
                }
                return email;
            }

            @Override
            public Email parseLiteral(Object input) throws CoercingParseLiteralException {
                if (!(input instanceof StringValue)) {
                    throw new CoercingParseLiteralException("unsupported input type " + input.getClass());
                }
                Email email = Email.from((String) input);
                if (!email.isValid()) {
                    throw new CoercingParseLiteralException("invalid email " + email.getValue());
                }
                return email;
            }
        }, emptyList(), new ScalarTypeDefinition("email"));
    }
}
