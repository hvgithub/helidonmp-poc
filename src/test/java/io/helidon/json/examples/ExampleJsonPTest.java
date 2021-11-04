package io.helidon.json.examples;

import org.junit.jupiter.api.Test;

import javax.json.JsonObject;

class ExampleJsonPTest {
    ExampleJsonP jp = new ExampleJsonP();

    public static String jsonAsString = "{\n" +
            "  \"title\": \"JSON-Processing With Java EE\",\n" +
            "  \"chapters\": [\n" +
            "    \"Introduction\",\n" +
            "    \"1. JSON and Java\",\n" +
            "    \"2. JSON-Processing API features\",\n" +
            "    \"3. The Java EE JSON Object model\",\n" +
            "    \"4. The Java EE JSON Streaming model\",\n" +
            "    \"Conclusion\"\n" +
            "  ],\n" +
            "  \"released\": true,\n" +
            "  \"length\": 90,\n" +
            "  \"sourceCode\": {\n" +
            "    \"repositoryName\": \"JSON-Processing-with-Java-EE\",\n" +
            "    \"url\": \"github.com/readlearncode\"\n" +
            "  },\n" +
            "  \"complementaryCourse\": [\n" +
            "    {\n" +
            "      \"title\": \"RESTful Service with JAX-RS 2.0\",\n" +
            "      \"length\": 120\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"Java Enterprise Edition Introduction\",\n" +
            "      \"length\": 130\n" +
            "    }\n" +
            "  ],\n" +
            "  \"notes\": null\n" +
            "}";

    @Test
    void parseStrin2JsonUsingJOTest1() {
        JsonObject jo = jp.parseStrin2JsonUsingJO(jsonAsString);
        System.out.println( jo.getString("title") +
            jo.getJsonArray("chapters") +
            jo.getBoolean("released") +
            jo.getJsonObject("sourceCode"));
        System.out.printf("JSON"+jo.getJsonString(""));
    }

    String str = " { \"integer\":1, \"number\":\"one\"} ";
    @Test
    void parseStrin2JsonUsingJOTest2() {
        JsonObject jo = jp.parseStrin2JsonUsingJO(str);
        System.out.println( "number:" + jo.getString("number") +
               ", integer:" + jo.getInt("integer"));

    }
}