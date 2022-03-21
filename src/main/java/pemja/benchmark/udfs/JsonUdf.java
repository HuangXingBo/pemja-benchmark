package pemja.benchmark.udfs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUdf implements Udf {

    private final ObjectWriter writer;
    private ObjectMapper mapper;

    public JsonUdf() {
        this.mapper = new ObjectMapper();
        this.writer = mapper.writerWithDefaultPrettyPrinter();
    }

    @Override
    public Object invoke(Object... args) {
        String input = (String) args[0];
        try {
            StringObject object = mapper.readValue(input, StringObject.class);
            object.setA(object.a.toLowerCase());
            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to read json value", e);
        }
    }

    private static class StringObject {
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "StringObject{" + "a='" + a + '\'' + '}';
        }
    }
}
