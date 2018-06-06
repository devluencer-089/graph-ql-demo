package com.senacor.university.graphql.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import graphql.language.SourceLocation;

import java.io.IOException;

public class SourceLocationDeserializer extends StdDeserializer<SourceLocation> {

    public SourceLocationDeserializer() {
        this(null);
    }

    public SourceLocationDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SourceLocation deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        int line = (Integer) node.get("line").numberValue();
        int column = (Integer) node.get("column").numberValue();
        return new SourceLocation(line, column);
    }

    @Override
    public Class<?> handledType() {
        return SourceLocation.class;
    }
}
