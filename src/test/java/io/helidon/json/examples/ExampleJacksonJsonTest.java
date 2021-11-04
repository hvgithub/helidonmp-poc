package io.helidon.json.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static io.helidon.json.examples.ExampleJacksonJson.parserString2Json;

class ExampleJacksonJsonTest {

    String str = " { \"integer\":1, \"number\":\"one\"} ";
    @Test
    void parserString2JsonTest() throws JsonProcessingException {
        JsonNode jn = parserString2Json(str);
        String strNumber = jn.get("number").asText();
        Integer intInteger = jn.get("integer").asInt();
        System.out.println("number:"+strNumber);
        System.out.println("integer:"+intInteger);
        System.out.println("Json Node print:" + jn.toPrettyString());
    }
}