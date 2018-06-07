package com.senacor.university.graphql.domain.employee;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email from(String value) {
        return new Email(value.trim());
    }

    public boolean isValid() {
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Email other = (Email) obj;
        return Objects.equals(this.value, other.value);
    }

    public static class Serializer extends JsonSerializer<Email> {

        public final static Serializer INSTANCE = new Serializer();

        @Override
        public void serialize(Email email, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeRawValue(email.getValue());
        }
    }

    public static class Deserializer extends JsonDeserializer<Email> {

        public final static Deserializer INSTANCE = new Deserializer();

        @Override
        public Email deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            return new Email(jp.getText());
        }
    }

}
