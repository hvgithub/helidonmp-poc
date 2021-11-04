package io.helidon.json.examples;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

public class ExampleJsonP {

    private String str = " { \"integer\":1, \"number\":\"one\"} ";

    public static JsonObject parseStrin2JsonUsingJO(String jsonasStr) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonasStr));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return jsonObject;
    }
}
