
package io.helidon.restapi.examples;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.spi.CDI;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.helidon.microprofile.server.Server;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * The type Main test.
 */
class MainTest {

    private static Server server;
    private static String serverUrl;

    /**
     * Start the server.
     */
    @BeforeAll
    public static void startTheServer() {
        server = Server.create().start();
        serverUrl = "http://localhost:" + server.port();
    }

    /**
     * All endpoints test.
     */
    @Test
    void allEndpointsTest() {
        Client client = ClientBuilder.newClient();

        JsonObject jsonObject = client
                .target(serverUrl)
                .path("greet")
                .request()
                .get(JsonObject.class);
        Assertions.assertEquals("Hello World!", jsonObject.getString("message"),
                "default message");

        Response r = client
                .target(serverUrl)
                .path("metrics")
                .request()
                .get();
        Assertions.assertEquals(200, r.getStatus(), "GET metrics status code");

        r = client
                .target(serverUrl)
                .path("health")
                .request()
                .get();
        Assertions.assertEquals(200, r.getStatus(), "GET health status code");

        String s;
        s = client
                .target(serverUrl)
                .path("tables")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

        System.out.println("Output ===>>>" + s + "<<<===");
    }


    /**
     * Destroy class.
     */
    @AfterAll
    static void destroyClass() {
        CDI<Object> current = CDI.current();
        ((SeContainer) current).close();
    }
}