package br.com.tiago.labs_and_exams.frameworkAndDrivers.json;

import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.JsonUtilException;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.JsonUtilMappingException;
import br.com.tiago.labs_and_exams.frameworkAndDrivers.exception.ParseObjectToJsonException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    private JsonUtil() {

    }

    public static String objectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ParseObjectToJsonException(e);
        }
    }

    public static Object jsonToObject(String json, Class<?> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            throw new ParseObjectToJsonException(e);
        } catch (JsonMappingException e) {
            throw new JsonUtilMappingException(e);
        } catch (IOException e) {
            throw new JsonUtilException(e);
        }
    }

    public static JsonNode jsonToJsonNode(String json) {
        try {
            return mapper.readValue(json, JsonNode.class);
        } catch (JsonParseException e) {
            throw new ParseObjectToJsonException(e);
        } catch (JsonMappingException e) {
            throw new JsonUtilMappingException(e);
        } catch (IOException e) {
            throw new JsonUtilException(e);
        }
    }
}
